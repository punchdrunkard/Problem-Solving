
// 정수 n을 k진법으로 변환하기
const getNdecimal = (n, k) => {
    let result = [];
        
    while (n >= k){
        result.push(n % k);
        n = Math.floor(n / k);
    }
    
    result.push(n);
    
    return result.reverse().join('');
}

// 일단 루트 n까지 나눠보자,,
const isPrime = (number) => {
    if (number === 0 || number === 1) return false;
    
    for (let i = 2; i * i <= number; i++){
        if (number % i === 0) {
            return false;
        }
    }
    
    return true;
}

function solution(n, k) {
    let answer = 0;
    
    // 정수 n을 k로 변환한다.
    let converted = getNdecimal(n, k);
    
    // 해당 변환한 수를 0으로 파싱한다.
    const numbers = converted.split('0').filter(num => num !== '');
    
    // 파싱한 수가 소수인지 판단한다.
    numbers.forEach(number => {
        if (isPrime(parseInt(number))) answer += 1;
    });
    
    return answer;
}