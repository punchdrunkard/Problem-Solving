#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int result, input;
    string res = "DCBAE";

    for (int i = 0; i < 3; i++)
    {
        result = 0;
        for (int j = 0; j < 4; j++)
        {
            cin >> input;
            result += input;
        }
        cout << res[result] << '\n';
    }
}