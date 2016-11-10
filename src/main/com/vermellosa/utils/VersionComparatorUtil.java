package com.vermellosa.utils;

/**
 * Created by Gary Cassar on 02/09/2016.
 */
public class VersionComparatorUtil {
    private VersionComparatorUtil() {
    }

    public static int compare(Integer majorVersionA, Integer minorVersionA, Integer majorVersionB, Integer minorVersionB) {
        if (!majorVersionA.equals(majorVersionB)) {
            return Integer.compare(majorVersionA, majorVersionB);
        }
        if (!minorVersionA.equals(minorVersionB)) {
            return Integer.compare(minorVersionA, minorVersionB);
        }

        return 0;
    }
}
