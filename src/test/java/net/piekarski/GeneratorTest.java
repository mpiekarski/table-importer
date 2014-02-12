package net.piekarski;

import org.junit.Test;

import java.io.FileNotFoundException;

public class GeneratorTest {
    private Generator generator = new Generator();

    @Test
    public void shouldCountCells() throws FileNotFoundException {
        // given
        // when
        generator.generate("src/test/resources/test.csv");
        // then
    }
}
