package com.peopleandcars.mycarproducer.peopleandcarfilereader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileProvider {
    private Queue<String> filesQueue;
    private Iterator<String> iterator;

    public FileProvider(String rootPath) throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get(rootPath))) {
            filesQueue = stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map((path) -> {
                        return rootPath + "/" + path.toString();
                    }).collect(Collectors.toCollection(PriorityQueue::new));
            ;
        }
        iterator = filesQueue.iterator();
    }

    public synchronized String getNext() {
        return iterator.hasNext() ? iterator.next() : null;
    }
}
