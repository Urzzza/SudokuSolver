package sudoku;

import java.util.LinkedList;

class Field {
    static int sideSize = 9;
    private LinkedList<LinkedList<Cell>> cells;

    Field(Integer[] inputTable)
    {
        if (inputTable == null || inputTable.length != 81)
        {
            throw new IllegalArgumentException("Input data has unknown format");
        }

        cells = new LinkedList<>();
        for(var row = 0; row < sideSize; row++)
        {
            cells.add(new LinkedList<>());
            for(var column = 0; column < sideSize; column++)
            {
                cells.getLast().add(new Cell(row, column, inputTable[row * sideSize + column]));
            }
        }
    }

    Cell GetFirst()
    {
        return cells.getFirst().getFirst();
    }

    Cell GetNextCell(int currentIndex)
    {
        int row = currentIndex / sideSize;
        int column = currentIndex % sideSize + 1;
        if (column == 9)
        {
            row++;
            column = 0;
            if (row == 9)
                return null;
        }

        return cells.get(row).get(column);
    }

    Cell GetPreviousCellAndClearCurrent(int currentIndex)
    {
        if(currentIndex == 0)
            return this.GetFirst();

        int row = currentIndex / sideSize;
        int column = currentIndex % sideSize;

        var current = cells.get(row).get(column);
        if (!current.IsPredefined)
            current.Value = 0;

        column--;

        if (column == -1)
        {
            row--;
            column = 8;
        }

        return cells.get(row).get(column);
    }

    boolean IsValueValid(Cell cell)
    {
        // process row
        for(var column = 0; column < sideSize; column++)
        {
            if(column == cell.Column)
                continue;

            var otherCell = this.cells.get(cell.Row).get(column);
            if (otherCell.HasValue() && otherCell.Value == cell.Value)
                return false;
        }

        // process column
        for(var row = 0; row < sideSize; row++)
        {
            if(row == cell.Row)
                continue;

            var otherCell = this.cells.get(row).get(cell.Column);
            if (otherCell.HasValue() && otherCell.Value == cell.Value)
                return false;
        }

        // process group
        var rowStartIndex = cell.Row - (cell.Row % 3);
        var colStartIndex = cell.Column - (cell.Column % 3);

        for(var row = rowStartIndex; row < rowStartIndex + 3; row++)
        {
            for(var column = colStartIndex; column < colStartIndex + 3; column++)
            {
                if (row == cell.Row && column == cell.Column)
                    continue;

                var otherCell = this.cells.get(row).get(column);
                if (otherCell.HasValue() && otherCell.Value == cell.Value)
                    return false;
            }
        }

        return true;
    }

    void PrintState()
    {
        for (var cellRows : this.cells)
        {
            for (var cell : cellRows)
                System.out.print(cell.Value + " ");

            System.out.print(System.lineSeparator());
        }
    }

    boolean IsFieldValid() {
        // validate rows
        for (var row = 0; row < sideSize; row++)
        {
            var values = new LinkedList<Integer>();
            for (var column = 0; column < sideSize; column++)
            {
                var cell = this.cells.get(row).get(column);
                values.add(cell.Value);
            }
            if (!this.IsSubsetCorrect(values)) return false;
        }

        // validate columns
        for (var column = 0; column < sideSize; column++)
        {
            var values = new LinkedList<Integer>();
            for (var row = 0; row < sideSize; row++)
            {
                var cell = this.cells.get(row).get(column);
                values.add(cell.Value);
            }
            if (!this.IsSubsetCorrect(values)) return false;
        }

        // validates 3x3 groups
        for (var rowStartIndex = 0; rowStartIndex < sideSize; rowStartIndex += 3)
        {
            for (var colStartIndex = 0; colStartIndex < sideSize; colStartIndex += 3)
            {
                var values = new LinkedList<Integer>();
                for(var row = rowStartIndex; row < rowStartIndex + 3; row++)
                {
                    for(var column = colStartIndex; column < colStartIndex + 3; column++)
                    {
                        var cell = this.cells.get(row).get(column);
                        values.add(cell.Value);
                    }
                }
                if (!this.IsSubsetCorrect(values)) return false;
            }
        }

        return true;
    }

    private boolean IsSubsetCorrect(LinkedList<Integer> data)
    {
        if (data.size() != 9)
            return false;

        for(var i = 1; i <= 9; i++)
        {
            if (!data.contains(i))
            {
                return false;
            }
        }

        return true;
    }
}
