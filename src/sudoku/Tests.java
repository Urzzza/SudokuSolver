package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class Tests {

    @Test
    void TestSolver_Sample1() {
        try {
            var solver = new SudokuSolver(".\\content\\input1.txt");
            var field = solver.Solve();
            field.PrintState();
            Assertions.assertTrue(field.IsFieldValid());
        }
        catch (IOException e)
        {
            Assertions.fail("input file not found");
        }
    }

    @Test
    void TestSolver_Sample2() {
        try {
            var solver = new SudokuSolver(".\\content\\input2.txt");
            var field = solver.Solve();
            field.PrintState();
            Assertions.assertTrue(field.IsFieldValid());
        }
        catch (IOException e)
        {
            Assertions.fail("input file not found");
        }
    }

    @Test
    void TestSolver_Sample3() {
        try {
            var solver = new SudokuSolver(".\\content\\input3.txt");
            var field = solver.Solve();
            field.PrintState();
            Assertions.assertTrue(field.IsFieldValid());
        }
        catch (IOException e)
        {
            Assertions.fail("input file not found");
        }
    }

    @Test
    void TestSolver_Sample4() {
        try {
            var solver = new SudokuSolver(".\\content\\input4.txt");
            var field = solver.Solve();
            field.PrintState();
            Assertions.assertTrue(field.IsFieldValid());
        }
        catch (IOException e)
        {
            Assertions.fail("input file not found");
        }
    }
}
