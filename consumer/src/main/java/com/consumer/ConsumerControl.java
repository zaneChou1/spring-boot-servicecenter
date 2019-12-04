package com.consumer;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;
import org.apache.servicecomb.service.center.client.ServiceCenterClient;
import org.apache.servicecomb.service.center.client.model.MicroserviceInstancesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerControl{
  @RequestMapping("/")
  public String consumerHello() throws URISyntaxException {
    return findServer();
  }

  public String findServer() throws URISyntaxException {
    //find service
    ServiceCenterClient sc = new ServiceCenterClient();
    MicroserviceInstancesResponse instances = sc.getMicroserviceInstanceList("1111");
    URI endpointURIBuilder = new URIBuilder(instances.getInstances().get(0).getEndpoints().get(0)).build();
    int port = endpointURIBuilder.getPort();
    String host = endpointURIBuilder.getHost();

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> result = restTemplate.getForEntity("http://"+host+":"+port,String.class);
    return result.getBody();
  }
}
