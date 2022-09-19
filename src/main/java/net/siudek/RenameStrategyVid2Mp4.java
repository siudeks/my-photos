package net.siudek;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class RenameStrategyVid2Mp4  extends RenameStrategyBaseOnRegex {

    private static final String FILENAME_REGEX = "VID\\d{14}\\.mp4";

    public RenameStrategyVid2Mp4() {
        super(FILENAME_REGEX);
    }

    @Override
    protected String generateNewFileName(String current) {
        return current.substring(3, 11) + "-" + current.substring(11,21);
    }

}
