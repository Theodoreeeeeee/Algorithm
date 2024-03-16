package DataStructure;

public class TrieTree {

    // 前缀树节点
    public class TrieNode {
        public int pass;
        public int end;
        public TrieNode[] nexts;

        public TrieNode() {
            pass = 0;
            end = 0;
            nexts = new TrieNode[26]; // nexts[0] == null 没有走向'a'的路, != null, 有
        }
    }

    public class Trie {
        private TrieNode root; // 根节点的pass值可以被视为由空串作为前缀的字符串数量，即所有字符串数量

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            if (word == null) return;
            char[] cs = word.toCharArray();
            TrieNode node = root;
            node.pass++;
            for (int i = 0; i < cs.length; i++) {
                int idx = cs[i] - 'a';
                if (node.nexts[idx] == null) node.nexts[idx] = new TrieNode();
                node = node.nexts[idx];
                node.pass++;
            }
            node.end++;
        }

        // 删除一个字符串
        public void delete(String word) {
            if (search(word) != 0) {
                char[] cs = word.toCharArray();
                TrieNode node = root;
                node.pass--;
                for (char c : cs) {
                    int idx = c - 'a';
                    if (--node.nexts[idx].pass == 0) {
                        node.nexts[idx] = null;
                        return;
                    }
                    node = node.nexts[idx];
                }
                node.end--;
            }
        }

        // word这个单词之前加入过几次
        public int search(String word) {
            if (word == null) return 0;
            char[] cs = word.toCharArray();
            TrieNode node = root;
            for (char c : cs) {
                int idx = c - 'a';
                if (node.nexts[idx] == null) return 0;
                node = node.nexts[idx];
            }
            return node.end;
        }

        // 所有加入的字符串中， 有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null) return 0;
            char[] cs = pre.toCharArray();
            TrieNode node = root;
            for (char c : cs) {
                int idx = c - 'a';
                if (node.nexts[idx] == null) return 0;
                node = node.nexts[idx];
            }
            return node.pass;
        }
    }
}