package com.maurits;

public final class Sudoku {
    static int SIZE = 9;
    static int SUBGRIDSIZE = 3;
    private int[][] grid;
    Sudoku() {
        this.grid = new int[9][9];
    }

    private boolean isInRow(int r, int value) {
        for (int c = 0; c < SIZE; c++) {
            if (grid[r][c] == value) return true;
        }
        return false;
    }

    private boolean isInColumn(int c, int value) {
        for (int r = 0; r < SIZE; r++) {
            if (grid[r][c] == value) return true;
        }
        return false;
    }

    private boolean isInSubgrid(int r, int c, int value) {
        int gr, gc;
        gr = r/SUBGRIDSIZE;
        gc = c/SUBGRIDSIZE;
        for (int nr = gr*SUBGRIDSIZE; nr < (gr+1)*SUBGRIDSIZE; nr++) {
            for (int nc = gc * SUBGRIDSIZE; nc < (gc + 1) * SUBGRIDSIZE; nc++) {
                if (grid[nr][nc] == value) return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < SIZE; r++) {
            if (r != 0 && r % SUBGRIDSIZE == 0) {
                for (int i = 1; i < SIZE; i++) {
                    if (i % SUBGRIDSIZE == 0) sb.append("-+");
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

    /**
     * @return true if solve was success
     */
    public boolean solve() {
        for (int nr = 0; nr < SIZE; nr++) {
            for (int nc = 0; nc < SIZE; nc++) {
                if (grid[nr][nc] == 0) {
                    for (int nv = 1; nv <= 9; nv++) {
                        if (isValid(nr, nc, nv)) {
                            setValue(nr, nc, nv);
                            if (solve()) return true;
                            setValue(nr, nc, 0);
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if a value fits.
     * @return true if value fits, false if value doesn't fit
     */
    private boolean isValid(int r, int c, int value) {
        return !isInRow(r, value) && !isInColumn(c, value) && !isInSubgrid(r, c, value);
    }

    public void setValue(int r, int c, int value) {
        grid[r][c] = value;
    }
}
