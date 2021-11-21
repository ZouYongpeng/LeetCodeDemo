* 找大于等于target的第一个数，小于等于target的最后一个数

``` java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int l=0,r=nums.length-1;
        while(l<r){
            int mid=l+(r-l>>1);
            if(nums[mid]>=target){
                r=mid;
            }else{
                l=mid+1;
            }
        }
        if(r==-1||nums[l]!=target){
            return new int[]{-1,-1};
        }
        int start=l;
        r=nums.length-1;
        while(l<r){
            int mid=l+(r-l>>1)+1;
            if(nums[mid]<=target){
                l=mid;
            }else{
                r=mid-1;
            }
        }
        return new int[]{start,l};
    }
}
```

* 时间复杂度：*O(logN)*

>  若阐述不清楚or讲得不对的地方，欢迎在评论区指出，我都会回复。谢谢！ 
>
>  2021.11.20
