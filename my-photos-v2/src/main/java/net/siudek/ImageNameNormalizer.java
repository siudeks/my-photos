package net.siudek;

import java.io.File;
import java.nio.file.Files;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;

import io.vavr.Tuple;
import io.vavr.collection.Array;
import io.vavr.collection.Stream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
class ImageNameNormalizer {
  private final Array<RenameStrategy> renameStrategies;

  public ImageNameNormalizer(Instance<RenameStrategy> strategies) {
    this.renameStrategies = Array.ofAll(strategies);
  }

  @SneakyThrows
  public void run(String rootPath) {
    log.info("Start processing {}", rootPath);
    var rootAsFile = new File(rootPath);
    Stream
        .ofAll(Files.walk(rootAsFile.toPath()))
        .map(filePath -> {
          var oneStrategy = renameStrategies.filter(it -> it.canRename(filePath)).headOption();
          return oneStrategy.map(it -> Tuple.of(it, filePath));
        })
        .flatMap(it -> it.toStream())
        .forEach(it -> {
          var strategy = it._1;
          var filePath = it._2;
          var normalizedName = strategy.rename(filePath);

          var file = filePath.toFile();
          log.info("Change name {} -> {}", file.getName(), normalizedName);

          var newFile = new File(file.getParent(), normalizedName);
          file.renameTo(newFile);
        });
  }

}
