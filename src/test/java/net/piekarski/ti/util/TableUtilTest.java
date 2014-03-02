package net.piekarski.ti.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;

public class TableUtilTest {
    @Test
    public void shouldGetMapFromLists() {
        // given
        List<String> keys = Lists.newArrayList("1", "2", "3");
        List<String> values = Lists.newArrayList("a", "b", "c");

        // when
        Map<String, String> actualMap = TableUtil.getMapFromLists(keys, values);

        // then
        Map<String, String> expectedMap = Maps.newHashMap();
        expectedMap.put("1", "a");
        expectedMap.put("2", "b");
        expectedMap.put("3", "c");

        assertThat(actualMap).isEqualTo(expectedMap);
    }

    @Test
    public void shouldGetMapFromListsWithValuesListTooShort() {
        // given
        List<String> keys = Lists.newArrayList("1", "2", "3");
        List<String> values = Lists.newArrayList("a", "b");

        // when
        Map<String, String> actualMap = TableUtil.getMapFromLists(keys, values);

        // then
        Map<String, String> expectedMap = Maps.newHashMap();
        expectedMap.put("1", "a");
        expectedMap.put("2", "b");
        expectedMap.put("3", "");

        assertThat(actualMap).isEqualTo(expectedMap);
    }

    @Test
    public void shouldGetMapFromListsWithValuesListEmpty() {
        // given
        List<String> keys = Lists.newArrayList("1", "2", "3");
        List<String> values = Lists.newArrayList();

        // when
        Map<String, String> actualMap = TableUtil.getMapFromLists(keys, values);

        // then
        Map<String, String> expectedMap = Maps.newHashMap();
        expectedMap.put("1", "");
        expectedMap.put("2", "");
        expectedMap.put("3", "");

        assertThat(actualMap).isEqualTo(expectedMap);
    }
}