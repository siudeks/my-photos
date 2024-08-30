package net.siudek;

import org.springframework.stereotype.Component;

@Component
class RenameStrategyPxlJpgPortrait  extends RenameStrategyBaseOnRegex {

    private static final String FILENAME_REGEX = "PXL_\\d{8}_\\d{9}\\.PORTRAIT.jpg";

    public RenameStrategyPxlJpgPortrait() {
        super(FILENAME_REGEX);
    }

    @Override
    protected String generateNewFileName(String current) {
        return current.substring(4, 12) + "-" + current.substring(13,19) + ".jpg";
    }

}
