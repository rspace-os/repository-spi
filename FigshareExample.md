``` java

package com.researchspace.figshare.rspaceadapter;
import static java.util.Comparator.comparing;
import static org.apache.commons.io.FilenameUtils.getExtension;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClientException;
import com.researchspace.figshare.api.Figshare;
import com.researchspace.figshare.model.ArticlePost;
import com.researchspace.figshare.model.ArticlePost.ArticlePostBuilder;
import com.researchspace.figshare.model.Author;
import com.researchspace.figshare.model.Category;
import com.researchspace.figshare.model.FigshareResponse;
import com.researchspace.figshare.model.Location;
import com.researchspace.figshare.model.PrivateArticleLink;
import com.researchspace.repository.spi.IDepositor;
import com.researchspace.repository.spi.IRepository;
import com.researchspace.repository.spi.License;
import com.researchspace.repository.spi.LicenseConfigInfo;
import com.researchspace.repository.spi.LicenseDef;
import com.researchspace.repository.spi.RepositoryConfig;
import com.researchspace.repository.spi.RepositoryConfigurer;
import com.researchspace.repository.spi.RepositoryOperationResult;
import com.researchspace.repository.spi.Subject;
import com.researchspace.repository.spi.SubmissionMetadata;
import com.researchspace.zipprocessing.ArchiveIterator;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class FigshareRSpaceRepository implements IRepository, RepositoryConfigurer {
    private static final String ARCHIVE_RESOURCE_FOLDER = "/resources/";
    @Autowired
    private Figshare figshare;
    @Autowired
    private ArchiveIterator archiveIterator;
    public void setFigshare(Figshare figshare) {
        this.figshare = figshare;
    }
    @Override
    public void configure(RepositoryConfig config) {
        log.info("Configuration not needed");
    }
    @Override
    public RepositoryOperationResult submitDeposit(IDepositor depositor, File toDeposit, SubmissionMetadata metadata,
            RepositoryConfig repoCfg) {
        log.info("Depositing file {} of size {} ", toDeposit.getAbsolutePath(), toDeposit.length());
        ArticlePostBuilder articleBuilder = ArticlePost.builder();
        articleBuilder.title(metadata.getTitle()).description(metadata.getDescription());
        for (IDepositor author : metadata.getAuthors()) {
            articleBuilder.author(new Author(author.getUniqueName(), null));
        }
        List<Category> subjects = figshare.getCategories();
        Category matching = Category.UNCATEGORIZED;
        if (!metadata.getSubjects().isEmpty()) {
            matching = subjects.stream().filter(cat -> cat.getTitle().equals(metadata.getSubjects().get(0))).findFirst()
                    .orElse(Category.UNCATEGORIZED);
        }
        articleBuilder.category(matching.getId().intValue());
        // tag required for publishing to work, if needed.
        articleBuilder.tags(Arrays.asList(new String[] { "RSpace" }));
        List<com.researchspace.figshare.model.License> allLicenses = figshare.getLicenses();
        Optional<com.researchspace.figshare.model.License> matchingLicense = allLicenses.stream()
                .filter(figshareLicense -> metadata.getLicense().isPresent()
                        && figshareLicense.getUrl().equals(metadata.getLicense().get()))
                .findFirst();
        com.researchspace.figshare.model.License toSet = matchingLicense.orElse(getDefaultLicense());
        articleBuilder.license(toSet.getValue());
        ArticlePost toPost = articleBuilder.build();
        return doPost(toDeposit, toPost, metadata);
    }
    private com.researchspace.figshare.model.License getDefaultLicense() {
        try {
            return new com.researchspace.figshare.model.License(new URL(Figshare.DEFAULT_LICENSE_URL), "CC BY", 1,
                    true);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
    RepositoryOperationResult doPost(File toDeposit, ArticlePost toPost, SubmissionMetadata metadata) {
        try {
            Location articleId = figshare.createArticle(toPost);
            PrivateArticleLink privateLink = figshare.createPrivateArticleLink(articleId.getId());
            uploadExport(toDeposit, articleId);
            String feedbackMsg = String.format("Deposit succeeded.");
            URL link = privateLink.getWeblink();
            log.info("New article will be at URL {}", link);
            String publishingFeedback = "";
            if (metadata.isPublish()) {
                FigshareResponse<Location> published = figshare.publishArticle(articleId.getId());
                if (published.hasError()) {
                    publishingFeedback = String.format(" Publishing failed:  %s", published.getError().getMessage());
                } else {
                    publishingFeedback = "Publishing succeeded";
                    link = published.getData().getLocation();
                }
                feedbackMsg = feedbackMsg + publishingFeedback;
            }
            return new RepositoryOperationResult(true, feedbackMsg, link);
        } catch (RestClientException e) {
            log.error("Couldn't perform  Figshare API operation : {}" + e.getMessage());
            return new RepositoryOperationResult(false, "Submission failed - {} " + e.getMessage(), null);
        } catch (IOException e) {
            log.error("IO error during zip archive traversal. Figshare upload may not be complete :{}", e.getMessage());
            return new RepositoryOperationResult(false, "Submission failed - {} " + e.getMessage(), null);
        }
    }
    private void uploadExport(File toDeposit, Location articleId) throws IOException {
        if ("zip".equals(getExtension(toDeposit.getName()))) {
            log.info("Uploading main zip as single zip archive...");
            figshare.uploadFile(articleId.getId(), toDeposit);
            archiveIterator.processZip(toDeposit, (file) -> {
                figshare.uploadFile(articleId.getId(), file);
            }, (entry) -> {
                return !entry.getName().contains(ARCHIVE_RESOURCE_FOLDER);
            });
        } else {
            figshare.uploadFile(articleId.getId(), toDeposit);
        }
    }
    @Override
    public RepositoryOperationResult testConnection() {
        try {
            if (figshare.test()) {
                return new RepositoryOperationResult(true, "Test connection OK!", null);
            } else {
                return new RepositoryOperationResult(false, "Test connection failed - please check settings.", null);
            }
        } catch (RestClientException e) {
            log.error("Couldn't perform test action {}" + e.getMessage());
            return new RepositoryOperationResult(false, "Test connection failed - " + e.getMessage(), null);
        }
    }
    @Override
    public RepositoryConfigurer getConfigurer() {
        return this;
    }
    private List<Subject> subjects = new ArrayList<>();
    private List<License> licenses = new ArrayList<>();
    @Override
    public List<Subject> getSubjects() {
        if (subjects.isEmpty()) {
            List<Category> categories = figshare.getCategories();
            categories.forEach((c) -> {
                subjects.add(new Subject(c.getTitle()));
            });
        }
        Collections.sort(subjects, comparing(Subject::getName));
        return Collections.unmodifiableList(subjects);
    }
    @Override
    public LicenseConfigInfo getLicenseConfigInfo() {
        if (licenses.isEmpty()) {
            List<com.researchspace.figshare.model.License> figLicenses = figshare.getLicenses();
            figLicenses.forEach(license -> {
                licenses.add(
                        new License(new LicenseDef(license.getUrl(), license.getName()), license.isDefaultLicense()));
            });
        }
        Collections.sort(licenses, new LicenseComparator());
        return new LicenseConfigInfo(true, false, Collections.unmodifiableList(licenses));
    }
    static class LicenseComparator implements Comparator<License> {
        @Override
        public int compare(License o1, License o2) {
            return o1.getLicenseDefinition().getName().compareTo(o2.getLicenseDefinition().getName());
        }
    }
}
```
