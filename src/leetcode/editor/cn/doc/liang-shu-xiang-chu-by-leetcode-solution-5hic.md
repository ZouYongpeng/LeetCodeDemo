### 📺 视频题解  
![29.两数相除-沈老师的副本.mp4](121a473d-cf86-44b3-9536-d5d17c003be8)

### 📖 文字题解
#### 前言

由于题目规定了「只能存储 *32* 位整数」，本题解的正文部分和代码中都不会使用任何 *64* 位整数。**诚然，使用 *64* 位整数可以极大地方便我们的编码，但这是违反题目规则的。**

如果除法结果溢出，那么我们需要返回 *2^{31} - 1* 作为答案。因此在编码之前，我们可以首先对于溢出或者容易出错的边界情况进行讨论：

- 当被除数为 *32* 位有符号整数的最小值 *-2^{31}* 时：

    - 如果除数为 *1*，那么我们可以直接返回答案 *-2^{31}*；
    - 如果除数为 *-1*，那么答案为 *2^{31}*，产生了溢出。此时我们需要返回 *2^{31} - 1*。

- 当除数为 *32* 位有符号整数的最小值 *-2^{31}* 时：

    - 如果被除数同样为 *-2^{31}*，那么我们可以直接返回答案 *1*；
    - 对于其余的情况，我们返回答案 *0*。

- 当被除数为 *0* 时，我们可以直接返回答案 *0*。

对于一般的情况，根据除数和被除数的符号，我们需要考虑 *4* 种不同的可能性。因此，为了方便编码，我们可以将被除数或者除数取相反数，使得它们符号相同。

如果我们将被除数和除数都变为正数，那么可能会导致溢出。例如当被除数为 *-2^{31}* 时，它的相反数 *2^{31}* 产生了溢出。因此，我们可以考虑将被除数和除数都变为负数，这样就不会有溢出的问题，在编码时只需要考虑 *1* 种情况了。

如果我们将被除数和除数的其中（恰好）一个变为了正数，那么在返回答案之前，我们需要对答案也取相反数。

#### 方法一：二分查找

**思路与算法**

根据「前言」部分的讨论，我们记被除数为 *X*，除数为 *Y*，并且 *X* 和 *Y* 都是负数。我们需要找出 *X/Y* 的结果 *Z*。*Z* 一定是正数或 *0*。

根据除法以及余数的定义，我们可以将其改成乘法的等价形式，即：

![Z\timesY\geqX>(Z+1)\timesY ](./p___Z_times_Y_geq_X____Z+1__times_Y__.png) 

因此，我们可以使用二分查找的方法得到 *Z*，即找出**最大**的 *Z* 使得 ![Z\timesY\geqX ](./p__Z_times_Y_geq_X_.png)  成立。

