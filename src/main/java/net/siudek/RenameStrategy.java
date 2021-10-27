package net.siudek;

import java.nio.file.Path;

interface RenameStrategy {
  boolean canRename(Path file);

  String rename(Path file);
}
