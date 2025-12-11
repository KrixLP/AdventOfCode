package aoc2025;

public class Day7 {
    private static long splits = 0;

    public static void main(String[] args) {
        long[][] grid = new long[args.length][args[0].length()];
        setInitialGridLONG(args, grid);
        calculateGridPT1(grid);
        System.out.println(splits);
    }

    public static void part2(String[] args) {
        long[][] grid = new long[args.length][args[0].length()];
        setInitialGridLONG(args, grid);
        calculateGridPT2(grid);
        sumUpLastRowLONG(grid);
        System.out.println(splits);
    }

    private static void setInitialGridLONG(String[] args, long[][] grid) {
        for (int i = 0; i < args.length; i++) {
            for (int j = 0; j < args[i].length(); j++) {
                switch (args[i].charAt(j)) {
                    case 'S' -> grid[i][j] = 1;
                    case '^' -> grid[i][j] = -1;
                    default -> grid[i][j] = 0;
                }
            }
        }
    }

    private static void calculateGridPT1(long[][] grid) {
        for (int i = 0; i < grid.length - 1; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                propagateBeamPT1(grid, i, j);
            }
        }
    }

    private static void calculateGridPT2(long[][] grid) {
        for (int i = 0; i < grid.length - 1; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                propagateBeamPT2(grid, i, j);
            }
        }
    }

    private static void propagateBeamPT1(long[][] grid, int i, int j) {
        if (grid[i][j] < 1) {
            return;
        }
        int nextRow = i + 1;
        if (grid[nextRow][j] == -1) {
            grid[nextRow][j - 1] += grid[i][j];
            grid[nextRow][j + 1] += grid[i][j];
            splits++;
        } else {
            grid[nextRow][j] += grid[i][j];
        }
    }

    private static void propagateBeamPT2(long[][] grid, int i, int j) {
        if (grid[i][j] == -1) {
            return;
        }
        int nextRow = i + 1;
        if (grid[nextRow][j] == -1) {
            grid[nextRow][j - 1] += grid[i][j];
            grid[nextRow][j + 1] += grid[i][j];
        } else {
            grid[nextRow][j] += grid[i][j];
        }
    }

    private static void sumUpLastRowLONG(long[][] grid) {
        for (int i = 0; i < grid[grid.length - 1].length; i++) {
            splits += grid[grid.length - 1][i];
        }
    }
}
