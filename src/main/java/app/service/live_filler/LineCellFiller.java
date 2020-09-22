package app.service.live_filler;

import app.domain.Cell;
import app.domain.State;

public class LineCellFiller implements CellsFiller {

    private final int length;

    public LineCellFiller(int length) {
        this.length = length;
    }

    /**
     * Fills array of cells with live cells as horizontal line with 'length'
     * @param cells - array to be filled
     */
    @Override
    public void setCells(Cell[][] cells) {
        if (cells.length < length || cells[0].length < length) {
            throw new IndexOutOfBoundsException("Для генерации линии живых клеток " +
                    "размер поля должен быть не меньше, " +
                    "чем длина линии.");
        }
        int row = (cells.length / 2);
        int firstIndex = (cells[0].length / 2) - (length / 2);
        for (int i = 0; i < length; i++) {
            cells[row][firstIndex + i].setCurrentCondition(State.ALIVE);
        }
    }
}
