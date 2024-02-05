package cs3500.hw01.ebooks;

import java.util.List;
import java.util.Objects;

public class TextFlow implements EBookFlow {
  private final String content;

  public TextFlow(String content) {
    this.content = Objects.requireNonNull(content);
    if (content.contains("\n")) {
      throw new IllegalArgumentException("Text flows cannot contain line breaks");
    }
  }

  public int countWords() {
    return this.content.split(" ").length;
  }

  public boolean contains(String word) {
    List<String> allWords = List.of(this.content.split(" "));
    for(String s: allWords) {
      if (s.equals(word)) { return true; }
    }
    return false;
  }

  public String format(int lineWidth) {
    String concat = "";
    int leftInLine = lineWidth;
    List<String> allWords = List.of(content.split(" "));
    for(String word : allWords) {
      if(leftInLine > word.length()) {
        concat += word + " ";
        leftInLine -= (word.length() + 1);
      }
      else{
        concat += "\n";
        leftInLine = lineWidth;
      }
    }
    return concat;
  }

  public int longestWord() {
   int longest = 0;
   for(String s: this.content.split(" ")){
     if (s.length() > longest) {longest = s.length();}
   }
   return longest;
  }

}
