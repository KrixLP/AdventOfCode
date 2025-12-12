package aoc2025;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Day8 {
    private static int result = 1;

    public static void part1(String[] args) {
        int[][] coordinatesList = new int[args.length][3];
        BitSet[] connected = parseInput(args, coordinatesList);
        ArrayList<Set<Integer>> circuits = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            findAndConnectNearest(circuits, coordinatesList, connected);
        }
        circuits.sort(Comparator.comparingInt(Set::size));
        result *= circuits.get(circuits.size() - 1).size();
        result *= circuits.get(circuits.size() - 2).size();
        result *= circuits.get(circuits.size() - 3).size();
        System.out.println(result);
    }

    public static void main(String[] args) {
        ArrayList<Set<Integer>> circuits = new ArrayList<>();
        int[][] coordinatesList = new int[args.length][3];
        BitSet[] connected = parseInput(args, coordinatesList);
        while (true) {
            int[] newPair = findAndConnectNearest(circuits, coordinatesList, connected);
            circuits.sort(Comparator.comparingInt(Set::size));
            if (circuits.get(circuits.size() - 1).size() == coordinatesList.length) {
                result = coordinatesList[newPair[0]][0] * coordinatesList[newPair[1]][0];
                System.out.println(result);
                break;
            }
        }
    }

    private static int[] findAndConnectNearest(ArrayList<Set<Integer>> circuits, int[][] coordinatesList, BitSet[] connected) {
        int[] newPair = findNearestUnconnected(coordinatesList, connected);
        connected[newPair[0]].set(newPair[1]);
        connected[newPair[1]].set(newPair[0]);
        connect(circuits, connected, coordinatesList, newPair[0], newPair[1]);
        consolidateCircuits(circuits, newPair[0], newPair[1]);
        return newPair;
    }

    @NotNull
    private static BitSet[] parseInput(String[] args, int[][] coordinatesList) {
        BitSet[] connected = new BitSet[coordinatesList.length];
        for (int i = 0; i < args.length; i++) {
            String[] split = args[i].split(",");
            int[] coordinates = new int[3];
            for (int j = 0; j < coordinates.length; j++) {
                coordinates[j] = Integer.parseInt(split[j]);
            }
            coordinatesList[i] = coordinates;
            connected[i] = new BitSet(coordinatesList.length);
        }
        return connected;
    }

    private static void connect(ArrayList<Set<Integer>> circuits, BitSet[] connected, int[][] coordinatesList, int j, int k) {
        boolean add = false;
        for (Set<Integer> integers : circuits) {
            if (integers.contains(j)) {
                integers.add(k);
                add = true;
                break;
            }
        }
        if (!add) {
            HashSet<Integer> integers = new HashSet<>();
            circuits.add(integers);
            integers.add(j);
            integers.add(k);
        }
    }

    private static void consolidateCircuits(ArrayList<Set<Integer>> circuits, int j, int k) {
        for (Set<Integer> integers : circuits) {
            if (integers.contains(j)) {
                for (Set<Integer> integers2 : circuits) {
                    if (integers == integers2) {
                        continue;
                    }
                    if (integers2.contains(k)) {
                        integers.addAll(integers2);
                        integers2.clear();
                    }
                }
            }
        }
    }

    public static int[] findNearestUnconnected(int[][] coords, BitSet[] connected) {
        int n = coords.length;

        double bestDist = Double.MAX_VALUE;
        int[] bestPair = null;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                if (connected[i].get(j)) {
                    continue;
                }

                double d = distSquared(coords[i], coords[j]);
                if (d < bestDist) {
                    bestDist = d;
                    bestPair = new int[]{i, j};
                }
            }
        }

        return bestPair;
    }

    private static double distSquared(int[] a, int[] b) {
        long dx = a[0] - b[0];
        long dy = a[1] - b[1];
        long dz = a[2] - b[2];
        return dx * dx + dy * dy + dz * dz;
    }
}

