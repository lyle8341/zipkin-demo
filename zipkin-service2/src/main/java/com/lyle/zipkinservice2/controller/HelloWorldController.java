package com.lyle.zipkinservice2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    private static final Logger log = LoggerFactory.getLogger(HelloWorldController.class);

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @RequestMapping("/http")
    public Object httpQuery() {
        String service1_port = "8888";
        String studyUrl = "http://localhost:" + service1_port + "/ping";
        String study = restTemplateBuilder
                .build()
                .getForEntity(studyUrl, String.class)
                .getBody();
        log.info("study:{}", study);

        String floorUrl = "http://localhost:" + service1_port + "/log";
        String floor = restTemplateBuilder
                .build()
                .getForEntity(floorUrl, String.class)
                .getBody();
        log.info("floor:{}", floor);

        String roomUrl = "http://localhost:" + service1_port + "/ping";
        String room = restTemplateBuilder
                .build()
                .getForEntity(roomUrl, String.class)
                .getBody();
        log.info("room:{}", room);
        return study + "-------" + floor + "-------" + room + "-------";
    }
}