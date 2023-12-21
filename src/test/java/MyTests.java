import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyTests {
    private static boolean arrayEqualsUpTo(int lastIndexExclusive, String[] arr1, String[] arr2) {
        for (int i = 0; i < lastIndexExclusive; i++) {
            if (!arr1[i].equals(arr2[i])) {
                System.out.println("Trie does not match expected results at index " + i +
                        ". Got " + arr2[i] + " instead of " + arr1[i]);
                return false;
            }
        }
        return true;
    }

    private static void trieToArrayRec(TrieNode current, String str, String[] words, int[] i2) {
        if (current.isWord) {
            words[i2[0]++] = str;
        }

        for (int i = 0; i < 26; i++) {
            if (current.children[i] == null) {
                continue;
            }
            trieToArrayRec(current.children[i], str + (char) (i + 'a'), words, i2);
        }
    }

    private static void trieEquals(Trie test, String[] expected) {
        String[] words = new String[10];
        int[] i = new int[1];
        trieToArrayRec(test.root, "", words, i);
        assertTrue(arrayEqualsUpTo(expected.length, expected, words));
    }

    @Test
    public void insertionAdd() {
        // --------------------------
        // Test 1: Insertion (add)
        // --------------------------
        Trie test = new Trie();

        test.add("hi");
        test.add("world");
        test.add("his");
        test.add("history");
        test.add("hiss");

        trieEquals(test, new String[] {"hi", "his", "hiss", "history", "world"});
    }

    @Test
    public void deletionRemove() {
        Trie test = new Trie();
        test.add("hi");
        test.add("world");
        test.add("his");
        test.add("history");
        test.add("hiss");
        // --------------------------
        // Test 2: Deletion (remove)
        // --------------------------
        test.remove("his");

        trieEquals(test, new String[] {"hi", "hiss", "history", "world"});
    }

    @Test
    public void searchContains() {
        // --------------------------
        // Test 3: Search (contains) and other tests
        // --------------------------
        Trie trie1 = new Trie();
        assertFalse(trie1.contains("card"));

        trie1.add("card");
        assertTrue(trie1.contains("card"));
        assertFalse(trie1.contains("car"));

        trie1.remove("card");
        assertFalse(trie1.contains("card"));
    }
}
