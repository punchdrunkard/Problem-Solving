> This question is intended as an introduction to JavaScript functions. This editorial will cover their syntax and topics like closures and higher-order functions.

## Closures

```javascript
function createAdder(a) {
  function f(b) {
    const sum = a + b;
    return sum;
  }
  return f;
}
const f = createAdder(3);
console.log(f(4)); // 7
```

- in this example, inner function `f` can access to `createAdder`'s parameter `a`.
- this way, `createAdder` serves as a factory of new functions (with each returned function having different behavior)

## Rest Arguments

> You can use **rest** syntax to access all the passed arguments as an array.

### Basic Syntax

```javascript
function f(...args) {
  const sum = args[0] + args[1];
  return sum;
}
console.log(f(3, 4)); // 7,  args =[3, 4]
```

## Why?

> The primary use-cases is for creating generic factory functions that accept any function as input and return a new version of the function with some specific modification.

- generic factory
  - 여러 가지 유형의 객체를 생성할 수 있는 공통 인터페이스를 제공한다.
  - 팩토리 함수의 핵심 목적은 다양한 종류의 객체를 생성하는 공통 로직을 캡슐화 하여 코드의 재사용성과 유지보수성을 높힌다.
  - generic factory function은 어떤 **함수**를 input을 받아서, 해당 함수를 몇몇 특정한 modification을 가하여, **새로운 함수**로 반환하는데, 이를 **higher-order-function**이라고 한다.

```javascript
function log(inputFunction) {
  return function (...args) {
    console.log("Input", args);
    const result = inputFunction(...args);
    console.log("Output", result);
    return result;
  };
}
const f = log((a, b) => a + b);
f(1, 2); // Logs: Input [1, 2] Output 3
```
