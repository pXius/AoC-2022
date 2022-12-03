package com.adventofcode.days.day2;

import com.adventofcode.util.FileReader;

import java.util.List;

public class Day2 {
    private static final String STRATEGY_GUIDE = "/day2/strategy-guide.txt";
    static List<String> matches = FileReader.fileStringReader(STRATEGY_GUIDE);

    public static void main(String[] args) {
        // Part 1 Points
        int totalPoints1 = 0;
        for (String m : matches
        ) {
            totalPoints1 += playedHandPointCalculator1(m.substring(m.length() - 1));
            totalPoints1 += outcomeCalculatorPart1(m);
        }
        System.out.println(totalPoints1);

        // Part 2 Points
        int totalPoints2 = 0;
        for (String m : matches
        ) {
            totalPoints2 += outcomeCalculatorPart2(m);
            totalPoints2 += playedHandPointCalculator2(m);
        }
        System.out.println(totalPoints2);
    }

    private static int outcomeCalculatorPart1(String match) {
        return switch (match) {
            case "A Y", "B Z", "C X" -> 6;
            case "A X", "B Y", "C Z" -> 3;
            case "A Z", "B X", "C Y" -> 0;
            default -> throw new RuntimeException("Match not mapped!");
        };
    }

    private static int outcomeCalculatorPart2(String match) {
        return switch (match) {
            case "A Z", "B Z", "C Z" -> 6;
            case "A Y", "B Y", "C Y" -> 3;
            case "A X", "B X", "C X" -> 0;
            default -> throw new RuntimeException("Match not mapped!");
        };
    }

    private static int playedHandPointCalculator1(String hand) {
        return switch (hand) {
            case "X" -> 1;
            case "Y" -> 2;
            case "Z" -> 3;
            default -> throw new RuntimeException("Hand not mapped!");
        };
    }

    private static int playedHandPointCalculator2(String match) {
        return switch (match) {
            case "A Y", "B X", "C Z" -> 1;
            case "A Z", "B Y", "C X" -> 2;
            case "A X", "B Z", "C Y" -> 3;
            default -> throw new RuntimeException("Hand not mapped!");
        };
    }

}
