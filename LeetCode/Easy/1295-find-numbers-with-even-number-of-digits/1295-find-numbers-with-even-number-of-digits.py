class Solution(object):
    def findNumbers(self, nums):
        filtered = filter(lambda num : len(str(num)) % 2 == 0, nums)
        return len(filtered)