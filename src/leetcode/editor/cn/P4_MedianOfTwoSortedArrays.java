//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 
//输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
// 
//
// 示例 4： 
//
// 
//输入：nums1 = [], nums2 = [1]
//输出：1.00000
// 
//
// 示例 5： 
//
// 
//输入：nums1 = [2], nums2 = []
//输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -106 <= nums1[i], nums2[i] <= 106 
// 
//
// 
//
// 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？ 
// Related Topics 数组 二分查找 分治 
// 👍 4623 👎 0


package leetcode.editor.cn;

import java.util.Arrays;

class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new MedianOfTwoSortedArrays().new Solution();
        solution.findMedianSortedArrays(
                new int[]{1,3},
                new int[]{2}
        );
        solution.findMedianSortedArrays(
                new int[]{1,2},
                new int[]{3,4}
        );
        solution.findMedianSortedArrays(
                new int[]{0,0},
                new int[]{0,0}
        );
        solution.findMedianSortedArrays(
                new int[]{},
                new int[]{1}
        );
        solution.findMedianSortedArrays(
                new int[]{2},
                new int[]{}
        );
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//            return firstMethod(nums1, nums2);
//            return secondMethod(nums1, nums2);
            return thirdMethod(nums1, nums2);
        }

        private double firstMethod(int[] nums1, int[] nums2) {
            int[] array = mergeArrays(nums1, nums2);
            int size = array.length;
            if (size % 2 == 0) {
                return (array[size / 2 - 1] + array[size / 2]) / 2d;
            } else {
                return array[size / 2];
            }
        }

        private int[] mergeArrays(int[] nums1, int[] nums2) {
            int[] array = new int[nums1.length + nums2.length];
            int i = 0,j = 0,k = 0;
            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    array[k++] = nums1[i];
                    i++;
                } else {
                    array[k++] = nums2[j];
                    j++;
                }
            }
            if (i == nums1.length) {
                for (; j < nums2.length; j++) {
                    array[k++] = nums2[j];
                }
            } else {
                for (; i < nums1.length; i++) {
                    array[k++] = nums1[i];
                }
            }
            System.out.println("merge : " +
                    "\nnum1 : "+Arrays.toString(nums1)+
                    "\nnum2 : "+Arrays.toString(nums2)+
                    "\n ->  : "+Arrays.toString(array));
            return array;
        }

        private double secondMethod(int[] nums1, int[] nums2) {
            int[] array = new int[nums1.length + nums2.length];
            int i = 0, j = 0, k = 0;
            double ans = -1;
            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    ans = setValueAndGetAns(array, k, nums1[i]);
                    i++;
                } else {
                    ans = setValueAndGetAns(array, k, nums2[j]);
                    j++;
                }
                if (ans != -1) {
                    return ans;
                }
                k++;
            }
            if (i == nums1.length) {
                for (; j < nums2.length; j++) {
                    ans = setValueAndGetAns(array, k, nums2[j]);
                    if (ans != -1) {
                        return ans;
                    }
                    k++;
                }
            } else {
                for (; i < nums1.length; i++) {
                    ans = setValueAndGetAns(array, k, nums1[i]);
                    if (ans != -1) {
                        return ans;
                    }
                    k++;
                }
            }
            return ans;
        }

        private double setValueAndGetAns(int[] array, int index, int value) {
            array[index] = value;
            int size = array.length;
            if (index == array.length / 2) {
                if (array.length % 2 == 0) {
                    return (array[size / 2 - 1] + array[size / 2]) / 2d;
                } else {
                    return array[size / 2];
                }
            }
            return -1;
        }

        private double thirdMethod(int[] nums1, int[] nums2) {
            int size = nums1.length + nums2.length;
            int i = 0, j = 0, k = 0, lastNum = 0;
            double ans = -1;
            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    ans = setValueAndGetAns(size, k, nums1[i], lastNum);
                    lastNum = nums1[i];
                    i++;
                } else {
                    ans = setValueAndGetAns(size, k, nums2[j], lastNum);
                    lastNum = nums2[j];
                    j++;
                }
                if (ans != -1) {
                    return ans;
                }
                k++;
            }
            if (i == nums1.length) {
                for (; j < nums2.length; j++) {
                    ans = setValueAndGetAns(size, k, nums2[j], lastNum);
                    lastNum = nums2[j];
                    if (ans != -1) {
                        return ans;
                    }
                    k++;
                }
            } else {
                for (; i < nums1.length; i++) {
                    ans = setValueAndGetAns(size, k, nums1[i], lastNum);
                    lastNum = nums1[i];
                    if (ans != -1) {
                        return ans;
                    }
                    k++;
                }
            }
            return ans;
        }

        private double setValueAndGetAns(int size, int index, int value, int lastNum) {
            if (index == size / 2) {
                if (size % 2 == 0) {
                    return (lastNum + value) / 2d;
                } else {
                    return value;
                }
            }
            return -1;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}