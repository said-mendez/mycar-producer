package com.peopleandcars.mycarproducer;

import model.People;
import model.PeopleCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class MyCarProducerApplication implements CommandLineRunner {
	@Autowired
	private CarPublisher carPublisher;
	@Autowired
	private PeoplePublisher peoplePublisher;
	@Autowired
	private PeopleCarPublisher peopleCarPublisher;

	public static void main(String[] args) {
		SpringApplication.run(MyCarProducerApplication.class, args);
	}

	@Override
	public void run(String ...strings) throws Exception {
		for (int i = 1; i < 6; i++) {
//			Car car = new Car();
//			car.setVin(UUID.randomUUID().toString());
//			car.setYear(2023);
//			car.setBrand("Brand " + i);
//			car.setModel("Model " + i);
//			car.setColor("Color " + i);
//			carPublisher.publish(car);

//			People people = new People();
//			people.setGuid(UUID.randomUUID().toString());
//			people.setFirstName("Name " + i);
//			people.setLastName("Last " + i);
//			people.setEmail("email" + i + "@mail.com");
//			people.setGender("Gender " + i);
//			peoplePublisher.publish(people);
		}

		PeopleCar peopleCar = new PeopleCar();
		peopleCar.setUuid(UUID.randomUUID().toString());
		peopleCar.setCarId("bef36085-c952-4824-83c8-1b29ffb82b84");
		peopleCar.setPeopleId("107facb5-5027-4d26-9f1b-ba54da305644");
		peopleCarPublisher.publish(peopleCar);

		PeopleCar peopleCar1 = new PeopleCar();
		peopleCar1.setUuid(UUID.randomUUID().toString());
		peopleCar1.setCarId("0dceb20a-6400-4ba6-9d51-5e67b881480b");
		peopleCar1.setPeopleId("9f699072-3de9-4b4a-a2eb-13bb1a54e0a4");
		peopleCarPublisher.publish(peopleCar1);
	}

}
