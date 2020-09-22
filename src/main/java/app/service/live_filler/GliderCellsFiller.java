package app.service.live_filler;

import app.domain.Cell;
import app.domain.State;

public class GliderCellsFiller implements CellsFiller {

    /**
     * Sets alive cells according to Glider figure
     * @param cells - array of cells
     */
    @Override
    public void setCells(Cell[][] cells) {
        if (cells.length < 3 || cells[0].length < 3) {
            throw new IndexOutOfBoundsException("Заданный размер поля не должен быть менее, чем 3х3");
        }
        cells[0][0].setCurrentCondition(State.ALIVE);
        cells[1][1].setCurrentCondition(State.ALIVE);
        cells[1][2].setCurrentCondition(State.ALIVE);
        cells[2][0].setCurrentCondition(State.ALIVE);
        cells[2][1].setCurrentCondition(State.ALIVE);

    }
}
