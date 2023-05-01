package com.peopleandcars.mycarproducer.peopleandcarfilereader;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.peopleandcars.mycarproducer.publishers.Publisher;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class PeopleAndCarReadCSVFile<T> implements PeopleAndCarReader<T> {
    private Publisher<T> publisher;

    public PeopleAndCarReadCSVFile(Publisher<T> publisher) {
        this.publisher = publisher;
    }

    @Override
    public void read(String path) {
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader(path))
                    .withSkipLines(1).build();
            String [] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                publisher.publish(publisher.generate(nextLine));
            }
        } catch(IOException | CsvValidationException exception) {
            log.error("Error reading CSV: ", exception);
        }
    }

    @Override
    public boolean isDataFilled(String[] row) {
        for (String str : row) {
            if (str.replace(",", "").trim().isEmpty()) {
                return false;
            }
        }

        return true;
    }
}
