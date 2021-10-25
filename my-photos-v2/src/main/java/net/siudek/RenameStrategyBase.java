package net.siudek;

import java.io.File;
import java.nio.file.Path;

public abstract class RenameStrategyBase implements IRenameStrategy {

    @Override
    public abstract boolean CanRename(Path file);

    @Override
    public String Rename(Path file) {
        var asFile = file.toFile();
        var fileName = asFile.getName();
        var newFileName = generateNewFileName(fileName);
        var parentFile = asFile.getParentFile();
        var newFile = new File(parentFile, newFileName);
        return newFile.getAbsolutePath();
    }

    protected abstract String generateNewFileName(String current);
}
