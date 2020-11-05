package org.hesperides.core.domain.platforms.queries.views.properties;

import lombok.Value;

@Value
public class PropertySearchResultView {
    String propertyName;
    String propertyValue;
    String applicationName;
    String platformName;
    String propertiesPath;
}
