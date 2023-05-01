package com.peopleandcars.mycarproducer.peopleandcarfilereader;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.peopleandcars.mycarproducer.model.Car;
import com.peopleandcars.mycarproducer.publishers.CarPublisher;
import com.peopleandcars.mycarproducer.publishers.Publisher;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class PeopleAndCarFileReader<T> implements Runnable {
//    private Publisher<T> publisher;
    private FileProvider fileProvider;
    private Character fileSeparator;
    private PeopleAndCarReader<T> peopleAndCarReader;

    public PeopleAndCarFileReader(FileProvider fileProvider, PeopleAndCarReader<T> peopleAndCarReader) {
        this.fileProvider = fileProvider;
        this.peopleAndCarReader = peopleAndCarReader;
    }

    @Override
    public void run() {
        String path;
        while ((path = fileProvider.getNext()) != null) {
            peopleAndCarReader.read(path);
//            try {
//                CSVReader reader = new CSVReaderBuilder(new FileReader(path))
//                        .withCSVParser(new CSVParserBuilder()
//                                .withSeparator(fileSeparator)
//                                .build())
//                        .withSkipLines(1).build();
//                String [] nextLine;
//                while ((nextLine = reader.readNext()) != null) {
//                    publisher.publish(publisher.generate(nextLine));
//                }
//            } catch(IOException | CsvValidationException exception) {
//                log.error("Error reading CSV: ", exception);
//            }
        }
    }

//    @Override
//    public void run() {
//        String path;
//        while ((path = fileProvider.getNext()) != null) {
//            try {
//                CSVReader reader = new CSVReaderBuilder(new FileReader(path)).withSkipLines(1).build();
//                String [] nextLine;
//                while ((nextLine = reader.readNext()) != null) {
//                    Car car = new Car();
//                    car.setVin(nextLine[0]);
//                    car.setBrand(nextLine[1]);
//                    car.setModel(nextLine[2]);
//                    car.setYear(Integer.parseInt(nextLine[3]));
//                    car.setColor(nextLine[4]);
//                    carPublisher.publish(car);
//                }
//            } catch(IOException | CsvValidationException exception) {
//                log.error("Error reading CSV: ", exception);
//            }
//        }
//    }
}
