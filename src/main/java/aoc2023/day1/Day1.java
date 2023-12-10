package aoc2023.day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.FileNotFoundException;

public class Day1 {
    
    private static int getLineCalibrationValuePart1(final char[] line) {
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

    private static Map<String, Integer> getNumberMap() {
        final Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("thr", 0);
        map.put("fou", 0);
        map.put("fiv", 0);
        map.put("six", 6);
        map.put("sev", 0);
        map.put("eig", 0);
        map.put("nin", 0);
        map.put("thre", 0);
        map.put("four", 4);
        map.put("five", 5);
        map.put("seve", 0);
        map.put("eigh", 0);
        map.put("nine", 9);
        map.put("three", 3);
        map.put("seven", 7);
        map.put("eight", 8);
        return map;
    }

    private static Map<String, Integer> getReversedNumberMap() {
        final Map<String, Integer> map = new HashMap<>();
        map.put("eno", 1);
        map.put("owt", 2);
        map.put("eer", 0);
        map.put("ruo", 0);
        map.put("evi", 0);
        map.put("xis", 6);
        map.put("nev", 0);
        map.put("thg", 0);
        map.put("eni", 0);
        map.put("eerh", 0);
        map.put("ruof", 4);
        map.put("evif", 5);
        map.put("neve", 0);
        map.put("thgi", 0);
        map.put("enin", 9);
        map.put("eerht", 3);
        map.put("neves", 7);
        map.put("thgie", 8);
        return map;
    }



    private static int getLineCalibrationValuePart2(final char[] line) {
        final Map<String, Integer> map = Day1.getNumberMap();
        final Map<String, Integer> revMap = Day1.getReversedNumberMap();
        int i = 0;
        int j = line.length - 1;
        int first=0;
        String firstStr="";
        Integer fMatch = null;
        int last=0;
        String lastStr="";
        Integer lMatch = null;

        do {
            // First
            if (first == 0 && firstStr.length() >= 3) {
                fMatch = map.get(firstStr);
                if(fMatch != null && fMatch > 0) {
                    first = fMatch;
                } else if(fMatch != null && fMatch == 0) {
                    firstStr = firstStr + line[i];
                    i++;
                } else if (Character.isDigit(line[i])) {
                    first = Character.getNumericValue(line[i]);
                } else {
                    firstStr = firstStr.substring(1) + line[i];
                    i++;
                }
            } else if (first == 0 && Character.isDigit(line[i])) {
                first = Character.getNumericValue(line[i]);
            } else if (first == 0) {
                firstStr = firstStr + line[i];
                i++;
            }

            // Last
            if (last == 0 && lastStr.length() >= 3) {
                lMatch = revMap.get(lastStr);
                if(lMatch != null && lMatch > 0) {
                    last = lMatch;
                } else if(lMatch != null && lMatch == 0) {
                    lastStr = lastStr + line[j];
                    j--;
                } else if (Character.isDigit(line[j])) {
                    last = Character.getNumericValue(line[j]);
                } else {
                    lastStr = lastStr.substring(1) + line[j];
                    j--;
                }
            } else if (last == 0 && Character.isDigit(line[j])) {
                last = Character.getNumericValue(line[j]);
            } else if (last == 0) {
                lastStr = lastStr + line[j];
                j--;
            }

            if(first != 0 && last != 0) {
                return Integer.parseInt("" + first + last);
            }
        } while(i < line.length && j >= 0);

        try {
            if(first != 0 && last != 0) {
                return Integer.parseInt("" + first + last);
            } else if(first != 0) {
                return Integer.parseInt("" + first + first);
            }
            return Integer.parseInt("" + last + last);
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
                sum += Day1.getLineCalibrationValuePart2(line.toCharArray());
            }
            System.out.println(sum);
        }
    }
}
