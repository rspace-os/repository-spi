package com.researchspace.repository.spi;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Definitions of commonly used licenses
 * 
 * @author rspace
 *
 */
public class LicenseDefs {

	public static LicenseDef CC_BY;
	public static LicenseDef CC_0;
	public static LicenseDef MIT = null;
	public static LicenseDef GPL = null;
	public static LicenseDef GPL2 = null;
	public static LicenseDef GPL3 = null;
	public static LicenseDef APACHE_2;
	public static LicenseDef CC_NC = null;
	public static LicenseDef CC_ND = null;

	/**
	 * RSpace-specific constant for no license.
	 */
	public static final String NO_LICENSE = "NO_LICENSE";

	static {
		try {
			CC_BY = new LicenseDef(new URL("https://creativecommons.org/licenses/by/4.0/"), "CC-BY");
			CC_0 = new LicenseDef(new URL("https://creativecommons.org/publicdomain/zero/1.0/"), "CC-0");
			CC_NC = new LicenseDef(new URL("https://creativecommons.org/licenses/by-nc/4.0/"), "CC-NC");
			CC_ND = new LicenseDef(new URL("https://creativecommons.org/licenses/by-nd/4.0/"), "CC-ND");
			MIT = new LicenseDef(new URL("https://opensource.org/licenses/MIT"), "MIT");
			GPL = new LicenseDef(new URL("https://www.gnu.org/copyleft/gpl.html"), "GPL");
			GPL2 = new LicenseDef(new URL("https://www.gnu.org/licenses/gpl-2.0.html"), "GPL-2.0");
			GPL3 = new LicenseDef(new URL("https://www.gnu.org/licenses/gpl-3.0.html"), "GPL-3.0");
			APACHE_2 = new LicenseDef(new URL("https://www.apache.org/licenses/LICENSE-2.0.html"), "Apache-2");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
