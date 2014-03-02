package net.piekarski.ti;

import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.fest.assertions.Assertions.assertThat;

public class MainIntegrationTest {
    @Before
    public void setup() {
        deleteOutputFileIfExists();
        DateTimeUtils.setCurrentMillisFixed(0l);
    }

    @After
    public void teardown() {
        deleteOutputFileIfExists();
        DateTimeUtils.setCurrentMillisSystem();
    }

    @Test
    public void shouldProduceInsertSql() {
        // given
        String[] args = new String[]{
                "-i", "src/test/resources/input.csv",
                "-o", "src/test/resources/output",
                "-t", "PERSON"
        };

        // when
        Main.main(args);

        // then
        File actual = new File("src/test/resources/output");
        File expected = new File("src/test/resources/insert.sql");

        assertThat(actual).hasSameContentAs(expected);
    }

    @Test
    public void shouldProduceUpdateSql() {
        // given
        String[] args = new String[]{
                "-i", "src/test/resources/input.csv",
                "-o", "src/test/resources/output",
                "-t", "PERSON",
                "-U", "ID"
        };

        // when
        Main.main(args);

        // then
        File actual = new File("src/test/resources/output");
        File expected = new File("src/test/resources/update.sql");

        assertThat(actual).hasSameContentAs(expected);
    }

    @Test
    public void shouldProduceInsertLiquibaseChangesets() {
        // given
        String[] args = new String[]{
                "-i", "src/test/resources/input.csv",
                "-o", "src/test/resources/output",
                "-t", "PERSON",
                "-l"
        };

        // when
        Main.main(args);

        // then
        File actual = new File("src/test/resources/output");
        File expected = new File("src/test/resources/insert.xml");

        assertThat(actual).hasSameContentAs(expected);
    }

    @Test
    public void shouldProduceUpdateLiquibaseChangesets() {
        // given
        String[] args = new String[]{
                "-i", "src/test/resources/input.csv",
                "-o", "src/test/resources/output",
                "-t", "PERSON",
                "-U", "ID",
                "-l"
        };

        // when
        Main.main(args);

        // then
        File actual = new File("src/test/resources/output");
        File expected = new File("src/test/resources/update.xml");

        assertThat(actual).hasSameContentAs(expected);
    }

    private void deleteOutputFileIfExists() {
        File file = new File("src/test/resources/output");
        if (file.exists()) {
            file.delete();
        }
    }
}
