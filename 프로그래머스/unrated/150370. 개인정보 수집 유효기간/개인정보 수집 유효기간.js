// 시뮬레이션
// 오늘 날짜에 대해서, 파기해야 할 개인정보의 번호를 오름차순으로
// terms <= 20, privavies <= 100 이라서 따로 최적화는 안해도 될듯..

//  "2020.12.17", ["A 12"], ["2010.01.01 A", "2019.12.17 A"]

function solution(today, terms, privacies) {
    var answer = [];
    
    // 각 날짜를 파싱하여 저장한다.
    // 마지막 원소의 경우, 약관과 함께 오기 때문에, 이에 대한 처리도 한다. 
    const parsedPrivacies = privacies.map(privacie => {
        const parts = privacie.split(' ');
        const dateParts = parts[0].split('.').map(Number);
        
        return [
            ...dateParts,
            parts[1],
        ]
    });
    
    // 각 terms를 hash 형태로 저장한다.
    const termsMap = new Map();
    
    terms.forEach(term => {
        const parsed = term.split(' ');
        termsMap.set(parsed[0], parseInt(parsed[1]));
    });
    
    const [YEAR, MONTH, DAY] = [0, 1, 2];
    
    // parsedPrivacies에 각 약관을 적용한다.
    const expireDate = parsedPrivacies.map(privacie => {
        const term = termsMap.get(privacie[3]);
        const date = privacie.slice(0, 3)
        const newDate = [...date];
        
        // date에 term을 적용한다.
        newDate[MONTH] += term;
        
     
         // 월이 12일 이하일 때 까지 감소시키기
        while (newDate[MONTH] > 12){
            newDate[MONTH] -= 12;
            newDate[YEAR] += 1;
        }
        

        return [
            ...newDate
        ]
    })
    
    console.log(expireDate);
    
    // expireDate와 현재 날짜를 비교한다. => 문자열 비교해도 될 듯
    expireDate.forEach((date, index) => {
        const yearString = date[0].toString();
        const monthString = date[1] < 10 ? '0' + date[1].toString() : date[1].toString();
        const dayString = date[2] < 10 ? '0' + date[2].toString() : date[2].toString();
        
        const dateString = yearString + '.' + monthString + '.' + dayString;
        
        console.log('dateString', dateString);
        
        if (dateString <= today){
            answer.push(index + 1);
        }
    })
        
    return answer;
}