public class Palindrome {

    public static Deque<Character> wordToDeque(String word) {
        Deque<Character> charDeque = new ArrayDequeSolution<>();
        char[] words = word.toCharArray();
        for (Character singleChar : words) {
            charDeque.addLast(singleChar);
        }
        return charDeque;
    }

    public static boolean isPalindrome(String word) {
        Deque<Character> charDeque = wordToDeque(word);
        while (charDeque.size() > 1) {
            if (!charDeque.removeFirst().equals(charDeque.removeLast())) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> charDeque = wordToDeque(word);
        while (charDeque.size() > 1) {
            if (cc.equalChars(charDeque.removeFirst(), charDeque.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
