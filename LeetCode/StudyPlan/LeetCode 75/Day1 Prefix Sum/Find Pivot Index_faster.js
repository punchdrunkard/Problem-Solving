// https://leetcode.com/problems/find-pivot-index/submissions/940385389/?envType=study-plan&id=level-1
// Runtime : 74 ms (65.69 %), Memory : 45 MB (28.74 %)

/**
 * @param {number[]} nums
 * @return {number}
 */

const pivotIndex = (nums) => {
  const numsSize = nums.length;
  const sum = nums.reduce((a, b) => a + b);
  let leftSum = 0;

  for (let i = 0; i < numsSize; i++) {
    // target : leftSum === rightSum을 만족시키는 i
    // sum = leftSum + rightSum + nums[i]
    // -> rightSum = sum - leftSum - nums[i]
    if (leftSum === sum - leftSum - nums[i]) {
      return i;
    }

    leftSum += nums[i];
  }

  return -1;
};
