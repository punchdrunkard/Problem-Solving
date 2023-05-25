// Appraoch 2애 async/await 문법을 사용할 수 있다.

let timeLimit = function (fn, t) {
  return async function (...args) {
    return new Promise(async (resolve, reject) => {
      const timeout = setTimeout(() => {
        reject("Time Limit Exceeded");
      }, t);

      try {
        // fn을 async/await을 사용하여 호출한다.
        const result = await fn(...args);
        resolve(result);
      } catch (e) {
        // 호출이 실패하면 catch 블록으로 와서 reject를 실행
        reject(err);
      } finally {
        clearTimeout(timeout);
      }
    });
  };
};
