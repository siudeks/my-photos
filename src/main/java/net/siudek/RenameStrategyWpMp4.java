package net.siudek;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class RenameStrategyWpMp4 extends RenameStrategyBaseOnRegex {

  private static final String FILENAME_REGEX = "wp_\\d{8}_.*\\.mp4";

  public RenameStrategyWpMp4() {
    super(FILENAME_REGEX);
  }

  @Override
  protected String generateNewFileName(String current) {
    return current.substring(3);
  }

}
