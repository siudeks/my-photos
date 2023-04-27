package net.siudek;

import java.io.File;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.common.io.Files;

import lombok.SneakyThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest
public class ImageNameNormalizerTests extends TestsBase {

  @Autowired
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

  @Nested
  class ShouldNormalizeImg2Jpg extends SingleFileTest {
    ShouldNormalizeImg2Jpg() {
      super("IMG20220907121718.jpg", "20220907-121718.jpg");
    }
  }

  @Nested
  class ShouldNormalizeVid2Mp4 extends SingleFileTest {
    ShouldNormalizeVid2Mp4() {
      super("VID20220907123413.mp4", "20220907-123413.mp4");
    }
  }

  @Nested
  class ShouldNormalizePxl2Jpg extends SingleFileTest {
    ShouldNormalizePxl2Jpg() {
      super("PXL_20210820_120217056.jpg", "20210820-120217.jpg");
    }
  }

  @Nested
  class ShouldNormalizePxlMp4 extends SingleFileTest {
    ShouldNormalizePxlMp4() {
      super("PXL_20210820_120217056.mp4", "20210820-120217.mp4");
    }
  }

}
