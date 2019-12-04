package com.provider;

import java.util.ArrayList;
import java.util.List;

import org.apache.servicecomb.service.center.client.ServiceCenterClient;
import org.apache.servicecomb.service.center.client.model.HeartbeatsRequest;
import org.apache.servicecomb.service.center.client.model.Microservice;
import org.apache.servicecomb.service.center.client.model.MicroserviceInstance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloServer {

  public static void main(String[] args){
    SpringApplication.run(HelloServer.class,args);
    registerMicroservice();
  }

  public static void registerMicroservice(){
    ServiceCenterClient sc = new ServiceCenterClient();

    //register service
    Microservice microservice = new Microservice();
    //set serviceId, serviceName and register service
    microservice.setServiceId("1111");
    microservice.setServiceName("HelloServer");
    sc.registerMicroservice(microservice);

    //register service instance
    MicroserviceInstance instance = new MicroserviceInstance();
    List<String> endPoints = new ArrayList<String>();
    //bind server IP and  port
    endPoints.add("rest://127.0.0.1:8080/");
    instance.setEndpoints(endPoints);
    //setting serviceId, hostName, instanceId and register service instance
    instance.setServiceId("1111");
    instance.setHostName("test");
    instance.setInstanceId("2222");
    sc.registerMicroserviceInstance(instance,microservice.getServiceId());

    //heartbeat
    HeartbeatsRequest heartbeatsRequest = new HeartbeatsRequest("1111","2222");
    while(true){
      sc.sendHeartBeats(heartbeatsRequest);
      try {
        Thread.sleep(30000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
