package cs3500.hw01.ebooks;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

/**
 * A Paragraph of an e-book consists of a chunk of plain text.
 */
public class Paragraph implements EBookChunk {
  private final List<EBookFlow> contents;

  public Paragraph(List<EBookFlow> contents) {
    validateContents(contents);
    this.contents = new ArrayList<>(contents);
  }

  private static void validateContents(List<EBookFlow> content) {
    if (content == null) {
      throw new IllegalArgumentException("Contentlist cannot be null");
    }
    if (content.stream().anyMatch(c -> c == null)) {
      throw new IllegalArgumentException("Content list cannot contain null content");
    }
  }


  public int countWords() {
    int total = 0;
    for (EBookFlow f : this.contents) {
      total += f.countWords();
    }
    return total;
  }

  public boolean contains(String word) {
    if (word == null || word.contains(" ")) { throw new IllegalArgumentException("Invalid word"); }
    return this.contents.stream().anyMatch(c -> c.contains(word));
  }

  public String format(int lineWidth) {
    String concat = "";
    for (EBookFlow f : contents) {
      concat += f.format(lineWidth);
      concat += "\n\n";
    }
    return concat;
  }

  public int longestWord(){
    int longest = 0;
    for (EBookFlow f : contents){
      if (f.longestWord() > longest) {longest = f.longestWord();}
    }
    return longest;
  }

}
