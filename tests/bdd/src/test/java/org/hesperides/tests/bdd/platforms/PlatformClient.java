/*
 *
 * This file is part of the Hesperides distribution.
 * (https://github.com/voyages-sncf-technologies/hesperides)
 * Copyright (c) 2016 VSCT.
 *
 * Hesperides is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, version 3.
 *
 * Hesperides is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 *
 */
package org.hesperides.tests.bdd.platforms;

import org.apache.commons.lang3.StringUtils;
import org.hesperides.core.presentation.io.platforms.InstanceModelOutput;
import org.hesperides.core.presentation.io.platforms.PlatformIO;
import org.hesperides.core.presentation.io.platforms.SearchResultOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PlatformClient {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity create(PlatformIO platformInput) {
        return create(platformInput, PlatformIO.class);
    }

    public ResponseEntity create(PlatformIO platformInput, Class responseType) {
        return restTemplate.postForEntity(
                "/applications/{application_name}/platforms",
                platformInput,
                responseType,
                platformInput.getApplicationName());
    }

    public ResponseEntity delete(PlatformIO platformInput, Class responseType) {
        return restTemplate.exchange(
                "/applications/{application_name}/platforms/{platform_name}",
                HttpMethod.DELETE,
                null,
                responseType,
                platformInput.getApplicationName(),
                platformInput.getPlatformName());
    }

    public ResponseEntity getApplication(PlatformIO platformInput, boolean hidePlatform, Class responseType) {
        return restTemplate.getForEntity(
                "/applications/{application_name}?hide_platform={hide_platform}",
                responseType,
                platformInput.getApplicationName(),
                hidePlatform);
    }

    public ResponseEntity get(PlatformIO platformInput, Class responseType) {
        return restTemplate.getForEntity(
                "/applications/{application_name}/platforms/{platform_name}",
                responseType,
                platformInput.getApplicationName(),
                platformInput.getPlatformName());
    }

    public ResponseEntity update(PlatformIO platformInput, boolean copyProperties) {
        String url = "/applications/{application_name}/platforms";
        if (copyProperties) {
            url += "?copyPropertiesForUpgradedModules=true";
        }
        return restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(platformInput),
                PlatformIO.class,
                platformInput.getApplicationName());
    }

    public ResponseEntity<SearchResultOutput[]> searchApplication(String search) {
        return restTemplate.postForEntity("/applications/perform_search?name=" + search, null, SearchResultOutput[].class);
    }

    public ResponseEntity<SearchResultOutput[]> search(String applicationName) {
        return search(applicationName, null);
    }

    public ResponseEntity<SearchResultOutput[]> search(String applicationName, String platformName) {
        String url = "/applications/platforms/perform_search?applicationName=" + applicationName;
        if (StringUtils.isNotBlank(platformName)) {
            url += "&platformName=" + platformName;
        }
        return restTemplate.postForEntity(
                url,
                null,
                SearchResultOutput[].class);
    }

    public ResponseEntity<InstanceModelOutput> getInstanceModel(PlatformIO platformInput, String propertiesPath) {
        return restTemplate.getForEntity(
                "/applications/{application_name}/platforms/{platform_name}/properties/instance_model?path={path}",
                InstanceModelOutput.class,
                platformInput.getApplicationName(),
                platformInput.getPlatformName(),
                propertiesPath);
    }
}
