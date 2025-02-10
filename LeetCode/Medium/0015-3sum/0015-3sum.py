class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        # 투 포인터를 이용하기 위하여 정렬한다.
        answer = []

        nums.sort()

        for i in range(len(nums)):
            # 투 포인터를 이용하되,
            # index를 활용해서 중복된 값을 건너뛴다.
            if i > 0 and nums[i - 1] == nums[i]:
                continue

            target = -nums[i]

            left = i + 1
            right = len(nums) - 1

            while left < right:
                if nums[left] + nums[right] == target:
                    # 스킵처리
                    while left < right and nums[left] == nums[left + 1]:
                        left += 1
                    
                    while left < right and nums[right] == nums[right - 1]:
                        right -= 1
                        
                    answer.append([nums[i], nums[left], nums[right]])
                    left += 1
                    right -= 1

                elif nums[left] + nums[right] > target:
                    right -= 1

                elif nums[left] + nums[right] < target:
                    left += 1

        return answer
    