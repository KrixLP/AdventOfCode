package aoc2024;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Day1 {
    static ArrayList<Integer> list1 = new ArrayList<>();
    static ArrayList<Integer> list2 = new ArrayList<>();
    static int result = 0;

    public static void part1(String[] args) {
        readInput(args);

        for (int i = 0; i < list1.size(); i++) {
            result += Math.abs(list1.get(i) - list2.get(i));
        }
        System.out.println(result);
    }

    public static void part2(String[] args) {
        readInput(args);

        for (Integer integer : list1) {
            int occurrences = list2.stream().filter(Predicate.isEqual(integer)).toArray().length;
            result += integer * occurrences;
        }
        System.out.println(result);
    }

    private static void readInput(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (i % 2 == 0) {
                list1.add(Integer.parseInt(args[i]));
            } else {
                list2.add(Integer.parseInt(args[i]));
            }
        }
        list1.sort(Integer::compareTo);
        list2.sort(Integer::compareTo);
    }
}
