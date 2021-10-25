package net.siudek;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
final class RenameStrategyWpMp4Tests {
  IRenameStrategy strategy = new RenameStrategyWpMp4();

  @Test
  public void should_rename() {
    // const string fileName = "WP_20000102_name.mP4";
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
