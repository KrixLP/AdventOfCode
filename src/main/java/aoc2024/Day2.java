package aoc2024;

import java.util.Arrays;

public class Day2 {
    public static void part1(String[] args) {
        int safeReports = 0;
        for (String arg : args) {
            int[] report = Arrays.stream(arg.split(",")).mapToInt(Integer::parseInt).toArray();
            boolean safe = isSafe(report);
            if (safe) {
                safeReports++;
            }
        }
        System.out.println(safeReports);
    }

    public static void part2(String[] args) {
        int safeReports = 0;
        for (String arg : args) {
            int[] report = Arrays.stream(arg.split(",")).mapToInt(Integer::parseInt).toArray();

            if (isSafe(report) || hasSafeSubReport(report)) {
                safeReports++;
            }
        }
        System.out.println(safeReports);
    }

    private static boolean hasSafeSubReport(int[] report) {
        for (int i = 0; i < report.length; i++) {

            int[] subReport = report.clone();
            subReport[i] = 0;
            subReport = Arrays.stream(subReport).filter(n -> n != 0).toArray();

            if (isSafe(subReport)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSafe(int[] report) {
        int[] diffs = getDiffs(report);

        boolean safe = hasUnsafeDiff(diffs);

        if (safe) {
            safe = (Math.abs(report[0] - report[report.length - 1]) == Arrays.stream(diffs).sum());
        }
        return safe;
    }

    private static int[] getDiffs(int[] report) {
        int[] diffs = new int[report.length - 1];
        for (int j = 0; j < report.length - 1; j++) {
            diffs[j] = Math.abs(report[j] - report[j + 1]);
        }
        return diffs;
    }

    private static boolean hasUnsafeDiff(int[] diffs) {
        for (double diff : diffs) {
            if (diff > 3 || diff == 0) {
                return false;
            }
        }
        return true;
    }
}
