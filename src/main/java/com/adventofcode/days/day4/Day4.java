package com.adventofcode.days.day4;

import com.adventofcode.util.FileReader;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class Day4 {

    private static final String SECTION_IDS = "/day4/section-ids.txt";
    private static final List<String> sections = FileReader.fileStringReader(SECTION_IDS);

    public static void main(String[] args) {
        // Part 1
        System.out.println(countFullOverlappedSections());

        // Part 2
        System.out.println(countAnyOverlappingSections());

    }

    private static int countAnyOverlappingSections() {
        int overlapSectionsCount = 0;
        for (String section : sections) {
            String[] splitSections = section.split(",");
            Integer[] section1 = createIntegerArray(splitSections[0]);
            Integer[] section2 = createIntegerArray(splitSections[1]);

            for (int i = 0; i < section1.length; i++) {
                int lambdaI = i;
                if (Arrays.stream(section2).anyMatch(id -> id.equals(section1[lambdaI]))) {
                    overlapSectionsCount++;
                    break;
                }
            }
        }
        return overlapSectionsCount;
    }

    private static int countFullOverlappedSections() {
        int overlapSectionsCount = 0;
        for (String section : sections) {
            String[] splitSections = section.split(",");
            Integer[] section1 = createIntegerArray(splitSections[0]);
            Integer[] section2 = createIntegerArray(splitSections[1]);

            if (section1.length >= section2.length) {
                if (new HashSet<>(Arrays.asList(section1)).containsAll(Arrays.asList(section2))) {
                    overlapSectionsCount++;
                }
            } else {
                if (new HashSet<>(Arrays.asList(section2)).containsAll(Arrays.asList(section1))) {
                    overlapSectionsCount++;
                }
            }

        }
        return overlapSectionsCount;
    }

    private static Integer[] createIntegerArray(String stringSection) {
        String[] rangeValues = stringSection.split("-");
        return IntStream.rangeClosed(
                        Integer.parseInt(rangeValues[0]),
                        Integer.parseInt(rangeValues[1]))
                .boxed()
                .toArray(Integer[]::new);
    }
}
