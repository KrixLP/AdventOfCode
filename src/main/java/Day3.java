import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Day3 {
    static long joltsResult = 0;

    public static void part1(String[] args) {
        for (String arg : args) {
            int[] levels = parseInput(arg);

            String result = getMaxSubstring(levels, 2);
            joltsResult += Long.parseLong(result);
        }
        System.out.println(joltsResult);
    }

    public static void part2(String[] args) {
        for (String arg : args) {
            int[] levels = parseInput(arg);

            String result = getMaxSubstring(levels, 12);
            joltsResult += Long.parseLong(result);
        }
        System.out.println(joltsResult);
    }

    private static int[] parseInput(String arg) {
        int[] levels = new int[arg.length()];
        for (int j = 0; j < levels.length; j++) {
            levels[j] = Integer.parseInt(String.valueOf(arg.charAt(j)));
        }
        return levels;
    }

    private static String getMaxSubstring(int[] levels, int size) {
        int firstDigit = maxInt(levels, size);
        int firstDigitPosition;
        String substring;
        if (size == 1) return String.valueOf(firstDigit);

        firstDigitPosition = getFirstDigitPosition(levels, firstDigit);
        substring = generateMaxSubstring(levels, size, firstDigitPosition);
        return firstDigit + substring;
    }

    private static int getFirstDigitPosition(int[] levels, int firstDigit) {
        return IntStream.range(0, levels.length)
                .filter(i -> levels[i] == firstDigit)
                .findFirst().orElse(-1);
    }

    private static String generateMaxSubstring(int[] levels, int size, int firstDigitPosition) {
        String substring;
        int[] subLevels = Arrays.stream(levels).skip(firstDigitPosition +1).toArray();
        substring = getMaxSubstring(subLevels, size -1);
        return substring;
    }

    private static int maxInt(int[] levels, int size) {
        int maxSize = levels.length - size + 1;
        OptionalInt firstDigit = Arrays.stream(levels).limit(maxSize).max();
        if (firstDigit.isPresent()) {
            return firstDigit.getAsInt();
        } else return 0;
    }
}
