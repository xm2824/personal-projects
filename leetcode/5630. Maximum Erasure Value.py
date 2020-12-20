'''
You are given an array of positive integers nums and want to erase a subarray containing unique elements.
The score you get by erasing the subarray is equal to the sum of its elements.

Return the maximum score you can get by erasing exactly one subarray.

An array b is called to be a subarray of a if it forms a contiguous subsequence of a,
that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).
'''
from typing import List
class Solution:
    def maximumUniqueSubarray(self, nums: List[int]) -> int:
        ret = 0
        length = len(nums)

        if length ==0: return 0
        if length == 1: return nums[0]


        # 从每一个字符开始寻找subarray
        for i in range (length):
            count = nums[i]
            temp = [nums[i]]
            # 从该字符开始寻找
            for j in range(i+1,length):
                #print(nums[j],temp)
                if nums[j] not in temp:
                    count  += nums[j]
                    temp.append(nums[j])
                    if ret < count: ret = count
                else:
                    if ret < count: ret = count
                    break


        return ret

