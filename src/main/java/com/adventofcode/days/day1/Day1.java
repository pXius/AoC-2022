package com.adventofcode.days.day1;

import com.adventofcode.util.FileReader;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day1 {
    private static final String ELF_CALORIES_DATA = "/day1/calories.txt";

    public static void main(String[] args) {
        List<String> caloriesPerFoodItem = FileReader.fileStringReader(ELF_CALORIES_DATA);

        int[] indexes =
                Stream.of(IntStream.of(-1), IntStream.range(0, caloriesPerFoodItem.size())
                                .filter(i -> caloriesPerFoodItem.get(i).equals("")), IntStream.of(caloriesPerFoodItem.size()))
                        .flatMapToInt(s -> s).toArray();


        List<List<Integer>> inventories =
                IntStream.range(0, indexes.length - 1)
                        .mapToObj(i -> caloriesPerFoodItem.subList(indexes[i] + 1, indexes[i + 1]).stream()
                                .map(Integer::parseInt).collect(Collectors.toList()))
                        .toList();


        List<Integer> inventoryCaloriesPerElf =
                inventories.stream()
                        .map(inventory -> inventory
                                .stream()
                                .reduce(0, Integer::sum))
                        .sorted(Comparator.reverseOrder())
                        .toList();

        // Highest Caloric Count
        System.out.println(inventoryCaloriesPerElf.get(0));

        // Total Calories top 3 elves
        System.out.println(inventoryCaloriesPerElf.subList(0, 3).stream()
                .mapToInt(Integer::intValue)
                .sum());
    }

}
