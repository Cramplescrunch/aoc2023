package aoc2023;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day3 {

    private static boolean isPartNumber(final int numStart, final int numEnd, final String[] currentLines) {
        String toAnalyze="";
        // Check top line
        if(currentLines[0].length() > 0) {
            if(numStart > 0 && numEnd < currentLines[0].length() -2) {
                toAnalyze =  currentLines[0].substring(numStart-1, numEnd+2);
            } else if (numStart > 0 && numEnd < currentLines[0].length() -1) { // end of line
                toAnalyze =  currentLines[0].substring(numStart-1, numEnd+1);
            } else if (numStart == 0) { // start of line
                toAnalyze =  currentLines[0].substring(numStart, numEnd+2);
            }
            for(Character c : toAnalyze.toCharArray()) {
                if(!Character.isDigit(c) && c != '.') {
                    return true;
                }
            }
        }

        // Check middle line
        if((numStart > 0 && currentLines[1].charAt(numStart-1) != '.') || 
            (numEnd < currentLines[1].length()-1  && currentLines[1].charAt(numEnd+1) != '.')) {
            return true;
        }

        // Check bottom line
        if(currentLines[2].length() > 0) {
            if(numStart > 0 && numEnd < currentLines[2].length()-2) {
                toAnalyze =  currentLines[2].substring(numStart-1, numEnd+2);
            } else if (numStart > 0 && numEnd < currentLines[2].length()-1) { // end of line
                toAnalyze =  currentLines[2].substring(numStart-1, numEnd+1);
            } else if (numStart == 0) { // start of line
                toAnalyze =  currentLines[2].substring(numStart, numEnd+2);
            }
            for(Character c : toAnalyze.toCharArray()) {
                if(!Character.isDigit(c) && c != '.') {
                    return true;
                }
            }
        }
        return false;
    }

    private static int getLineSum(final String line, final String[] currentLines) {
        String tempNum = "";
        int sum = 0;
        int numStart = -1;
        for (int i=0; i < line.length(); i++) {
            if(Character.isDigit(line.charAt(i))) {
                if(numStart == -1) {
                    numStart = i;
                }
                tempNum += line.charAt(i);
            } else if (tempNum.length() > 0) {
                if(isPartNumber(numStart, numStart + tempNum.length()-1, currentLines)) {
                    System.out.println(tempNum);
                    sum += Integer.parseInt(tempNum);
                }
                numStart=-1;
                tempNum="";
            }
        }
        return sum;
    }

    private static List<Integer[]> getRelatedGears(final int lineNumber, final String line, final String[] currentLines) {
        
    }

    private static  Map<Integer[], String[]> updateGearMapForLine(final  Map<Integer[], String[]> map,
                                                                    final String line,
                                                                    final String[] currentLines,
                                                                    final int lineNumber) {
        Map<Integer[], String[]> updatedMap = map;                                                                
        String tempNum = "";
        int sum = 0;
        int numStart = -1;
        for (int i=0; i < line.length(); i++) {
            if(Character.isDigit(line.charAt(i))) {
                if(numStart == -1) {
                    numStart = i;
                }
                tempNum += line.charAt(i);
            } else if (tempNum.length() > 0) {
                if(isPartNumber(numStart, numStart + tempNum.length()-1, currentLines)) {
                    System.out.println(tempNum);
                    sum += Integer.parseInt(tempNum);
                }
                numStart=-1;
                tempNum="";
            }
        }
        return updatedMap;
    }

    private static long getSumOfGearRatiosForGearMap(final  Map<Integer[], String[]> map) {
        long result = 0;
        for(String[] value : map.values()) {
            if(value.length == 2) {
                result += Integer.parseInt(value[1]) * Integer.parseInt(value[2]);
            }
        }
        return result;
    }

    public static void printSumOfGearRatios(final File file) throws FileNotFoundException, IOException {
        String[]currentLines = {"", "", ""};
        int lineNumber = 0;
        Map<Integer[], String[]> gearMap = new HashMap<Integer[], String[]>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String readLine;
            // Init current lines
            currentLines[0] = "";
            currentLines[1] = br.readLine();
            currentLines[2] = br.readLine();
            long sum=0;

            while((readLine = br.readLine()) != null) {
                gearMap = Day3.updateGearMapForLine(gearMap, currentLines[1], currentLines, lineNumber);
                // move to next lines 
                currentLines[0] = currentLines[1];
                currentLines[1] = currentLines[2];
                currentLines[2] = readLine;
                lineNumber++;
            }
            // When readLine returns null we must complete the last cycle
            gearMap = Day3.updateGearMapForLine(gearMap, currentLines[1], currentLines, lineNumber);
            currentLines[0] = currentLines[1];
            currentLines[1] = currentLines[2];
            currentLines[2] = "";
            lineNumber++;
            gearMap = Day3.updateGearMapForLine(gearMap, currentLines[1], currentLines, lineNumber);
            System.out.println(sum);
        }
    }

    public static void printSumPartsNumbers(final File file) throws FileNotFoundException, IOException {
        String[]currentLines = {"", "", ""};
        
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String readLine;
            // Init current lines
            currentLines[0] = "";
            currentLines[1] = br.readLine();
            currentLines[2] = br.readLine();
            int i=0;
            long sum=0;

            while((readLine = br.readLine()) != null) {
                sum += Day3.getLineSum(currentLines[1], currentLines);
                // move to next lines 
                currentLines[0] = currentLines[1];
                currentLines[1] = currentLines[2];
                currentLines[2] = readLine;
            }
            // When readLine returns null we must complete the last cycle
            sum += Day3.getLineSum(currentLines[1], currentLines);
            currentLines[0] = currentLines[1];
            currentLines[1] = currentLines[2];
            currentLines[2] = "";
            sum += Day3.getLineSum(currentLines[1], currentLines);
            System.out.println(sum);
        }
    }
}
