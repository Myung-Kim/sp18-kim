import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    // Uncomment this class once you've created your Palindrome class.

    @Test
    public void TestIsPalindrome() {
        assertTrue(palindrome.isPalindrome("aka"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertFalse(palindrome.isPalindrome("kaa"));
    }

    @Test
    public void TestIsPalindromeByOne() {
//        OffByOne obo = new OffByOne();
        // why?
        CharacterComparator obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("axb", obo));
        assertTrue(palindrome.isPalindrome("axyb", obo));
        assertFalse(palindrome.isPalindrome("axa", obo));

    }

    @Test
    public void TestIsPalindromeByN(){
        CharacterComparator obo = new OffByN(2);
        assertTrue(palindrome.isPalindrome("axc", obo));
        assertTrue(palindrome.isPalindrome("bxzd", obo));
        assertFalse(palindrome.isPalindrome("axa", obo));
    }
}
