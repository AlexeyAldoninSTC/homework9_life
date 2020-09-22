package app.service;

import app.service.live_filler.CellsFiller;
import app.service.live_filler.GliderCellsFiller;
import app.service.live_filler.LineCellFiller;
import app.service.live_filler.RandomCellsFiller;

public class CellFillerFabric {

    /**
     * Provides certain CellsFiller implementation according to provided params
     * @param option - provided number of filler implementation
     * @param quantity - number of life cells at the game start
     * @return - new CellsFiller implementation instance
     */
    public CellsFiller getFiller(int option, int quantity) {
        switch (option) {
            case 1:
                return new LineCellFiller(quantity);
            case 2:
                return new RandomCellsFiller(quantity);
            default:
                return new GliderCellsFiller();
        }
    }
}
