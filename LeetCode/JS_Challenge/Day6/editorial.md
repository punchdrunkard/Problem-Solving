## Use-cases of Reduce

- Reduce는 array의 각 원소를 iterate하며 **_accumulator_** 에 값을 누적시킨다.
- **첫 번째 인자** : `callback` (현재 `accumulator`와 array의 각 value에 적용할 콜백 함수), **두 번째 인자** : current `accumulator`

> Some JavaScript developers use it for almost all array iterations when **`Array.map` and `Array.filter` don't solve the problem.**

### Sum Values in Array

```javascript
const nums = [1, 2, 3];
const sum = nums.reduce((acc, val) => acc + val, 0);
```

### Index Array by Key

- 데이터 목록을 가져와서, 각 데이터를 특정 키로 indexing 하는 작업

```javascript
const groceries = [
  { id: 173, name: "Soup" },
  { id: 964, name: "Apple" },
  { id: 535, name: "Cheese" },
];

const indexedGroceries = groceries.reduce((acc, val) => {
  acc[val.id] = val;
  return acc;
}, {});

console.log(indexedGroceries);
/**
 * {
 *   "173": { id: 173, name: "Soup" },
 *   "964": { id: 964, name: "Apple" },
 *   "535": { id: 535, name: "Cheese" },
 * }
 */
```

- 🌧 이 예제의 경우, 직접 `forEach`와 같은 반복문을 돌고 temp 변수를 만드는 과정을 간단하게 수행할 수 있게 한다.

- **주의사항** : 이러한 연산을 할 때, acc를 복사하는 작업을 하면 안된다. (복사하는 비용까지 n의 순회를 돌게 되므로 추가적인 시간 복잡도가 든다.)
  > Note that a common performance mistake that developers make is to create a clone of the accumulator for each array iteration. i.e. return { ...accumulator, [val.id]: val };.
  > This results in an O(n^2) algorithm.

### Combine Filter and Map

- `filter().map()`을 함께 사용하게 되는 경우, 두 array method가 각자 새로운 배열을 만들기 때문에 less efficient 하다. (` Two arrays are created when only one is necessary.`)
- `filter().map()` 작업은 `reduce`를 활용하여 improved performance를 낼 수 있다.

```javascript
// 목표 : id가 500이상인 groceries의 name의 배열 만들기
const groceries = [
  { id: 173, name: "Soup" },
  { id: 964, name: "Apple" },
  { id: 535, name: "Cheese" },
];

/** With filter and map */
var names_with_filter_and_map = groceries
  .filter((item) => item.id > 500)
  .map((item) => item.name);

/** With Reduce */
var names_with_reduce = groceries.reduce((acc, val) => {
  if (val.id > 500) acc.push(val.name);
  return acc;
}, []);

console.log(names); // ["Apple", "Cheese"]
```

## Built-in Array.reduce

- `Array.reduce`의 다른 인자
  - 3번째 인자 : `currentIndex`
  - 4번째 인자 : reference to the original array
- `Array.reduce optionally allows you to NOT pass an initialValue as the 2nd parameter.
  - pass하지 않는 경우, `initialValue`는 **해당 배열의 첫 번째 원소**
- sparse array를 처리할 수 있음.
  - `reduce()`를 호출하기 전에 모든 빈 값이 자동으로 필터링 됨.
