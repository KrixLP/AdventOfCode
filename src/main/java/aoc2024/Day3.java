package aoc2024;

import java.util.ArrayList;

public class Day3 {
    private static final String REGEX_MULT = "^mul\\(\\d{1,3},\\d{1,3}\\).*";
    private static final String REGEX_DO = "^do\\(\\).*";
    private static final String REGEX_DONT = "^don't\\(\\).*";
    private static int result = 0;
    private static boolean dewit = true;

    public static void part1(String[] args) {
        ArrayList<String> muList = new ArrayList<>();
        parseInput(args, muList, Day3::inputParserPt1);
        parseAndExecuteMultiplications(muList);
    }
    public static void part2(String[] args) {
        ArrayList<String> muList = new ArrayList<>();
        parseInput(args, muList, Day3::inputParserPt2);
        parseAndExecuteMultiplications(muList);
    }

    private static void parseInput(String[] args, ArrayList<String> muList, Inputparser parser) {
        String[] input = args.clone();
        for (String arg : input) {
            while (!arg.isBlank()) {
                parser.parse(arg, muList);

                arg = arg.substring(1);
            }
        }
    }

    private static void inputParserPt1(String arg, ArrayList<String> muList) {
        if (arg.matches(REGEX_MULT)) {
            muList.add(arg.substring(0, Math.min(arg.length(), 11)));
        }
    }

    private static void inputParserPt2(String arg, ArrayList<String> muList) {
        if (arg.matches(REGEX_MULT) && dewit) {
            muList.add(arg.substring(0, Math.min(arg.length(), 11)));
        }

        if (arg.matches(REGEX_DO)) dewit = true;
        if (arg.matches(REGEX_DONT)) dewit = false;
    }

    private static void parseAndExecuteMultiplications(ArrayList<String> muList) {
        for (String arg : muList) {
            arg = arg.substring(4);
            String[] temp = arg.split(",");
            for (int i = 0; i < temp.length; i++) {
                temp[i] = temp[i].replaceAll("\\D", "");
            }
            int subResult = Integer.parseInt(temp[0]) * Integer.parseInt(temp[1]);
            result += subResult;
        }
        System.out.println(result);
    }

    interface Inputparser {
        void parse(String input, ArrayList<String> muList);
    }
}
