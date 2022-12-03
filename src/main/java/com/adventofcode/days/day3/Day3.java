package com.adventofcode.days.day3;

import com.adventofcode.util.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day3 {
    private static final String BACKPACK_INVENTORIES = "/day3/backpack-inventory.txt";
    private static final List<String> backpacks = FileReader.fileStringReader(BACKPACK_INVENTORIES);
    private static final List<String> alphabet = Arrays.asList(IntStream.concat(
            IntStream.rangeClosed('a', 'z'),
            IntStream.rangeClosed('A', 'Z')
    ).mapToObj(c -> (char) c + ",").collect(Collectors.joining()).split(","));

    public static void main(String[] args) {

        // Part 1
        int duplicateValue = calculateDuplicatesValue(findCompartmentDuplicates());
        System.out.println(duplicateValue);

        // Part 2
        int badgesValue = calculateDuplicatesValue(findBadges());
        System.out.println(badgesValue);
    }

    private static List<String> findCompartmentDuplicates() {
        List<String> duplicates = new ArrayList<>();
        backpacks
                .forEach(backpack -> {
                    List<String> compartment1 = Arrays
                            .stream(backpack
                                    .substring(0, (backpack.length() / 2))
                                    .split("(?!^)"))
                            .toList();

                    List<String> compartment2 = Arrays
                            .stream(backpack
                                    .substring(backpack.length() / 2)
                                    .split("(?!^)"))
                            .toList();

                    duplicates
                            .addAll(compartment1.stream()
                                    .filter(compartment2::contains)
                                    .collect(Collectors.toSet()));
                });
        return duplicates;
    }

    private static List<String> findBadges() {
        List<String> duplicates = new ArrayList<>();
        for (int b = 0; b < backpacks.size(); b += 3) {
            duplicates.addAll(
                    Arrays.stream(backpacks.get(b).split("(?!^)"))
                            .filter(backpacks.get(b + 1)::contains)
                            .collect(Collectors.toSet())
                            .stream()
                            .filter(backpacks.get(b + 2)::contains)
                            .collect(Collectors.toSet()));
        }
        return duplicates;
    }

    private static int calculateDuplicatesValue(List<String> duplicates) {
        return duplicates
                .stream()
                .mapToInt(duplicate -> alphabet.indexOf(duplicate) + 1)
                .sum();
    }

}
