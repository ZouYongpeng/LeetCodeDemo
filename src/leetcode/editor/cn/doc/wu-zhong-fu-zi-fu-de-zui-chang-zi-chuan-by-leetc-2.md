### 📺 视频题解  
![3. 无重复字符的最长子串_2.mp4](fc6033f3-e9c6-41d7-b660-328fc288d2e2)

### 📖 文字题解
#### 方法一：滑动窗口

**思路和算法**

我们先用一个例子考虑如何在较优的时间复杂度内通过本题。

我们不妨以示例一中的字符串 ![\texttt{abcabcbb} ](./p__texttt{abcabcbb}_.png)  为例，找出**从每一个字符开始的，不包含重复字符的最长子串**，那么其中最长的那个字符串即为答案。对于示例一中的字符串，我们列举出这些结果，其中括号中表示选中的字符以及最长的字符串：

- 以 ![\texttt{(a)bcabcbb} ](./p__texttt{_a_bcabcbb}_.png)  开始的最长字符串为 ![\texttt{(abc)abcbb} ](./p__texttt{_abc_abcbb}_.png) ；
- 以 ![\texttt{a(b)cabcbb} ](./p__texttt{a_b_cabcbb}_.png)  开始的最长字符串为 ![\texttt{a(bca)bcbb} ](./p__texttt{a_bca_bcbb}_.png) ；
- 以 ![\texttt{ab(c)abcbb} ](./p__texttt{ab_c_abcbb}_.png)  开始的最长字符串为 ![\texttt{ab(cab)cbb} ](./p__texttt{ab_cab_cbb}_.png) ；
- 以 ![\texttt{abc(a)bcbb} ](./p__texttt{abc_a_bcbb}_.png)  开始的最长字符串为 ![\texttt{abc(abc)bb} ](./p__texttt{abc_abc_bb}_.png) ；
- 以 ![\texttt{abca(b)cbb} ](./p__texttt{abca_b_cbb}_.png)  开始的最长字符串为 ![\texttt{abca(bc)bb} ](./p__texttt{abca_bc_bb}_.png) ；
- 以 ![\texttt{abcab(c)bb} ](./p__texttt{abcab_c_bb}_.png)  开始的最长字符串为 ![\texttt{abcab(cb)b} ](./p__texttt{abcab_cb_b}_.png) ；
- 以 ![\texttt{abcabc(b)b} ](./p__texttt{abcabc_b_b}_.png)  开始的最长字符串为 ![\texttt{abcabc(b)b} ](./p__texttt{abcabc_b_b}_.png) ；
- 以 ![\texttt{abcabcb(b)} ](./p__texttt{abcabcb_b_}_.png)  开始的最长字符串为 ![\texttt{abcabcb(b)} ](./p__texttt{abcabcb_b_}_.png) 。

发现了什么？如果我们依次递增地枚举子串的起始位置，那么子串的结束位置也是递增的！这里的原因在于，假设我们选择字符串中的第 *k* 个字符作为起始位置，并且得到了不包含重复字符的最长子串的结束位置为 *r_k*。那么当我们选择第 *k+1* 个字符作为起始位置时，首先从 *k+1* 到 *r_k* 的字符显然是不重复的，并且由于少了原本的第 *k* 个字符，我们可以尝试继续增大 *r_k*，直到右侧出现了重复字符为止。

这样一来，我们就可以使用「滑动窗口」来解决这个问题了：

- 我们使用两个指针表示字符串中的某个子串（或窗口）的左右边界，其中左指针代表着上文中「枚举子串的起始位置」，而右指针即为上文中的 *r_k*；

- 在每一步的操作中，我们会将左指针向右移动一格，表示 **我们开始枚举下一个字符作为起始位置**，然后我们可以不断地向右移动右指针，但需要保证这两个指针对应的子串中没有重复的字符。在移动结束后，这个子串就对应着 **以左指针开始的，不包含重复字符的最长子串**。我们记录下这个子串的长度；

