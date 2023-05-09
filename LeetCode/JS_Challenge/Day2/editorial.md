> This question is intended as an introduction to **closures**.

## Closures Versus Classes

- 문법은 다르지만, 둘 다 같은 목적으로 사용된다.
- 둘 다 "constructor"에 state를 넘길 수 있고, "method"를 이용하여 그 state에 접근할 수 있다.

## 차이점

> One key difference is that closures allow for true _encapsulation._

- (2022년 이전) class에서는 완전한 private 변수가 없었다.

  - 그러나 2022년 이후의 새로운 ECMAScript 명세에서는 `# prefix syntax`를 이용하여 private로 사용할 수 있음.

> Another difference is how the functions are stored in memory.

- 클래스의 인스턴스를 여러 개 생성하는 경우, 각 인스턴스는 모든 메서드가 저장된 프로토타입 객체에 대한 단일 참조를 저장
- 클로저의 경우, 모든 "메서드"가 생성되고, 외부 함수가 호출될 때 마다 각 메서드의 "복사본"이 메모리에 저장된다.
  => 따라서, 클래스는 특히 메서드가 많은 경우에 더 효율적일 수 있다.
