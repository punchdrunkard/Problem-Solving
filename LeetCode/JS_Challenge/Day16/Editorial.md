## Throttle

> The one-sentence description of throttle is that **it should call the provided callback as frequently as possible but no more frequently than `t` milliseconds**.

- throttling 된 함수가 호출되는 즉시, 동일한 인수를 사용하여 콜백을 호출한다.
- 이후 `t` ms 이후에 함수가 다시 호출되었는지 확인하고 호출되었다면 가장 최근에 호출된 함수를 실행 (가장 최근에 제공된 매개변수를 사용한 함수를 실행)하고, 다시 호출되지 않았다면 대기한다.

## Use-cases for Throttle

- Throttle은 가능한 한 빨리 특정 작업을 수행하고자 할 때 사용되지만, 동시에 그 작업이 수행되는 빈도에 대한 한계를 보장하고 싶을 때 사용된다.
  - 즉, Throttle은 함수나 특정 작업의 호출 빈도를 제한하는 방법을 의미한다. 이 기법은 주로 성능을 최적화 하거나, 특정 작업이 과도하게 호출되는 것을 방지하기 위해 사용된다.
  - 또한 즉시 호출이 필요한 작업에 있어서 효율적으로 관리할 수 있다.

### use-case detail

예를 들어 사용자가 버튼을 클릭함으로써 데이터를 다운로드 받는다고 하자. 사용자는 처음 버튼을 클릭할 때 지연이 발생하지 않기를 바랄 것이다. (이 작업은 즉시 호출이 필요한 작업이다. 따라서 디바운싱이 적합하지 않다. ⛈️ debounce를 사용하는 경우, debounce가 설정된 시간 동안 사용자의 클릭이 계속 발생하면 다운로드는 클릭이 멈춘 후에야 시작된다.)

그렇다고 사용자가 버튼을 계속 클릭해서 수십 개의 복사본을 다운로드 받는 것도 이상적이지 않다. 따라서 다운로드 함수에 몇 초간의 지연시간을 가지는 throttle을 추가함으로써 이를 최적화할 수 있다.

> Throttle과 Debounce는 함수 호출의 빈도와 타이밍을 제어하는 데 각각 적합합니다. Throttle은 일정 시간 동안의 호출 빈도를 제한하므로, 일정한 빈도로 업데이트가 필요한 스크롤 이벤트나 애니메이션 처리에 적합합니다. 반면 Debounce는 마지막 호출 후 일정 시간 동안 다른 호출이 없을 때만 함수를 실행하므로, 사용자 입력 처리나 API 호출 등과 같이 연속된 호출을 그룹화하여 처리하는 데 적합합니다.

> Debounce는 연속된 호출을 그룹화 하고, **마지막 호출 후 일정 시간이 경과한 후에만 함수를 실행**한다.

### when to use debounce and when to use throttle:

- `Debounce`는 렉(lag)를 발생시키는 원치 않는 상황에서 유저를 보호한다. (예시 : 검색창에서 과도한 리렌더링) 이는 유저가 그들의 상호작용을 **모두 마치고** 코드를 실행할 수 있다. (` This is achieved by only executing code AFTER the user is done with their interaction.`)

- `Throttle`은 인프라나 앱이 처리할 수 있는 것 보다 코드가 더 자주 호출되는 것을 방지한다.

## Approach 2 : setInterval + clearInterval

- **_looping_** phase를 `setInterval`을 통해 수행하는 방법, recursive function을 사용하지 않고도 구현할 수 있다.
- `clearInterval`을 통해서 loop를 취소할 수 있음

- **주의점 : `setInterval`을 멈추지 않으면 계속 반복 실행되므로, 반드시 적절한 시점에 `clearInterval` 을 호출하여 타이머를 중지해야 한다.**

```typescript
type F = (...args: any[]) => void;

function throttle(fn: F, t: number): F {
  let intervalInProgress = null; // 현재 진행중인 interval의 ID를 저장
  let argsToProcess = null; // 다음 interval에서 처리할 수 있는 함수 fn의 매개변수 저장

  const intervalFunction = () => {
    // 처리할 매개변수가 없는 경우
    if (argsToProcess === null) {
      // interval timer 중지
      clearInterval(intervalInProgress);
      intervalInProgress = null; // enter the waiting phase
    } else {
      // 처리할 매개변수가 있으면
      fn(...argsToProcess);
      argsToProcess = null;
    }
  };

  return function throttled(...args) {
    if (intervalInProgress) {
      // interval timer가 실행중이면
      argsToProcess = args; // 새로운 매개변수를 저장ㅎ한다.
    } else {
      // interval timer가 실행중이지 않으면 fn을 즉시 호출
      fn(...args); // enter the looping phase (새로운 타이머 시작)
      intervalInProgress = setInterval(intervalFunction, t);
    }
  };
}
```
