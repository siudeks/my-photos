package net.siudek;

import java.nio.file.Path;

interface IRenameStrategy {
    boolean CanRename(Path file);

    String Rename(Path file);
}
