type CurryFnBind = (...args: any[]) => any;

function curryBind(fn: CurryFnBind): CurryFnBind {
  // curried 함수는 클로저를 형성하여 이전 호출에서 받은 인자들을 기억하며, (자신이 선언된 환경, 즉 args 인자를 기억함)
  // bind 메소드를 이용해 이 인자들을 다음 호출의 context에 바인딩 한다.
  return function curried(...args: any[]): CurryFnBind | any {
    if (args.length >= fn.length) {
      return fn.apply(this, args);
    }

    // bind 메서드를 사용하여, accumulated 된 arguments들을 해당 컨텍스트 (this)에 기억함
    return (...nextArgs: any[]) => curried.bind(this, ...args, ...nextArgs);
  };
}
