package aoc2025;

public class Day4 {
    private static int accessibleRolls = 0;
    private static int removedRolls = 0;

    public static void part1(String[] args) {
        int[][] grid = new int[args[0].length()][args.length];
        int lastRow = grid.length - 1;
        int lastCol = grid[0].length - 1;

        parseInput(args, grid);
        checkAccessibleRolls(grid, lastRow, lastCol);
        System.out.println(accessibleRolls);
        System.out.println(lastRow);
        System.out.println(lastCol);
    }

    public static void part2(String[] args) {
        int[][] grid = new int[args[0].length()][args.length];
        int lastRow = grid.length - 1;
        int lastCol = grid[0].length - 1;

        parseInput(args, grid);
        accessibleRolls =1;
        while (accessibleRolls > 0) {
            accessibleRolls = 0;
            checkAndRemoveAccessibleRolls(grid, lastRow, lastCol);
        }
        System.out.println(removedRolls);
        System.out.println(lastRow);
        System.out.println(lastCol);
    }

    private static void checkAccessibleRolls(int[][] grid, int lastRow, int lastCol) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    int neighbours = checkNeighbours(grid, lastRow, lastCol, i, j);
                    if (neighbours < 4) accessibleRolls++;
                }
            }
        }
    }

    private static void checkAndRemoveAccessibleRolls(int[][] grid, int lastRow, int lastCol) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    int neighbours = checkNeighbours(grid, lastRow, lastCol, i, j);
                    if (neighbours < 4) {
                        accessibleRolls++;
                        removedRolls++;
                        grid[i][j] = 0;
                    }
                }
            }
        }
    }

    private static int checkNeighbours(int[][] grid, int lastRow, int lastCol, int i, int j) {
        int neighbours = 0;

        if (i < lastRow) {
            neighbours += grid[i + 1][j];
            if (j < lastCol) neighbours += grid[i + 1][j + 1];
            if (j > 0) neighbours += grid[i + 1][j - 1];
        }
        if (i > 0) {
            neighbours += grid[i - 1][j];
            if (j > 0) neighbours += grid[i - 1][j - 1];
            if (j < lastCol) neighbours += grid[i - 1][j + 1];
        }
        if (j > 0) neighbours += grid[i][j - 1];
        if (j < lastCol) neighbours += grid[i][j + 1];
        return neighbours;
    }

    private static void parseInput(String[] args, int[][] grid) {
        for (int i = 0; i < args.length; i++) {
            for (int j = 0; j < args[i].length(); j++) {
                if (args[i].charAt(j) == '@') {
                    grid[i][j] = 1;
                } else if (args[i].charAt(j) == '.') {
                    grid[i][j] = 0;
                }
            }
        }
    }
}
