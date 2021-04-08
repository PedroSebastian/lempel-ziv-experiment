package com.labs;

import com.sauljohnson.lizard.LzwCompressor;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    /**
     * Application entry point.
     *
     * @param args application command line arguments
     */
    public static void main(String[] args) {
        try {
            final Options options = new Options();
            options.addOption(new Option("f", "file", true, "Select file."));

            final CommandLineParser parser = new DefaultParser();
            final CommandLine commandLine = parser.parse(options, args);

            byte[] file;

            if (commandLine.hasOption('f')) {
                final String fileName = commandLine.getOptionValue("file");

                file = Files.readAllBytes(Paths.get(fileName));
                System.out.println(file.toString());
            } else {
                throw new IllegalArgumentException("Invalid argument");
            }

            final long reference = System.nanoTime();

            LzwCompressor lzwCompressor = new LzwCompressor();
            lzwCompressor.compress("Hello World".getBytes(StandardCharsets.UTF_8));

            final long finish = System.nanoTime();
            logger.info(((double) (finish - reference)) / 1000000000.0);  // In seconds
        } catch (IOException | ParseException | IllegalArgumentException exception) {
            logger.error(exception.getMessage());
        }
    }
}
