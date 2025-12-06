package aoc2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Input should be given as a single argument with lines separated by a comma
 */
public class Day6 {
    private static long result = 0;

    public static void part1(String[] args) {
        ArrayList<String[]> assignments = parseInput(args);
        result = solveAssignmentsPt1(assignments);
        System.out.println(result);
    }

    public static void part2(String[] args) {
        ArrayList<String> assignments = new ArrayList<>();
        for (String arg : args) {
            String[] split = arg.split(",");
            assignments.addAll(Arrays.asList(split));
        }
        ArrayList<Integer> operands = new ArrayList<>();
        for (int i = assignments.get(0).length() - 1; i >= 0; i--) {
            String newOperand = getNewOperand(assignments, i);
            if (newOperand.isBlank()) {
                continue;
            }
            operands.add(Integer.parseInt(newOperand));
            char operator = assignments.get(4).charAt(i);
            calculateResult(operands, operator);
        }
        System.out.println(result);
    }

    private static String getNewOperand(ArrayList<String> assignments, int i) {
        String newOperand = assignments.get(0).substring(i, i + 1)
                + assignments.get(1).charAt(i)
                + assignments.get(2).charAt(i)
                + assignments.get(3).charAt(i);
        newOperand = newOperand.strip();
        return newOperand;
    }

    private static void calculateResult(ArrayList<Integer> operands, char operator) {
        long assResult = 0;
        if (operator == '*') {
            assResult = 1;
            for (int operand : operands) {
                assResult *= operand;
            }
            result += assResult;
            operands.clear();
        } else if (operator == '+') {
            for (int operand : operands) {
                assResult += operand;
            }
            result += assResult;
            operands.clear();
        }
    }

    private static long solveAssignmentsPt1(ArrayList<String[]> assignments) {
        long sumResult = 0;
        for (String[] assignment : assignments) {
            long assResult = 0;
            assResult = getResult(assignment, assResult);
            sumResult += assResult;
        }
        return sumResult;
    }

    private static long getResult(String[] assignment, long assResult) {
        if (assignment[assignment.length - 1].equals("*")) {
            assResult = multiply(assignment);
        } else if (assignment[assignment.length - 1].equals("+")) {
            assResult = add(assignment);
        }
        return assResult;
    }

    private static long add(String[] assignment) {
        long assResult = 0;
        for (int i = 0; i < assignment.length - 1; i++) {
            assResult += Integer.parseInt(assignment[i]);
        }
        return assResult;
    }

    private static long multiply(String[] assignment) {
        long assResult = 1;
        for (int i = 0; i < assignment.length - 1; i++) {
            assResult *= Integer.parseInt(assignment[i]);
        }
        return assResult;
    }

    private static ArrayList<String[]> parseInput(String[] args) {
        ArrayList<String[]> assignments = new ArrayList<>();
        String[] split = args[0].split(",");
        ArrayList<String[]> lines = new ArrayList<>();
        formatInputIntoLines(split, lines);
        formatLinesIntoAssignments(lines, assignments);
        return assignments;
    }

    private static void formatLinesIntoAssignments(ArrayList<String[]> lines, ArrayList<String[]> assignments) {
        for (int i = 0; i < lines.get(0).length; i++) {
            String[] assignment = new String[lines.size()];
            for (int j = 0; j < lines.size(); j++) {
                assignment[j] = lines.get(j)[i];
            }
            assignments.add(assignment);
        }
    }

    private static void formatInputIntoLines(String[] split, ArrayList<String[]> lines) {
        for (String s : split) {
            List<String> temp = Arrays.stream(s.split(" ")).toList();
            temp = temp.stream().filter(x -> !x.isEmpty()).toList();
            String[] line = new String[temp.size()];
            for (int j = 0; j < temp.size(); j++) {
                line[j] = temp.get(j);
            }
            lines.add(line);
        }
    }
}
