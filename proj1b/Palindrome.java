public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> a = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i = i + 1) {
            a.addLast(word.charAt(i));
        }
        return a;
    }

    private Deque<Character> reversedDeque(String word) {
        Deque<Character> a = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i = i + 1) {
            a.addFirst(word.charAt(i));}
        return a;
    }

    public boolean isPalindrome(String word){
        Deque<Character> a = wordToDeque(word);
        Deque<Character> b = reversedDeque(word);
        while (a.size() != 0){
            if ( a.removeFirst() != b.removeFirst() ) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> a = wordToDeque(word);
        Deque<Character> b = reversedDeque(word);
        if (a.size() <= 1) { //空或单个 字符串
            return true;
        }
        else if (a.size() % 2 == 0) { //偶数 字符串
            while (a.size() != 0) {
                if (!cc.equalChars(a.removeFirst(),b.removeFirst())) {
                    return false;
                }
            }
            return true;
        }
        else { //奇数 字符串
            int middleIndex = (a.size() - 1) / 2;
            int counter = 0;
            while (a.size() != 0) {
                if (counter == middleIndex) {
                    a.removeFirst();
                    b.removeFirst();
                }
                if (!cc.equalChars(a.removeFirst(),b.removeFirst())) {
                    return false;
                }
                counter = counter + 1;
            }
            return true;
        }
    }
}
