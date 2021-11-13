//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 哈希表 字符串 回溯 
// 👍 1595 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Solution solution = new LetterCombinationsOfAPhoneNumber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private final char[][] CHARS = new char[][]{
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'j', 'k', 'l'},
                {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'},
                {'w', 'x', 'y', 'z'}
        };

        public List<String> letterCombinations(String digits) {
            List<String> list = new ArrayList<>();
            int size = digits.length();
            if (size != 0) {
                Map<Character, String> phoneMap = new HashMap<Character, String>() {{
                    put('2', "abc");
                    put('3', "def");
                    put('4', "ghi");
                    put('5', "jkl");
                    put('6', "mno");
                    put('7', "pqrs");
                    put('8', "tuv");
                    put('9', "wxyz");
                }};
                backtrack(list, phoneMap, digits, 0, new StringBuilder());
            }
            return list;
        }

        private void backtrack(
                List<String> list, Map<Character, String> phoneMap,
                String digits, int index, StringBuilder stringBuilder) {
            if (index == digits.length()) {
                list.add(stringBuilder.toString());
            } else {
                char digit = digits.charAt(index);
                String text = phoneMap.get(digit);
                for (int i = 0; i < text.length(); i++) {
                    stringBuilder.append(text.charAt(i));
                    backtrack(list, phoneMap, digits, index + 1, stringBuilder);
                    stringBuilder.deleteCharAt(index);
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}