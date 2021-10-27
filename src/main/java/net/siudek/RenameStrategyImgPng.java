package net.siudek;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class RenameStrategyImgPng extends RenameStrategyBaseOnRegex {

  private final static String FILENAME_REGEX = "img_\\d{8}_.*\\.png";

  public RenameStrategyImgPng() {
    super(FILENAME_REGEX);
  }

  @Override
  protected String generateNewFileName(String current) {
    return current.substring(4);
  }
}
