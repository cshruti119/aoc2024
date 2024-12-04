package day4;

import lib.FileReader;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static final String XMAS = "XMAS";
    public static final String XMAS_R = "SAMX";
    public static final String MAS = "MAS";
    public static final String MAS_R = "SAM";

    public static void main(String[] args) {
//        String filename = "src/day4/sample.txt";
        String filename = "src/day4/input.txt";
        List<List<String>> puzzle = FileReader.readFile(filename).stream().map(s -> List.of(s.split(""))).collect(Collectors.toList());


        // part 2
        int n = puzzle.size();
        int m = puzzle.get(0).size();
        System.out.println("Part 1 : " + getCount(n, m, puzzle));
        System.out.println("Part 2 : " + getXMasCount(n, m, puzzle));
    }

    private static int getXMasCount(int n, int m, List<List<String>> puzzle) {
        int count1 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                String letter = puzzle.get(i).get(j);
                if (letter.equals("A")) {
                    if (checkXmas(puzzle, i, j, m, n)) ++count1;
                }
            }
        }
        return count1;
    }

    private static boolean checkXmas(List<List<String>> puzzle, int i, int j, int m, int n) {
        StringBuilder right = new StringBuilder();
        StringBuilder left = new StringBuilder();
        if (i - 1 > -1 && j - 1 > -1 && i + 1 < n && j + 1 < m) {
            right.append(puzzle.get(i - 1).get(j - 1));
            right.append(puzzle.get(i).get(j));
            right.append(puzzle.get(i + 1).get(j + 1));

            left.append(puzzle.get(i - 1).get(j + 1));
            left.append(puzzle.get(i).get(j));
            left.append(puzzle.get(i + 1).get(j - 1));
        }
        return (right.toString().equals(MAS) || right.toString().equals(MAS_R)) &&
                (left.toString().equals(MAS) || left.toString().equals(MAS_R));
    }

    private static int getCount(int n, int m, List<List<String>> puzzle) {
        int count = 0;
        for (int i = 0; i < puzzle.size(); i++) {
            for (int j = 0; j < puzzle.get(0).size(); j++) {
                String letter = puzzle.get(i).get(j);
                if (letter.equals("X") || letter.equals("S")) {
                    if (findWordInVertical(puzzle, i, j, n, m)) count++;
                    if (findWordInHorizontal(puzzle, i, j, n, m)) count++;
                    if (findWordInDiagonalRight(puzzle, i, j, n, m)) count++;
                    if (findWordInDiagonalLeft(puzzle, i, j, n, m)) count++;
                }
            }
        }
        return count;
    }

    private static boolean findWordInVertical(List<List<String>> puzzle, int i, int j, int n, int m) {
        StringBuilder vertical = new StringBuilder();
        for (int k = 0; k < 4; k++) {
            if (i + k < m) {
                vertical.append(puzzle.get(i + k).get(j));
            }
        }

        return (vertical.toString().equals(XMAS) || vertical.toString().equals(XMAS_R));
    }

    private static boolean findWordInHorizontal(List<List<String>> puzzle, int i, int j, int n, int m) {
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < 4; k++) {
            if (j + k < n) {
                sb.append(puzzle.get(i).get(j + k));
            }
        }

        return (sb.toString().equals("XMAS") || sb.toString().equals("SAMX"));
    }

    private static boolean findWordInDiagonalRight(List<List<String>> puzzle, int i, int j, int n, int m) {
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < 4; k++) {
            if (j + k < n && i + k < m) {
                sb.append(puzzle.get(i + k).get(j + k));
            }
        }

        return (sb.toString().equals("XMAS") || sb.toString().equals("SAMX"));
    }

    private static boolean findWordInDiagonalLeft(List<List<String>> puzzle, int i, int j, int n, int m) {
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < 4; k++) {
            if (j - k > -1 && i + k < m) {
                sb.append(puzzle.get(i + k).get(j - k));
            }
        }

        return (sb.toString().equals("XMAS") || sb.toString().equals("SAMX"));
    }
}
