package day1;

import lib.FileReader;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String fileLocation = "lib/aoc2024/day1/input.txt";
        List<String> strings = FileReader.readFile(fileLocation);

        long totalDistance = getDistance(strings);
        System.out.println("Part 1 : " + totalDistance);

        long similarityScore = getSimilarityScore(strings);
        System.out.println("Part 2 : " + similarityScore);
    }

    private static long getDistance(List<String> strings) {
        List<Long> list1 = new ArrayList<>();
        List<Long> list2 = new ArrayList<>();
        strings.forEach(s -> {
            String[] word = s.split("\\s+");
            list1.add(Long.parseLong(word[0]));
            list2.add(Long.parseLong(word[1]));
        });
        list1.sort(Comparator.comparingLong(a -> a));
        list2.sort(Comparator.comparingLong(a -> a));

        long sum = 0;
        for(int i=0;i<list2.size();i++) {
            long difference = Math.abs(list1.get(i) - list2.get(i));
            sum += difference;
        }
        return sum;
    }

    private static long getSimilarityScore(List<String> strings) {
        List<Long> list1 = new ArrayList<>();
        List<Long> list2 = new ArrayList<>();
        strings.forEach(s -> {
            String[] word = s.split("\\s+");
            list1.add(Long.parseLong(word[0]));
            list2.add(Long.parseLong(word[1]));
        });

        Map<Long, Integer> freqMap = new HashMap<>();
        for (long number : list2) {
            if (!freqMap.containsKey(number)) {
                freqMap.put(number, 1);
            } else {
                int inc = freqMap.get(number) + 1;
                freqMap.put(number, inc);
            }
        }


        long sum = 0;
        for (Long number : list1) {
            Integer freqCount = freqMap.get(number);
            long score = 0;
            if(freqCount != null) {
                score = number * freqCount;
            }
            sum += score;
        }
        return sum;
    }


}
