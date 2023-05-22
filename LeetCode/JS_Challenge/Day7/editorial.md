## 핵심 컨셉 : Function Composition

### Basic

- functional programming의 핵심 개념으로, **한 함수의 출력을 다른 함수의 입력으로 사용**
  - 수학의 합성 함수 개념이라고 생각
- 이 문제에서는 함수들의 array를 받아서, 이를 single function으로 만든다.

### Handling `this` context

- JavaScript에서는 **함수가 어떻게 호출되었느냐에 따라서** 함수 내부의 `this` 값이 바뀔 수 있다.
  > Although the provided test cases may not explicitly test for this, handling the this context correctly can be crucial in real-world scenarios.
- `this` 를 다루는 방법 : `call` 이나 `apply` 사용하기
  이를 통해 함수를 호출할 때 `this`의 값을 명시적으로 설정할 수 있다.

  - 예제

  ```javascript
  const composedFn = function (x) {
    let result = x;
    for (let i = functions.length - 1; i >= 0; i--) {
      result = functions[i].call(this, result); // 여기서 this는 함수가 실행되는 컨텍스트를 참조한다.
      // 즉, functions[i]가 실행되는 환경인 composedFn 의 컨텍스트를 참조하여 값을 설정
    }
    return result;
  };
  ```

  - `this`로 인해 문제가 생기는 예시

  ```javascript
  const obj = {
    value: 1,
    increment: function () {
      this.value++; // 여기서 this는 obj를 가리킨다.
      console.log("this의 값", this);
      return this.value;
    },
    double: function () {
      this.value *= 2;
      console.log("this의 값", this);
      return this.value;
    },
  };

  // Composing the methods without preserving `this`
  const badCompose = function (functions) {
    return function (x) {
      let result = x;
      for (let i = functions.length - 1; i >= 0; i--) {
        // NOTE: badCompose 함수에서는 위의 this의 컨텍스트를 보존하지 않는다.
        result = functions[i](result);
      }
      return result;
    };
  };

  const badComposedFn = badCompose([obj.increment, obj.double]);
  console.log(badComposedFn(1)); // This will return NaN, because `this` is not `obj` inside `increment` and `double`
  ```

  JavaScript에서 `this`는 **호출 컨텍스트에 따라 결정되므로**, 함수가 호출될 때 결정된다. 위의 코드에서 문제가 생긴 이유는 `obj`의 `increment`와 `double`은 `obj`를 `this` 로 참조하는 것을 예상하지만, 실제 JavaScript가 실행될 때는 `this`가 함수가 호출될 때 결정되고 `functions`는 일반 함수 호출로 간주되기 때문에 `this`가 전역 객체로 간주된다. (즉, `badCompose` 내부에서는 `obj`가 컨텍스트가 아니라서 문제가 생긴다.)

  따라서 명시적으로 보존할 컨텍스트를 `call`에 작성함으로써, **해당 컨텍스트 내부에서 함수를 호출할 수 있다.**

  ```javascript
  result = functions[i].call(obj, result);
  ```

### Using External Libraries

`Rambda`나 `Lodash`와 같은 라이브러리를 사용하여 function compose를 사용할 수 있다.

- 라이브러리 사용 예제

  - `Rambda`

    ```javascript
    import { compose } from "ramda";
    const composedFn = compose(...functions);
    ```

  - `Loadsh`

    ```javascript
    import { flowRight } from "lodash";
    const composedFn = flowRight(...functions);
    ```

## Note

> **`Array.prototype.reduceRight()`**
>
> reduce를 배열의 마지막 원소부터 하고 싶을 때 사용한다.
>
> **예시**
>
> ```typescript
> functions.reduceRight((acc, fn) => fn(acc), x);
> ```
