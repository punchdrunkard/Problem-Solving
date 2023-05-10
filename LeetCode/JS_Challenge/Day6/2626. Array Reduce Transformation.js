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
