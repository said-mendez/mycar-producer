package com.peopleandcars.mycarproducer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "process")
@Component
public class PeopleAndCarConfiguration {
    private ThreadsConfiguration people;
    private ThreadsConfiguration cars;
    private ThreadsConfiguration relations;
}
