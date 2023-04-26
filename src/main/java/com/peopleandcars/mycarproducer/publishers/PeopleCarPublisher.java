package com.peopleandcars.mycarproducer.publishers;

import com.peopleandcars.mycarproducer.config.QueuesConfig;
import com.peopleandcars.mycarproducer.model.PeopleCar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

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
}
