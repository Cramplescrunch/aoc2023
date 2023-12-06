package aoc2023.day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Day1 {
    
    public static int getLineCalibrationValue(final char[] line) {
        int i = 0;
        int j = line.length - 1;
        char first=' ';
        char last=' ';
        do {
            if(first == ' ' && Character.isDigit(line[i])) {
                first = line[i];
            } else if (first == ' ') {
                i++;
            }
            if(last == ' ' && Character.isDigit(line[j])) {
                last = line[j];
            } else if (last == ' ') {
                j--;
            }
            if(first != ' ' && last != ' ') {
                return Integer.parseInt("" + first + last);
            }
        } while(i < j);
        try {
            return Integer.parseInt("" + line[i] + line[j]);
        } catch(NumberFormatException e) {
            System.err.println(e);
            return 0;
        }
    }

    public static void printSumOfCalibrationValues(final File file) throws FileNotFoundException, IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            long sum=0;
            while((line = br.readLine()) != null) {
                sum += Day1.getLineCalibrationValue(line.toCharArray());
            }
            System.out.println(sum);
        }
    }
}
