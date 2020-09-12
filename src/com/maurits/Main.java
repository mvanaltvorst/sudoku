package com.maurits;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Sudoku s = new Sudoku();
        s.solve();
        System.out.println(s);
    }
}


