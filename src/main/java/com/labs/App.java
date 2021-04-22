package com.labs;

import com.sauljohnson.lizard.LzwCompressor;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
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
            } else {
                throw new IllegalArgumentException("Invalid argument");
            }

            long reference = System.nanoTime();

            LzwCompressor lzwCompressor = new LzwCompressor();
            final byte[] compressed = lzwCompressor.compress(file).clone();

            long finish = System.nanoTime();

            logger.info("Tamanho inicial: " + file.length + " bytes");
            logger.info("Tamanho final: " + compressed.length + " bytes");
            logger.info("Tempo de compressão: " + (((double) (finish - reference)) / 1000000000.0) + " segundos");  // In seconds

            reference = System.nanoTime();

            lzwCompressor.decompress(compressed);
            finish = System.nanoTime();
            logger.info("Tempo de descompressão: " + (((double) (finish - reference)) / 1000000000.0) + " segundos");  // In seconds
        } catch (IOException | ParseException | IllegalArgumentException exception) {
            logger.error(exception.getMessage());
        }
    }
}
