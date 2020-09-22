package app.domain;

import java.io.Serializable;
import java.util.Objects;

public class Cell implements Serializable {

    private final int xPoint;
    private final int yPoint;

    private State currentState;
    private State futureState;

    private int neighbors = 0;

    public Cell(int xPoint, int yPoint) {
        this.xPoint = xPoint;
        this.yPoint = yPoint;
        currentState = State.DEAD;
        futureState = State.DEAD;
    }

    /**
     * Checks if curtain cell current state is ALIVE
     * @return 'true' if cell is alive, else - false
     */
    boolean isAlive() {
        return currentState == State.ALIVE;
    }

    /**
     * Counts all alive cells around the curtain cell
     * @param field - two-dimensional array of Cells
     * @return - number of neighbour alive cells
     */
    public int countNeighbors(Cell[][] field) {
        if (field.length == 0 || field[0].length == 0) {
            return 0;
        }
        int yLength = field.length;
        int xLength = field[0].length;
        int count = 0;
        for (int y = yPoint - 1; y <= yPoint + 1; y++) {
            for (int x = xPoint - 1; x <= xPoint + 1; x++) {
                if (pointIsInsideField(yLength, xLength, x, y) &&
                        field[y][x].isAlive()) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Checks if current cell is inside field and its coordinates do not match some specific point
     * @param yLength - field vertical size
     * @param xLength - field horizontal size
     * @param x - coordinate on the abscissa
     * @param y - coordinate on the ordinate
     * @return - 'true' if current cell position meets all the requirements
     */
    private boolean pointIsInsideField(int yLength, int xLength, int x, int y) {
        return ((x >= 0 && x < xLength) &&
                (y >= 0 && y < yLength)) &&
                (x != this.xPoint || y != this.yPoint);
    }

    public State getCurrentCondition() {
        return currentState;
    }

    public void setCurrentCondition(State currentState) {
        this.currentState = currentState;
    }

    public State getFutureCondition() {
        return futureState;
    }

    public void setFutureCondition() {
        futureState = currentState.getFutureState(neighbors);
    }

    public void setNeighbors(Cell[][] cells) {
        this.neighbors = countNeighbors(cells);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return currentState == cell.currentState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentState);
    }

    @Override
    public String toString() {
        switch (currentState) {
            case DEAD: return "|_";
            case ALIVE: return "<>";
            default: return " ";
        }
    }
}
