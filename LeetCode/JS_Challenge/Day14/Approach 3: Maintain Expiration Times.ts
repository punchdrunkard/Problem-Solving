// 키를 삭제하는 타이머를 유지하는 대신, 만료 시간을 저장
// Data.now()를 통하여 현재 시간을 가져오고, 만료 시간을 저장한다.
type Entry = { value: number; expiration: number };

class TimeLimitedCache3 {
  cache: Record<string, Entry> = {};

  set(key: number, value: number, duration: number): boolean {
    // in 연산자를 통해 this.cache라는 객체가 key라는 속성을 가지는지 확인한다.
    // 만약 key라는 속성이 있다면, 해당 속성의 만료 시간(expiration)이  Date.now()보다 큰지 확인한다.
    const hasUnexpiredCache =
      key in this.cache && this.cache[key].expiration > Date.now();

    this.cache[key] = { value, expiration: Date.now() + duration };

    return hasUnexpiredCache;
  }

  get(key: number): number {
    if (!this.cache[key]) return -1;

    // 만료시간 이후인 경우
    if (Date.now() > this.cache[key].expiration) {
      return -1;
    }

    return this.cache[key].value;
  }

  count(): number {
    return Object.values(this.cache).reduce((count, entry) => {
      return entry.expiration > Date.now() ? count + 1 : count;
    }, 0);
  }
}

// Approach 3 has a few problems

// The values are stored in memory even after they have expired. This could be considered a memory leak.
// Counting the unexpired keys is an O(N)O(N)O(N) operation with respect to the size of the cache.
// We could fix the memory leak problem by removing all expired keys every time set is called, but this makes set an O(N)O(N)O(N) operation as well.

// A more optimal approach is to maintain a sorted list of all the expiration times in a Priority Queue. This allows us to remove an element that is expired in O(logN)O(logN)O(logN) time.
