package net.piekarski;

import org.junit.Test;

public class MainIntegrationTest {
    @Test
    public void shouldMain() {
        // given
        String[] args = new String[] {
                "-i", "src/test/resources/input.csv",
                "-o", "src/test/resources/output.sql",
                "-t", "person"
        };
        // when
        Main.main(args);
        // then
    }
}
