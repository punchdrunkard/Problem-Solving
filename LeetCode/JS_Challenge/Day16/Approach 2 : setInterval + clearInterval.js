function throttle2(fn, t) {
  let intervalInProgress = null; // 현재 진행중인 interval id
  let argsToProcess = null; // 다음 interval에서 처리할 매개변수

  const intervalFunction = () => {
    // 처리할 매개변수가 없는 경우 (타이머로 기다릴 필요 없음)
    if (!argsToProcess) {
      clearInterval(intervalInProgress);
      intervalInProgress = null;
    } else {
      // 처리할 매개변수가 존재함 : 함수를 실행
      fn(...argsToProcess);
      argsToProcess = null;
    }
  };

  return function (...args) {
    if (intervalInProgress) {
      // 타이머가 실행 중인 경우
      argsToProcess = args;
    } else {
      // 타이머가 실행 중이 아닌 경우 fn을 즉시 호출한다
      fn(...args);
      // 함수 호출 이후 새로운 타이머를 시작한다.
      intervalInProgress = setInterval(intervalFunction, t);
    }
  };
}

/**
 * const throttled = throttle(console.log, 100);
 * throttled("log"); // logged immediately.
 * throttled("log"); // logged at t=100ms.
 */
