package cs3500.hw01.ebooks;

import java.util.List;
import java.util.ArrayList;

/**
 * A simple representation of an e-book, that can contain an arbitrary
 * vertical collection of horizontally-wrapped text content.
 *
 * @implNote: The starter code given to you uses loops and stream operations
 *            interchangeably (for instance, {@link EBook#countWords()} and
 *            {@link Paragraph#countWords()} compute very similar things) ---
 *            this is to give examples of how to use streams fluently.
 */
public final class EBook {
  private final List<EBookChunk> chunks;

  public EBook(List<EBookChunk> chunks) {
    validateChunks(chunks);
    this.chunks = new ArrayList<>(chunks);
  }

  private static void validateChunks(List<EBookChunk> chunks) {
    if (chunks == null) {
      throw new IllegalArgumentException("Chunk list cannot be null");
    }
    if (chunks.stream().anyMatch(c -> c == null)) {
      throw new IllegalArgumentException("Chunk list cannot contain null chunk");
    }
  }

  public int countWords() {
    int ans = 0;
    for (EBookChunk chunk : this.chunks) {
      ans += chunk.countWords();
    }
    return ans;
  }

  public boolean contains(String word) {
    return this.chunks.stream().anyMatch(c -> c.contains(word));
  }

  public String format(int lineWidth) {
    for(EBookChunk c: this.chunks){
      if (lineWidth < c.longestWord()){ throw new
              IllegalArgumentException("Line width must be longer than any words in the ebook");}
    }
    String concat = "";
    for(EBookChunk c : chunks) {
      concat += c.format(lineWidth);
    }
    return concat;
  }


}
