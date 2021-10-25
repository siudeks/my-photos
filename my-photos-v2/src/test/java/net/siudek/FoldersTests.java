package net.siudek;


import java.io.File;
import java.nio.file.Path;

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
    Assertions.assertThat(asTree(root)).isEqualTo(Items.builder().build());
  }

  @Test
  @SneakyThrows
  public void should_process_single_file(@TempDir Path root) {
    var newFile = new File(root.toFile(), "file.txt");
    newFile.createNewFile();

    var expected = Items.builder()
        .child(Item.builder().name("file.txt").build())
        .build();
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

    var expected = Items.builder()
        .child(Item.builder()
          .name("1")
          .children(Items.builder()
            .child(Item.builder()
              .name("2")
              .children(Items.builder()
                .child(Item.builder().name("file.txt").build())
                .build())
              .build())
            .build())
          .build())
        .build();
    Assertions
      .assertThat(asTree(root))
      .isEqualTo(expected);
  }
}
