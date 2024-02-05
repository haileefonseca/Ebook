import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.hw01.ebooks.EBookChunk;
import cs3500.hw01.ebooks.EBookFlow;
import cs3500.hw01.ebooks.Paragraph;
import cs3500.hw01.ebooks.Section;
import cs3500.hw01.ebooks.TextFlow;
import cs3500.hw01.ebooks.EBook;

public class ExamplarEBooks {

  //tests the empty case for count
  @Test
  public void exampleCountEmpty() {
    ArrayList<EBookFlow> empty = new ArrayList<EBookFlow>();
    empty.add(new TextFlow(" "));
    Paragraph emptyp = new Paragraph(empty);
    ArrayList<EBookChunk> book = new ArrayList<EBookChunk>();
    book.add(emptyp);
    EBook emptybook = new EBook(book);
    Assert.assertEquals(emptybook.countWords(), 0);
}

  //tests a bug where the section title is not included in the word count
  @Test
  public void exampleCountWordsAcc() {
    List<EBookFlow> text = Arrays.asList(new TextFlow("more words!"));
    List<EBookChunk> paragraphs = Arrays.asList(new Paragraph(text));
    Section sect = new Section("title has words", paragraphs);
    List<EBookChunk> book = Arrays.asList(sect);
    EBook ebook = new EBook(book);
    Assert.assertEquals(ebook.countWords(), 5);
  }

  //tests a bug where contains will return true if a section title
  // contains a fragment of the searched word
  @Test
  public void exampleSectionContain() {
    EBookChunk sect = new Section("title", List.of());
    List<EBookChunk> book = Arrays.asList(sect);
    EBook ebook = new EBook(book);
    Assert.assertFalse(ebook.contains("le"));
  }

  //tests a bug where contains will return true if a paragraph
  // contains a fragment of the searched word
  @Test
  public void exampleContainsOverlappingParagraph() {
    List<EBookFlow> text = Arrays.asList(new TextFlow("high"));
    List<EBookChunk> book = Arrays.asList(new Paragraph(text));
    EBook ebook = new EBook(book);
    Assert.assertFalse(ebook.contains("hi"));
  }

  //testing for a bug where a runtime exception is thrown if contains is used to check the
  //entirety of a paragraphs content
  @Test
  public void exampleContainsWholeString() {
    List<EBookFlow> text= Arrays.asList(new TextFlow("this is a sentence"));
    List<EBookChunk> book = Arrays.asList(new Paragraph(text));
    EBook ebook = new EBook(book);
    Assert.assertThrows(RuntimeException.class, () -> ebook.contains("this is a sentence"));
  }

  //testing for a bug where a runtime exception is thrown if contains is used to check the
  //entirety of a title
  @Test
  public void exampleContainsWholeStringSection() {
    List<EBookChunk> book = Arrays.asList(new Paragraph(new ArrayList<EBookFlow>()));
    EBookChunk sect = new Section("this is a sentence", book);
    EBook ebook = new EBook(Arrays.asList(sect));
    Assert.assertThrows(RuntimeException.class, () -> ebook.contains("this is a sentence"));
  }
}
