package app.domain;

public enum State {
    DEAD,
    ALIVE;

    /**
     * Defines certain Cell future condition according to its current condition
     * and amount of neighbour alive cells
     * @param neighbors - amount of alive cells around
     * @return - defined state
     */
    public State getFutureState(int neighbors) {
        switch (this) {
            case DEAD:
                return (neighbors == 3) ?  ALIVE : DEAD;
            case ALIVE:
                return (neighbors < 2 || neighbors > 3) ? DEAD : ALIVE;
            default:
                return this;
        }
    }

}
