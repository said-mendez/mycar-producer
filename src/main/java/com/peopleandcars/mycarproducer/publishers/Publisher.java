package com.peopleandcars.mycarproducer.publishers;

public interface Publisher<T> {
    void publish(T t);
    T generate(String row []);
}
