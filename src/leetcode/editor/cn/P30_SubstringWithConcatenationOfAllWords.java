//给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。 
//
// 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "barfoothefoobarman", words = ["foo","bar"]
//输出：[0,9]
//解释：
//从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
//输出的顺序不重要, [9,0] 也是有效答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
//输出：[6,9,12]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 由小写英文字母组成 
// 1 <= words.length <= 5000 
// 1 <= words[i].length <= 30 
// words[i] 由小写英文字母组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 567 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class SubstringWithConcatenationOfAllWords {
    public static void main(String[] args) {
        Solution solution = new SubstringWithConcatenationOfAllWords().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findSubstring(String s, String[] words) {
            List<Integer> ans = new ArrayList<>();

            int wordsSize = words.length;
            if (wordsSize == 0) {
                return ans;
            }

            int wordLength = words[0].length();
            HashMap<String, Integer> wordMap = new HashMap<>();
            for (String word : words) {
                int count = wordMap.getOrDefault(word, 0);
                wordMap.put(word, count+1);
            }

            for (int i = 0; i <= s.length() - wordsSize * wordLength; i++) {
                HashMap<String, Integer> hasWords = new HashMap<>();
                int num = 0;
                while (num < wordsSize) {
                    String word = s.substring(i + num * wordLength, i + (num+1)*wordLength);
                    if (wordMap.containsKey(word)) {
                        int count = hasWords.getOrDefault(word, 0);
                        hasWords.put(word, count+1);
                        if (hasWords.get(word) > wordMap.get(word)) {
                            break;
                        }
                    } else {
                        break;
                    }
                    num++;
                }
                if (num == wordsSize) {
                    ans.add(i);
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}