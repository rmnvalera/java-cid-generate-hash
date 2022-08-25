package com.github.rmnvalera;

import io.ipfs.multihash.Multihash;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

import static org.junit.Assert.*;

public class CidTestEncode {

    @Test
    public void encodeTest() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("file.txt")).getFile());
        Multihash multihash = Cid.encode(file);

        assertEquals("Hashes is not equals",
                multihash.toString(), "QmQDHpYbrtRzp3MTcrjjkaUbtJa3HMj7Uww3ef5YyEe3zU");
    }

    @Test
    public void encodePathTest() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("file.txt")).getFile());
        Multihash multihash = Cid.encode(file.toPath());

        assertEquals("Hashes is not equals",
                multihash.toString(), "QmQDHpYbrtRzp3MTcrjjkaUbtJa3HMj7Uww3ef5YyEe3zU");
    }

    @Test
    public void encodeBytesTest() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("file.txt")).getFile());
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        long fileSize = Files.size(file.toPath());
        Multihash multihash = Cid.encode(fileBytes, fileSize, Cid.FILE);

        assertEquals("Hashes is not equals",
                multihash.toString(), "QmQDHpYbrtRzp3MTcrjjkaUbtJa3HMj7Uww3ef5YyEe3zU");
    }

    @Test
    public void fileIsNotExistTest() throws IOException {
        File file = new File("notExist.txt");

        Exception exception = assertThrows(IOException.class, () -> {
            Cid.encode(file);
        });

        String expectedMessage = "File is not exist";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
}
