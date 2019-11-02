/**
 * @author: linxiunan
 * @date: 2019/10/23 11:58
 * @descrption
 */
package com.linitly.zuul.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Primary
@Component
@EnableSwagger2
@Profile({"dev","test"})
public class DocumentationConfig implements SwaggerResourcesProvider {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private InfoProperties infoProperties;
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${zuul.prefix}")
    private String zuulPrefix;

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        // 排除自身, 将其他的服务添加进去
        discoveryClient.getServices().stream().filter(s -> !s.equals(applicationName)).forEach(name -> {
            resources.add(swaggerResource(name, zuulPrefix + "/" + name + "/v2/api-docs", infoProperties.getVersion()));
        });
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
