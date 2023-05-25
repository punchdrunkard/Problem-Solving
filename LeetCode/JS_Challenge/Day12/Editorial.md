## 핵심 컨셉 : Time Limit

## NOTE

### 자바스크립트에서 함수를 인자로 전달하는 두 가지 방법

1. 함수 참조를 전달하는 방법 : 함수의 이름만 전달하며, 이 함수는 나중에 호출될 수 있다.

```javascript
function myFunction() {
  console.log("Hello, world!");
}

// 함수 자체가 전달되며, setTimeout 내부의 로직에 의해 이 함수가 나중에 호출된다.
setTimeout(myFunction, 1000);
```

2. 함수를 즉시 실행하는 방법 : 함수를 즉시 호출하며, 함수의 반환값이 `setTimeout`에 전달된다.

```javascript
function myFunction() {
  console.log("Hello, world!");
}

// myFunction의 리턴값 (undefined)가 전달된다.
setTimeout(myFunction(), 1000);
```

문제에서는 **화살표 함수를 사용하여, 함수를 전달한다.**

### Emulate

- emulate : 일반적으로 "흉내내다" 또는 "모방하다"라는 뜻을 가지고 있다. 컴퓨터 과학에서 이는 특정 시스템이나 프로세스가 다른 시스템이나 프로세스의 동작을 모방하네 하는 것을 의미한다.
