/**
 * @author: linxiunan
 * @date: 2019/10/14 15:38
 * @descrption
 */
package com.linitly.monitor.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableTurbine
@EnableAdminServer
@SpringCloudApplication
public class MonitorServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorServerApplication.class, args);
    }
}
