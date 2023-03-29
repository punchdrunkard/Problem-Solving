# 맨날 까먹는 자바스크립트 문법 및 주의사항 모음

## 목차

  <ul>
    <li>
      <a href="#다차원-배열-선언하기">다차원 배열 선언하기</a>
     </li>
     <li>
     <a href="#문자열-뒤집기">문자열 뒤집기</a>
     </li>
     <li>
     <a href="#큰-수-다루기">큰 수 다루기</a>
     </li>
      <li>
     <a href="#소수점-자리수-설정하기">소수점 자리수 설정하기</a>
     </li>
  </ul>

---

## 다차원 배열 선언하기

`Array.from` 을 이용한다.
이는 `Array.from({ 만들고자하는 배열 껍데기, 그 배열을 채울 원소를 만들 callback)` 의 형태로 사용하며, **주의 할 점은 callback 에 바로 `new Array(3)`과 같은 방식을 사용하면 l 개 만큼의 똑같은 참조값을 만드는 셈이된다.**

따라서 `callback` 부분에는 화살표 함수를 이용하여 새로운 Array 를 생성하는 함수를 작성해준다.

```javascript
// r행, c열의 배열을 만들고, 배열의 원소를 0으로 초기화하는 코드
Array.from(new Array(r), () => new Array(c).fill(0));

// 다음과 같은 형태도 가능하다.
Array.from({ length: r }, () => new Array(c).fill(0));
```

## 문자열 뒤집기

`JavsScript` 에서 문자열을 뒤집는 방밥은 배열의 `reverse()` 를 이용하기 위하여 **문자열을 배열로 변환하고, 배열을 뒤집은 후, 그 배열을 다시 문자열로 만드는 것** 이다.

정리하면

1. `String.prototype.split()` 를 이용하여 문자열을 배열로 변환한다.
2. `Array.prototype.reverse()` 를 이용하여 배열을 뒤집는다.
3. `Array.prototype.join()` 를 이용하여 배열을 문자열로 변환한다.

```javascript
const string = "blahblah";
string.split("").reverse().join("");
```

## 큰 수 다루기

`JavaScript`에서 `number` 타입의 범위는 **`-(2^53 − 1)`부터 `2^53 − 1`** 까지의 수이다. (실수와 정수 구분을 하지 않기 때문에)

따라서 큰 수의 연산이 필요할 때-범위가 `-(2^64 − 1)`부터 `2^64 − 1`로 주어질 때(`C++`에서 `long long`을 사용하는 경우)-
`BigInt` 를 이용하여 명시적으로 자료형을 변환해야한다.

또한 이 타입을 사용할 경우, 각 수는 `BigInt` 끼리만 계산이 가능하며, 출력할 때 `toString` 을 통하여 문자열로 바꿔주는 과정이 필요하다.

### 주의점! : JavaScript는 모든 수를 실수형으로 처리하므로, 다른 언어에 비해 정수의 최댓값이 작다.

JavaScript는 숫자 타입이 `number` 하나 밖에 없기 때문에, 모든 비트를 **실수형**으로 관리한다. (double precision, IEEE)
따라서, 수를 저장하는 비트가 다른 언어의 `int` 처럼 많지 않기 때문에 큰 수의 계산이 필요할 때 가급적이면 `JavaScript`를 피하거나,
`BigInt`를 사용하여야 한다.

## 소수점 자리수 설정하기

`toFixed(소숫점 자리수)`를 사용한다.

```javascript
let a = 1.24 * 3.964;

// 소수 세 번째 자리까지 출력
console.log(a.toFixed(3)); // 4.915
```
