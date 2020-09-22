package app.service.result_saver;

import app.domain.Cell;

import java.io.*;

public class ResultToFileWriter implements ResultWriter {

    private final String targetPath;

    public ResultToFileWriter(String targetPath) {
        this.targetPath = targetPath;
    }

    @Override
    public void saveResult(Cell[][] cells) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(targetPath))) {
            os.writeObject(cells);
            System.out.println("Итоговое состояние игрового поля успешно сохранено в файл [" + targetPath + "]");
        } catch (IOException e) {
            System.out.println("Сбой при записи результата в файл: [" + targetPath + "]");
        }
    }
}
