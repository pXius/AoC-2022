package com.adventofcode.days.day6;

import com.adventofcode.util.FileReader;

import java.util.*;

public class Day6 {
    private static final String RAW_SUBROUTINE = "/day6/subroutine.txt";
    private static final List<String> subroutine = List.of(FileReader.fileStringReader(RAW_SUBROUTINE).get(0).split("(?!^)"));

    public static void main(String[] args) {
        // First Marker
        System.out.println(findIndexAfterPattern(4));

        // First Message
        System.out.println(findIndexAfterPattern(14));
    }

    private static int findIndexAfterPattern(int patternLength) {
        for (int i = 0; i < subroutine.size(); i++) {
            Set<String> possibleMarker = new HashSet<>();
            for (int j = 0; j <= patternLength - 1 && i + j < subroutine.size(); j++) {
                possibleMarker.add(subroutine.get(i + j));
            }
            if (possibleMarker.size() == patternLength) {
                return i + patternLength;
            }
        }
        return 0;
    }
}

