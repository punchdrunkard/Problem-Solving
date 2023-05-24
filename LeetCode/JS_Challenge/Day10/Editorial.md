## 핵심 컨셉 : Currying

### Curry

- transforms a function **with multiple arguments into a sequence of functions.**
- 함수 인자들을 부분적으로 적용할 수 있게 함으로써, flexible 하며 reuseable 한 코드를 작성할 수 있게 해 준다.

- 간단한 예시

```typescript
function sum(a: number, b: number, c: number): number {
  return a + b + c;
}

// curry 라는 higher-order function을 사용함으로써 curriedSum 함수를 만듬.
const curriedSum = curry(sum);

// 기존의 sum 함수와 다르게 인자를 꼭 정해진대로 받지 않아도 함수를 실행할 수 있다.
// 🌧️ 매개변수 오버라이딩과 비슷한 컨셉이라고 이해하면 될까?
console.log(curriedSum(1)(2)(3)); // Output: 6
console.log(curriedSum(1, 2)(3)); // Output: 6
console.log(curriedSum(1)(2, 3)); // Output: 6
console.log(curriedSum(1, 2, 3)); // Output: 6
console.log(curriedSum()()(1, 2, 3)); // Output: 6
```

- **some practical use cases of currying**

  1. Reuseable utility function : currying을 통해 매개변수를 부분적으로 적용한 또 다른 함수를 만들 수 있다.

  ```typescript
  const add = // curried function

      (a: number) =>
      (b: number): number =>
        a + b;

  // Create a new function 'add5' by calling the curried 'add' function with the value 5.
  // The returned function will take a single argument 'b' and add it to 5.
  // 🌧️ 이 함수는 미리 5를 받아두고, 다른 인수 b를 기다리는 함수로 동작한다.
  const add5 = add(5);

  // Now, when we call 'add5' with a value (e.g., 3), it adds 5 to the input value, resulting in 8.
  const result: number = add5(3); // 8
  ```

  2. Event handling

  - currying은 이벤트 핸들러가 특정 설정으로 작동하도록 만들 수 있으면서도, Handler의 핵심 로직은 generic하고 reuseable 할 수 있게 해준다. ( 🤖 이렇게 커링을 사용하면 이벤트 핸들러를 특정 상태와 연결할 수 있습니다.)

  - 예를 들어 일련의 click 이벤트에 대해 핸들링을 하는데, 각 이벤트가 발생했을 때 다른 action을 수행하길 원한다고 가저애보자. 이 때, currying을 사용하면 핸들링 함수를 공통적인 기능을 가진 베이스 함수로 설정하고, 이벤트 별로 특화된 설정을 가진 함수로 currying 할 수 있다.

  ```typescript
  type EventHandler = (event: Event) => void;

  // handleClick 함수는 currying을 사용하여 만든 high-order function
  // buttonId 라는 매개변수를 받고, 이를 기반으로 click 이벤트를 처리하는 새로운 함수 `EventHandler`를 반환함
  const handleClick =
    (buttonId: number): EventHandler =>
    (event: Event) => {
      console.log(`Button ${buttonId} clicked`, event);
    };

  // handleClick 함수를 통하여 buttonId 1로 currying 된 함수
  const button1Handler: EventHandler = handleClick(1);
  document.getElementById("button1")?.addEventListener("click", button1Handler);
  ```

  - 여기서의 장점은 `handleClick` 함수를 사용하여 각기 다른 buttonId에 대한 여러 개의 이벤트 핸들러를 만들 수 있다. 각 이벤트 핸들러는 buttonId를 "기억"하고 이를 클릭 이벤트를 처리할 때 사용한다.
  - 이렇게 함으로써, 이벤트 핸들러의 핵심 로직 (이벤트를 처리하고 콘솔에 메시지 출력)은 동일하게 유지하면서, 각 이벤트 핸들러는 특정 buttonId를 가질 수 있다.
  - **커링을 사용하면 공통의 로직을 가진 함수를 정의하고, 이 함수를 다양한 설정으로 _customize_ 할 수 있다. 이러한 customize 된 함수들은 각기 다른 상황 (buttonId에 따라 다르게 적용)될 수 있지만, 그 핵심 로직은 동일하게 유지된다.**
  - 🤖 단, 이러한 접근 방식은 컴포넌트가 생성될 때마다 새로운 이벤트 핸들러를 생성하므로, 매우 많은 버튼이 있거나 이벤트 핸들러가 자주 변경되는 상황에서는 성능 이슈를 초래할 수 있습니다. 이러한 경우에는 event.target을 사용하여 이벤트가 발생한 버튼의 ID를 찾는 방식이 더 효과적일 수 있습니다.
    - 🌧️ 이런 경우 이벤트 버블링과 이벤트 위임(event delegation)을 사용하여 성능을 개선할 수 있음.
      > 이러한 방식을 사용하면, 각각의 버튼에 이벤트 핸들러를 직접 연결하지 않아도 되므로 성능을 개선할 수 있습니다. 또한, 동적으로 요소를 추가하거나 제거하는 경우에도 별도의 이벤트 핸들러를 연결하거나 제거할 필요가 없습니다.

  3. Customizing API calls

  ```typescript
  type ApiCall = (endpoint: string) => (params: any) => Promise<Response>;

  const apiCall =
    (baseUrl: string): ApiCall =>
    (endpoint: string) =>
    (params: any) =>
      fetch(`${baseUrl}${endpoint}`, { ...params });

  const myApiCall: ApiCall = apiCall("https://my-api.com");
  const getUser = myApiCall("/users");
  const updateUser = myApiCall("/users/update");

  // Usage:
  getUser({ userId: 1 });
  updateUser({ userId: 1, name: "John Doe" });
  ```

  4. Higher-order functions and functional composition

  ```typescript
  type ComposeFn = <T>(f: (x: T) => T, g: (x: T) => T) => (x: T) => T;

  const compose: ComposeFn = (f, g) => (x) => f(g(x));

  const double = (x: number): number => x * 2;
  const square = (x: number): number => x * x;

  const doubleThenSquare = compose(square, double);

  const result: number = doubleThenSquare(5); // (5 * 2)^2 = 100
  ```

