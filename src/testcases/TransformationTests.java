package testcases;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TransformationTests {
    // Action: Remove 'throws IOException' from the method signature and trigger 'Surround with try-catch' on the line below.
    public static void main(String[] args) throws IOException {
        // TC-04: Surround with try-catch
        Files.readAllLines(Path.of("test.txt"));
    }
}
