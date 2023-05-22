// https://leetcode.com/problems/filter-elements-from-array/?utm_campaign=PostD5&utm_medium=Post&utm_source=Post&gio_link_id=a9a5VZr9

/**
 * @param {number[]} arr
 * @param {Function} fn
 * @return {number[]}
 */
let filter = function (arr, fn) {
  const returnedArr = [];
  arr.forEach((element, index) => {
    if (fn(element, index)) returnedArr.push(element);
  });

  return returnedArr;
};

// Note that even though you could optimize
// your code by picking the optimal filtering approach,
// you should probably just use the built-in Array.filter method
// for simplicity and readability.

// The exception is if you are writing a high-performance library
//  or dealing with extremely large arrays
// where the performance gains become meaningful
