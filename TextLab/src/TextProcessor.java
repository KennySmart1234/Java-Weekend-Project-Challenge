public class TextProcessor {
    public int countCharacter(String text){
        return text.length();
    }

    public int countCharacterWithoutWhiteSpaces(String text) {
      return (int)  text.chars().filter(character-> !Character.isWhitespace(character)).count();
    }

    public int countWords(String text) {
        String cleanedText = text.trim();
        if(cleanedText.isEmpty()){
            return 0;
        }

        String[] words = cleanedText.split("\\s+");
        return words.length;

    }

    public int sentenceCount(String text) {
        String clearedText = text.trim();
        if(clearedText.isEmpty()){
            return 0;
        }
        String[] sentences = clearedText.split("[.!?]+");
        int sentenceCount = 0;
        for (String sentence : sentences){
            if(!sentence.isBlank()){
                sentenceCount++;
            }

        }
        return sentenceCount;
    }

    public String normalizeSentence(String text) {
        return text.trim().replaceAll("\\s+", " ");
    }

    public int countWordOccurrences(String text, String word ) {
        if(word == null || word.isBlank()){
            return 0;
        }

        String pattern = "(?i)\\b" + java.util.regex.Pattern.quote(word.trim()) + "\\b";
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(pattern).matcher(text);

        int count = 0;

        while(matcher.find()){
            count++;
        }
        return count;
    }

    public String replaceWholeWord(String text, String target, String replacement) {
        if(target == null || target.isBlank()){
            return text;
        }

        String pattern = "(?i)\\b" + java.util.regex.Pattern.quote(target.trim()) + "\\b";

        return text.replaceAll(java.util.regex.Matcher.quoteReplacement(replacement), pattern);
    }
}