- 在枚举结束后，我们找到的最长的子串的长度即为答案。

**判断重复字符**

在上面的流程中，我们还需要使用一种数据结构来判断 **是否有重复的字符**，常用的数据结构为哈希集合（即 `C++` 中的 `std::unordered_set`，`Java` 中的 `HashSet`，`Python` 中的 `set`, `JavaScript` 中的 `Set`）。在左指针向右移动的时候，我们从哈希集合中移除一个字符，在右指针向右移动的时候，我们往哈希集合中添加一个字符。

至此，我们就完美解决了本题。

```C++ [sol1-C++]
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        // 哈希集合，记录每个字符是否出现过
        unordered_set<char> occ;
        int n = s.size();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        // 枚举左指针的位置，初始值隐性地表示为 -1
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.erase(s[i - 1]);
            }
            while (rk + 1 < n && !occ.count(s[rk + 1])) {
                // 不断地移动右指针
                occ.insert(s[rk + 1]);
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = max(ans, rk - i + 1);
        }
        return ans;
    }
};
```

```Java [sol1-Java]
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
}
```

```Python [sol1-Python3]
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        # 哈希集合，记录每个字符是否出现过
        occ = set()
        n = len(s)
        # 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        rk, ans = -1, 0
        for i in range(n):
            if i != 0:
                # 左指针向右移动一格，移除一个字符
                occ.remove(s[i - 1])
            while rk + 1 < n and s[rk + 1] not in occ:
                # 不断地移动右指针
                occ.add(s[rk + 1])
                rk += 1
            # 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = max(ans, rk - i + 1)
        return ans
```

```JavaScript [sol1-JavaScript]
var lengthOfLongestSubstring = function(s) {
    // 哈希集合，记录每个字符是否出现过
    const occ = new Set();
    const n = s.length;
    // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
    let rk = -1, ans = 0;
    for (let i = 0; i < n; ++i) {
        if (i != 0) {
            // 左指针向右移动一格，移除一个字符
            occ.delete(s.charAt(i - 1));
        }
        while (rk + 1 < n && !occ.has(s.charAt(rk + 1))) {
            // 不断地移动右指针
            occ.add(s.charAt(rk + 1));
            ++rk;
        }
        // 第 i 到 rk 个字符是一个极长的无重复字符子串
        ans = Math.max(ans, rk - i + 1);
    }
    return ans;
};
```

```golang [sol1-Golang]
func lengthOfLongestSubstring(s string) int {
    // 哈希集合，记录每个字符是否出现过
    m := map[byte]int{}
    n := len(s)
    // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
    rk, ans := -1, 0
    for i := 0; i < n; i++ {
        if i != 0 {
            // 左指针向右移动一格，移除一个字符
            delete(m, s[i-1])
        }
        for rk + 1 < n && m[s[rk+1]] == 0 {
            // 不断地移动右指针
            m[s[rk+1]]++
            rk++
        }
        // 第 i 到 rk 个字符是一个极长的无重复字符子串
        ans = max(ans, rk - i + 1)
    }
    return ans
}

func max(x, y int) int {
    if x < y {
        return y
    }
    return x
}
```

**复杂度分析**

- 时间复杂度：*O(N)*，其中 *N* 是字符串的长度。左指针和右指针分别会遍历整个字符串一次。

- 空间复杂度：![O(|\Sigma|) ](./p__O_|Sigma|__.png) ，其中 ![\Sigma ](./p__Sigma_.png)  表示字符集（即字符串中可以出现的字符），![|\Sigma| ](./p__|Sigma|_.png)  表示字符集的大小。在本题中没有明确说明字符集，因此可以默认为所有 ASCII 码在 *[0, 128)* 内的字符，即 ![|\Sigma|=128 ](./p__|Sigma|_=_128_.png) 。我们需要用到哈希集合来存储出现过的字符，而字符最多有 ![|\Sigma| ](./p__|Sigma|_.png)  个，因此空间复杂度为 ![O(|\Sigma|) ](./p__O_|Sigma|__.png) 。