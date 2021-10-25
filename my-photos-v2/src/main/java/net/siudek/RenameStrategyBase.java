package net.siudek;

import java.nio.file.Path;

public abstract class RenameStrategyBase implements RenameStrategy {

  @Override
  public abstract boolean canRename(Path file);

  @Override
  public String rename(Path file) {
    var asFile = file.toFile();
    var fileName = asFile.getName();
    return generateNewFileName(fileName);
  }

  protected abstract String generateNewFileName(String current);
}
