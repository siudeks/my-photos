package net.siudek;


import java.io.File;
import java.nio.file.Path;
import java.util.List;

import com.google.common.io.Files;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import lombok.SneakyThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public final class FoldersTests extends TestsBase {

  @Test
  public void should_process_empty_folder(@TempDir Path root) {
    Assertions.assertThat(asTree(root)).isEqualTo(List.of());
  }

  @Test
  @SneakyThrows
  public void should_process_single_file(@TempDir Path root) {
    var newFile = new File(root.toFile(), "file.txt");
    newFile.createNewFile();

    var expected = List.of(
        Item.builder().name("file.txt").build());
    Assertions
      .assertThat(asTree(root))
      .isEqualTo(expected);
  }

  @Test
  @SneakyThrows
  public void should_process_single_nested_file(@TempDir Path root) {
    var newFile = new File(root.toFile(), "1/2/file.txt");
    Files.createParentDirs(newFile);
    newFile.createNewFile();

    var expected = List.of(
        Item.builder()
          .name("1")
          .child(Item.builder()
              .name("2")
              .child(Item.builder().name("file.txt").build())
              .build())
        .build());
    Assertions
      .assertThat(asTree(root))
      .isEqualTo(expected);
  }
}
