package com.labs;

import com.sauljohnson.lizard.LzwCompressor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    /**
     * Application entry point.
     *
     * @param args application command line arguments
     */
    public static void main(String[] args) {
        try {
            final long reference = System.nanoTime();

            LzwCompressor lzwCompressor = new LzwCompressor();
            lzwCompressor.compress("Hello World".getBytes(StandardCharsets.UTF_8));

            final long finish = System.nanoTime();
            logger.info(((double) (finish - reference)) / 1000000000.0);  // In seconds
        } catch (IOException exception) {
            logger.error(exception.getMessage());
        }
    }
}
