# 맨날 까먹는 자바스크립트 문법 모음

## 목차

  <ul>
    <li>
      <a href="#다차원-배열-선언하기">다차원 배열 선언하기</a>
     </li>
     <li>
     <a href="#문자열-뒤집기">문자열 뒤집기</a>
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
