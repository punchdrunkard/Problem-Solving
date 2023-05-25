/**
 * @param {Function} fn
 * @param {number} t
 * @return {Function}
 */

let timeLimit = function (fn, t) {
  return async function (...args) {
    const timeout = new Promise((_, reject) => {
      setTimeout(() => reject("Time Limit Exceeded"), t);
    });

    // 가장 먼저 reject되거나 resolve된 promise를 리턴한다.
    return Promise.race([fn(...args), timeout]);
  };
};
