package net.siudek;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class RenameStrategyImgJpg extends RenameStrategyBaseOnRegex {

  private static final String FILENAME_REGEX = "img_\\d{8}_.*\\.jpg";

  public RenameStrategyImgJpg() {
    super(FILENAME_REGEX);
  }

  @Override
  protected String generateNewFileName(String current) {
    return current.substring(4);
  }

}
