package net.siudek;

import org.springframework.stereotype.Component;

@Component
class RenameStrategyScreenshotJpg extends RenameStrategyBaseOnRegex {

  private static final String FILENAME_REGEX = "Screenshot_\\d{8}_.*\\.jpg";

  public RenameStrategyScreenshotJpg() {
    super(FILENAME_REGEX);
  }

  @Override
  protected String generateNewFileName(String current) {
    return current.substring(11);
  }

}
