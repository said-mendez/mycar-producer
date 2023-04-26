package com.peopleandcars.mycarproducer.config;

import lombok.Data;

@Data
public class ThreadsConfiguration {
    private String path;
    private String threadPrefix;
    private int threadsCount;
}
