### 📺视频题解  
![32.最长有效括号.mp4](0a7923be-03e4-4682-9851-1136a30387ae)

### 📖文字题解
#### 方法一：动态规划

**思路和算法**

我们定义 ![\textit{dp}\[i\] ](./p__textit{dp}_i__.png)  表示以下标 *i* 字符结尾的最长有效括号的长度。我们将 ![\textit{dp} ](./p__textit{dp}_.png)  数组全部初始化为 *0* 。显然有效的子串一定以 ![\text{‘)’} ](./p__text{‘_’}_.png)  结尾，因此我们可以知道以 ![\text{‘(’} ](./p__text{‘_’}_.png)  结尾的子串对应的 ![\textit{dp} ](./p__textit{dp}_.png)  值必定为 *0* ，我们只需要求解 ![\text{‘)’} ](./p__text{‘_’}_.png)  在 ![\textit{dp} ](./p__textit{dp}_.png)  数组中对应位置的值。

我们从前往后遍历字符串求解 ![\textit{dp} ](./p__textit{dp}_.png)  值，我们每两个字符检查一次：

1. ![s\[i\]=\text{‘)’} ](./p__s_i__=_text{‘_’}_.png)  且 ![s\[i-1\]=\text{‘(’} ](./p__s_i_-_1__=_text{‘_’}_.png) ，也就是字符串形如 *“……()”*，我们可以推出：
    ![\textit{dp}\[i\]=\textit{dp}\[i-2\]+2 ](./p_______textit{dp}_i_=textit{dp}_i-2_+2______.png) 
    我们可以进行这样的转移，是因为结束部分的 "()" 是一个有效子字符串，并且将之前有效子字符串的长度增加了 *2* 。

2. ![s\[i\]=\text{‘)’} ](./p__s_i__=_text{‘_’}_.png)  且 ![s\[i-1\]=\text{‘)’} ](./p__s_i_-_1__=_text{‘_’}_.png) ，也就是字符串形如 *“……))”*，我们可以推出：
    如果 ![s\[i-\textit{dp}\[i-1\]-1\]=\text{‘(’} ](./p__s_i_-_textit{dp}_i_-_1__-_1__=_text{‘_’}_.png) ，那么
    ![\textit{dp}\[i\]=\textit{dp}\[i-1\]+\textit{dp}\[i-\textit{dp}\[i-1\]-2\]+2 ](./p_______textit{dp}_i_=textit{dp}_i-1_+textit{dp}_i-textit{dp}_i-1_-2_+2______.png) 

我们考虑如果倒数第二个 ![\text{‘)’} ](./p__text{‘_’}_.png)  是一个有效子字符串的一部分（记作 *sub_s*），对于最后一个 ![\text{‘)’} ](./p__text{‘_’}_.png)  ，如果它是一个更长子字符串的一部分，那么它一定有一个对应的 ![\text{‘(’} ](./p__text{‘_’}_.png)  ，且它的位置在倒数第二个 ![\text{‘)’} ](./p__text{‘_’}_.png)  所在的有效子字符串的前面（也就是 *sub_s* 的前面）。因此，如果子字符串 *sub_s* 的前面恰好是 ![\text{‘(’} ](./p__text{‘_’}_.png)  ，那么我们就用 *2* 加上 *sub_s* 的长度（![\textit{dp}\[i-1\] ](./p__textit{dp}_i-1__.png) ）去更新 ![\textit{dp}\[i\] ](./p__textit{dp}_i__.png) 。同时，我们也会把有效子串 *“(sub_s)”* 之前的有效子串的长度也加上，也就是再加上 ![\textit{dp}\[i-\textit{dp}\[i-1\]-2\] ](./p__textit{dp}_i-textit{dp}_i-1_-2__.png) 。

最后的答案即为 ![\textit{dp} ](./p__textit{dp}_.png)  数组中的最大值。

 ![fig1](https://assets.leetcode-cn.com/solution-static/32/p1.png) ![fig2](https://assets.leetcode-cn.com/solution-static/32/p2.png) ![fig3](https://assets.leetcode-cn.com/solution-static/32/p3.png) ![fig4](https://assets.leetcode-cn.com/solution-static/32/p4.png) ![fig5](https://assets.leetcode-cn.com/solution-static/32/p5.png) ![fig6](https://assets.leetcode-cn.com/solution-static/32/p6.png) ![fig7](https://assets.leetcode-cn.com/solution-static/32/p7.png) ![fig8](https://assets.leetcode-cn.com/solution-static/32/p8.png) 


```Java [sol1-Java]
class Solution {
    public int longestValidParentheses(String s) {
        int maxans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }
}
```

```C++ [sol1-C++]
class Solution {
public:
    int longestValidParentheses(string s) {
        int maxans = 0, n = s.length();
        vector<int> dp(n, 0);
        for (int i = 1; i < n; i++) {
            if (s[i] == ')') {
                if (s[i - 1] == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s[i - dp[i - 1] - 1] == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = max(maxans, dp[i]);
            }
        }
        return maxans;
    }
};
```

```C [sol1-C]
int longestValidParentheses(char* s) {
    int maxans = 0, n = strlen(s);
    if (n == 0) return 0;
    int dp[n];
    memset(dp, 0, sizeof(dp));
    for (int i = 1; i < n; i++) {
        if (s[i] == ')') {
            if (s[i - 1] == '(') {
                dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
            } else if (i - dp[i - 1] > 0 && s[i - dp[i - 1] - 1] == '(') {
                dp[i] = dp[i - 1] +
                        ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
            }
            maxans = fmax(maxans, dp[i]);
        }
    }
    return maxans;
}
```

```golang [sol1-Golang]
func longestValidParentheses(s string) int {
    maxAns := 0
    dp := make([]int, len(s))
    for i := 1; i < len(s); i++ {
        if s[i] == ')' {
            if s[i-1] == '(' {
                if i >= 2 {
                    dp[i] = dp[i - 2] + 2
                } else {
                    dp[i] = 2
                }
            } else if i - dp[i - 1] > 0 && s[i - dp[i - 1] - 1] == '(' {
                if i - dp[i - 1] >= 2 {
                    dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2
                } else {
                    dp[i] = dp[i - 1] + 2
                }
            }
            maxAns = max(maxAns, dp[i])
        }
    }
    return maxAns
}

func max(x, y int) int {
    if x > y {
        return x
    }
    return y
}
```

**复杂度分析**

* 时间复杂度： *O(n)*，其中 *n* 为字符串的长度。我们只需遍历整个字符串一次，即可将 ![\textit{dp} ](./p__textit{dp}_.png)  数组求出来。

* 空间复杂度： *O(n)*。我们需要一个大小为 *n* 的 ![\textit{dp} ](./p__textit{dp}_.png)  数组。

#### 方法二：栈

**思路和算法**

撇开方法一提及的动态规划方法，相信大多数人对于这题的第一直觉是找到每个可能的子串后判断它的有效性，但这样的时间复杂度会达到 *O(n^3)*，无法通过所有测试用例。但是通过栈，我们可以在遍历给定字符串的过程中去判断到目前为止扫描的子串的有效性，同时能得到最长有效括号的长度。

具体做法是我们始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」，这样的做法主要是考虑了边界条件的处理，栈里其他元素维护左括号的下标：

- 对于遇到的每个 ![\text{‘(’} ](./p__text{‘_’}_.png)  ，我们将它的下标放入栈中
- 对于遇到的每个 ![\text{‘)’} ](./p__text{‘_’}_.png)  ，我们先弹出栈顶元素表示匹配了当前右括号：
  - 如果栈为空，说明当前的右括号为没有被匹配的右括号，我们将其下标放入栈中来更新我们之前提到的「最后一个没有被匹配的右括号的下标」
  - 如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」

我们从前往后遍历字符串并更新答案即可。

需要注意的是，如果一开始栈为空，第一个字符为左括号的时候我们会将其放入栈中，这样就不满足提及的「最后一个没有被匹配的右括号的下标」，为了保持统一，我们在一开始的时候往栈中放入一个值为 *-1* 的元素。

 ![ppt1](https://assets.leetcode-cn.com/solution-static/32/1.png) ![ppt2](https://assets.leetcode-cn.com/solution-static/32/2.png) ![ppt3](https://assets.leetcode-cn.com/solution-static/32/3.png) ![ppt4](https://assets.leetcode-cn.com/solution-static/32/4.png) ![ppt5](https://assets.leetcode-cn.com/solution-static/32/5.png) ![ppt6](https://assets.leetcode-cn.com/solution-static/32/6.png) ![ppt7](https://assets.leetcode-cn.com/solution-static/32/7.png) ![ppt8](https://assets.leetcode-cn.com/solution-static/32/8.png) ![ppt9](https://assets.leetcode-cn.com/solution-static/32/9.png) ![ppt10](https://assets.leetcode-cn.com/solution-static/32/10.png) ![ppt11](https://assets.leetcode-cn.com/solution-static/32/11.png) 


```Java [sol2-Java]
class Solution {
    public int longestValidParentheses(String s) {
        int maxans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }
}
```

```C++ [sol2-C++]
class Solution {
public:
    int longestValidParentheses(string s) {
        int maxans = 0;
        stack<int> stk;
        stk.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s[i] == '(') {
                stk.push(i);
            } else {
                stk.pop();
                if (stk.empty()) {
                    stk.push(i);
                } else {
                    maxans = max(maxans, i - stk.top());
                }
            }
        }
        return maxans;
    }
};
```

```C [sol2-C]
int longestValidParentheses(char* s) {
    int maxans = 0, n = strlen(s);
    int stk[n + 1], top = -1;
    stk[++top] = -1;
    for (int i = 0; i < n; i++) {
        if (s[i] == '(') {
            stk[++top] = i;
        } else {
            --top;
            if (top == -1) {
                stk[++top] = i;
            } else {
                maxans = fmax(maxans, i - stk[top]);
            }
        }
    }
    return maxans;
}
```

```golang [sol2-Golang]
func longestValidParentheses(s string) int {
    maxAns := 0
    stack := []int{}
    stack = append(stack, -1)
    for i := 0; i < len(s); i++ {
        if s[i] == '(' {
            stack = append(stack, i)
        } else {
            stack = stack[:len(stack)-1]
            if len(stack) == 0 {
                stack = append(stack, i)
            } else {
                maxAns = max(maxAns, i - stack[len(stack)-1])
            }
        }
    }
    return maxAns
}

func max(x, y int) int {
    if x > y {
        return x
    }
    return y
}
```

**复杂度分析**

* 时间复杂度： *O(n)*，*n* 是给定字符串的长度。我们只需要遍历字符串一次即可。

* 空间复杂度： *O(n)*。栈的大小在最坏情况下会达到 *n*，因此空间复杂度为 *O(n)* 。

#### 方法三：不需要额外的空间

**思路和算法**

在此方法中，我们利用两个计数器 ![\textit{left} ](./p__textit{left}_.png)  和 ![\textit{right} ](./p__textit{right}_.png)  。首先，我们从左到右遍历字符串，对于遇到的每个 ![\text{‘(’} ](./p__text{‘_’}_.png) ，我们增加 ![\textit{left} ](./p__textit{left}_.png)  计数器，对于遇到的每个 ![\text{‘)’} ](./p__text{‘_’}_.png)  ，我们增加 ![\textit{right} ](./p__textit{right}_.png)  计数器。每当 ![\textit{left} ](./p__textit{left}_.png)  计数器与 ![\textit{right} ](./p__textit{right}_.png)  计数器相等时，我们计算当前有效字符串的长度，并且记录目前为止找到的最长子字符串。当 ![\textit{right} ](./p__textit{right}_.png)  计数器比 ![\textit{left} ](./p__textit{left}_.png)  计数器大时，我们将 ![\textit{left} ](./p__textit{left}_.png)  和 ![\textit{right} ](./p__textit{right}_.png)  计数器同时变回 *0*。

这样的做法贪心地考虑了以当前字符下标结尾的有效括号长度，每次当右括号数量多于左括号数量的时候之前的字符我们都扔掉不再考虑，重新从下一个字符开始计算，但这样会漏掉一种情况，就是遍历的时候左括号的数量始终大于右括号的数量，即 `(()` ，这种时候最长有效括号是求不出来的。

解决的方法也很简单，我们只需要从右往左遍历用类似的方法计算即可，只是这个时候判断条件反了过来：

- 当 ![\textit{left} ](./p__textit{left}_.png)  计数器比 ![\textit{right} ](./p__textit{right}_.png)  计数器大时，我们将 ![\textit{left} ](./p__textit{left}_.png)  和 ![\textit{right} ](./p__textit{right}_.png)  计数器同时变回 *0* 
- 当 ![\textit{left} ](./p__textit{left}_.png)  计数器与 ![\textit{right} ](./p__textit{right}_.png)  计数器相等时，我们计算当前有效字符串的长度，并且记录目前为止找到的最长子字符串

这样我们就能涵盖所有情况从而求解出答案。

 ![f1](https://assets.leetcode-cn.com/solution-static/32/3_1.png) ![f2](https://assets.leetcode-cn.com/solution-static/32/3_2.png) ![f3](https://assets.leetcode-cn.com/solution-static/32/3_3.png) ![f4](https://assets.leetcode-cn.com/solution-static/32/3_4.png) ![f5](https://assets.leetcode-cn.com/solution-static/32/3_5.png) ![f6](https://assets.leetcode-cn.com/solution-static/32/3_6.png) ![f7](https://assets.leetcode-cn.com/solution-static/32/3_7.png) ![f8](https://assets.leetcode-cn.com/solution-static/32/3_8.png) ![f9](https://assets.leetcode-cn.com/solution-static/32/3_9.png) ![f10](https://assets.leetcode-cn.com/solution-static/32/3_10.png) ![f11](https://assets.leetcode-cn.com/solution-static/32/3_11.png) ![f12](https://assets.leetcode-cn.com/solution-static/32/3_12.png) ![f13](https://assets.leetcode-cn.com/solution-static/32/3_13.png) ![f14](https://assets.leetcode-cn.com/solution-static/32/3_14.png) ![f15](https://assets.leetcode-cn.com/solution-static/32/3_15.png) ![f16](https://assets.leetcode-cn.com/solution-static/32/3_16.png) ![f17](https://assets.leetcode-cn.com/solution-static/32/3_17.png) 

```Java [sol3-Java]
class Solution {
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }
}
```

```C++ [sol3-C++]
class Solution {
public:
    int longestValidParentheses(string s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s[i] == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = (int)s.length() - 1; i >= 0; i--) {
            if (s[i] == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }
};
```

```C [sol3-C]
int longestValidParentheses(char* s) {
    int n = strlen(s);
    int left = 0, right = 0, maxlength = 0;
    for (int i = 0; i < n; i++) {
        if (s[i] == '(') {
            left++;
        } else {
            right++;
        }
        if (left == right) {
            maxlength = fmax(maxlength, 2 * right);
        } else if (right > left) {
            left = right = 0;
        }
    }
    left = right = 0;
    for (int i = n - 1; i >= 0; i--) {
        if (s[i] == '(') {
            left++;
        } else {
            right++;
        }
        if (left == right) {
            maxlength = fmax(maxlength, 2 * left);
        } else if (left > right) {
            left = right = 0;
        }
    }
    return maxlength;
}
```

```golang [sol3-Golang]
func longestValidParentheses(s string) int {
    left, right, maxLength := 0, 0, 0
    for i := 0; i < len(s); i++ {
        if s[i] == '(' {
            left++
        } else {
            right++
        }
        if left == right {
            maxLength = max(maxLength, 2 * right)
        } else if right > left {
            left, right = 0, 0
        }
    }
    left, right = 0, 0
    for i := len(s) - 1; i >= 0; i-- {
        if s[i] == '(' {
            left++
        } else {
            right++
        }
        if left == right {
            maxLength = max(maxLength, 2 * left)
        } else if left > right {
            left, right = 0, 0
        }
    }
    return maxLength
}

func max(x, y int) int {
    if x > y {
        return x
    }
    return y
}
```

**复杂度分析**

* 时间复杂度： *O(n)*，其中 *n* 为字符串长度。我们只要正反遍历两边字符串即可。

* 空间复杂度： *O(1)*。我们只需要常数空间存放若干变量。