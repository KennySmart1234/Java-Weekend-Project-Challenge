import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextProcessorTest {

    private TextProcessor processor;
    @BeforeEach
    public void setup() {
        processor = new TextProcessor();
    }

    @Test
    public void testThatCountIsElevenIncludingTheWhiteSpaces() {
        assertEquals(12, processor.countCharacter("Hello World!"));
    }

    @Test
    public void testToCountTextCharacterWithWhiteSpaces(){
        assertEquals(10, processor.countCharacterWithoutWhiteSpaces("Kenny Smart"));
    }


    @Test
    public void testToCountWordInTheSentence(){
        assertEquals(1, processor.countWords("KennySmart"));
    }

    @Test
    public void testThatCountWordIsFive(){
        assertEquals(5, processor.countWords("olatunji Kenny Smart stephen kennysmart"));
    }

    @Test
    public void testThatCountWordIsZero(){
        assertEquals(0, processor.countWords(""));
    }

    @Test
    public void testThatWiteSpaceCountIsZero(){
        assertEquals(0, processor.countWords("     "));
    }

    @Test
    public void testToCountTheSentence(){
        assertEquals(2, processor.sentenceCount("KennySmart, is the son of Mr. Olatunji"));
    }

    @Test
    public void testThatSentenceCountIsFive(){
        assertEquals(3, processor.sentenceCount("What GOD cannot do, does not exist. All power below to God! the creator of all things"));

    }

    @Test
    public void testThatSentenceCountIsZero(){
        assertEquals(0, processor.sentenceCount("  "));
    }

    @Test
    public void testToNormalizeSentence(){
        assertEquals("Everything gonna be alright", processor.normalizeSentence("Everything          gonna       be              alright"));
    }

    @Test
    public void testToNormalizeSentenceWithSymbolsWithHandleTabAndNextLine(){
        assertEquals("Money  is  not  everything",  processor.normalizeSentence("Money    \t  is  \n  not \t  everything"));
    }

    @Test
    public void testToReturnEmptyStringForWhiteSpacesInput(){
        assertEquals("", processor.normalizeSentence(" \t        \n     "));
    }

    @Test
    public void testToCountWordOccurrencesInSentence(){
        assertEquals(2, processor.countWordOccurrences("java is for everyone, i love java", "JAVa"));
    }

    @Test
    public void testThatCountWordOccurrencesInSentenceIsZero(){
        assertEquals(0, processor.countWordOccurrences("KennySmart is a boy", "Kenny"));
    }

    @Test
    public void testThatCountWordOccurrencesInSentenceIsOne(){
        assertEquals(1, processor.countWordOccurrences("KennySmart is a boy", "KennySmart"));
    }

    @Test
    public void shouldReturnZeroWhenTextDoesNotExist(){
        assertEquals(0, processor.countWordOccurrences("kenny Smart", "Kehinde"));
    }

    @Test
    public void shouldReturnZeroWhenTextIsEmpty(){
        assertEquals(0, processor.countWordOccurrences("olatunji Keenny", ""));
    }

    @Test
    public void testToReplaceSearchWordInTheSentence(){
        assertEquals("Java is not the same as Python",
                processor.replaceWholeWord("Java is not the same as Python", "Java", "Python"));
    }


}