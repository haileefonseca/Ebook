package cs3500.hw01.ebooks;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

/**
 * A Section of an e-book consists of a section title, followed by
 * a sequence of e-book chunks (which could be paragraphs, sub-sections, etc.)
 */
public class Section implements EBookChunk {
  private final String title;
  private final List<EBookChunk> contents;

  public Section(String title, List<EBookChunk> contents) {
    this.title = Objects.requireNonNull(title);
    if (title.contains("\n")) {
      throw new IllegalArgumentException("Titles cannot contain line breaks");
    }
    this.contents = new ArrayList<>(Objects.requireNonNull(contents));
  }

  public int countWords() {
    int total = 0 ;
    for (EBookChunk c : this.contents) {
      total += this.title.split(" ").length + c.countWords();
    }
    return total;
  }

  public boolean contains(String word) {
    if (word == null || word.contains(" ")) { throw new IllegalArgumentException("Invalid word"); }
    for (String s: this.title.split(" ")) {
      if (s.equals(word)) {return true;}
    }
    return this.contents.stream().anyMatch(c -> c.contains(word));
  }

  public String format(int lineWidth) {
    String concat = "";
    for (EBookChunk c : contents) {
      concat += (this.title + "\n");
      concat += c.format(lineWidth);
      concat += "\n\n";
    }
    return concat;
  }

  public int longestWord() {
    int longest = 0;
    for (EBookChunk c : contents){
      if (c.longestWord() > longest) {longest = c.longestWord();}
    }
    for (String titleSection : this.title.split(" ")){
      if (titleSection.length() > longest) {longest = titleSection.length();}
    }
    return longest;
  }
}
