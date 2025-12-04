package aoc2025;

public class Day4 {
    private static int accessibleRolls = 0;
    private static int removedRolls = 0;
    private static final int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };

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
        accessibleRolls = 1;
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
                    if (neighbours < 4) {
                        accessibleRolls++;
                    }
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

        for (int[] d : directions) {
            int r = i + d[0];
            int c = j + d[1];

            if (isInBounds(lastRow, lastCol, r, c)) {
                neighbours += grid[r][c];
            }
        }

        return neighbours;
    }

    private static boolean isInBounds(int lastRow, int lastCol, int r, int c) {
        return r >= 0 && r <= lastRow && c >= 0 && c <= lastCol;
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
