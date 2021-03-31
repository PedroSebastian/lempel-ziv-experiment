package com.labs;

import com.sauljohnson.lizard.LzwCompressor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class App
{
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    /**
     * Application entry point.
     *
     * @param args application command line arguments
     */
    public static void main( String[] args )
    {
        try {
            LzwCompressor lzwCompressor = new LzwCompressor();
            lzwCompressor.compress("Hello World".getBytes(StandardCharsets.UTF_8));
        } catch (IOException exception) {
            logger.error(exception.getMessage());
        }
    }
}
