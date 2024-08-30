package net.siudek;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Profile("prod")
@Slf4j
public class Runner implements ApplicationRunner {

  private final ImageNameNormalizer normalizer;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    // Niektóre zdjecia mają przedrostek "WP_YYYYDDMM_*.jpg" gdzie YYYYMMDD reprezentuje jakąś datę.
    // Przedrostek WP_ chcę usunąć, podobnie jak i inne wyjątki
    // Because number of elements in my photo folder is too large, I need to automate renaming such files.

    Assert.isTrue(args.getSourceArgs().length == 1, "Required argument: dir with the images to process, has not been provided.");
    var initialDir = args.getSourceArgs()[0];
    normalizer.run(initialDir);
  }
}