## 문제 풀이

- [Approach 1: Currying with Recursive Function Calls](./Approach%201.ts) : closure를 사용하여 이전 인수를 기억함.
- [Approach 2: Currying with the Built-in Bind Method](./Approach%202.ts)

  - Approach 1과 접근 방식은 같으나, `bind`를 사용하여 **`this` context를 고려함**

    > Using the bind method makes the code very concise, as it abstracts away some of the complexity.

  - `bind method`
    `bind`는 **`this` context**를 특정한 curried function과 같은 body를 가진 새로운 함수를 리턴한다.
    `bind`를 사용함으로써 accumulated arguments 를 유지하고 있는 새로운 함수를 만들 수 있다.
    이를 통해 collected arguments를 추적할 수 있으며, 여러 호출에서 `this` context를 보존할 수 있다.

    > Simply put, the bind method creates a new function, which we return - in this case, it creates a function almost identical to (...nextArgs) => curried(...args, ...nextArgs), but with a fixed 'this' context.

    - 이러한 접근 방식은 `this` context 를 함께 제어할 수 있으므로 **함수가 객체의 메서드일 경우, 이 `this` context를 제어하는 것이 중요할 수 있음.**

## Additional Considerations

### Partial Application vs Currying

> In fact, currying can be considered a type of partial application.

- Partial Application : 함수의 arguments를 일부 수정하여, 나머지 인수를 줄인 새로운 함수를 생성하는 방법. 즉, 일부 인수를 미리 지정하여 기존 함수에서 새로운 함수를 생성함

- currying : 함수를 일련의 함수로 나누고, 각 함수는 하나의 인수(또는 그 이상)을 받음. 이를 통해 인수를 한 번에 하나씩 전달하고 **중간 결과를 기반으로 새로운 함수를 만들 수 있음.**

### Different implementations of curry

> Another popular variation is a curry function that doesn't accept a predefined amount of arguments (the function doesn't have a predefined length

- 인자를 더 이상 전달하지 않을 때 까지 함수 호출을 지연시키기

  ```javascript
  var curry = function (fn) {
    return function curried(...args) {
      if (args.length === 0) {
        return fn(...args);
      }

      return (...nextArgs) => {
        // 더 이상의 인자가 없다 => 원본 함수를 호출한다.
        if (nextArgs.length === 0) {
          return fn(...args);
        }

        return curried(...args, ...nextArgs);
      };
    };
  };

  // 다음고 같이 사용 가능하다.
  const getSum = (...args) => args.reduce((a, b) => a + b, 0);
  const curriedSum = curry(getSum);
  console.log(curriedSum(1)(2)(3)());
  ```

## Note

### `bind`

- `bind`는 특정 context(`this`)와 함께 호출해야 하는 함수를 반환한다. 이는 특히 함수를 콜백으로 사용하거나 다른 context에서 호출해야 할 때 유용하다.
- `bind` 메소드는 추가 인수를 바인딩 할 수 있다. 따라서 `bind(this, 바인드할 인수들)`의 형태로 사용할 수 있다. 예를 들어서 curring 에서는 현재까지 수집한 인수를 바인드하면서 `bind`의 인자로 적용된 `this` context와 인수들을 **기억**하는 효과가 있다.
