package net.siudek;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class RenameStrategyPxlJpg  extends RenameStrategyBaseOnRegex {

    private static final String FILENAME_REGEX = "PXL_\\d{8}_\\d{9}\\.jpg";

    public RenameStrategyPxlJpg() {
        super(FILENAME_REGEX);
    }

    @Override
    protected String generateNewFileName(String current) {
        return current.substring(4, 12) + "-" + current.substring(13,19) + ".jpg";
    }

}
