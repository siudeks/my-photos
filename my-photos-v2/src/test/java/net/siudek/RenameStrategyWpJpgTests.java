package net.siudek;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RenameStrategyWpJpgTests {
  IRenameStrategy strategy = new RenameStrategyWpJpg();

  @Test
  public void should_rename() {
    // const string fileName = "WP_20000102_name.jPg";
    // strategy
    // .CanRename(fileName)
    // .Should()
    // .BeTrue();
    // strategy
    // .Rename(fileName)
    // .Should()
    // .Be("20000102_name.jPg");
  }

  @Test
  public void should_not_rename_unknown() {
    // strategy
    // .CanRename("not supported naming style.jpg")
    // .Should()
    // .BeFalse();
  }
}
