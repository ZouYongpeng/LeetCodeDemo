### 📺 视频题解 
![...14 最长公共前缀 仲耀晖 3.mp4](8edae0a8-6480-4128-8244-9f10b83e7cf6)


### 📖 文字题解
#### 方法一：横向扫描

用 ![\textit{LCP}(S_1\ldotsS_n) ](./p__textit{LCP}_S_1_ldots_S_n__.png)  表示字符串 ![S_1\ldotsS_n ](./p__S_1_ldots_S_n_.png)  的最长公共前缀。

可以得到以下结论：

![\textit{LCP}(S_1\ldotsS_n)=\textit{LCP}(\textit{LCP}(\textit{LCP}(S_1,S_2),S_3),\ldotsS_n) ](./p___textit{LCP}_S_1_ldots_S_n__=_textit{LCP}_textit{LCP}_textit{LCP}_S_1,_S_2_,S_3_,ldots_S_n___.png) 

基于该结论，可以得到一种查找字符串数组中的最长公共前缀的简单方法。依次遍历字符串数组中的每个字符串，对于每个遍历到的字符串，更新最长公共前缀，当遍历完所有的字符串以后，即可得到字符串数组中的最长公共前缀。

![fig1](https://assets.leetcode-cn.com/solution-static/14/14_fig1.png)

如果在尚未遍历完所有的字符串时，最长公共前缀已经是空串，则最长公共前缀一定是空串，因此不需要继续遍历剩下的字符串，直接返回空串即可。

```Java [sol1-Java]
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    public String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }
}
```

```C++ [sol1-C++]
class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        if (!strs.size()) {
            return "";
        }
        string prefix = strs[0];
        int count = strs.size();
        for (int i = 1; i < count; ++i) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (!prefix.size()) {
                break;
            }
        }
        return prefix;
    }

    string longestCommonPrefix(const string& str1, const string& str2) {
        int length = min(str1.size(), str2.size());
        int index = 0;
        while (index < length && str1[index] == str2[index]) {
            ++index;
        }
        return str1.substr(0, index);
    }
};
```

```Python [sol1-Python3]
class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        if not strs:
            return ""
        
        prefix, count = strs[0], len(strs)
        for i in range(1, count):
            prefix = self.lcp(prefix, strs[i])
            if not prefix:
                break
        
        return prefix

    def lcp(self, str1, str2):
        length, index = min(len(str1), len(str2)), 0
        while index < length and str1[index] == str2[index]:
            index += 1
        return str1[:index]
```

```golang [sol1-Golang]
func longestCommonPrefix(strs []string) string {
    if len(strs) == 0 {
        return ""
    }
    prefix := strs[0]
    count := len(strs)
    for i := 1; i < count; i++ {
        prefix = lcp(prefix, strs[i])
        if len(prefix) == 0 {
            break
        }
    }
    return prefix
}

func lcp(str1, str2 string) string {
    length := min(len(str1), len(str2))
    index := 0
    for index < length && str1[index] == str2[index] {
        index++
    }
    return str1[:index]
}

func min(x, y int) int {
    if x < y {
        return x
    }
    return y
}
```

**复杂度分析**

- 时间复杂度：*O(mn)*，其中 *m* 是字符串数组中的字符串的平均长度，*n* 是字符串的数量。最坏情况下，字符串数组中的每个字符串的每个字符都会被比较一次。

- 空间复杂度：*O(1)*。使用的额外空间复杂度为常数。

#### 方法二：纵向扫描

方法一是横向扫描，依次遍历每个字符串，更新最长公共前缀。另一种方法是纵向扫描。纵向扫描时，从前往后遍历所有字符串的每一列，比较相同列上的字符是否相同，如果相同则继续对下一列进行比较，如果不相同则当前列不再属于公共前缀，当前列之前的部分为最长公共前缀。

![fig2](https://assets.leetcode-cn.com/solution-static/14/14_fig2.png)

```Java [sol2-Java]
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int length = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
```

```C++ [sol2-C++]
class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        if (!strs.size()) {
            return "";
        }
        int length = strs[0].size();
        int count = strs.size();
        for (int i = 0; i < length; ++i) {
            char c = strs[0][i];
            for (int j = 1; j < count; ++j) {
                if (i == strs[j].size() || strs[j][i] != c) {
                    return strs[0].substr(0, i);
                }
            }
        }
        return strs[0];
    }
};
```

```Python [sol2-Python3]
class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        if not strs:
            return ""
        
        length, count = len(strs[0]), len(strs)
        for i in range(length):
            c = strs[0][i]
            if any(i == len(strs[j]) or strs[j][i] != c for j in range(1, count)):
                return strs[0][:i]
        
        return strs[0]
```

```golang [sol2-Golang]
func longestCommonPrefix(strs []string) string {
    if len(strs) == 0 {
        return ""
    }
    for i := 0; i < len(strs[0]); i++ {
        for j := 1; j < len(strs); j++ {
            if i == len(strs[j]) || strs[j][i] != strs[0][i] {
                return strs[0][:i]
            }
        }
    }
    return strs[0]
}
```

**复杂度分析**

- 时间复杂度：*O(mn)*，其中 *m* 是字符串数组中的字符串的平均长度，*n* 是字符串的数量。最坏情况下，字符串数组中的每个字符串的每个字符都会被比较一次。

- 空间复杂度：*O(1)*。使用的额外空间复杂度为常数。

#### 方法三：分治

注意到 ![\textit{LCP} ](./p__textit{LCP}_.png)  的计算满足结合律，有以下结论：

![\textit{LCP}(S_1\ldotsS_n)=\textit{LCP}(\textit{LCP}(S_1\ldotsS_k),\textit{LCP}(S_{k+1}\ldotsS_n)) ](./p___textit{LCP}_S_1_ldots_S_n__=_textit{LCP}_textit{LCP}_S_1_ldots_S_k_,_textit{LCP}__S_{k+1}_ldots_S_n____.png) 

其中 ![\textit{LCP}(S_1\ldotsS_n) ](./p__textit{LCP}_S_1_ldots_S_n__.png)  是字符串 ![S_1\ldotsS_n ](./p__S_1_ldots_S_n_.png)  的最长公共前缀，*1 < k < n*。

基于上述结论，可以使用分治法得到字符串数组中的最长公共前缀。对于问题 ![\textit{LCP}(S_i\cdotsS_j) ](./p__textit{LCP}_S_icdots_S_j__.png) ，可以分解成两个子问题 ![\textit{LCP}(S_i\ldotsS_{mid}) ](./p__textit{LCP}_S_i_ldots_S_{mid}__.png)  与 ![\textit{LCP}(S_{mid+1}\ldotsS_j) ](./p__textit{LCP}_S_{mid+1}_ldots_S_j__.png) ，其中 ![mid=\frac{i+j}{2} ](./p__mid=frac{i+j}{2}_.png) 。对两个子问题分别求解，然后对两个子问题的解计算最长公共前缀，即为原问题的解。

![fig3](https://assets.leetcode-cn.com/solution-static/14/14_fig3.png)

```Java [sol3-Java]
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        } else {
            return longestCommonPrefix(strs, 0, strs.length - 1);
        }
    }

    public String longestCommonPrefix(String[] strs, int start, int end) {
        if (start == end) {
            return strs[start];
        } else {
            int mid = (end - start) / 2 + start;
            String lcpLeft = longestCommonPrefix(strs, start, mid);
            String lcpRight = longestCommonPrefix(strs, mid + 1, end);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    public String commonPrefix(String lcpLeft, String lcpRight) {
        int minLength = Math.min(lcpLeft.length(), lcpRight.length());       
        for (int i = 0; i < minLength; i++) {
            if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
                return lcpLeft.substring(0, i);
            }
        }
        return lcpLeft.substring(0, minLength);
    }
}
```

```C++ [sol3-C++]
class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        if (!strs.size()) {
            return "";
        }
        else {
            return longestCommonPrefix(strs, 0, strs.size() - 1);
        }
    }

    string longestCommonPrefix(const vector<string>& strs, int start, int end) {
        if (start == end) {
            return strs[start];
        }
        else {
            int mid = (start + end) / 2;
            string lcpLeft = longestCommonPrefix(strs, start, mid);
            string lcpRight = longestCommonPrefix(strs, mid + 1, end);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    string commonPrefix(const string& lcpLeft, const string& lcpRight) {
        int minLength = min(lcpLeft.size(), lcpRight.size());
        for (int i = 0; i < minLength; ++i) {
            if (lcpLeft[i] != lcpRight[i]) {
                return lcpLeft.substr(0, i);
            }
        }
        return lcpLeft.substr(0, minLength);
    }
};
```

```Python [sol3-Python3]
class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        def lcp(start, end):
            if start == end:
                return strs[start]

            mid = (start + end) // 2
            lcpLeft, lcpRight = lcp(start, mid), lcp(mid + 1, end)
            minLength = min(len(lcpLeft), len(lcpRight))
            for i in range(minLength):
                if lcpLeft[i] != lcpRight[i]:
                    return lcpLeft[:i]

            return lcpLeft[:minLength]

        return "" if not strs else lcp(0, len(strs) - 1)
```

```golang [sol3-Golang]
func longestCommonPrefix(strs []string) string {
    if len(strs) == 0 {
        return ""
    }
    var lcp func(int, int) string
    lcp = func(start, end int) string {
        if start == end {
            return strs[start]
        }
        mid := (start + end) / 2
        lcpLeft, lcpRight := lcp(start, mid), lcp(mid + 1, end)
        minLength := min(len(lcpLeft), len(lcpRight))
        for i := 0; i < minLength; i++ {
            if lcpLeft[i] != lcpRight[i] {
                return lcpLeft[:i]
            }
        }
        return lcpLeft[:minLength]
    }
    return lcp(0, len(strs)-1)
}

func min(x, y int) int {
    if x < y {
        return x
    }
    return y
}
```

**复杂度分析**

- 时间复杂度：*O(mn)*，其中 *m* 是字符串数组中的字符串的平均长度，*n* 是字符串的数量。时间复杂度的递推式是 ![T(n)=2\cdotT(\frac{n}{2})+O(m) ](./p__T_n_=2_cdot_T_frac{n}{2}_+O_m__.png) ，通过计算可得 *T(n)=O(mn)*。

- 空间复杂度：![O(m\logn) ](./p__O_m_log_n__.png) ，其中 *m* 是字符串数组中的字符串的平均长度，*n* 是字符串的数量。空间复杂度主要取决于递归调用的层数，层数最大为 ![\logn ](./p__log_n_.png) ，每层需要 *m* 的空间存储返回结果。

#### 方法四：二分查找

显然，最长公共前缀的长度不会超过字符串数组中的最短字符串的长度。用 ![\textit{minLength} ](./p__textit{minLength}_.png)  表示字符串数组中的最短字符串的长度，则可以在 ![\[0,\textit{minLength}\] ](./p___0,textit{minLength}__.png)  的范围内通过二分查找得到最长公共前缀的长度。每次取查找范围的中间值 ![\textit{mid} ](./p__textit{mid}_.png) ，判断每个字符串的长度为 ![\textit{mid} ](./p__textit{mid}_.png)  的前缀是否相同，如果相同则最长公共前缀的长度一定大于或等于 ![\textit{mid} ](./p__textit{mid}_.png) ，如果不相同则最长公共前缀的长度一定小于 ![\textit{mid} ](./p__textit{mid}_.png) ，通过上述方式将查找范围缩小一半，直到得到最长公共前缀的长度。

![fig4](https://assets.leetcode-cn.com/solution-static/14/14_fig4.png)

```Java [sol4-Java]
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }
        int low = 0, high = minLength;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (isCommonPrefix(strs, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return strs[0].substring(0, low);
    }

    public boolean isCommonPrefix(String[] strs, int length) {
        String str0 = strs[0].substring(0, length);
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            String str = strs[i];
            for (int j = 0; j < length; j++) {
                if (str0.charAt(j) != str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

```C++ [sol4-C++]
class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        if (!strs.size()) {
            return "";
        }
        int minLength = min_element(strs.begin(), strs.end(), [](const string& s, const string& t) {return s.size() < t.size();})->size();
        int low = 0, high = minLength;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (isCommonPrefix(strs, mid)) {
                low = mid;
            }
            else {
                high = mid - 1;
            }
        }
        return strs[0].substr(0, low);
    }

    bool isCommonPrefix(const vector<string>& strs, int length) {
        string str0 = strs[0].substr(0, length);
        int count = strs.size();
        for (int i = 1; i < count; ++i) {
            string str = strs[i];
            for (int j = 0; j < length; ++j) {
                if (str0[j] != str[j]) {
                    return false;
                }
            }
        }
        return true;
    }
};
```

```Python [sol4-Python3]
class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        def isCommonPrefix(length):
            str0, count = strs[0][:length], len(strs)
            return all(strs[i][:length] == str0 for i in range(1, count))

        if not strs:
            return ""

        minLength = min(len(s) for s in strs)
        low, high = 0, minLength
        while low < high:
            mid = (high - low + 1) // 2 + low
            if isCommonPrefix(mid):
                low = mid
            else:
                high = mid - 1

        return strs[0][:low]
```

```golang [sol4-Golang]
func longestCommonPrefix(strs []string) string {
    if len(strs) == 0 {
        return ""
    }

    isCommonPrefix := func(length int) bool {
        str0, count := strs[0][:length], len(strs)
        for i := 1; i < count; i++ {
            if strs[i][:length] != str0 {
                return false
            }
        }
        return true
    }
    minLength := len(strs[0])
    for _, s := range strs {
        if len(s) < minLength {
            minLength = len(s)
        }
    }
    low, high := 0, minLength
    for low < high {
        mid := (high - low + 1) / 2 + low
        if isCommonPrefix(mid) {
            low = mid
        } else {
            high = mid - 1
        }
    }
    return strs[0][:low]
}
```

**复杂度分析**

- 时间复杂度：![O(mn\logm) ](./p__O_mn_log_m__.png) ，其中 *m* 是字符串数组中的字符串的最小长度，*n* 是字符串的数量。二分查找的迭代执行次数是 ![O(\logm) ](./p__O_log_m__.png) ，每次迭代最多需要比较 *mn* 个字符，因此总时间复杂度是 ![O(mn\logm) ](./p__O_mn_log_m__.png) 。

- 空间复杂度：*O(1)*。使用的额外空间复杂度为常数。