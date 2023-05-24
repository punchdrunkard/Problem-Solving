## 핵심 컨셉 : asynchronous programming

> The problem involves the concept of asynchronous programming. Specifically, it focuses on promises and the setTimeout function, a web API method that introduces a delay in the execution of code.

### Promise

- asynchronous operation에서의 최종 성공(completion)과 실패(failure)를 나타내는 JavaScript object, 따라서 asynchronous operation 작업의 결과(성공 또는 실패)에 따른 동작을 정의할 수 있다.
- **핵심은, Promise는 함수에 callback을 전달하는 기존의 방식과 달리 반환된 객체에 callback을 attach하는 방식이다.**
- 반환된 객체가 성공일 경우 `.then` 메서드를 통해 callback을 attach 할 수 있고, 실패일 경우 `.catch` 메서드를 통해 callback을 attach 할 수 있다.

### setTimeout and Event Loop

- `setTimeout`이 호출되면 JavaScript 런타임은 timer를 set up 하고, 다음 코드르 바로 실행한다. (timer 가 끝날 때 까지 blocking 되지 않는다.)
- timer에 설정된 지연 시간이 지나면 넘겨준 callback 함수가 task queue에 추가된다. (유의해야 할 점은 실제 지연 시간은 지정된 시간보다 약간 더 길 수 있다.)
  > 참고 예시 (Leetcode 실행 결과)
  > ![](https://i.imgur.com/Ff3paPE.png) > **따라서 setTimeout에 지정된 시간은 "보장된 지연 시간" 보다는 콜백 함수가 호출되기 전의 "최소 지연 시간"으로 이해해야 한다.**
- cf) `setTimeout`은 timerId를 리턴한다. 이는 `clearTimeout` 에 사용됨

### `clearTimeout`

- 이전에 `setTimeout`에 의해 만들어진 timeout을 cancel하는 함수

### finally

- `.finally`는 프로미스의 실행 여부와 관계없이 항상 실행된다. 따라서 프로미스의 결과와 관계없이 실행되어야 하는 cleanup 코드를 넣기에 적합하다.
- 코드 예제

```javascript
let isLoading = true;

fetch("https://api.example.com/data")
  .then((response) => {
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    return response.json();
  })
  .then((data) => console.log(data))
  .catch((error) => console.error("Error:", error))
  // 이 finally block은 fetch operation의 성공, 실패 여부와 상관없이 실행된다.
  .finally(() => {
    isLoading = false;
    console.log("Fetch operation finished");
  });
```

### Understanding Promise Returns in Async Functions

```javascript
async function example() {
  try {
    return new Promise((resolve, reject) => {
      throw new Error("Oops!");
    });
  } catch (err) {
    console.error(err);
  }
}

// 프로미스가 에러를 발생시키기 전에 반환되기 때문에 (Promise가 생성되고 바로 반환되므로)
// catch 블록이 프로미스에서 에러를 catch 할 수 없다.

example(); // The error is not caught, and it rejects the promise returned by example.

async function example2() {
  try {
    return await new Promise((resolve, reject) => {
      throw new Error("Oops!");
    });
  } catch (err) {
    console.error(err);
  }
}

// 프로미스가 해결되거나 에러를 던질 때 까지 함수를 일시 중지하므로
// catch 블록이 에러를 포착할 수 있다.
example2(); // The error is caught, and the promise returned by example2 is resolved.
```

## Note
