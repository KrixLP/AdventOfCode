package aoc2025;

import java.util.*;

public class Day11 {
    private static final Map<String, Set<String>> connections = new HashMap<>();
    private static final Map<String, Long> pathCounts = new HashMap<>();

    public static void part1(String[] args) {
        parseInput(args);
        long counter = call("you", Set.of(), connections);
        System.out.println(counter);
    }


    public static void part2(String[] args) {
        parseInput(args);

        long counter = call("svr", Set.of("dac", "fft"), connections);
        System.out.println(counter);
    }

    private static long call(String node, Set<String> leftToVisit, Map<String, Set<String>> graph) {
        if (node.equals("out")) {
            return leftToVisit.isEmpty() ? 1L : 0L;
        }

        String pathKey = node + ":+" + leftToVisit;
        Long cached = pathCounts.get(pathKey);
        if (cached != null) {
            return cached;
        }

        long sum = getSum(node, leftToVisit, graph);
        pathCounts.put(pathKey, sum);
        return sum;
    }

    private static long getSum(String node, Set<String> leftToVisit, Map<String, Set<String>> graph) {
        return graph.get(node).stream().mapToLong(next -> {
            Set<String> newLeft = leftToVisit.contains(next) ? subtract(leftToVisit, next) : leftToVisit;
            return call(next, newLeft, graph);
        }).sum();
    }

    private static Set<String> subtract(Set<String> set, String element) {
        Set<String> result = new HashSet<>(set);
        result.remove(element);
        return result;
    }

    private static void parseInput(String[] args) {
        List<String> stringList = new ArrayList<>();
        for (String arg : args) {
            if (arg.endsWith(":") && !stringList.isEmpty()) {
                connections.put(stringList.get(0), Set.copyOf(stringList.subList(1, stringList.size())));
                stringList.clear();
            }
            stringList.add(arg.replace(":", ""));
        }
        connections.put(stringList.get(0), Set.copyOf(stringList.subList(1, stringList.size())));
    }

}
