package net.siudek;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

import javax.inject.Inject;

import com.google.common.io.Files;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
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

  abstract class SingleFileTest {
    private final String given;
    private final String normalized;

    protected SingleFileTest(String actual, String normalized) {
      this.given = actual;
      this.normalized = normalized;
    }

    @SneakyThrows
    @Test
    public void process(@TempDir File root) {
      var newFile = new File(root, given);
      newFile.createNewFile();
  
      normalizer.run(root.getAbsolutePath());
  
      var expected = List.of(
          Item.builder()
            .name(normalized)
            .build());
  
      Assertions
        .assertThat(asTree(root))
        .isEqualTo(expected);
    }

  }

  @Nested
  class ShouldNormalizeImgJpg extends SingleFileTest {
    ShouldNormalizeImgJpg() {
      super("IMG_20211010_154108.jpg", "20211010_154108.jpg");
    }
  }

  @Nested
  class ShouldNormalizeImgPng extends SingleFileTest {
    ShouldNormalizeImgPng() {
      super("iMg_20000102_name.pNg", "20000102_name.pNg");
    }
  }

  @Nested
  class ShouldNormalizeVidMp4 extends SingleFileTest {
    ShouldNormalizeVidMp4() {
      super("vId_20000102_name.mP4", "20000102_name.mP4");
    }
  }

  @Nested
  class ShouldNormalizeWpJpg extends SingleFileTest {
    ShouldNormalizeWpJpg() {
      super("WP_20000102_name.jPg", "20000102_name.jPg");
    }
  }

  @Nested
  class ShouldNormalizeWpMp4 extends SingleFileTest {
    ShouldNormalizeWpMp4() {
      super("WP_20000102_name.mP4", "20000102_name.mP4");
    }
  }

  @Nested
  class ShouldNotNormalizeUnsupportedFile extends SingleFileTest {
    ShouldNotNormalizeUnsupportedFile() {
      super("not supported naming style.jpg", "not supported naming style.jpg");
    }
  }

}