package com.linitly.service.provider.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-provider", fallback = ServiceProviderClientFallBack.class)
public interface ServiceProviderClient {

    @RequestMapping(value = "/example/hello", method = RequestMethod.GET)
    String getMessage(@RequestParam(value = "time") Integer time);

    @RequestMapping(value = "/cache", method = RequestMethod.GET)
    String cache(@RequestParam(value = "id") Integer id);
}
