// https://leetcode.com/problems/array-reduce-transformation/?utm_campaign=PostD6&utm_medium=Post&utm_source=Post&gio_link_id=nPN45jD9

/**
 * @param {number[]} nums
 * @param {Function} fn
 * @param {number} init
 * @return {number}
 */
let reduce = function (nums, fn, init) {
  let acc = init;

  for (let num of nums) {
    acc = fn(acc, num);
  }

  return acc;
};
