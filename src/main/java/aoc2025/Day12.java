package aoc2025;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day12 {
    private static int fits = 0;
    private static final List<int[]> trees = new ArrayList<>();
    private static final int[] giftSizes = new int[6];

    public static void main(String[] args) {
        String[] areas = args[1].split(" ");
        String[] gifts = args[0].split(" ");
        int[] tree = new int[8];

        parseGifts(gifts);
        parseTrees(areas, tree);
        checkWhatFits();

        System.out.println(fits);
    }

    private static void checkWhatFits() {
        for (int[] ints : trees) {
            int maxSize = (ints[0]) * (ints[1]);
            int maxNeeded = IntStream.range(0, giftSizes.length).map(i -> giftSizes[i] * ints[i + 2]).sum();
            if (maxSize > maxNeeded) {
                fits++;
            }
        }
    }

    private static void parseTrees(String[] areas, int[] tree) {
        int i = 2;
        for (String arg : areas) {
            if (arg.contains("x")) {
                if (tree[0] != 0) {
                    trees.add(tree);
                    tree = new int[8];
                    i = 2;
                }
                String[] split = arg.split("x");
                tree[0] = Integer.parseInt(split[0]);
                tree[1] = Integer.parseInt(split[1].replace(":", ""));
            } else tree[i++] = Integer.parseInt(arg);
        }
        trees.add(tree);
    }

    private static void parseGifts(String[] gifts) {
        int i = 0;
        for (String gift : gifts) {
            if (gift.contains("x")) {
                break;
            }
            if (gift.contains(":")) {
                i = Integer.parseInt(gift.split(":")[0]);
            } else giftSizes[i] += gift.replace(".", "").length();
        }
    }
}


