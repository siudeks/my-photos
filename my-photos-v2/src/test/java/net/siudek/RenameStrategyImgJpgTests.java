package net.siudek;

import java.nio.file.Path;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RenameStrategyImgJpgTests {
  IRenameStrategy strategy = new RenameStrategyImgJpg();

  @Test
  public void should_rename(@TempDir Path root) {
    // var fileName = "iMg_20000102_name.jPg";

    // strategy.CanRename(file) .CanRename(fileName).Should().BeTrue();
    // strategy.Rename(fileName).Should().Be("20000102_name.jPg");
  }
}
