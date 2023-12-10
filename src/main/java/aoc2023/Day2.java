package aoc2023;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day2 {
    
    private static Map<String, Integer> initCountMap() {
        final Map<String, Integer> countMap = new HashMap<String, Integer>();
        countMap.put("red", 0);
        countMap.put("green", 0);
        countMap.put("blue", 0);
        return countMap;
    }

    private static boolean isGamePossible(Map<String, Integer> map) {
        for(String color : map.keySet()) {
            switch (color) {
                case "red":
                    if(map.get(color) > 12) {
                        return false;
                    }
                    break;
                case "green":
                    if(map.get(color) > 13) {
                        return false;
                    }
                    break;
                case "blue":
                    if(map.get(color) > 14) {
                        return false;
                    }
                    break;  
                default:
                    break;
            }
        }
        return true;
    }

    private static int getGameIDIfPossible(final String line) {
        try {
            final String game = line.split(":")[1];
            final Integer gameId = Integer.parseInt(line.split(":")[0].split(" ")[1]);
            final String[] sets = game.split(";");
            Map<String, Integer> countMap = initCountMap();
            int count;
            String color;
            String[] colorCountSubSets;
            String[] colorCountSplit;
        
            for(String set : sets) {
                colorCountSubSets = set.split(",");
                for(String colorCount : colorCountSubSets) {
                    colorCountSplit = colorCount.split(" ");
                    count = Integer.parseInt(colorCountSplit[1]);
                    color = colorCountSplit[2];
            
                    // Fail fast
                    if(("red".equals(color) && count > 12) ||
                        ("green".equals(color) && count > 13) ||
                        ("blue".equals(color) && count > 14)) {
                        return 0;
                    }
                    switch (color) {
                        case "red":
                            if(count > 12) {
                                return 0;
                            }
                            if(count > countMap.get(color)) {
                                countMap.put(color, count);
                            }
                            break;
                        case "green":
                            if(count > 13) {
                                return 0;
                            }
                            if(count > countMap.get(color)) {
                                countMap.put(color, count);
                            }
                            break;
                        case "blue":
                            if(count > 14) {
                                return 0;
                            }
                            if(count > countMap.get(color)) {
                                countMap.put(color, count);
                            }
                            break;    
                        default:
                            break;
                    }

                }
            }

            if(isGamePossible(countMap)) {
                System.out.println(gameId);
                return gameId;
            }

            return 0;
        } catch (NumberFormatException e) {
            return 0;
        }
        
    }

    public static void printSumOfIDs(final File file) throws FileNotFoundException, IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            long sum=0;
            while((line = br.readLine()) != null) {
                sum += Day2.getGameIDIfPossible(line);
            }
            System.out.println(sum);
        }
    }
}