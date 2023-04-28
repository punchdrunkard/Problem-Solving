// 문제 링크 :
// https://leetcode.com/problems/running-sum-of-1d-array/?envType=study-plan&id=level-1

// Runtime : 0 ms (100%) , Memory 8.3 MB (95.31%)

#include <vector>
using namespace std;

class Solution {
 public:
  vector<int> runningSum(vector<int>& nums) {
    vector<int> dp(nums.size());

    // initialize
    dp[0] = nums[0];

    for (int i = 1; i < nums.size(); i++) {
      dp[i] = dp[i - 1] + nums[i];
    }

    return dp;
  }
};