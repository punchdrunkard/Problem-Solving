// Approach 3: Optimize Based on Numeric Constraints + Function.apply

function memoize3(fn) {
  const cache = new Map();
  return function () {
    let key = arguments[0];
    if (arguments[1]) {
      // `JSON.stringify`는 relatively expensive operation이다.
      //  또한 문제의 제약 조건 상 인수가 두 개를 넘지 않고 실제로 인수를 문자열로 변환하지 않을 수도 있다.
      key += arguments[1] * 100001;
    }
    const result = cache.get(key);
    if (result !== undefined) {
      return result;
    }
    const functionOutput = fn.apply(null, arguments);
    cache.set(key, functionOutput);
    return functionOutput;
  };
}
