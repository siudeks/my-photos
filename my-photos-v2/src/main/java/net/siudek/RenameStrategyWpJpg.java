package net.siudek;

final class RenameStrategyWpJpg  extends RenameStrategyBaseOnRegex {

    private final static String FILENAME_REGEX = "wp_\\d{8}_.*\\.jpg";

    public RenameStrategyWpJpg() {
        super(FILENAME_REGEX);
    }

    @Override
    protected String generateNewFileName(String current) {
        return current.substring(3);
    }

}
