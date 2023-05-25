// Approach 2 : Handle Clearing Timeout

// Approach 1의 경우 시간 제한이 경과되기 전에 fn이 완료되면,
// 미래의 어느 시점에 (시간 제한이 경과된 후에) reject 로직이 불필요하게 트리거 된다.
// (왜냐하면 시간 제한 이후에 태스크 큐로 reject가 들어간 건 제어할 수가 없기 때문이다.)

// 이는 메모리 누수를 일으킬 수 있기 때문에, 방지하는게 좋다.

let timeLimit = function (fn, t) {
  return async function (...args) {
    return new Promise((resolve, reject) => {
      const timeout = setTimeout(() => {
        reject("Time Limit Exceeded");
      }, t);

      fn(...args)
        .then(resolve)
        .catch(reject)
        // Promise.finally를 사용하면 이 메서드에 전달된 콜백은 Promise가 resolve되거나 reject되면 호출된다.
        .finally(() => clearTimeout(timeout));
    });
  };
};
