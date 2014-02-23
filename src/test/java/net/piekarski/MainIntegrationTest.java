package net.piekarski;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class MainIntegrationTest {
    @Before
    public void setup() {
        File file = new File("src/test/resources/output.sql");
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void shouldProduceInsertSql() {
        // given
        String[] args = new String[]{
                "-i", "src/test/resources/input.csv",
                "-o", "src/test/resources/output.sql",
                "-t", "PERSON"
        };
        // when
        Main.main(args);
        // then
    }

    @Test
    public void shouldProduceUpdateSql() {
        // given
        String[] args = new String[]{
                "-i", "src/test/resources/input.csv",
                "-o", "src/test/resources/output.sql",
                "-t", "PERSON",
                "-U", "ID"
        };
        // when
        Main.main(args);
        // then
    }

    @Test
    public void shouldProduceInsertLiquibaseChangesets() {
        // given
        String[] args = new String[]{
                "-i", "src/test/resources/input.csv",
                "-o", "src/test/resources/output.sql",
                "-t", "PERSON",
                "-l"
        };
        // when
        Main.main(args);
        // then
    }

    @Test
    public void shouldProduceUpdateLiquibaseChangesets() {
        // given
        String[] args = new String[]{
                "-i", "src/test/resources/input.csv",
                "-o", "src/test/resources/output.sql",
                "-t", "PERSON",
                "-U", "ID",
                "-l"
        };
        // when
        Main.main(args);
        // then
    }
}
