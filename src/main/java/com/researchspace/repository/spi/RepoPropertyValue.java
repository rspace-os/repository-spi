package com.researchspace.repository.spi;

import com.researchspace.repository.spi.properties.RepoProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class RepoPropertyValue {
    private RepoProperty property;
    private Object value;
}
