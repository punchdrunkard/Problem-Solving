type F = (...args: any[]) => void;
type NullableArray<T = any> = Array<T> | null;

function throttle(fn: F, t: number): F {
  // currArguments : 가장 최근에 실행된 함수의 arguments를 추적하도록 해준다.
  // 주의! timeout과는 무관하게, 함수가 언제 호출하느냐에 따라 변하는 값이다!
  // 따라서 timeout에서는 이 값을 신경쓰지 않아도 된다.
  let currArguments: NullableArray = null;
  let shouldCall = true; // throttle의 활성화 상태

  function execute() {
    if (shouldCall && currArguments) {
      fn(...currArguments);
      currArguments = null;
      shouldCall = false;
      setTimeout(() => {
        shouldCall = true;
        execute();
      }, t);
    }
  }

  return function (...args) {
    currArguments = args; // currArguments 변수는 throttle 함수가 호출될 때 마다 갱신된다.
    execute();
  };
}

/**
 * const throttled = throttle(console.log, 100);
 * throttled("log"); // logged immediately.
 * throttled("log"); // logged at t=100ms.
 */

// hint
// 현재 진행중인 timeout이 없다면 즉시 함수를 실행하고 타임아웃을 만드세요.
// timeout이 진행중이라면 새 arguments를 위한 currArguments를 만드세요.
// timeout이 끝났다면, currArguments가 null이라면 아무것도 하지마세요. 대신에, currArguemnts를 통하여 함수를 실행하고
// 또 다른 timeout을 만드세요.
