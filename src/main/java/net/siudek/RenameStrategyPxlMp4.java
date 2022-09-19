package net.siudek;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class RenameStrategyPxlMp4  extends RenameStrategyBaseOnRegex {

    private static final String FILENAME_REGEX = "PXL_\\d{8}_\\d{9}\\.mp4";

    public RenameStrategyPxlMp4() {
        super(FILENAME_REGEX);
    }

    @Override
    protected String generateNewFileName(String current) {
        return current.substring(4, 12) + "-" + current.substring(13,19) + ".mp4";
    }

}
