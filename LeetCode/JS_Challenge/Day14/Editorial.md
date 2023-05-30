## Cache with a Time Limit

> 문제의 목표
> key-value store 를 구현할 때, **각 entry가 특정 시간 이후에 expires** 되어야 한다.

## Approach 1 : setTimeout + clearTimeout + Class Syntax

- `key`-`value` 가 cache 에 저장될 때 마다 **만료 시간이 경과한 후 해당 키를 삭제**하는 타이머를 만든다.
- 주의해야 할 점은 **시간이 만료되기 전에 기존 키를 덮어쓸 때** 이다.
- **타이머에 대한 `ref`를 유지해야 키가 덮어쓰였을 때를 처리할 수 있다.**

## Note

- `Record`는 TypeScript의 유틸리티 타입 중 하나로, 특정 타입의 키와 값을 가지는 객체를 생성한다.
  `Record<K, T>` 형태로 사용하며, `K`는 키의 타입, `T`는 값의 타입을 나타낸다.

  예를 들어 `Record<string, number>`는 모든 키가 문자열이고, 모든 값이 숫자인 객체 타입을 나타낸다.
