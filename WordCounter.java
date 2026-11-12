public class WordCounter {

    private String text;

    public WordCounter(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int countWords() {
        String trimmed = text.trim();
        if (trimmed.isEmpty()) {
            return 0;
        }
        String[] words = trimmed.split("\\s+");
        return words.length;
    }

    public int countCharacters() {
        return text.length();
    }

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

    public int countLines() {
        if (text.trim().isEmpty()) {
            return 0;
        }
        String[] lines = text.split("\n");
        return lines.length;
    }

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

    public void displayBasicReport() {
        System.out.print("\n" + getBasicReport());
    }

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
        String[] words = new String[count];
        for (int i = 0; i < count; i++) {
            words[i] = temp[i];
        }
        return words;
    }

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

    public String getTopKeywords() {
        String[] words = getCleanWords();
        String[] unique = new String[words.length];
        int[] counts = new int[words.length];
        int uniqueCount = 0;

        for (int i = 0; i < words.length; i++) {
            if (words[i].length() < 4) {
                continue; 
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
                break; 
            }
            result = result + "   " + rank + ". " + unique[maxIndex] + "  (" + maxCount + " times)\n";
            counts[maxIndex] = 0; 
        }
        return result;
    }

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

    public String findPalindromes() {
        String[] words = getCleanWords();
        String result = "";
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() >= 3 && isPalindrome(words[i])) {
                if (!result.contains(words[i])) {
                    result = result + words[i] + " ";
                }
            }
        }
        if (result.isEmpty()) {
            return "(none)";
        }
        return result.trim();
    }

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

    public String readingTime() {
        int seconds = countWords() * 60 / 200;
        if (seconds < 60) {
            return seconds + " second(s)";
        }
        return (seconds / 60) + " min " + (seconds % 60) + " sec";
    }

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

    public void showSmartInsights() {
        System.out.print("\n" + getSmartInsights());
    }
}
