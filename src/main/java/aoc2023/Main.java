package aoc2023;

import aoc2023.day1.Day1;

import java.io.File;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        try {
            Day1.printSumOfCalibrationValues(
                new File("/home/arenard/personal/aoc2023/src/main/resources/d1_input.txt"));
        } catch (Exception e) {
            System.err.println(e);
        }
        
    }
}