public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }

        return deque;
    }


    private boolean isPlaindromeHelper(Deque<Character> deque) {
        if (deque.size() < 2) {
            return true;
        }
        if (deque.removeFirst() != deque.removeLast()) {
            return false;
        }
        return isPlaindromeHelper(deque);
    }

    private boolean isPlaindromeHelper(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() < 2) {
            return true;
        }
        if (!cc.equalChars(deque.removeFirst(), deque.removeLast())) {
            return false;
        }
        return isPlaindromeHelper(deque, cc);
    }

    public boolean isPalindrome(String word) {
        Deque<Character> initial = wordToDeque(word);
        return isPlaindromeHelper(initial);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> initial = wordToDeque(word);

        return isPlaindromeHelper(initial, cc);
    }
}
