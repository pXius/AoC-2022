package com.adventofcode.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class FileReader {

    public static List<String> fileStringReader(String fileName) {
        URL resource = FileReader.class.getResource(fileName);
        List<String> array = new ArrayList<>();
        try {
            File file = new File(Objects.requireNonNull(resource).getFile());
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                array.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return array;
    }
}