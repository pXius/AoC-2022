package com.adventofcode.days.day5;

import com.adventofcode.util.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Day5 {
    private static final String MOVE_INSTRUCTIONS = "/day5/moves.txt";
    private static final String STARTING_STACKS = "/day5/stacks.txt";
    private static final List<String> rawMoves = FileReader.fileStringReader(MOVE_INSTRUCTIONS);
    private static final List<String> rawStacks = FileReader.fileStringReader(STARTING_STACKS);
    private static final List<int[]> moves = intArrayFromFile();
    private static final List<Stack<String>> stacks = getStacksFromFile();

    public static void main(String[] args) {

        // Part 1 top crates
        // moves.forEach(Day5::moveCratesWithCrane9000);
        // System.out.println(getTopCrates());

        // Part 2 top crates
        moves.forEach(Day5::moveCratesWithCrane9001);
        System.out.println(getTopCrates());
    }

    private static String getTopCrates() {
        StringBuilder topCrates = new StringBuilder();
        stacks.forEach(stack -> {
            if (stack.size() > 0) {
                topCrates.append(stack.pop());
            }
        });
        return topCrates + "";
    }

    private static void moveCratesWithCrane9000(int[] instructions) {
        for (int i = 1; i <= instructions[0]; i++) {
            String crate = stacks.get(instructions[1] - 1).pop();
            stacks.get(instructions[2] - 1).push(crate);
        }
    }

    private static void moveCratesWithCrane9001(int[] instructions) {
        Stack<String> craneStack = new Stack<>();
        for (int i = 1; i <= instructions[0]; i++) {
            String crate = stacks.get(instructions[1] - 1).pop();
            craneStack.push(crate);
        }
        Collections.reverse(craneStack);
        stacks.get(instructions[2] - 1).addAll(craneStack);

    }

    private static List<int[]> intArrayFromFile() {
        return rawMoves
                .stream()
                .map(instruction -> {
                    return instruction
                            .replaceAll("[^0-9]+", " ")
                            .trim()
                            .split(" ");
                })
                .map(stringArray -> Arrays.stream(stringArray)
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .toList();
    }

    private static List<Stack<String>> getStacksFromFile() {
        List<Stack<String>> stackList = new ArrayList<>();
        for (String stack : rawStacks
        ) {
            Stack<String> newStack = new Stack<>();
            List<String> oldStack = new ArrayList<>(Arrays.stream(stack.split("(?!^)")).toList());
            Collections.reverse(oldStack);
            oldStack.forEach(newStack::push);
            stackList.add(newStack);
        }
        return stackList;
    }

}
