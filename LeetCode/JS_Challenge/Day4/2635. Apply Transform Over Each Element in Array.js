// https://leetcode.com/problems/apply-transform-over-each-element-in-array/?utm_campaign=PostD4&utm_medium=Post&utm_source=Post&gio_link_id=noqbNOv9

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
