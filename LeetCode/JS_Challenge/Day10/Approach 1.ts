// currying 기법에서 함수의 인자는 각 호출에 따라 점진적으로 제공될 수 있다.

// recursive approach (클로저를 사용하여 이전 호출에서의 인수를 기억함)
// base condition : 인자의 수가 많다면 original function을 수행 (basic function의 인자 수는 function prototype의 length 속성을 이용)
// recursive condition : 인자의 수가 더 적다면 curried로 그 인자를 기억함

// 이 curry 함수는 인자가 충분히 제공될 때 까지 기다렸다가, 인자의 수가
// 원래 함수에 필요한 매개변수의 수와 같거나 많아지면 원래 함수를 호출한다.
type CurryFn = (...args: any[]) => any;

function curryRecursive(fn: CurryFn): CurryFn {
  return function curried(...args: any[]): CurryFn | any {
    if (args.length >= fn.length) {
      return fn(...args);
    }

    // 인수의 수가 충분하지 않으면, 다음 인자를 기다림
    return (...nextArgs: any[]) => curried(...args, ...nextArgs); // ...args; 현재 까지의 인자, nextArgs: 다음으로 받을 인자 (...args에 의해 이전 인자가 curried된 버전에 기억될 수 있음)
  };
}

// Time Complexity : O(N), arguments를 spread 하는 과정을 거치므로
// Space Complexity : O(N), 함수의 인자를 계속 기억하는 과정을 거치므로
