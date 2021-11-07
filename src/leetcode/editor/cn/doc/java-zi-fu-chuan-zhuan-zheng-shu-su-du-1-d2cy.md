### 解题思路
先转换为字符数组,用i做下标来循环遍历
定义long型来对数进行堆进操作
最后在判断该数是否溢出,转为整型输出即可
### 代码

```java
class Solution {
    public int myAtoi(String s) {
       int INT_MAX=Integer.MAX_VALUE;
        char [] charArray = s.toCharArray();//定义字符数组 去掉空格
        int i=0,flag=1;
        long res=0;//下标，符号
        while(i<charArray.length&&charArray[i]==' '){
            i++;
        }
        if (i<charArray.length&&charArray[i]=='-'){
            flag=-1;
            i++;
        }else if(i<charArray.length&&charArray[i]=='+'){
            i++;
        }
        while(i<charArray.length&&(charArray[i]>='0'&&charArray[i]<='9') && ((res/10<INT_MAX/10) || ((res/10==INT_MAX/10)&&charArray[i]<='7'))){
               res = res * 10 + (charArray[i]-'0');
               i++;
        }
      return flag==1?(res>INT_MAX?INT_MAX:(int)res): (int) (res > INT_MAX ? -1 * (INT_MAX + 1) :  -1 * res);
    }
}


```