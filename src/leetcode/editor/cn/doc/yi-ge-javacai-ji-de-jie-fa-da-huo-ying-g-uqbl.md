521111这道题其实有规律的。但是打标记的方法我看不太懂，暂时也没时间研究。有空再研究大佬们的代码。我这里有一个简单的找规律的方法。如图所示：
    ![numRows为3的情形.png](https://pic.leetcode-cn.com/1633331746-GfoQkk-numRows%E4%B8%BA3%E7%9A%84%E6%83%85%E5%BD%A2.png)
![Snipaste_2021-10-04_15-12-08.png](https://pic.leetcode-cn.com/1633331764-oKHjLO-Snipaste_2021-10-04_15-12-08.png)
![Snipaste_2021-10-04_15-15-33.png](https://pic.leetcode-cn.com/1633331770-vrtTQZ-Snipaste_2021-10-04_15-15-33.png)

看完图开始写代码，很明显，我们需要两个变量，来指示下一次跳跃的位置。
这个变量我把它设置为du1（down and up），du2。
初始时，du1 = (numRows-1) * 2（原因图里可以看出来，这是规律，我也没法解释），du2 = 0。然后进入一个小于numRows的循环。每次循环开始，要把du1减去2， 把du2加上2。然后循环拼接字符即可。代码如下：
```
class Solution {
    public String convert(String s, int numRows) {
        if(numRows >= s.length() || numRows == 1) return s;  //如果numRows比字符串长或者numRows等于1，那直接返回s本身
        char[] cc = s.toCharArray();  //将字符串s转为数组，这样效率高
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < numRows; ++i){
            //设置du1、du2
            int du1 = (numRows - 1) *2 - 2*i;
            int du2 = 2*i;
            sb.append(cc[i]); //先将第一列的字符拼进去
            int pos1 = i + du1, pos2 = pos1 + du2;   //pos1代表第二列字符的位置，pos2代表第三列字符
            if(du1 != 0 && du2 != 0){
                while(pos1 < cc.length && pos2 < cc.length){
                    sb.append(cc[pos1]); 
                    pos1 = pos2+du1;    //pos1前进到第四列
                    sb.append(cc[pos2]);
                    pos2 = pos1 + du2;   //pos2前进到第五列，循环往复直至边界
                }
                //因为上边是与运算符，如果只有一个pos不满足条件，则可能另一个还没拼接，加上它
                if(pos1 < cc.length) sb.append(cc[pos1]);   
                if(pos2 < cc.length) sb.append(cc[pos2]);
            }else if(du2 == 0){          //如果有一个du变量为0，则只需要使用另一个不为0的即可
                while(pos1 < cc.length){
                    sb.append(cc[pos1]);
                    pos1 += du1;
                }
            }else if(du1 == 0){
                while(pos2 < cc.length){
                    sb.append(cc[pos2]);
                    pos2 += du2;
                }
            }
        }
        return sb.toString();
    }
}
```
然后放一下执行结果（最好的一次嘿嘿，平均执行用时和内存消耗均90+%）
![Snipaste_2021-10-04_14-55-17.png](https://pic.leetcode-cn.com/1633332277-jVbVWl-Snipaste_2021-10-04_14-55-17.png)

