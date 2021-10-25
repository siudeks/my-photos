package net.siudek;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import com.google.common.io.Files;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import io.quarkus.test.junit.QuarkusTest;
import lombok.SneakyThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@QuarkusTest
public class ImageNameNormalizerTests extends TestsBase {

  @Inject
  ImageNameNormalizer normalizer;

  @Test
  @SneakyThrows
  public void should_normalize_img_jpg_in_subfolder(@TempDir File root) {
    var fileName = "some-folder/iMg_20000102_name.jPg";
    var newFile = new File(root, fileName);
    Files.createParentDirs(newFile);
    newFile.createNewFile();

    normalizer.run(root.getAbsolutePath());

    var expected = List.of(
        Item.builder()
          .name("some-folder")
          .child(Item.builder()
            .name("20000102_name.jPg")
            .build())
          .build());

    Assertions
      .assertThat(asTree(root))
      .isEqualTo(expected);
  }

  @Test
  @SneakyThrows
  public void should_normalize_img_png(@TempDir File root) {
    var fileName = "iMg_20000102_name.pNg";
    var newFile = new File(root, fileName);
    newFile.createNewFile();

    normalizer.run(root.getAbsolutePath());

    var expected = List.of(
        Item.builder()
          .name("20000102_name.pNg")
          .build());

    Assertions
      .assertThat(asTree(root))
      .isEqualTo(expected);
  }

  @Test
  @SneakyThrows
  public void should_normalize_vid_mp4(@TempDir File root) {
    var fileName = "vId_20000102_name.mP4";
    var newFile = new File(root, fileName);
    newFile.createNewFile();

    normalizer.run(root.getAbsolutePath());

    var expected = List.of(
        Item.builder()
          .name("20000102_name.mP4")
          .build());

    Assertions
      .assertThat(asTree(root))
      .isEqualTo(expected);
  }

  @Test
  @SneakyThrows
  public void should_normalize_wp_jpg(@TempDir File root) {
    var fileName = "WP_20000102_name.jPg";
    var newFile = new File(root, fileName);
    newFile.createNewFile();

    normalizer.run(root.getAbsolutePath());

    var expected = List.of(
        Item.builder()
          .name("20000102_name.jPg")
          .build());

    Assertions
      .assertThat(asTree(root))
      .isEqualTo(expected);
  }

  @Test
  @SneakyThrows
  public void should_normalize_wp_mp4(@TempDir File root) {
    var fileName = "WP_20000102_name.mP4";
    var newFile = new File(root, fileName);
    newFile.createNewFile();

    normalizer.run(root.getAbsolutePath());

    var expected = List.of(
        Item.builder()
          .name("20000102_name.mP4")
          .build());

    Assertions
      .assertThat(asTree(root))
      .isEqualTo(expected);
  }

  @Test
  @SneakyThrows
  public void should_not_rename_unsupported_file(@TempDir File root) {
    var fileName = "not supported naming style.jpg";
    var newFile = new File(root, fileName);
    newFile.createNewFile();

    normalizer.run(root.getAbsolutePath());

    var expected = List.of(
        Item.builder()
          .name(fileName)
          .build());

    Assertions
      .assertThat(asTree(root))
      .isEqualTo(expected);
  }
  
}
