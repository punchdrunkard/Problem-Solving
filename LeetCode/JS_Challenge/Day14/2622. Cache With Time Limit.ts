// timeout에는 timer 객체에 대한 참조를 저장한다.
type MapEntry = { value: number; timeout: NodeJS.Timeout };

class TimeLimitedCache {
  cache = new Map<number, MapEntry>();

  set(key: number, value: number, duration: number): boolean {
    const storedValue = this.cache.get(key);

    if (storedValue) {
      // overwrite 되는 경우, 해당 타이머에 대해서 clearTimeout 을 한다.
      clearTimeout(storedValue.timeout);
    }

    // duration 값을 통하여 새로운 타이머 생성
    // duration이 경과되면 해당 캐시에서 키가 삭제되도록 한다.
    const timeout = setTimeout(() => this.cache.delete(key), duration);
    this.cache.set(key, { value, timeout });

    // 캐시에 값이 있으면 true, 없으면 false를 반환하므로
    return Boolean(storedValue);
  }

  get(key: number): number {
    return this.cache.get(key)?.value ?? -1;
  }

  count(): number {
    return this.cache.size;
  }
}

/**
 * Your TimeLimitedCache object will be instantiated and called as such:
 * var obj = new TimeLimitedCache()
 * obj.set(1, 42, 1000); // false
 * obj.get(1) // 42
 * obj.count() // 1
 */

// cf: prototype 이용해서 함수형으로 구현 가능
