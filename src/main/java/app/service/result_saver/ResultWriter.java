package app.service.result_saver;

import app.domain.Cell;

public interface ResultWriter {
    void saveResult(Cell[][] cells);
}
