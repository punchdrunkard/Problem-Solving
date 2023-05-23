let memoize4 =
  (fn, cache = {}) =>
  (...args) =>
    cache[args.join()] ?? (cache[args.join()] = fn(...args));
