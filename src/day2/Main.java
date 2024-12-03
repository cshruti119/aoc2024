package day2;

import lib.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        String fileLocation = "src/aoc2024/day2/sample.txt";
        String fileLocation = "lib/aoc2024/day2/input.txt";
        List<String> strings = FileReader.readFile(fileLocation);

        List<List<Integer>> records = strings.stream().map(line -> Arrays.stream(line.split("\\s+")).map(Integer::parseInt).collect(Collectors.toList())).collect(Collectors.toList());

        int count = 0;
        for (List<Integer> record : records) {
            if (isLevelSafe(record)) {
                count++;
            }
        }
        System.out.println("Part 1 : "+count);

        int count2 = 0;
        for (List<Integer> record : records) {
            if (isLevelSafe(record)) {
                count2++;
            } else {
                List<List<Integer>> allSublist = getSublist(record);
                boolean isAnyLevelSafe = allSublist.stream().anyMatch(Main::isLevelSafe);
                if (isAnyLevelSafe) {
                    count2++;
                }
            }
        }

        System.out.println("Part 2 : "+count2);
    }

    private static boolean isLevelSafe(List<Integer> record) {
        if (record.get(0) > record.get(1)) {
            return isDecreasing(record);
        } else {
            return isIncreasing(record);
        }
    }

    private static boolean isDecreasing(List<Integer> record) {
        for (int i = 1; i < record.size(); i++) {
            int diff = record.get(i - 1) - record.get(i);
            if (isNotBetweenOneAndThree(diff)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isIncreasing(List<Integer> record) {
        for (int i = 1; i < record.size(); i++) {
            int diff = record.get(i) - record.get(i - 1);

            if (isNotBetweenOneAndThree(diff)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNotBetweenOneAndThree(int diff) {
        return diff <= 0 || diff >= 4;
    }

    private static List<List<Integer>> getSublist(final List<Integer> report) {
        final List<List<Integer>> allSubseqs = new ArrayList<>();

        for (int i = 0; i < report.size(); i++) {
            final List<Integer> dampenedReport = new ArrayList<>(report.subList(0, i));
            dampenedReport.addAll(report.subList(i + 1, report.size()));
            allSubseqs.add(dampenedReport);
        }

        return allSubseqs;
    }
}
