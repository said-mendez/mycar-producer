package com.peopleandcars.mycarproducer.publishers;

import com.peopleandcars.mycarproducer.config.QueuesConfig;
import com.peopleandcars.mycarproducer.model.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
        try {
            logToFile("Publishing car: " + Thread.currentThread().getName());
        } catch (IOException ioException) {
            log.error("IOException: ", ioException);
        }
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

    public void logToFile(String log) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/eduardo.mendez/my-car-producer/data/log.txt", true));
        writer.newLine();
        writer.append(log);
        writer.close();
    }
}
