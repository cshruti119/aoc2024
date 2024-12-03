package day3;

import lib.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        String fileLocation = "src/aoc2024/day3/sample.txt";
        String fileLocation = "lib/aoc2024/day3/input.txt";
        List<String> strings = FileReader.readFile(fileLocation);

        String input = strings.get(0);

        Long sum1 = getSumOfAllInstruction(input);
        System.out.println("part 1 : "+ sum1);

        Long sum2 =  getFilteredInstructions(input);
        System.out.println("part 2 : "+ sum2);
    }

    private static Long getFilteredInstructions(String input) {
        Pattern compile = Pattern.compile("(do\\(\\)|don't\\(\\)|mul\\(\\d+,\\d+\\))");
        List<String> commands = compile.matcher(input).results().map(rs -> getMatchingString(rs, input)).collect(Collectors.toList());
        List<String> filtered = new ArrayList<>();
        int i=0;
        boolean enable = true;
        while(i<commands.size()) {
            if(commands.get(i).equals("don't()")) {
                enable = false;
            } else if (commands.get(i).equals("do()")) {
                enable = true;
            } else {
                if(enable) {
                    filtered.add(commands.get(i));
                }
            }
            i++;
        }
        return getSum(filtered);
    }


    private static Long getSumOfAllInstruction(String input) {
        Pattern mulPattern = Pattern.compile("(mul\\(\\d+,\\d+\\))");
        Matcher matcher = mulPattern.matcher(input);

        List<String> result = matcher.results()
                .map(matchResult -> getMatchingString(matchResult, input)).collect(Collectors.toList());

        return getSum(result);
    }

    private static Long getSum(List<String> result) {
        return result.stream().map(value -> {
            List<Long> numbers = new ArrayList<>();
            Pattern digitPattern = Pattern.compile("(\\d+)");
            digitPattern.matcher(value).results().forEach(rs -> {
                long digit = Long.parseLong(getMatchingString(rs, value));
                numbers.add(digit);
            });
            return numbers;
        })
        .map(list -> list.get(0) * list.get(1))
        .reduce(Long::sum).get();
    }

    private static String getMatchingString(MatchResult matchResult, String input) {
        StringBuilder sb = new StringBuilder(input);
        return sb.substring(matchResult.start(), matchResult.end());
    }
}
