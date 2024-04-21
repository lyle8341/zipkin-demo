package com.lyle.zipkinservice1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @deprecated 不能使用 restTemplate，因为spans总是1
 * @author lyle 2024-04-21 18:23
 */
@Deprecated
@RestController
public class HelloWorldController2 {

    private static final Logger log = LoggerFactory.getLogger(HelloWorldController.class);

    @Value("${server.port}")
    private String port;

    //!!!!不能使用!!!!
    private final RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("/ping2")
    public Object ping() {
        log.info("进入ping");
        return "pong study";
    }

    @RequestMapping("/log2")
    public Object log() {
        log.info("this is info log");
        log.error("this is error log");
        log.debug("this is debug log");
        log.warn("this is warn log");
        log.trace("this is trace log");
        return "123";
    }

    @RequestMapping("/http2")
    public Object httpQuery() {
        String studyUrl = "http://localhost:" + port + "/ping2";
        URI studyUri = URI.create(studyUrl);
        String study = restTemplate.getForObject(studyUri, String.class);
        log.info("study:{}", study);

        String floorUrl = "http://localhost:" + port + "/log2";
        URI floorUri = URI.create(floorUrl);
        String floor = restTemplate.getForObject(floorUri, String.class);
        log.info("floor:{}", floor);

        String roomUrl = "http://localhost:" + port + "/ping2";
        URI roomUri = URI.create(roomUrl);
        String room = restTemplate.getForObject(roomUri, String.class);
        log.info("room:{}", room);
        return study + "-------" + floor + "-------" + room + "-------";
    }
}