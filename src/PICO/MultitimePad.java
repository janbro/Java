package PICO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class MultitimePad {
    private final static int SPACE_TEST_NUM = 10;
    private final static int SPACE_TEST_MIN = 8;
    private final static int KEY_GUESS_MIN = 10;
    private final static double KEY_GUESS_MIN_PERCENTAGE = 0.8;

    public static void main(String[] args) throws IOException {
        MultitimePad p = new MultitimePad();


        byte[] key = new byte[10];
        new Random().nextBytes(key);
        byte[][] messages = p.generate(key);
        byte[] solvedKey = p.solve(key.length, messages);
        if (compareBytes(key, solvedKey)) {
            System.out.println("Success");
            System.exit(0);
        } else {
            System.out.println("Failure");
        }
        
    }

    private byte[][] generate(byte[] key) throws IOException {
        byte[] data = Files.readAllBytes(Paths.get("C:/Users/pc1/Documents/GitHub/Java/src/PICO/encrypted.txt"));
        byte[] filteredData = new byte[data.length];
        int filteredDataLength = 0;
        for (int i = 0; i < data.length; i++) {
            byte p = data[i];
            if (p >= 'a' && p <= 'z') {
                filteredData[filteredDataLength] = (byte) (p - 'a' + 'A');
                filteredDataLength++;
            } else if (p >= 'A' && p <= 'Z') {
                filteredData[filteredDataLength] = p;
                filteredDataLength++;
            } else if (p == ' ' || p == '.' || p == ',' || p == '!' || p == '?' || p == '-') {
                filteredData[filteredDataLength] = p;
                filteredDataLength++;
            }
        }
        int numMessages = filteredDataLength / key.length;
        byte[][] messages = new byte[numMessages][];
        for (int i = 0; i < numMessages; i++) {
            messages[i] = new byte[key.length];
            for (int j = 0; j < key.length; j++) {
                byte p = filteredData[i * key.length + j];
                messages[i][j] = (byte) (p ^ key[j]);
            }
        }
        return messages;
    }

    private static boolean compareBytes(byte[] b1, byte[] b2) {
        if (b1.length != b2.length) {
            return false;
        }
        for (int i = 0; i < b1.length; i++) {
            if (b1[i] != b2[i]) {
                return false;
            }
        }
        return true;
    }

    private byte[] solve(int length, byte[][] messages) {
        byte[] key = new byte[length];
        for (int i = 0; i < length; i++) {
            key[i] = solvePosition(i, messages);
        }
        return key;
    }

    private byte solvePosition(int pos, byte[][] messages) {
        int[] keyGuessCount = new int[256];
        int totalKeyGuess = 0;
        for (int i = 0; i < messages.length - SPACE_TEST_NUM; i++) {
            int success = 0;
            for (int j = 0; j < SPACE_TEST_NUM; j++) {
                if (((messages[i][pos] ^ messages[i + j][pos]) & ' ') != 0) {
                    success++;
                }
            }
            if (success >= SPACE_TEST_MIN) {
                int keyGuess = (messages[i][pos] ^ ' ') & 0xFF;
                keyGuessCount[keyGuess]++;
                totalKeyGuess++;
                if (keyGuessCount[keyGuess] >= KEY_GUESS_MIN && keyGuessCount[keyGuess] > totalKeyGuess *
                        KEY_GUESS_MIN_PERCENTAGE) {
                    System.out.println("Found " + pos + " using " + (i + 1 + SPACE_TEST_NUM) + " messages");
                    return (byte) keyGuess;
                }
            }
        }
       System.out.println("Too few messages");
       return 0;
    }
}