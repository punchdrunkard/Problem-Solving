// https://leetcode.com/problems/find-pivot-index/submissions/940385389/?envType=study-plan&id=level-1
// Runtime : 94 ms (29.81 %), Memory : 45.4 MB (23.1 %)

/**
 * @param {number[]} nums
 * @return {number}
 */

const pivotIndex = (nums) => {
  const numsSize = nums.length;
  const leftSum = Array.from({ length: numsSize }, () => 0);
  const rightSum = Array.from({ length: numsSize }, () => 0);

  // find left sum
  for (let i = 1; i < numsSize; i++) {
    leftSum[i] = leftSum[i - 1] + nums[i - 1];
  }

  // find right sum
  for (let i = numsSize - 2; i >= 0; i--) {
    rightSum[i] = rightSum[i + 1] + nums[i + 1];
  }

  // find answer
  for (let i = 0; i < numsSize; i++) {
    if (leftSum[i] === rightSum[i]) {
      return i;
    }
  }

  return -1;
};
