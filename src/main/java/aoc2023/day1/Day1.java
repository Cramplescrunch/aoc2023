package src.main.java.aoc2023.day1;

public class Day1 {
    
    public static int getLineCalibrationValue(final char[] line) {
        int i = 0;
        int j = line.length - 1;
        char first=' ';
        char last=' ';
        do {
            if(Character.isDigit(line[i])) {
                first = line[i];
            } else {
                i++;
            }
            if(Character.isDigit(line[j])) {
                last = line[j];
            } else {
                j--;
            }
            if(first != ' ' && last != ' ') {
                return Integer.parseInt("" + first + last);
            }
        } while(i < j);
        return Integer.parseInt("" + line[i] + line[j]);
    }
}
