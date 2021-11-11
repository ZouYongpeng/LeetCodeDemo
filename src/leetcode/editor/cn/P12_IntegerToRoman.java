//罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。 
//
// 
//字符          数值
//I             1
//V             5
//X             10
//L             50
//C             100
//D             500
//M             1000 
//
// 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做 XXVII, 即为 XX + V + I
//I 。 
//
// 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5
// 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况： 
//
// 
// I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。 
// X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
// C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。 
// 
//
// 给你一个整数，将其转为罗马数字。 
//
// 
//
// 示例 1: 
//
// 
//输入: num = 3
//输出: "III" 
//
// 示例 2: 
//
// 
//输入: num = 4
//输出: "IV" 
//
// 示例 3: 
//
// 
//输入: num = 9
//输出: "IX" 
//
// 示例 4: 
//
// 
//输入: num = 58
//输出: "LVIII"
//解释: L = 50, V = 5, III = 3.
// 
//
// 示例 5: 
//
// 
//输入: num = 1994
//输出: "MCMXCIV"
//解释: M = 1000, CM = 900, XC = 90, IV = 4. 
//
// 
//
// 提示： 
//
// 
// 1 <= num <= 3999 
// 
// Related Topics 哈希表 数学 字符串 
// 👍 720 👎 0


package leetcode.editor.cn;

class IntegerToRoman {
    public static void main(String[] args) {
        Solution solution = new IntegerToRoman().new Solution();
        solution.intToRoman(3);
        solution.intToRoman(4);
        solution.intToRoman(9);
        solution.intToRoman(58);
        solution.intToRoman(1994);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public String intToRoman(int num) {
//            return intToRoman_1(num);
            return intToRoman_2(num);
        }

        private String intToRoman_1(int num) {
            final int[] NUM_ARRAY = new int[]{
                    1000,
                    900, 500, 400, 100,
                    90, 50, 40, 10,
                    9, 5, 4, 1};
            final String[] STR_ARRAY = new String[]{
                    "M",
                    "CM", "D", "CD", "C",
                    "XC", "L", "XL", "X",
                    "IX", "V", "IV", "I"
            };
            StringBuilder stringBuilder = new StringBuilder();
            while (num != 0) {
                int value = 0;
                for (int i = 0; value == 0 && i < NUM_ARRAY.length; i++) {
                    if (num >= NUM_ARRAY[i]) {
                        value = NUM_ARRAY[i];
                        stringBuilder.append(STR_ARRAY[i]);
                    }
                }
                num -= value;
            }
            return stringBuilder.toString();
        }

        private String intToRoman_2(int num) {
            String[] thousands = {"", "M", "MM", "MMM"};
            String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
            String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
            String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(thousands[num / 1000]);
            stringBuilder.append(hundreds[num % 1000 / 100]);
            stringBuilder.append(tens[num % 100 / 10]);
            stringBuilder.append(ones[num % 10]);
            return stringBuilder.toString();
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}