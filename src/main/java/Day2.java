import java.math.BigInteger;
import java.util.ArrayList;

public class Day2 {
    static BigInteger result = BigInteger.ZERO;
    static ArrayList<Range> ranges = new ArrayList<>();

    public static void part1(String[] args) {
        parseRanges(args[0]);
        for (Range range : ranges) {
            result = result.add(checkRangePt1(range));
        }
        System.out.println(result);
    }

    public static void part2(String[] args) {
        parseRanges(args[0]);
        for (Range range : ranges) {
            result = result.add(checkRangePt2(range));
        }
        System.out.println(result);
    }

    private static void parseRanges(String input) {
        String[] rangesStrings = input.split(",");
        for (String range : rangesStrings) {
            String[] bounds = range.split("-");
            ranges.add(new Range(new BigInteger(bounds[0]), new BigInteger(bounds[1])));
        }
    }

    private static BigInteger checkRangePt1(Range range) {
        BigInteger returnValue = BigInteger.ZERO;
        BigInteger counter = range.low;
        int length = counter.toString().length();
        if (length % 2 == 1) {
            counter = new BigInteger("1" + "0".repeat(length));
        }

        while (counter.compareTo(range.high) < 0) {
            if (checkValuePt1(counter)) {
                returnValue = returnValue.add(counter);
            }
            counter = counter.add(BigInteger.ONE);
        }
        return returnValue;
    }

    private static boolean checkValuePt1(BigInteger value) {
        String string = value.toString();
        int half = string.length() / 2;
        String first = string.substring(0, half);
        String second = string.substring(half);
        return  first.equals(second);
    }

    private static BigInteger checkRangePt2(Range range) {
        BigInteger returnValue = BigInteger.ZERO;
        BigInteger counter = range.low;

        while (counter.compareTo(range.high) < 0) {
            if (checkValuePt2(counter)) {
                returnValue = returnValue.add(counter);
            }
            counter = counter.add(BigInteger.ONE);
        }
        return returnValue;
    }

    private static boolean checkValuePt2(BigInteger value) {
        String string = value.toString();
        String doubled = string + string;
        String trimmed = doubled.substring(1, doubled.length() - 1);
        return  trimmed.contains(string);
    }
}

class Range {
    public final BigInteger low;
    public final BigInteger high;

    Range(BigInteger low, BigInteger high) {
        this.low = low;
        this.high = high;
    }
}
