// Approach 1: Call Function Inside New Promise

// 즉, 전달된 함수가 어떻게 동작하는지에 따라 새로운 Promise도 함께 resolve되거나 reject 된다.

let timeLimit = function (fn, t) {
  return async function (...args) {
    // passed function이 resolve 되거나 reject되자마자 resolve되는 새로운 Promise를 생성한다.
    return new Promise((resolve, reject) => {
      // 먼저 setTimeout을 이용해 시간 제한을 설정한다.
      // t가 지나면 reject를 호출한다.
      setTimeout(() => {
        reject("Time Limit Exceeded");
      }, t);

      // setTimeout은 비동기로 동작하기 때문에 코드 순서대로 fn이 동작하는게 아니다!
      // 따라서 위의 setTimeout과 동시에 fn이 호출되고 결과를 기다리게 된다.
      // fn이 성공적으로 완료되면 해당 결과를 resolve한다.
      fn(...args)
        .then(resolve)
        .catch(reject);
    });
  };
};

// 이 코드의 핵심은 JavaScript의 비동기 처리 방식과 이벤트 루프에 기반한다.
// setTimeout은 비동기 함수로, JavaScript 엔진이 t 밀리 초가 경과할때 까지 기다리는 동작을 스케줄링 한다.
// 제한 시간이 지나면, setTimeout에 들어간 콜백 함수가 태스크 큐로 들어가게 된다.
// 이 코드에서는 reject를 전달하였으므로, 제한 시간이 지나면 reject가 호출된다.

// 동시에 fn이 실행된다. fn은 비동기 함수이므로 작업이 완료되면 그 결과가 태스크 큐로 들어간다.

// 이벤트 루프는 이 중 가장 먼저 들어온 태스크를 선택하여 실행한다.

// 이 동작을 간소화해서 Promise.race로 나타낼 수 있다.
