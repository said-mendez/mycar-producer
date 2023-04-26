package com.peopleandcars.mycarproducer.publishers;

import com.peopleandcars.mycarproducer.config.QueuesConfig;
import com.peopleandcars.mycarproducer.model.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CarPublisher implements Publisher<Car> {
    private RabbitTemplate rabbitTemplate;

    public CarPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(Car car) {
        log.info("Publishing car: {}", Thread.currentThread().getName());
        rabbitTemplate.convertAndSend(QueuesConfig.EXCHANGE, QueuesConfig.CAR_ROUTING_KEY, car);
    }

    @Override
    public Car generate(String[] row) {
        Car car = new Car();
        car.setVin(row[0]);
        car.setBrand(row[1]);
        car.setModel(row[2]);
        car.setYear(Integer.parseInt(row[3]));
        car.setColor(row[4]);
        return car;
    }
}
