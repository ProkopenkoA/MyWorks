package ru.cft.shift.task5.model;

import java.util.ArrayList;
import java.util.List;

public class ThreadGenerate {
    private final ConsumerItem consumerItem;
    private final GeneratorItem generatorItem;
    private static List<Thread> threads = new ArrayList<>();

    public ThreadGenerate(ConsumerItem consumerItem, GeneratorItem generatorItem) {
        this.consumerItem = consumerItem;
        this.generatorItem = generatorItem;
    }

    public void createThreads(int producerCount, int consumerCount) {
        Thread thread;
        for (int count = 0; count < producerCount; count++) {
            thread = new Thread(generatorItem);
            thread.setName(("Generator Thread - " + count));
            thread.start();
            threads.add(thread);
        }

        for (int count = 0; count < consumerCount; count++) {
            thread = new Thread(consumerItem);
            thread.setName(("Consumer Thread  - " + count));
            thread.start();
            threads.add(thread);
        }
    }
}
