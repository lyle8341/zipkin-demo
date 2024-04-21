package com.lyle.zipkinservice1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用 RestTemplateBuilder
 */
@RestController
public class HelloWorldController {
    private static final Logger log = LoggerFactory.getLogger(HelloWorldController.class);

    @Value("${server.port}")
    private String port;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @RequestMapping("/ping")
    public Object ping() {
        log.info("进入ping");
        return "pong study";
    }

    @RequestMapping("/log")
    public Object log() {
        log.info("this is info log");
        log.error("this is error log");
        log.debug("this is debug log");
        log.warn("this is warn log");
        log.trace("this is trace log");
        return "123";
    }

    @RequestMapping("/http")
    public Object httpQuery() {
        String studyUrl = "http://localhost:" + port + "/ping";
        String study = restTemplateBuilder
                .build()
                .getForEntity(studyUrl, String.class)
                .getBody();
        log.info("study:{}", study);

        String floorUrl = "http://localhost:" + port + "/log";
        String floor = restTemplateBuilder
                .build()
                .getForEntity(floorUrl, String.class)
                .getBody();
        log.info("floor:{}", floor);

        String roomUrl = "http://localhost:" + port + "/ping";
        String room = restTemplateBuilder
                .build()
                .getForEntity(roomUrl, String.class)
                .getBody();
        log.info("room:{}", room);
        return study + "-------" + floor + "-------" + room + "-------";
    }
}