package net.piekarski.util;

import com.google.common.collect.Maps;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class TableUtil {
    public static Map getMapFromLists(List<String> list1, List<String> list2) {
        Map map = Maps.newHashMap();

        Iterator<String> iterator1 = list1.iterator();
        Iterator<String> iterator2 = list2.iterator();

        while (iterator1.hasNext()) {
            String nextKey = iterator1.next();
            String nextValueOrBlank = iterator2.hasNext() ? iterator2.next() : "";

            map.put(nextKey, nextValueOrBlank);
        }

        return map;
    }

    private TableUtil() {
    }
}
