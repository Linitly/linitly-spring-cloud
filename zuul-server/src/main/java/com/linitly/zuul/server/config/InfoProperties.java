/**
 * @author: linxiunan
 * @date: 2019/10/23 10:11
 * @descrption
 */
package com.linitly.zuul.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "info")
public class InfoProperties {

    private String author;

    private String version;

    private String title;

    private String description;
}
