## 핵심 컨셉 : higher-order function

- 다른 함수의 동작을 수정하거나 확장하는 함수를 위하여 `higher-order function`을 이용한다.
- 이는 elegant하고 resusable 한 코드를 작성하는데 매우 유용하다.

### `higher-order function`의 활용 사례

- **Throttle**

  `throttling`을 통하여 해당 함수는 단위 시간에 한정되어 있는 수의 request 만을 요청함을 보장할 수 있다.
  🌧️ 다양한 throttle 함수의 구현을 보면, 콜백 함수를 받고 해당 콜백함수에 throttle 함수를 통하여 **몇 초에 한번씩 해당 콜백함수를 실행하는** 식으로 구현한다. 즉, throttle 함수는 입력으로 받은 콜백함수에 대해 특정 제약사항을 추가한 형태의 함수를 호출하는 것이다.

  <br>

- **Memoize**

  일반적으로 **동일한 입력이 있는 순수 함수를 두 번 호출하지 않고 이전에 cache된 결과만 반환**하여 성능 최적화가 가능하다.

  - 이 기능을 위하여 [memoizee](https://www.npmjs.com/package/memoizee) 패키지를 사용할 수 있다.

- **Time Limit**

  비동기 함수에 Time Limit를 사용하여, 영영 결과가 돌아오지 않아서 blocking 되는 현상을 막을 수 있다.

## Use-cases for Only Allowing One Function Call (`once`)

<details>
<summary>문제에 사용한 코드 확인하기</summary>
<div markdown="1">

```typescript
function once<T extends (...args: any[]) => any>(
  fn: T
): (...args: Parameters<T>) => ReturnType<T> | undefined {
  // 함수 내부에서 함수 호출 정보를 가지고 있어야하므로, closure 이용
  let isCalled = false;

  return function (...args): ReturnType<T> | undefined {
    if (isCalled) {
      return undefined;
    }

    isCalled = true;
    return fn(...args);
  };
}
```

</div>
</details>

- 문제에 사용된 코드는 **initialization**에 유용하게 사용할 수 있다. 예를 들어 파일을 메모리에 로드하거나 하는 함수(expensive operation)를 **딱 한번만 로드되도록 보장하고 싶을 때** 해당 함수를 사용할 수 있다.
- **_loadsh_**와 같은 라이브러리를 통하여 [once](https://lodash.com/docs/4.17.15#once) 함수를 사용할 수 있다.

### Approach 3 : `Bind` Function to a Context

- higher-order function을 구현하기 위한 기술적으로 가장 올바른 방식은 **제공된 함수가 반환된 함수와 동일한 컨텍스트에 바인딩되도록 하는 것**이다.
- 예시

```javascript
const context = { Name: "Alice" };

function getName() {
  return this.Name;
}

const boundGetName = getName.bind(context); // getName의 this 컨텍스트를 context로 고정한 함수를 정의함
boundGetName(); // "Alice"

getName(); // undefined, getName 함수의 this 컨텍스트는 global 객체이므로
getName.apply(context, []); // "Alice", 함수를 호출하면서 this 지정. 두 번째 인자는 전달할 매개변수의 배열을 나타낸다.
```

- 프로토타입 기반 상속 활용하기

```javascript
const Person = function (name) {
  this.name = name; // 생성자 함수이므로 this는 이 생성자 함수로 만들어지는 객체를 가리킴.
};

// 프로토타입 메서드 : Person의 모든 인스턴스가 사용할 수 있는 메서드를 정의함
Person.prototype.getName = once(function () {
  return this.name; // 호출하는 인스턴스를 가리킨다. once가 사용되었으므로 실제로 getName 메서드는 처음 호출될때만 실제로 사용됨.
});

const alice = new Person("Alice");
alice.getName(); // Expected Output: "Alice"
```

## Note

- **`argument` syntax**

  - arrow function을 제외하고, `arguments` 변수를 사용하여 매개변수에 접근할 수 있다. (그러나 이는 대부분 rest 구문으로 대체되었다. 왜냐하면 입력값을 미리 작성하지 않는 경우 혼동을 일으킬 수 있다.)

  <br>

- **❇️`TypeScript`의 유틸리티 타입 중 `ReturnType`**

  함수의 반환 타입을 추출한다. `<T>`는 함수 타입을 나타낸다.

  - 코드 예시

  ```typescript
  function once<T extends (...args: any[]) => any>(
    fn: T
  ): (...args: Parameters<T>) => ReturnType<T> | undefined {
    let isCalled = false;

    return function (...args): ReturnType<T> | undefined {
      if (isCalled) {
        return undefined;
      }

      isCalled = true;
      return fn(...args);
    };
  }
  ```

  이 코드에서 `once` 함수의 반환 타입은 **"원래 함수와 동일한 인수를 받고, 원래 함수의 반환 타입 또는 `undefined`를 반환하는 함수"**를 의미한다. 즉, **원래 함수의 반환 타입**을 나타내기 위하여 유틸리티 타입인 `ReturnType`을 이용한다.

<br>

- **`call`과 `apply`의 차이**

- `call` : 첫 번째 인수로 `this`의 값을 받고, 그 이후의 인수들은 호출할 함수의 매개변수로 전달한다. 각 매개변수는 별도의 인수로 전달한다. 즉, **수를 `,`로 분리하여 전달한다.**
- `apply` : 첫 번째 인수로 `this`의 값을 받고, 두 번째 인수로 함수의 매개변수의 **배열**을 받는다. 즉, **인수를 배열로 전달한다.**
- 예시

```javascript
function multiply(a, b) {
  return a * b;
}

console.log(multiply.call(null, 5, 2)); // 10, 인수를 ','로 분리해서 전달
console.log(multiply.apply(null, [5, 2])); // 10, 인수를 배열로 전달
```
