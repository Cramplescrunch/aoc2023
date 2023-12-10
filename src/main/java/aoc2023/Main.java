package aoc2023;

import java.io.File;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        try {
            //Day2.printSumOfPowers(new File("/home/arenard/personal/aoc2023/src/main/resources/d2_input.txt"));
            Day3.printSumPartsNumbers(new File("/home/arenard/personal/aoc2023/src/main/resources/d3_input.txt"));
        } catch (Exception e) {
            System.err.println(e);
        }
        
    }
}