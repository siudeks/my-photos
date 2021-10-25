package net.siudek;

import java.nio.file.Path;
import java.util.regex.Pattern;

public abstract class RenameStrategyBaseOnRegex extends RenameStrategyBase {

    private final String regex;

    protected RenameStrategyBaseOnRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean CanRename(Path file) {
        var pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        var fileName = file.toFile().getName();
        var matcher = pattern.matcher(fileName);
        return matcher.find();
    }
}
