package com.peopleandcars.mycarproducer.publishers;

import com.peopleandcars.mycarproducer.config.QueuesConfig;
import com.peopleandcars.mycarproducer.model.PeopleCar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
public class PeopleCarPublisher implements Publisher<PeopleCar>{
    private RabbitTemplate rabbitTemplate;

    public PeopleCarPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(PeopleCar peopleCar) {
        log.info("Publishing peopleCar: {}", Thread.currentThread().getName());
        try {
            logToFile("Publishing peopleCar: " + Thread.currentThread().getName());
        } catch (IOException ioException) {
            log.error("IOException: ", ioException);
        }
        rabbitTemplate.convertAndSend(QueuesConfig.EXCHANGE, QueuesConfig.PEOPLE_CAR_ROUTING_KEY, peopleCar);
    }

    @Override
    public PeopleCar generate(String[] row) {
        PeopleCar peopleCar = new PeopleCar();
        peopleCar.setUuid(UUID.randomUUID().toString());
        peopleCar.setPeopleId(row[0]);
        peopleCar.setCarId(row[1]);
        return peopleCar;
    }

    public void logToFile(String log) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/eduardo.mendez/my-car-producer/data/log.txt", true));
        writer.newLine();
        writer.append(log);
        writer.close();
    }
}
