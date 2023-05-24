// curry: 함수를 변환하여 이 함수의 매개변수를 한 번에 하나씩 받도록 함
// 커링된 함수는 단일 인수를 사용하여 원래 매개변수를 부분적으로 적용할 수 있다.
// 함수의 결과로 다른 함수를 반환하는데, 이 함수는 아직 적용되지 않은 나머지 매개변수를 처리한다.

function curry(fn: Function): Function {
  // 원래 함수 Fn을 인수로 받아들이는 함수
  // 외부 함수가 반환할 내부 함수를 정의한다. (curried)
  // 이 함수는 원래 함수를 호출하는데 사용할 인수 (args)를 받는다.
  return function curried(...args: any[]) {
    // 즉 args (curry 함수의 매개 변수)의 수가 원래 함수의 매개변수의 수 보다 커진다면
    // 제공된 인수를 사용하여 원래 함수를 호출한다.
    if (args.length >= fn.length) {
      return fn.apply(this, args);
    }

    // 커리 함수에서는 이미 args라는 매개변수로 일부 인수를 받았지만, 모든 인수를 받지는 않았음
    // 그래서 더 많은 인수를 기대하는 새로운 함수를 반환하는데, 이 새로운 함수를 호출할 때 전달되는 인수를 innerArgs가 수집한다.

    // 내부 함수 내에서 제공된 인수의 갯수(args.length)가 원래 함수의 필요한 인수의 갯수 (fn.length)보다 작으면
    // 나머지 인수를 허용하는 새로운 curry 함수를 반환한다.
    return function (...innerArgs: any[]) {
      // 새로 반환되는 함수가 호출될 때 전달된 인수
      return curried.apply(this, args.concat(innerArgs));
    };
  };
}

/**
 * function sum(a, b) { return a + b; }
 * const csum = curry(sum);
 * csum(1)(2) // 3
 */
