#include <bits/stdc++.h>

using namespace std;

// 행사 목적을 "최대한" 으로 달성했을 때 이모티콘 플러스 서비스 가입수와
// 이모티콘 매출액

// "할인율은 10%, 20%, 30%, 40% 중 하나로 설정됩니다."
// 숫자가 엄청 작기 때문에 완전탐색도 가능할듯 
// 전체 경우의 수 : 4^7 = 2^14 = 16,384

int rate[4] = {10, 20, 30, 40};
vector<vector<int>> discounts;

// 할인율의 조합을 만드는 백트래킹 
void makeSequence(vector<int> sequence, int length){
    if (sequence.size() == length){
        discounts.push_back(sequence);
        return;
    }
    
    for (int i = 0; i < 4; i++){
        sequence.push_back(rate[i]);
        makeSequence(sequence, length);
        sequence.pop_back();
    }
}

vector<int> solution(vector<vector<int>> users, vector<int> emoticons) {
    vector<int> answer = {-1, -1};
    
    // 가능한 할인율의 모든 조합을 만든다.
    makeSequence({}, emoticons.size());
    
    for (auto &discount: discounts){
       // 각 할인률에 따라서 사용자가 이를 구매할지 안할지 보고 사용자의 구매 비용을 계산
        vector<int> buy_price(users.size(), 0);
        
        for (int i = 0; i < discount.size(); i++){ // 첫번째 이모티콘 부터..
             // 사용자의 구매 비용에 따라 이모티콘 플러스 서비스 가입 여부 계산
            // cout << i << "번 이모티콘을 " << discount[i] << "퍼센트 할인함\n";
            
            for (int j = 0; j < users.size(); j++){
                if (discount[i] >= users[j][0]){ // 이모티콘을 구매함
                    buy_price[j] += emoticons[i] * (100 - discount[i]) / 100;
                }
            }
        }
        
        int count = 0, sell_price = 0; // 이모티콘 플러스 가입자 수, 이모티콘 총 판매액
        
        // 이모티콘 플러스에 가입하는 사람 수 카운팅 
        for (int i = 0; i < users.size(); i++){
            if (buy_price[i] >= users[i][1]){
                count++;
                buy_price[i]  = 0;
            }
        }
        
        // 이모티콘 판매 가격 카운팅
        for (auto &price : buy_price){
            sell_price += price;
        }
        
        // 답 갱신
        if (answer[0] < count){
            answer = {count, sell_price};
        } else if (answer[0] == count) {
            if (answer[1] < sell_price){
                answer = {count, sell_price};
            }
        }
    }
    
    return answer;
}