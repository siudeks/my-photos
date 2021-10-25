package net.siudek;

import io.vavr.Tuple;
import io.vavr.collection.Array;
import io.vavr.collection.Stream;

import java.io.File;
import java.nio.file.Files;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ImageNameNormalizer {
  private final Array<IRenameStrategy> renameStrategies;

  public ImageNameNormalizer(IRenameStrategy[] strategies) {
    this.renameStrategies = Array.of(strategies);
  }

    @SneakyThrows
    public void Run(String rootPath) {
        log.info("Start processing {}", rootPath);
        var rootAsFile = new File(rootPath);
        Stream
            .ofAll(Files.walk(rootAsFile.toPath()))
            .map(filePath -> {
                var oneStrategy = renameStrategies.filter(it -> it.CanRename(filePath)).headOption();
                return oneStrategy.map(it -> Tuple.of(it, filePath));
            })
            .flatMap(it -> it.toStream())
            .forEach(it -> {
                var strategy = it._1;
                var filePath = it._2;
                var normalizedName = strategy.Rename(filePath);

                var file = filePath.toFile();
                log.info("Change name {} -> {}", file.getName(), normalizedName);

                var newFile = new File(file.getParent(), normalizedName);
                file.renameTo(newFile);
        });
    }

}
