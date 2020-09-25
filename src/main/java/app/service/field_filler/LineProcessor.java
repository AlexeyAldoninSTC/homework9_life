package app.service.field_filler;

import app.domain.Cell;

public class LineProcessor implements Runnable {

    private final Cell[][] cells;
    private final Cell[][] result;
    int lineIndex;

    public LineProcessor(Cell[][] cells, Cell[][] result, int lineIndex) {
        this.cells = cells;
        this.result = result;
        this.lineIndex = lineIndex;
    }

    @Override
    public void run() {
        for (int i = 0; i < cells.length; i++) {
            cells[lineIndex][i].setNeighbors(cells);
            cells[lineIndex][i].setFutureCondition();
            result[lineIndex][i].
                    setCurrentCondition(
                            cells[lineIndex][i].
                                    getFutureCondition().
                                    getCurrentState());
        }
    }
}
