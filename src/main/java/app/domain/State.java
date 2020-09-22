package app.domain;

public enum State {
    DEAD,
    BORN,
    ALIVE,
    DIES;

    /**
     * Defines certain Cell future condition according to its current condition
     * and amount of neighbour alive cells
     * @param neighbors - amount of alive cells around
     * @return - defined state
     */
    public State getFutureState(int neighbors) {
        switch (this) {
            case DEAD:
                return (neighbors == 3) ?  BORN : DEAD;
            case ALIVE:
                return (neighbors < 2 || neighbors > 3) ? DIES : ALIVE;
            default:
                return this;
        }
    }

    /**
     * Provides a new Current State depending on defined future State
     * @return - new defined Current state
     */
    public State getCurrentState() {
        switch (this) {
            case DIES: 
                return DEAD;
            case BORN:
                return ALIVE;
            default:
                return this;
        }
    }
}
