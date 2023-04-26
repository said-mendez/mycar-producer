package com.peopleandcars.mycarproducer;

import com.peopleandcars.mycarproducer.config.PeopleAndCarConfiguration;
import com.peopleandcars.mycarproducer.model.Car;
import com.peopleandcars.mycarproducer.peopleandcarfilereader.FileProvider;
import com.peopleandcars.mycarproducer.peopleandcarfilereader.PeopleAndCarFileReader;
import com.peopleandcars.mycarproducer.publishers.CarPublisher;
import com.peopleandcars.mycarproducer.publishers.PeopleCarPublisher;
import com.peopleandcars.mycarproducer.publishers.PeoplePublisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyCarProducerApplication implements CommandLineRunner {
	private CarPublisher carPublisher;
	private PeoplePublisher peoplePublisher;
	private PeopleCarPublisher peopleCarPublisher;
	private PeopleAndCarConfiguration peopleAndCarConfiguration;
	public static final Character CSV = ',';
	public static final Character TABS = '\t';


	public MyCarProducerApplication(CarPublisher carPublisher,
									PeoplePublisher peoplePublisher,
									PeopleCarPublisher peopleCarPublisher,
									PeopleAndCarConfiguration peopleAndCarConfiguration) {
		this.carPublisher = carPublisher;
		this.peoplePublisher = peoplePublisher;
		this.peopleCarPublisher = peopleCarPublisher;
		this.peopleAndCarConfiguration = peopleAndCarConfiguration;
	}

	public static void main(String[] args) {
		SpringApplication.run(MyCarProducerApplication.class, args);
	}

	@Override
	public void run(String ...strings) throws Exception {
		PeopleAndCarFileReader<Car> carrFileReader = new PeopleAndCarFileReader(carPublisher, new FileProvider(peopleAndCarConfiguration.getCars().getPath()), CSV);
		int carThreadsCount = peopleAndCarConfiguration.getCars().getThreadsCount();
		String carThreadPrefix = peopleAndCarConfiguration.getCars().getThreadPrefix();
		for (int i = 1; i <= carThreadsCount; i++) {
			Thread thread = new Thread(carrFileReader);
			thread.setName(carThreadPrefix + "-" + i);
			thread.start();
		}

		PeopleAndCarFileReader<Car> peopleFileReader = new PeopleAndCarFileReader(peoplePublisher, new FileProvider(peopleAndCarConfiguration.getPeople().getPath()), CSV);
		int peopleThreadsCount = peopleAndCarConfiguration.getPeople().getThreadsCount();
		String peopleThreadPrefix = peopleAndCarConfiguration.getPeople().getThreadPrefix();
		for (int i = 1; i <= peopleThreadsCount; i++) {
			Thread thread = new Thread(peopleFileReader);
			thread.setName(peopleThreadPrefix + "-" + i);
			thread.start();
		}

		PeopleAndCarFileReader<Car> peopleCarFileReader = new PeopleAndCarFileReader(peopleCarPublisher, new FileProvider(peopleAndCarConfiguration.getRelations().getPath()), TABS);
		int peopleCarThreadsCount = peopleAndCarConfiguration.getRelations().getThreadsCount();
		String peopleCarThreadPrefix = peopleAndCarConfiguration.getRelations().getThreadPrefix();
		for (int i = 1; i <= peopleCarThreadsCount; i++) {
			Thread thread = new Thread(peopleCarFileReader);
			thread.setName(peopleCarThreadPrefix + "-" + i);
			thread.start();
		}
	}

}
