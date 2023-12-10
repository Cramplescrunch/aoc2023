package aoc2023;

import aoc2023.day1.Day1;

import java.io.File;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        try {
            Day2.printSumOfIDs(
                new File("/home/arenard/personal/aoc2023/src/main/resources/d2_input.txt"));
        } catch (Exception e) {
            System.err.println(e);
        }
        
    }
}