package sudoku;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class SudokuSolver {

    private Field field;

    SudokuSolver(String filePath) throws IOException {
        FileReader fr = new FileReader(filePath);
        int i;
        ArrayList<Integer> data = new ArrayList<>();
        while ((i=fr.read()) != -1)
        {
            var value = i - (int)'0';
            if (value >= 0)
                data.add(value);
        }

        this.field = new Field(data.toArray(new Integer[0]));
    }

    Field Solve()
    {
        var cell = field.GetFirst();


        for(int currentIndex = 0; currentIndex < Field.sideSize * Field.sideSize; )
        {
            if (cell.IsPredefined)
            {
                cell = field.GetNextCell(currentIndex);
                currentIndex++;
                continue;
            }

            var valueIsFound = false;
            for (int val = cell.Value + 1; val <= Field.sideSize; val++)
            {
                cell.Value = val;
                if (field.IsValueValid(cell))
                {
                    cell = field.GetNextCell(currentIndex);
                    currentIndex++;
                    valueIsFound = true;
                    break;
                }
            }

            if (valueIsFound)
                continue;

            do{
                cell = field.GetPreviousCellAndClearCurrent(currentIndex);
                currentIndex--;

            } while (cell.IsPredefined);
        }

        return field;
    }
}
