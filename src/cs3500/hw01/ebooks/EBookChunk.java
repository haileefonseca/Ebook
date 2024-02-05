package cs3500.hw01.ebooks;

/**
 * An EBookChunk is a larger piece of content within an EBook, like a paragraph
 * or a section, whose position in the document is fixed relative to other chunks.
 */
public interface EBookChunk {
  /**
   * How many words are in this e-book chunk?
   *
   * @return the relevant count
   */
  int countWords();

  /**
   * Does this ebook chunk contain the given word?  Only complete words count:
   * for instance, the document "hello" does not contain the word "ell".
   *
   * @return true iff the given word appears in this e-book
   * @throws IllegalArgumentException if the given word is null,
   *         or contains spaces.
   */
  boolean contains(String word);

  /**
   * Formats an EBookChunk with the given line width (in characters), only
   * splitting by whole words, placing double line breaks between chunks
   * and single breaks between section titles and content
   *
   * @returns a formatted String of all content in the EBook
   * @throws IllegalArgumentException if the given line width is less than
   */
  String format(int lineWidth);

  int longestWord();
}
