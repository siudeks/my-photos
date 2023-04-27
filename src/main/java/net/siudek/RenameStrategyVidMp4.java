package net.siudek;

import org.springframework.stereotype.Component;

@Component
class RenameStrategyVidMp4  extends RenameStrategyBaseOnRegex {

    private final static String FILENAME_REGEX = "vid_\\d{8}_.*\\.mp4";

    public RenameStrategyVidMp4() {
        super(FILENAME_REGEX);
    }

    @Override
    protected String generateNewFileName(String current) {
        return current.substring(4);
    }

}
