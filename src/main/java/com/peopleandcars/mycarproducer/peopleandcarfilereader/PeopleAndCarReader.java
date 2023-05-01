package com.peopleandcars.mycarproducer.peopleandcarfilereader;

public interface PeopleAndCarReader<T>{
    void read(String path);
    boolean isDataFilled(String[] row);
}
