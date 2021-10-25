package net.siudek;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import io.vavr.collection.Queue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.SneakyThrows;
import lombok.Value;
import lombok.Builder.Default;

public class TestsBase {

  private static Queue<Path> ancestors(Path root, Path current) {
    var result = Queue.<Path>empty();
    while (!current.getParent().equals(root)) {
      current = current.getParent();
      result = result.append(current);
    }
    return result.reverse();
  }

  private static void insert(List<ItemInt> items, Queue<Path> ancestors, Path target) {
    if (!ancestors.isEmpty()) {
      var ancestor = ancestors.head();
      var ancestorsLeft = ancestors.tail();
      var ancestorItem = items.stream().filter(it -> it.getPath().equals(ancestor)).findAny().get();
      insert(ancestorItem.getChildren().getChildren(), ancestorsLeft, target);
      return;
    }
    if (target.toFile().isDirectory()) {
      insertFolder(items, target);
    } else {
      insertFile(items, target);
    }
  }

  private static void insertFolder(List<ItemInt> items, Path current) {
    var newItem = new ItemInt();
    newItem.setPath(current);
    items.add(newItem);
  }

  private static void insertFile(List<ItemInt> items, Path current) {
    var newItem = new ItemInt();
    newItem.setPath(current);
    items.add(newItem);
  }

  @SneakyThrows
  protected List<Item> asTree(Path root) {
    var items = new ItemsInt();
    Files.walk(root)
        // in our result we do not include root element, but it is included in
        // Files.walk
        .filter(it -> !it.equals(root)).forEach(v -> {
          var ancestors = ancestors(root, v);
          insert(items.getChildren(), ancestors, v);
        });

    return map(items);
  }

  private static List<Item> map(ItemsInt items) {
    var result = new ArrayList<Item>();
    for (var item : items.children) {
      var mappedItem = map(item);
      result.add(mappedItem);
    }
    return result;
  }

  private static Item map(ItemInt item) {
    return Item.builder()
        .name(item.path.toFile().getName())
        .children(map(item.getChildren()))
        .build();
  }

  @NoArgsConstructor
  @Data
  @AllArgsConstructor
  @Builder
  private static class ItemsInt {
    @Default
    private List<ItemInt> children = new ArrayList<>();
  }

  @NoArgsConstructor
  @Data
  @AllArgsConstructor
  @Builder
  private static class ItemInt {
    private Path path;
    @Default
    private ItemsInt children = new ItemsInt();
  }

}

@Value
@Builder
class Item {
  private String name;
  @Singular
  private List<Item> children;
}
