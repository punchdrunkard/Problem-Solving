// debounced 된 함수를 호출하면, 그 함수는 지정된 시간 동안 기다린 다음
// 주어진 함수를 실행한다.
// 만약에 지정된 시간 이전에 함수가 호출되었다면
// 해당 타이머를 clear 하고 새롭게 타이머를 설정한다.

type F = (...p: any[]) => any;

function debounce(fn: F, t: number): F {
  // 원래의 함수 호출을 취소하기 위함
  let timeoutId: NodeJS.Timeout; // 타이머 값을 저장하는 변수

  return function (...args) {
    clearTimeout(timeoutId);

    timeoutId = setTimeout(() => {
      fn(...args);
    }, t);
  };
}

/**
 * const log = debounce(console.log, 100);
 * log('Hello'); // cancelled
 * log('Hello'); // cancelled
 * log('Hello'); // Logged at t=100ms
 */
