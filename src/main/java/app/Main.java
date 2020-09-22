package app;

import app.domain.Cell;
import app.service.CellFillerFabric;
import app.service.PropertiesReader;
import app.service.field_filler.ArrayFiller;
import app.service.live_filler.CellsFiller;
import app.service.result_saver.ResultToFileWriter;
import app.service.result_saver.ResultWriter;

import java.util.Arrays;
import java.util.Properties;

public class Main {

    private static final String PROPERTIES_PATH = "src/main/resources/input.properties";

    public static void main(String[] args) throws InterruptedException {

        final Properties properties = new PropertiesReader().getProperties(PROPERTIES_PATH);

        ArrayFiller filler = new ArrayFiller();
        Cell[][] cells = filler.generateField(
                Integer.parseInt(properties.getProperty("fieldHeight")),
                Integer.parseInt(properties.getProperty("fieldWidth")));

        CellFillerFabric fillerFabric = new CellFillerFabric();
        CellsFiller cellsFiller = fillerFabric.getFiller(
                Integer.parseInt(properties.getProperty("lifeInitialOption")),
                Integer.parseInt(properties.getProperty("initialAliveCellsNumber")));

        cellsFiller.setCells(cells);
        printCells(cells);

        int moves = Integer.parseInt(
                properties.getProperty("moves"));

        for (int i = 0; i < moves; i++) {
            Thread.sleep(1000);
            Cell[][] generated = filler.reBuildField(cells);
            if (Arrays.deepEquals(generated, cells)) {
                System.out.println("Игра окончена ввиду отсутстввя прогресса");
                break;
            }
            cells = generated;
            printCells(cells);
        }
        ResultWriter writer = new ResultToFileWriter(
                properties.getProperty("outputPath"));
        writer.saveResult(cells);
    }

    /**
     * Prints out certain two-dimensional array condition
     * @param cells - two-dimensional array to be displayed
     */
    private static void printCells(Cell[][] cells) {
        for (Cell[] cell : cells) {
            for (Cell value : cell) {
                System.out.print(value);
            }
            System.out.println("");
        }
        System.out.println("\n====================\n");
    }
}
