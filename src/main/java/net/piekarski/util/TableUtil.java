package net.piekarski.util;

import com.google.common.collect.Maps;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class TableUtil {
    public static Map getMapFromLists(List<String> keys, List<String> values) {
        Map map = Maps.newLinkedHashMap();

        Iterator<String> keysIterator = keys.iterator();
        Iterator<String> valuesIterator = values.iterator();

        while (keysIterator.hasNext()) {
            String nextKey = keysIterator.next();
            String nextValueOrBlank = valuesIterator.hasNext() ? valuesIterator.next() : "";

            map.put(nextKey, nextValueOrBlank);
        }

        return map;
    }

    private TableUtil() {
    }
}
