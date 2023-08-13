package net.siudek;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Runner implements ApplicationRunner {

  private final ImageNameNormalizer normalizer;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    // Niektóre zdjecia mają przedrostek "WP_YYYYDDMM_*.jpg" gdzie YYYYMMDD reprezentuje jakąś datę.
    // Przedrostek WP_ chcę usunąć, podobnie jak i inne wyjątki
    // Because number of elements in my photo folder is too large, I need to automate renaming such files.

    normalizer.run("/z-aparatu");
  }
}
