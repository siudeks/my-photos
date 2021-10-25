package net.siudek;

import java.nio.file.Path;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.io.TempDir;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RenameStrategyImgPngTests {

  IRenameStrategy strategy = new RenameStrategyImgPng();

  public void should_rename(@TempDir Path root) {
    // const string fileName = "iMg_20000102_name.pNg";
    // strategy
    // .CanRename(fileName)
    // .Should()
    // .BeTrue();
    // strategy
    // .Rename(fileName)
    // .Should()
    // .Be("20000102_name.pNg");
  }
}
