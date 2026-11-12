/**
 * ============================================================
 *  PROJECT: WORD COUNTER  (Java, OOPs, runs in terminal)
 *  FILE: WordCounter.java  (basic + innovation features in ONE class)
 * ============================================================
 *  PART 1 - BASIC FEATURES : words, characters, sentences, lines,
 *                            word search, longest word
 *
 *  PART 2 - INNOVATION     : "SMART INSIGHTS"  (extra unique feature)
 *                            vowels/consonants, unique words, top keywords,
 *                            palindrome words, average word length,
 *                            reading level, estimated reading time
 *
 *  OOPs concepts used: class, object, constructor, encapsulation
 *  (private variable + getter/setter), methods, this keyword.
 * ============================================================
 */
public class WordCounter {

    // private data member (ENCAPSULATION)
    private String text;

    // Constructor
    public WordCounter(String text) {
        this.text = text;
    }

    // Getter
    public String getText() {
        return text;
    }

    // Setter
    public void setText(String text) {
        this.text = text;
    }

    // ============================================================
    //  PART 1 : BASIC FEATURES
    // ============================================================

    // Count total words
    public int countWords() {
        String trimmed = text.trim();
        if (trimmed.isEmpty()) {
            return 0;
        }
        String[] words = trimmed.split("\\s+"); // split on one or more spaces
        return words.length;
    }

    // Count all characters (including spaces)
    public int countCharacters() {
        return text.length();
    }

