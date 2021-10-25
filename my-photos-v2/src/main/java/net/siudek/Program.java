package net.siudek;

import javax.inject.Inject;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Program implements QuarkusApplication {

  @Inject
  ImageNameNormalizer normalizer;

  @Override
  public int run(String... args) {
    // Niektóre zdjecia mają przedrostek "WP_YYYYDDMM_*.jpg" gdzie YYYYMMDD reprezentuje jakąś datę.
    // Przedrostek WP_ chcę usunąć, podobnie jak i inne wyjątki
    // Because number of elements in my photo folder is too large, I need to automate renaming such files.

    // entry.Run(@"C:\Users\siude\OneDrive\Obrazy\Osobiste\2017\07 Wenecja");
    // entry.Run(@"S:\siudeks\OneDrive\Obrazy\Z aparatu");
    normalizer.run("aaa");
    return 0;
  }
}
