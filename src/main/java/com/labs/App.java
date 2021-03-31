package com.labs;

import com.sauljohnson.lizard.LzwCompressor;


import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class App {
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
            System.out.println(((double) (finish - reference)) / 1000000000.0);  // In seconds
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
