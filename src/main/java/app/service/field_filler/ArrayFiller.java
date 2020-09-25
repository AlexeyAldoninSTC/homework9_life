package app.service.field_filler;

import app.domain.Cell;
import app.domain.State;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ArrayFiller {

    ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * Fills a two-dimensional array af Cells according to original array each cell state
     * @param cells - original array filled with dead and alive Cells in current stem of the game
     * @return - updated array;
     */
    public Cell[][] reBuildField(Cell[][] cells) {
        Cell[][] result = generateField(cells[0].length, cells.length);
        for (int i = 0; i < cells.length; i++) {
            final int lineIndex = i;
            threadPool.execute(new LineProcessor(cells, result, lineIndex));
            }
        try {
            threadPool.awaitTermination(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed during cells field re-building");
        }
        return result;
    }

    /**
     * Generates a two-dimensional array filling it with new dead cells
     * @param width - length of inner arrays
     * @param height - length of main array
     * @return - generated array
     */
    public Cell[][] generateField(int width, int height) {
        Cell[][] cells = new Cell[height][width];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(j, i);
            }
        }
        return cells;
    }

    public boolean hasLife(Cell[][] cells) {
        for (Cell[] cellArray : cells) {
            for (Cell cell : cellArray) {
                if (cell.getCurrentCondition() == State.ALIVE){
                    return true;
                }
            }
        }
        return false;
    }
    public void terminate() {
        if (!threadPool.isShutdown()) {
            threadPool.shutdown();
        }
    }
}
