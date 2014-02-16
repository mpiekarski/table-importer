package net.piekarski;

import org.junit.Test;

public class MainIntegrationTest {
    @Test
    public void shouldMain() {
        // given
        String[] args = new String[] {
                "-i", "input.csv",
                "-o", "output.sql"
        };
        // when
        Main.main(args);
        // then
    }
}
