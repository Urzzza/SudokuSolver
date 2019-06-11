package sudoku;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(String.join(" | ", args));
        if (args.length != 1 || !new File(args[0]).isFile())
        {
            System.err.println("Please provide valid file path with input data as argument");
        }

        var solver = new SudokuSolver(args[0]);
        var field = solver.Solve();
        field.PrintState();
        System.out.println("Validation check: " + (field.IsFieldValid() ? "PASS" : "FAIL"));
    }
}
