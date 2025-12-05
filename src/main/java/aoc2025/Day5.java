package aoc2025;

import java.util.ArrayList;
import java.util.Comparator;

public class Day5 {
    static long freshIDs = 0;
    static ArrayList<long[]> ranges = new ArrayList<>();
    static ArrayList<Long> ids = new ArrayList<>();

    public static void part1(String[] args) {
        parseInput(args, ranges, ids);
        for (long id : ids) {
            for (long[] range : ranges) {
                if (range[0] <= id && range[1] >= id) {
                    freshIDs++;
                    break;
                }
            }
        }
        System.out.println(freshIDs);
        System.out.println(ids.size());
    }

    public static void part2(String[] args) {
        parseInput(args, ranges, ids);


        consolidateAndNullRanges();
        for (long[] range : ranges) {
            System.out.println(range[0] + " " + range[1]);
            long rangeSize = range[1] - range[0] + 1;
            freshIDs += rangeSize;
        }
        System.out.println(freshIDs);
        System.out.println(ids.size());
    }

    private static void consolidateAndNullRanges() {
        ranges.sort(Comparator.comparingLong(a -> a[0]));
        for (long[] range : ranges) {
            for (long[] range1 : ranges) {
                if (range == range1) {
                    continue;
                }
                consilidateOverlapping(range, range1);
                consolidateAdjecent(range, range1);
            }
        }
        ranges.removeIf(range -> range[0] == range[1] && range[0] == 0);
    }

    private static void consilidateOverlapping(long[] range, long[] range1) {
        if (checkForOverlap(range, range1)) {
            range[1] = Math.max(range[1], range1[1]);
            nullRange(range1);
        }
    }

    private static void consolidateAdjecent(long[] range, long[] range1) {
        if (range1[0] == range[1]) {
            range[1] = range1[1];
            nullRange(range1);
        }
    }

    private static void nullRange(long[] range1) {
        range1[0] = 0;
        range1[1] = 0;
    }

    private static boolean checkForOverlap(long[] range, long[] range1) {
        return range1[0] >= range[0] && range1[0] <= range[1];
    }

    private static void parseInput(String[] args, ArrayList<long[]> ranges, ArrayList<Long> ids) {
        for (String arg : args) {
            if (arg.contains("-")) {
                String[] parts = arg.split("-");
                long[] range = new long[2];
                range[0] = Long.parseLong(parts[0]);
                range[1] = Long.parseLong(parts[1]);
                ranges.add(range);
            } else {
                ids.add(Long.parseLong(arg));
            }
        }
    }
}
