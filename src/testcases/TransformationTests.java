package testcases;

import java.nio.file.Files;
import java.nio.file.Path;

public class TransformationTests {
    public static void main(String[] args) {
        //Test case for TC-04: Surround with try-catch
        // Trigger command completion here to see TC-04 in action

        Files.readAllLines(Path.of("test.txt"));
    }
}
