package aoc2025;

public class Day1 {
    static int dialPosition = 50;
    static int zeroes = 0;

    public static void part1(String[] args) {
        for (String arg : args) {
            int turnDistance = Integer.parseInt(arg.substring(1));
            if (arg.startsWith("L")) {
                turnDistance = -turnDistance;
            }
            dialPosition += turnDistance;
            dialPosition = dialPosition % 100;
            if (dialPosition == 0) {
                zeroes++;
            }
        }
        System.out.println(zeroes);
    }

    public static void part2(String[] args) {
        for (String arg : args) {
            int turnDistance = Integer.parseInt(arg.substring(1));
            if (arg.startsWith("R")) {
                turnRightPt2(turnDistance);
            } else if (arg.startsWith("L")) {
                turnLeftPt2(turnDistance);
            }

        }
        System.out.println(zeroes);
    }

    private static void turnLeftPt2(int turnDistance) {
        while (turnDistance > 0) {
            dialPosition = dialPosition - 1;
            turnDistance--;
            if (dialPosition == 0) {
                zeroes++;
            }
            if (dialPosition < 0) {
                dialPosition = dialPosition + 100;
            }
        }
    }

    private static void turnRightPt2(int turnDistance) {
        while (turnDistance > 0) {
            dialPosition = dialPosition + 1;
            turnDistance--;
            if (dialPosition == 100) {
                zeroes++;
                dialPosition = 0;
            }
        }
    }
}
