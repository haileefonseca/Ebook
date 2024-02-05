package cs3500.hw01.ebooks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hyperlink implements EBookFlow{
  private final EBookFlow contents;
  private final String destination;

  public Hyperlink(EBookFlow contents, String destination) {
    this.contents = contents;
    this.destination = destination;
  }

  @Override
  public int countWords() {
    return this.contents.countWords();
  }

  @Override
  public boolean contains(String word) {
    if (word == null || word.contains(" ")) { throw new IllegalArgumentException("Invalid word"); }
    return this.contents.contains(word);
  }

  public String format(int lineWidth) {
    String concat = "";
    concat += this.contents.format(lineWidth);
    concat += "\n\n";
    return concat;
  }

  public int longestWord(){
    return this.contents.longestWord();
  }
}
