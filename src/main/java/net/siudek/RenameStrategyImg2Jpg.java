package net.siudek;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class RenameStrategyImg2Jpg extends RenameStrategyBaseOnRegex {

  private static final String FILENAME_REGEX = "IMG\\d{14}\\.jpg";

  public RenameStrategyImg2Jpg() {
    super(FILENAME_REGEX);
  }

  @Override
  protected String generateNewFileName(String current) {
    return current.substring(3, 11) + "-" + current.substring(11,21);
  }

}
