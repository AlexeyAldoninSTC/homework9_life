package app.service.live_filler;

import app.domain.Cell;
import app.domain.State;

import java.util.Random;

public class RandomCellsFiller implements CellsFiller {

    private final int quantity;

    public RandomCellsFiller(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Fills a two-dimensional array of Cells with life within smallest square
     * @param cells - array to be filled
     */
    @Override
    public void setCells(Cell[][] cells) {
        int squareSide = (int) Math.ceil(Math.sqrt(quantity));
        if (squareSide >= cells.length) {
            throw new IndexOutOfBoundsException("Начальное количество живых клеток несовместимо с размером поля");
        }
        int aliveCount = 0;
        Random random = new Random();
        int figureInitIndex = (cells.length + squareSide) / 2 - 1;
        while (aliveCount != quantity) {
            for (int i = figureInitIndex; i < figureInitIndex + squareSide; i++) {
                int innerIndex = (cells[i].length - squareSide) / 2 - 1;
                for (int j = innerIndex; j < innerIndex + squareSide; j++) {
                    if (random.nextBoolean() &&
                            cells[i][j].getCurrentCondition() != State.ALIVE) {
                        cells[i][j].setCurrentCondition(State.ALIVE);
                        aliveCount++;
                    }
                }
            }
        }
    }
}
