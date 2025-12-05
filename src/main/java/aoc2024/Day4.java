package aoc2024;

public class Day4 {
    private static int xmases = 0;
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
        checkForXmases(grid, lastRow, lastCol);
        System.out.println(xmases);
    }

    public static void part2(String[] args) {
        int[][] grid = new int[args[0].length()][args.length];
        int lastRow = grid.length - 1;
        int lastCol = grid[0].length - 1;

        parseInput(args, grid);
        checkForCrossMASes(grid, lastRow, lastCol);
        System.out.println(xmases);
        System.out.println(lastRow);
        System.out.println(lastCol);
    }

    private static void checkForXmases(int[][] grid, int lastRow, int lastCol) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    xmases += checkNeighbours(grid, lastRow, lastCol, i, j);

                }
            }
        }
    }

    private static int checkNeighbours(int[][] grid, int lastRow, int lastCol, int i, int j) {
        int xmases = 0;

        for (int[] d : directions) {
            int sizeX = i + d[0] * 3;
            int sizeY = j + d[1] * 3;
            if (!isInBounds(lastRow, lastCol, sizeX, sizeY)) {
                continue;
            }
            int[] positions = calculateArray(grid, i, j, d);
            if (positions[0] == 2 && positions[1] == 3 && positions[2] == 4) {
                xmases++;
            }
        }

        return xmases;
    }


    private static int[] calculateArray(int[][] grid, int i, int j, int[] d) {
        int[] returnArray = new int[3];
        for (int k = 1; k < 4; k++) {
            int x = i + d[0] * k;
            int y = j + d[1] * k;
            returnArray[k - 1] = grid[x][y];
        }
        return returnArray;
    }

    private static void checkForCrossMASes(int[][] grid, int lastRow, int lastCol) {
        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[i].length - 1; j++) {
                if (grid[i][j] == 3) {
                    xmases += checkCrosses(grid, lastRow, lastCol, i, j);

                }
            }
        }
    }

    private static int checkCrosses(int[][] grid, int lastRow, int lastCol, int i, int j) {

        int m1a = i + 1;
        int m2a = j + 1;
        int s1a = i - 1;
        int s2a = j - 1;

        if (isInBounds(lastRow, lastCol, m1a, m2a) && isInBounds(lastRow, lastCol, s1a, s2a)) {
            if (checkForXMas(grid, i, j)) {
                return 1;
            }
        }
        return 0;
    }

    private static boolean checkForXMas(int[][] grid, int i, int j) {
        int xPos = i + 1;
        int yPos = j + 1;
        int xNeg = i - 1;
        int yNeg = j - 1;

        return checkLegOfX(grid, xPos, yNeg, xNeg, yPos) && checkLegOfX(grid, xPos, yPos, xNeg, yNeg);


    }

    private static boolean checkLegOfX(int[][] grid, int x1, int y1, int x2, int y2) {
        return grid[x1][y1] + grid[x2][y2] == 6 && grid[x1][y1] != 3;
    }

    private static boolean isInBounds(int lastRow, int lastCol, int r, int c) {
        return r >= 0 && r <= lastRow && c >= 0 && c <= lastCol;
    }

    private static void parseInput(String[] args, int[][] grid) {
        for (int i = 0; i < args.length; i++) {
            for (int j = 0; j < args[i].length(); j++) {
                if (args[i].charAt(j) == 'X') {
                    grid[i][j] = 1;
                } else if (args[i].charAt(j) == 'M') {
                    grid[i][j] = 2;
                } else if (args[i].charAt(j) == 'A') {
                    grid[i][j] = 3;
                } else if (args[i].charAt(j) == 'S') {
                    grid[i][j] = 4;
                }
            }
        }
    }
}
