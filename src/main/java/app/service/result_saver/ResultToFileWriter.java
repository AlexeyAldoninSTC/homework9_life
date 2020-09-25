package app.service.result_saver;

import app.domain.Cell;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResultToFileWriter implements ResultWriter {

    private final String targetPath;

    public ResultToFileWriter(String targetPath) {
        this.targetPath = "src/main/resources/" + targetPath;
    }

    @Override
    public void saveResult(Cell[][] cells) {
        StringBuilder builder = new StringBuilder();
        for (Cell[] cell : cells) {
            for (Cell cell1 : cell) {
                builder.append(cell1);
            }
            builder.append("\n");
        }
        try {
            Files.write(Paths.get(targetPath), builder.toString().getBytes());
            System.out.println("Итоговое состояние игрового поля успешно сохранено в файл [" + targetPath + "]");
        } catch (IOException e) {
            System.out.println("Сбой при записи результата в файл: [" + targetPath + "]");
        }
    }
}
