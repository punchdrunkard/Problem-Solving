#include <bits/stdc++.h>
using namespace std;

int main() {
    int N;
    cin >> N;

    int count = 1; 
    int range = 1;

    while (N > range) {
        range += (6 * count); // 각 단계에서 더해지는 수는 6의 배수가 더해짐
        count++; // 벌집 층을 하나 올라감
    }

    cout << count << endl;

    return 0;
}

