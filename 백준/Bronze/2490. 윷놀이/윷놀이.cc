#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    for (int i = 0; i < 3; i++)
    {
        // 0 배 1 등
        // 도(A) : 배 1, 등 3 -> 3
        // 개 B: 배 2 등 2 -> 2
        // 걸 C1
        // 윷 D 0
        // 모 E 4
        int a, b, c, d;
        cin >> a >> b >> c >> d;

        int sum = a + b + c + d;

        switch (sum)
        {
        case 1:
            cout << "C"
                 << "\n";
            break;
        case 2:
            cout << "B"
                 << "\n";
            break;
        case 3:
            cout << "A"
                 << "\n";
            break;
        case 4:
            cout << "E"
                 << "\n";
            break;
        case 0:
            cout << "D"
                 << "\n";
            break;
        }
    }
}