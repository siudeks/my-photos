package net.siudek;

import java.nio.file.Path;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
final class RenameStrategyVidMp4Tests {

  IRenameStrategy strategy = new RenameStrategyVidMp4();

  @Test
  public void shouldRename(@TempDir Path root) {
    // const string fileName = "vId_20000102_name.mP4";
    // strategy
    // .CanRename(fileName)
    // .Should()
    // .BeTrue();
    // strategy
    // .Rename(fileName)
    // .Should()
    // .Be("20000102_name.mP4");
  }
}
