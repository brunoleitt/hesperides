/*
 *
 * This file is part of the Hesperides distribution.
 * (https://github.com/voyages-sncf-technologies/hesperides)
 * Copyright (c) 2020 VSCT.
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
package org.hesperides.core.infrastructure.axon;

import com.thoughtworks.xstream.XStream;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.CompactDriver;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.thoughtworks.xstream.XStream.PRIORITY_VERY_LOW;

@Configuration
public class AxonSerializerConfiguration {

    @Bean
    @Qualifier("eventSerializer")
    public Serializer eventSerializer() { // Doc: https://docs.axoniq.io/reference-guide/v/3.4/part-iii-infrastructure-components/spring-boot-autoconfiguration#serializer-configuration
        XStream xstream = new XStream(new CompactDriver());
        xstream.registerConverter(new UserEventConverter(xstream.getMapper(), xstream.getReflectionProvider()), PRIORITY_VERY_LOW + 1);  // on veut être sélectionné juste avant le ReflectionConverter
        return new XStreamSerializer(xstream);
    }
}
