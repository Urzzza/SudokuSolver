package sudoku;

class Cell {
    int Row;
    int Column;
    int Value;
    boolean IsPredefined;

    Cell(int row, int column, int value)
    {
        this.Row = row;
        this.Column = column;
        this.Value = value;
        this.IsPredefined = value != 0;
    }

    boolean HasValue()
    {
        return this.Value > 0;
    }
}
