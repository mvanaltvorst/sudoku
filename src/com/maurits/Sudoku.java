package com.maurits;


public final class Sudoku {
    static int SIZE = 9;
    static int SUBGRIDSIZE = 3;
    private int[][] grid;
    Sudoku() {
        this.grid = new int[9][9];
    }

    private static boolean checkParams(int r, int c, int value) {
        return value >= 1 && value <= 9 && r >= 0 && r < SIZE && c >= 0 && c < SIZE;
    }

    public boolean isSolved() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (grid[r][c] == 0) return false;
            }
        }
        return true;
    }

    public void solve() {
        boolean success = dfs();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < SIZE; r++) {
            if (r != 0 && r % SUBGRIDSIZE == 0) {
                for (int i = 0; i < SIZE; i++) {
                    if (i != 0 && i % SUBGRIDSIZE == 0) sb.append('+');
                    else sb.append('-');
                }
                sb.append("-\n");
            }
            for (int c = 0; c < SIZE; c++) {
                if (c != 0 && c % SUBGRIDSIZE == 0) sb.append('|');
                if (grid[r][c] == 0) sb.append('#');
                else sb.append(grid[r][c]);
            }
            if (r + 1 != SIZE) sb.append('\n');
        }
        return sb.toString();
    }

    private boolean dfs() {
        boolean isSolved = true;
        for (int nr = 0; nr < SIZE; nr++) {
            for (int nc = 0; nc < SIZE; nc++) {
                if (grid[nr][nc] == 0) {
                    isSolved = false;
                    for (int nv = 1; nv <= 9; nv++) {
                        if (checkValue(nr, nc, nv)) {
                            setValue(nr, nc, nv);
                            if (dfs()) return true;
                            setValue(nr, nc, 0);
                        }
                    }
                }
            }
        }
        return isSolved;
    }

    /**
     * Checks if a value fits. Ignores the value on the current coordinate.
     * @return true if value fits, false if value doesn't fit
     */
    public boolean checkValue(int r, int c, int value) {
        // check row
        for (int nc = 0; nc < SIZE; nc++) {
            if (nc == c) continue;
            if (grid[r][nc] == value) return false;
        }
        // check column
        for (int nr = 0; nr < SIZE; nr++) {
            if (nr == r) continue;
            if (grid[nr][c] == value) return false;
        }
        // check subgrid
        int gr, gc;
        gr = r/SUBGRIDSIZE;
        gc = c/SUBGRIDSIZE;
        for (int nr = gr*SUBGRIDSIZE; nr < (gr+1)*SUBGRIDSIZE; nr++) {
            for (int nc = gc * SUBGRIDSIZE; nc < (gc + 1) * SUBGRIDSIZE; nc++) {
                if (nr == r && nc == c) continue;
                if (grid[nr][nc] == value) return false;
            }
        }
        return true;
    }

    public void setValue(int r, int c, int value) {
        grid[r][c] = value;
    }
}
