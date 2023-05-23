type Fn = (...params: any[]) => any;

function memoize<T extends Fn>(fn: T): T {
  // 입력에 따른 값을 캐시하기 위한 Map을 정의
  // 이 Map은 "입력 값"을 key로 하고, 해당 값에 대한 함수 결과를 value로 한다.
  const cache = new Map<string, ReturnType<T>>();

  return ((...args: Parameters<T>): ReturnType<T> => {
    const key = JSON.stringify(args); // args가 참조 타입일 수 있기 때문

    if (cache.has(key)) {
      return cache.get(key) as ReturnType<T>;
    }

    const result = fn(...args);
    cache.set(key, result);
    return result;
  }) as T;
}

/**
 * let callCount = 0;
 * const memoizedFn = memoize(function (a, b) {
 *	 callCount += 1;
 *   return a + b;
 * })
 * memoizedFn(2, 3) // 5
 * memoizedFn(2, 3) // 5
 * console.log(callCount) // 1
 */