由于我们不能使用乘法运算符，因此我们需要使用「快速乘」算法得到 ![Z\timesY ](./p__Z_times_Y_.png)  的值。「快速乘」算法与「快速幂」类似，前者通过加法实现乘法，后者通过乘法实现幂运算。「快速幂」算法可以参考[「50. Pow(x, n)」的官方题解](https://leetcode-cn.com/problems/powx-n/solution/powx-n-by-leetcode-solution/)，「快速乘」算法只需要在「快速幂」算法的基础上，将乘法运算改成加法运算即可。

**细节**

由于我们只能使用 *32* 位整数，因此二分查找中会有很多细节。

首先，二分查找的下界为 *1*，上界为 *2^{31} - 1*。唯一可能出现的答案为 *2^{31}* 的情况已经被我们在「前言」部分进行了特殊处理，因此答案的最大值为 *2^{31} - 1*。如果二分查找失败，那么答案一定为 *0*。

在实现「快速乘」时，我们需要使用加法运算，然而较大的 *Z* 也会导致加法运算溢出。例如我们要判断 *A + B* 是否小于 *C* 时（其中 *A, B, C* 均为负数），*A + B* 可能会产生溢出，因此我们必须将判断改为 *A < C - B* 是否成立。由于任意两个负数的差一定在 *[-2^{31} + 1, 2^{31} - 1]* 范围内，这样就不会产生溢出。

读者可以阅读下面的代码和注释，理解如何避免使用乘法和除法，以及正确处理溢出问题。

**代码**

```C++ [sol1-C++]
class Solution {
public:
    int divide(int dividend, int divisor) {
        // 考虑被除数为最小值的情况
        if (dividend == INT_MIN) {
            if (divisor == 1) {
                return INT_MIN;
            }
            if (divisor == -1) {
                return INT_MAX;
            }
        }
        // 考虑除数为最小值的情况
        if (divisor == INT_MIN) {
            return dividend == INT_MIN ? 1 : 0;
        }
        // 考虑被除数为 0 的情况
        if (dividend == 0) {
            return 0;
        }
        
        // 一般情况，使用二分查找
        // 将所有的正数取相反数，这样就只需要考虑一种情况
        bool rev = false;
        if (dividend > 0) {
            dividend = -dividend;
            rev = !rev;
        }
        if (divisor > 0) {
            divisor = -divisor;
            rev = !rev;
        }

        // 快速乘
        auto quickAdd = [](int y, int z, int x) {
            // x 和 y 是负数，z 是正数
            // 需要判断 z * y >= x 是否成立
            int result = 0, add = y;
            while (z) {
                if (z & 1) {
                    // 需要保证 result + add >= x
                    if (result < x - add) {
                        return false;
                    }
                    result += add;
                }
                if (z != 1) {
                    // 需要保证 add + add >= x
                    if (add < x - add) {
                        return false;
                    }
                    add += add;
                }
                // 不能使用除法
                z >>= 1;
            }
            return true;
        };
        
        int left = 1, right = INT_MAX, ans = 0;
        while (left <= right) {
            // 注意溢出，并且不能使用除法
            int mid = left + ((right - left) >> 1);
            bool check = quickAdd(divisor, mid, dividend);
            if (check) {
                ans = mid;
                // 注意溢出
                if (mid == INT_MAX) {
                    break;
                }
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return rev ? -ans : ans;
    }
};
```

```Java [sol1-Java]
class Solution {
    public int divide(int dividend, int divisor) {
        // 考虑被除数为最小值的情况
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        // 考虑除数为最小值的情况
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        // 考虑被除数为 0 的情况
        if (dividend == 0) {
            return 0;
        }
        
        // 一般情况，使用二分查找
        // 将所有的正数取相反数，这样就只需要考虑一种情况
        boolean rev = false;
        if (dividend > 0) {
            dividend = -dividend;
            rev = !rev;
        }
        if (divisor > 0) {
            divisor = -divisor;
            rev = !rev;
        }
        
        int left = 1, right = Integer.MAX_VALUE, ans = 0;
        while (left <= right) {
            // 注意溢出，并且不能使用除法
            int mid = left + ((right - left) >> 1);
            boolean check = quickAdd(divisor, mid, dividend);
            if (check) {
                ans = mid;
                // 注意溢出
                if (mid == Integer.MAX_VALUE) {
                    break;
                }
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return rev ? -ans : ans;
    }

    // 快速乘
    public boolean quickAdd(int y, int z, int x) {
        // x 和 y 是负数，z 是正数
        // 需要判断 z * y >= x 是否成立
        int result = 0, add = y;
        while (z != 0) {
            if ((z & 1) != 0) {
                // 需要保证 result + add >= x
                if (result < x - add) {
                    return false;
                }
                result += add;
            }
            if (z != 1) {
                // 需要保证 add + add >= x
                if (add < x - add) {
                    return false;
                }
                add += add;
            }
            // 不能使用除法
            z >>= 1;
        }
        return true;
    }
}
```

```C# [sol1-C#]
public class Solution {
    public int Divide(int dividend, int divisor) {
        // 考虑被除数为最小值的情况
        if (dividend == int.MinValue) {
            if (divisor == 1) {
                return int.MinValue;
            }
            if (divisor == -1) {
                return int.MaxValue;
            }
        }
        // 考虑除数为最小值的情况
        if (divisor == int.MinValue) {
            return dividend == int.MinValue ? 1 : 0;
        }
        // 考虑被除数为 0 的情况
        if (dividend == 0) {
            return 0;
        }
        
        // 一般情况，使用二分查找
        // 将所有的正数取相反数，这样就只需要考虑一种情况
        bool rev = false;
        if (dividend > 0) {
            dividend = -dividend;
            rev = !rev;
        }
        if (divisor > 0) {
            divisor = -divisor;
            rev = !rev;
        }
        
        int left = 1, right = int.MaxValue, ans = 0;
        while (left <= right) {
            // 注意溢出，并且不能使用除法
            int mid = left + ((right - left) >> 1);
            bool check = quickAdd(divisor, mid, dividend);
            if (check) {
                ans = mid;
                // 注意溢出
                if (mid == int.MaxValue) {
                    break;
                }
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return rev ? -ans : ans;
    }

    // 快速乘
    public bool quickAdd(int y, int z, int x) {
        // x 和 y 是负数，z 是正数
        // 需要判断 z * y >= x 是否成立
        int result = 0, add = y;
        while (z != 0) {
            if ((z & 1) != 0) {
                // 需要保证 result + add >= x
                if (result < x - add) {
                    return false;
                }
                result += add;
            }
            if (z != 1) {
                // 需要保证 add + add >= x
                if (add < x - add) {
                    return false;
                }
                add += add;
            }
            // 不能使用除法
            z >>= 1;
        }
        return true;
    }
}
```

```Python [sol1-Python3]
class Solution:
    def divide(self, dividend: int, divisor: int) -> int:
        INT_MIN, INT_MAX = -2**31, 2**31 - 1

        # 考虑被除数为最小值的情况
        if dividend == INT_MIN:
            if divisor == 1:
                return INT_MIN
            if divisor == -1:
                return INT_MAX
        
        # 考虑除数为最小值的情况
        if divisor == INT_MIN:
            return 1 if dividend == INT_MIN else 0
        # 考虑被除数为 0 的情况
        if dividend == 0:
            return 0
        
        # 一般情况，使用二分查找
        # 将所有的正数取相反数，这样就只需要考虑一种情况
        rev = False
        if dividend > 0:
            dividend = -dividend
            rev = not rev
        if divisor > 0:
            divisor = -divisor
            rev = not rev

        # 快速乘
        def quickAdd(y: int, z: int, x: int) -> bool:
            # x 和 y 是负数，z 是正数
            # 需要判断 z * y >= x 是否成立
            result, add = 0, y
            while z > 0:
                if (z & 1) == 1:
                    # 需要保证 result + add >= x
                    if result < x - add:
                        return False
                    result += add
                if z != 1:
                    # 需要保证 add + add >= x
                    if add < x - add:
                        return False
                    add += add
                # 不能使用除法
                z >>= 1
            return True
        
        left, right, ans = 1, INT_MAX, 0
        while left <= right:
            # 注意溢出，并且不能使用除法
            mid = left + ((right - left) >> 1)
            check = quickAdd(divisor, mid, dividend)
            if check:
                ans = mid
                # 注意溢出
                if mid == INT_MAX:
                    break
                left = mid + 1
            else:
                right = mid - 1

        return -ans if rev else ans
```

```go [sol1-Golang]
// 快速乘
// x 和 y 是负数，z 是正数
// 判断 z * y >= x 是否成立
func quickAdd(y, z, x int) bool {
    for result, add := 0, y; z > 0; z >>= 1 { // 不能使用除法
        if z&1 > 0 {
            // 需要保证 result + add >= x
            if result < x-add {
                return false
            }
            result += add
        }
        if z != 1 {
            // 需要保证 add + add >= x
            if add < x-add {
                return false
            }
            add += add
        }
    }
    return true
}

func divide(dividend, divisor int) int {
    if dividend == math.MinInt32 { // 考虑被除数为最小值的情况
        if divisor == 1 {
            return math.MinInt32
        }
        if divisor == -1 {
            return math.MaxInt32
        }
    }
    if divisor == math.MinInt32 { // 考虑除数为最小值的情况
        if dividend == math.MinInt32 {
            return 1
        }
        return 0
    }
    if dividend == 0 { // 考虑被除数为 0 的情况
        return 0
    }

    // 一般情况，使用二分查找
    // 将所有的正数取相反数，这样就只需要考虑一种情况
    rev := false
    if dividend > 0 {
        dividend = -dividend
        rev = !rev
    }
    if divisor > 0 {
        divisor = -divisor
        rev = !rev
    }

    ans := 0
    left, right := 1, math.MaxInt32
    for left <= right {
        mid := left + (right-left)>>1 // 注意溢出，并且不能使用除法
        if quickAdd(divisor, mid, dividend) {
            ans = mid
            if mid == math.MaxInt32 { // 注意溢出
                break
            }
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    if rev {
        return -ans
    }
    return ans
}
```

```JavaScript [sol1-JavaScript]
var divide = function(dividend, divisor) {
    const MAX_VALUE = 2 ** 31 - 1, MIN_VALUE = -(2 ** 31);
    // 考虑被除数为最小值的情况
    if (dividend === MIN_VALUE) {
        if (divisor === 1) {
            return MIN_VALUE;
        }
        if (divisor === -1) {
            return MAX_VALUE;
        }
    }
    // 考虑除数为最小值的情况
    if (divisor === MIN_VALUE) {
        return dividend === MIN_VALUE ? 1 : 0;
    }
    // 考虑被除数为 0 的情况
    if (dividend === 0) {
        return 0;
    }
    
    // 一般情况，使用二分查找
    // 将所有的正数取相反数，这样就只需要考虑一种情况
    let rev = false;
    if (dividend > 0) {
        dividend = -dividend;
        rev = !rev;
    }
    if (divisor > 0) {
        divisor = -divisor;
        rev = !rev;
    }
    
    let left = 1, right = MAX_VALUE, ans = 0;
    while (left <= right) {
        // 注意溢出，并且不能使用除法
        const mid = left + ((right - left) >> 1);
        const check = quickAdd(divisor, mid, dividend);
        if (check) {
            ans = mid;
            // 注意溢出
            if (mid === MAX_VALUE) {
                break;
            }
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return rev ? -ans : ans;
}

// 快速乘
const quickAdd = (y, z, x) => {
    // x 和 y 是负数，z 是正数
    // 需要判断 z * y >= x 是否成立
    let result = 0, add = y;
    while (z !== 0) {
        if ((z & 1) !== 0) {
            // 需要保证 result + add >= x
            if (result < x - add) {
                return false;
            }
            result += add;
        }
        if (z !== 1) {
            // 需要保证 add + add >= x
            if (add < x - add) {
                return false;
            }
            add += add;
        }
        // 不能使用除法
        z >>= 1;
    }
    return true;
};
```

**复杂度分析**

- 时间复杂度：![O(\log^2C) ](./p__O_log^2_C__.png) ，其中 *C* 表示 *32* 位整数的范围。二分查找的次数为 ![O(\logC) ](./p__O_log_C__.png) ，其中的每一步我们都需要 ![O(\logC) ](./p__O_log_C__.png)  使用「快速乘」算法判断 ![Z\timesY\geqX ](./p__Z_times_Y_geq_X_.png)  是否成立，因此总时间复杂度为 ![O(\log^2C) ](./p__O_log^2_C__.png) 。

- 空间复杂度：*O(1)*。

#### 方法二：类二分查找

**前言**

常规意义下的二分查找为：给定区间 *[l, r]*，取该区间的中点 ![\textit{mid}=\lfloor\dfrac{l+r}{2}\rfloor ](./p__textit{mid}_=_lfloor_dfrac{l+r}{2}_rfloor_.png) ，根据 ![\textit{mid} ](./p__textit{mid}_.png)  处是否满足某一条件，来决定移动左边界 *l* 还是右边界 *r*。

我们也可以考虑另一种二分查找的方法：

- 记 *k* 为满足 ![2^k\leqr-l<2^{k+1} ](./p__2^k_leq_r-l___2^{k+1}_.png)  的 *k* 值。

- 二分查找会进行 *k+1* 次。在第 ![i~(1\leqi\leqk+1) ](./p__i_~__1_leq_i_leq_k+1__.png)  次二分查找时，设区间为 *[l_i, r_i]*，我们取 ![\textit{mid}=l_i+2^{k+1-i} ](./p__textit{mid}_=_l_i_+_2^{k+1-i}_.png) ：

- 如果 ![\textit{mid} ](./p__textit{mid}_.png)  不在 *[l_i, r_i]* 的范围内，那么我们直接忽略这次二分查找；

- 如果 ![\textit{mid} ](./p__textit{mid}_.png)  在 *[l_i, r_i]* 的范围内，并且 ![\textit{mid} ](./p__textit{mid}_.png)  处满足某一条件，我们就将 *l_i* 更新为 ![\textit{mid} ](./p__textit{mid}_.png) ，否则同样忽略这次二分查找。

最终 *l_i* 即为二分查找的结果。这样做的正确性在于：

> 设在常规意义下的二分查找的答案为 ![\textit{ans} ](./p__textit{ans}_.png) ，记 ![\delta ](./p__delta_.png)  为 ![\textit{ans} ](./p__textit{ans}_.png)  与左边界的差值 ![\textit{ans}-l ](./p__textit{ans}_-_l_.png) 。![\delta ](./p__delta_.png)  不会大于 *r-l*，并且 ![\delta ](./p__delta_.png)  一定可以用 ![2^k,2^{k-1},2^{k-2},\cdots,2^1,2^0 ](./p__2^k,_2^{k-1},_2^{k-2},_cdots,_2^1,_2^0_.png)  中的若干个元素之和表示（考虑 ![\delta ](./p__delta_.png)  的二进制表示的意义即可）。因此上述二分查找是正确的。

**思路与算法**

基于上述的二分查找的方法，我们可以设计出如下的算法：

- 我们首先不断地将 *Y* 乘以 *2*（通过加法运算实现），并将这些结果放入数组中，其中数组的第 *i* 项就等于 ![Y\times2^i ](./p__Y_times_2^i_.png) 。这一过程直到 *Y* 的两倍严格小于 *X* 为止。

- 我们对数组进行逆序遍历。当遍历到第 *i* 项时，如果其大于等于 *X*，我们就将答案增加 *2^i*，并且将 *X* 中减去这一项的值。

本质上，上述的逆序遍历就模拟了二分查找的过程。

**代码**

```C++ [sol2-C++]
class Solution {
public:
    int divide(int dividend, int divisor) {
        // 考虑被除数为最小值的情况
        if (dividend == INT_MIN) {
            if (divisor == 1) {
                return INT_MIN;
            }
            if (divisor == -1) {
                return INT_MAX;
            }
        }
        // 考虑除数为最小值的情况
        if (divisor == INT_MIN) {
            return dividend == INT_MIN ? 1 : 0;
        }
        // 考虑被除数为 0 的情况
        if (dividend == 0) {
            return 0;
        }
        
        // 一般情况，使用类二分查找
        // 将所有的正数取相反数，这样就只需要考虑一种情况
        bool rev = false;
        if (dividend > 0) {
            dividend = -dividend;
            rev = !rev;
        }
        if (divisor > 0) {
            divisor = -divisor;
            rev = !rev;
        }

        vector<int> candidates = {divisor};
        // 注意溢出
        while (candidates.back() >= dividend - candidates.back()) {
            candidates.push_back(candidates.back() + candidates.back());
        }
        int ans = 0;
        for (int i = candidates.size() - 1; i >= 0; --i) {
            if (candidates[i] >= dividend) {
                ans += (1 << i);
                dividend -= candidates[i];
            }
        }

        return rev ? -ans : ans;
    }
};
```

```Java [sol2-Java]
class Solution {
    public int divide(int dividend, int divisor) {
        // 考虑被除数为最小值的情况
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        // 考虑除数为最小值的情况
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        // 考虑被除数为 0 的情况
        if (dividend == 0) {
            return 0;
        }
        
        // 一般情况，使用类二分查找
        // 将所有的正数取相反数，这样就只需要考虑一种情况
        boolean rev = false;
        if (dividend > 0) {
            dividend = -dividend;
            rev = !rev;
        }
        if (divisor > 0) {
            divisor = -divisor;
            rev = !rev;
        }

        List<Integer> candidates = new ArrayList<Integer>();
        candidates.add(divisor);
        int index = 0;
        // 注意溢出
        while (candidates.get(index) >= dividend - candidates.get(index)) {
            candidates.add(candidates.get(index) + candidates.get(index));
            ++index;
        }
        int ans = 0;
        for (int i = candidates.size() - 1; i >= 0; --i) {
            if (candidates.get(i) >= dividend) {
                ans += 1 << i;
                dividend -= candidates.get(i);
            }
        }

        return rev ? -ans : ans;
    }
}
```

```C# [sol2-C#]
public class Solution {
    public int Divide(int dividend, int divisor) {
        // 考虑被除数为最小值的情况
        if (dividend == int.MinValue) {
            if (divisor == 1) {
                return int.MinValue;
            }
            if (divisor == -1) {
                return int.MaxValue;
            }
        }
        // 考虑除数为最小值的情况
        if (divisor == int.MinValue) {
            return dividend == int.MinValue ? 1 : 0;
        }
        // 考虑被除数为 0 的情况
        if (dividend == 0) {
            return 0;
        }
        
        // 一般情况，使用类二分查找
        // 将所有的正数取相反数，这样就只需要考虑一种情况
        bool rev = false;
        if (dividend > 0) {
            dividend = -dividend;
            rev = !rev;
        }
        if (divisor > 0) {
            divisor = -divisor;
            rev = !rev;
        }

        IList<int> candidates = new List<int>();
        candidates.Add(divisor);
        int index = 0;
        // 注意溢出
        while (candidates[index] >= dividend - candidates[index]) {
            candidates.Add(candidates[index] + candidates[index]);
            ++index;
        }
        int ans = 0;
        for (int i = candidates.Count - 1; i >= 0; --i) {
            if (candidates[i] >= dividend) {
                ans += 1 << i;
                dividend -= candidates[i];
            }
        }

        return rev ? -ans : ans;
    }
}
```

```Python [sol2-Python3]
class Solution:
    def divide(self, dividend: int, divisor: int) -> int:
        INT_MIN, INT_MAX = -2**31, 2**31 - 1

        # 考虑被除数为最小值的情况
        if dividend == INT_MIN:
            if divisor == 1:
                return INT_MIN
            if divisor == -1:
                return INT_MAX
        
        # 考虑除数为最小值的情况
        if divisor == INT_MIN:
            return 1 if dividend == INT_MIN else 0
        # 考虑被除数为 0 的情况
        if dividend == 0:
            return 0
        
        # 一般情况，使用类二分查找
        # 将所有的正数取相反数，这样就只需要考虑一种情况
        rev = False
        if dividend > 0:
            dividend = -dividend
            rev = not rev
        if divisor > 0:
            divisor = -divisor
            rev = not rev
        
        candidates = [divisor]
        # 注意溢出
        while candidates[-1] >= dividend - candidates[-1]:
            candidates.append(candidates[-1] + candidates[-1])
        
        ans = 0
        for i in range(len(candidates) - 1, -1, -1):
            if candidates[i] >= dividend:
                ans += (1 << i)
                dividend -= candidates[i]

        return -ans if rev else ans
```

```go [sol2-Golang]
func divide(dividend, divisor int) int {
    if dividend == math.MinInt32 { // 考虑被除数为最小值的情况
        if divisor == 1 {
            return math.MinInt32
        }
        if divisor == -1 {
            return math.MaxInt32
        }
    }
    if divisor == math.MinInt32 { // 考虑除数为最小值的情况
        if dividend == math.MinInt32 {
            return 1
        }
        return 0
    }
    if dividend == 0 { // 考虑被除数为 0 的情况
        return 0
    }

    // 一般情况，使用二分查找
    // 将所有的正数取相反数，这样就只需要考虑一种情况
    rev := false
    if dividend > 0 {
        dividend = -dividend
        rev = !rev
    }
    if divisor > 0 {
        divisor = -divisor
        rev = !rev
    }

    candidates := []int{divisor}
    for y := divisor; y >= dividend-y; { // 注意溢出
        y += y
        candidates = append(candidates, y)
    }

    ans := 0
    for i := len(candidates) - 1; i >= 0; i-- {
        if candidates[i] >= dividend {
            ans |= 1 << i
            dividend -= candidates[i]
        }
    }
    if rev {
        return -ans
    }
    return ans
}
```

```JavaScript [sol2-JavaScript]
var divide = function(dividend, divisor) {
    const MAX_VALUE = 2 ** 31 - 1, MIN_VALUE = -(2 ** 31);
    // 考虑被除数为最小值的情况
    if (dividend === MIN_VALUE) {
        if (divisor === 1) {
            return MIN_VALUE;
        }
        if (divisor === -1) {
            return MAX_VALUE;
        }
    }
    // 考虑除数为最小值的情况
    if (divisor === MIN_VALUE) {
        return dividend === MIN_VALUE ? 1 : 0;
    }
    // 考虑被除数为 0 的情况
    if (dividend === 0) {
        return 0;
    }
    
    // 一般情况，使用类二分查找
    // 将所有的正数取相反数，这样就只需要考虑一种情况
    let rev = false;
    if (dividend > 0) {
        dividend = -dividend;
        rev = !rev;
    }
    if (divisor > 0) {
        divisor = -divisor;
        rev = !rev;
    }

    const candidates = [divisor];
    let index = 0;
    // 注意溢出
    while (candidates[index] >= dividend - candidates[index]) {
        candidates.push(candidates[index] + candidates[index]);
        ++index;
    }
    let ans = 0;
    for (let i = candidates.length - 1; i >= 0; --i) {
        if (candidates[i] >= dividend) {
            ans += 1 << i;
            dividend -= candidates[i];
        }
    }

    return rev ? -ans : ans;
};
```

**复杂度分析**

- 时间复杂度：![O(\logC) ](./p__O_log_C__.png) ，即为二分查找需要的时间。方法二时间复杂度优于方法一的原因是：方法一的每一步二分查找都需要重新计算 ![Z\timesY ](./p__Z_times_Y_.png)  的值，需要 ![O(\logC) ](./p__O_log_C__.png)  的时间复杂度；而方法二的每一步的权重都是 *2* 的幂，在二分查找开始前就都是已知的值，因此我们可以在 ![O(\logC) ](./p__O_log_C__.png)  的时间内，一次性将它们全部预处理出来。

- 空间复杂度：![O(\logC) ](./p__O_log_C__.png) ，即为需要存储的 ![Y\times2^i ](./p__Y_times_2^i_.png)  的数量。