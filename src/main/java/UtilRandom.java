package main.java;

import java.security.MessageDigest;
import java.util.UUID;

public class UtilRandom {

    private UUID uuid = UUID.randomUUID();

    private String randomGeneratedTestData = uuid.toString();

    public String getRandomGeneratedTestData() {
        return randomGeneratedTestData;
    }
}