    // Count characters excluding spaces, tabs and new lines
    public int countCharactersWithoutSpaces() {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch != ' ' && ch != '\t' && ch != '\n') {
                count++;
            }
        }
        return count;
    }

    // Count sentences (ends with . ! or ?)
    public int countSentences() {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == '.' || ch == '!' || ch == '?') {
                count++;
            }
        }
        return count;
    }

    // Count lines
    public int countLines() {
        if (text.trim().isEmpty()) {
            return 0;
        }
        String[] lines = text.split("\n");
        return lines.length;
    }

    // Count how many times a particular word appears (ignores upper/lower case)
    public int countOccurrences(String searchWord) {
        String[] words = getCleanWords();
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase(searchWord)) {
                count++;
            }
        }
        return count;
    }

    // Find the longest word in the text
    public String findLongestWord() {
        String[] words = getCleanWords();
        String longest = "";
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > longest.length()) {
                longest = words[i];
            }
        }
        return longest;
    }

    // Build the basic report as text (used by both the terminal and the GUI)
    public String getBasicReport() {
        String r = "------------- BASIC REPORT -------------\n";
        r = r + "Words                     : " + countWords() + "\n";
        r = r + "Characters (with spaces)  : " + countCharacters() + "\n";
        r = r + "Characters (no spaces)    : " + countCharactersWithoutSpaces() + "\n";
        r = r + "Sentences                 : " + countSentences() + "\n";
        r = r + "Lines                     : " + countLines() + "\n";
        r = r + "Longest word              : " + findLongestWord() + "\n";
        r = r + "----------------------------------------\n";
        return r;
    }

    // Print the basic report in the terminal
    public void displayBasicReport() {
        System.out.print("\n" + getBasicReport());
    }

    // Helper method (used by both parts): returns all words in lowercase,
    // without punctuation like , . ! ?
    private String[] getCleanWords() {
        String trimmed = text.trim();
        if (trimmed.isEmpty()) {
            return new String[0];
        }
        String[] rawWords = trimmed.split("\\s+");
        String[] temp = new String[rawWords.length];
        int count = 0;
        for (int i = 0; i < rawWords.length; i++) {
            String cleaned = rawWords[i].replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            if (!cleaned.isEmpty()) {
                temp[count] = cleaned;
                count++;
            }
        }
        // copy into an array of exact size
        String[] words = new String[count];
        for (int i = 0; i < count; i++) {
            words[i] = temp[i];
        }
        return words;
    }

    // ============================================================
    //  PART 2 : INNOVATION  >>>  SMART INSIGHTS  <<<
    //  (Everything below is the extra, unique feature of this project)
    // ============================================================

    // INNOVATION 1: Count vowels
    public int countVowels() {
        String lower = text.toLowerCase();
        int count = 0;
        for (int i = 0; i < lower.length(); i++) {
            char ch = lower.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }

    // INNOVATION 2: Count consonants (letters that are not vowels)
    public int countConsonants() {
        String lower = text.toLowerCase();
        int count = 0;
        for (int i = 0; i < lower.length(); i++) {
            char ch = lower.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                if (ch != 'a' && ch != 'e' && ch != 'i' && ch != 'o' && ch != 'u') {
                    count++;
                }
            }
        }
        return count;
    }

    // INNOVATION 3: Count unique (different) words
    public int countUniqueWords() {
        String[] words = getCleanWords();
        String[] unique = new String[words.length];
        int uniqueCount = 0;
        for (int i = 0; i < words.length; i++) {
            boolean found = false;
            for (int j = 0; j < uniqueCount; j++) {
                if (unique[j].equals(words[i])) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                unique[uniqueCount] = words[i];
                uniqueCount++;
            }
        }
        return uniqueCount;
    }

    // INNOVATION 4: Top 3 keywords (words with 4+ letters, so "the", "and" are skipped)
    public String getTopKeywords() {
        String[] words = getCleanWords();
        String[] unique = new String[words.length];
        int[] counts = new int[words.length];
        int uniqueCount = 0;

        for (int i = 0; i < words.length; i++) {
            if (words[i].length() < 4) {
                continue; // skip short words
            }
            boolean found = false;
            for (int j = 0; j < uniqueCount; j++) {
                if (unique[j].equals(words[i])) {
                    counts[j]++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                unique[uniqueCount] = words[i];
                counts[uniqueCount] = 1;
                uniqueCount++;
            }
        }

        if (uniqueCount == 0) {
            return "Top keywords (4+ letters) : (none)\n";
        }

        String result = "Top keywords (4+ letters) :\n";
        for (int rank = 1; rank <= 3; rank++) {
            int maxIndex = -1;
            int maxCount = 0;
            for (int i = 0; i < uniqueCount; i++) {
                if (counts[i] > maxCount) {
                    maxCount = counts[i];
                    maxIndex = i;
                }
            }
            if (maxIndex == -1) {
                break; // no more words left
            }
            result = result + "   " + rank + ". " + unique[maxIndex] + "  (" + maxCount + " times)\n";
            counts[maxIndex] = 0; // so it is not picked again
        }
        return result;
    }

    // INNOVATION 5: Palindrome checker (helper method)
    private boolean isPalindrome(String word) {
        int left = 0;
        int right = word.length() - 1;
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // INNOVATION 5: Find palindrome words (3 or more letters)
    public String findPalindromes() {
        String[] words = getCleanWords();
        String result = "";
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() >= 3 && isPalindrome(words[i])) {
                if (!result.contains(words[i])) { // avoid repeating
                    result = result + words[i] + " ";
                }
            }
        }
        if (result.isEmpty()) {
            return "(none)";
        }
        return result.trim();
    }

    // INNOVATION 6: Average length of a word
    public double averageWordLength() {
        String[] words = getCleanWords();
        if (words.length == 0) {
            return 0;
        }
        int totalLetters = 0;
        for (int i = 0; i < words.length; i++) {
            totalLetters = totalLetters + words[i].length();
        }
        return (double) totalLetters / words.length;
    }

    // INNOVATION 7: Reading difficulty level based on average word length
    public String readingLevel() {
        double avg = averageWordLength();
        if (avg == 0) {
            return "N/A";
        } else if (avg < 4.0) {
            return "Easy";
        } else if (avg < 5.5) {
            return "Medium";
        } else {
            return "Hard";
        }
    }

    // INNOVATION 8: Estimated reading time (average person reads ~200 words per minute)
    public String readingTime() {
        int seconds = countWords() * 60 / 200;
        if (seconds < 60) {
            return seconds + " second(s)";
        }
        return (seconds / 60) + " min " + (seconds % 60) + " sec";
    }

    // Build all the innovation features as text (used by both terminal and GUI)
    public String getSmartInsights() {
        String r = "===== SMART INSIGHTS (INNOVATION) =====\n";
        r = r + "Vowels                    : " + countVowels() + "\n";
        r = r + "Consonants                : " + countConsonants() + "\n";
        r = r + "Unique words              : " + countUniqueWords() + "\n";
        r = r + "Palindrome words          : " + findPalindromes() + "\n";
        r = r + String.format("Average word length       : %.2f letters\n", averageWordLength());
        r = r + "Reading level             : " + readingLevel() + "\n";
        r = r + "Estimated reading time    : " + readingTime() + "\n";
        r = r + getTopKeywords();
        r = r + "=======================================\n";
        return r;
    }

    // Print the innovation features in the terminal
    public void showSmartInsights() {
        System.out.print("\n" + getSmartInsights());
    }
}