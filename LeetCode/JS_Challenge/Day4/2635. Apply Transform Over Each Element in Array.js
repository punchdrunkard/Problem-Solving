/**
 * @param {number[]} arr
 * @param {Function} fn
 * @return {number[]}
 */
let map = function (arr, fn) {
  const answer = [];

  arr.forEach((ele, idx) => {
    answer.push(fn(ele, idx));
  });

  return answer;
};
