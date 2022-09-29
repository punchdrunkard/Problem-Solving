#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main()
{
    int a, b, c;
    cin >> a >> b >> c;

    if (a == b && b == c && a == c)
    {
        cout << 10000 + a * 1000;
    }
    else if (a == b || a == c || a == b || b == c)
    {
        int sameNumber;

        if (b == c)
        {
            sameNumber = b;
        }
        else
        {
            sameNumber = a;
        }

        cout << 1000 + sameNumber * 100;
    }
    else
    {
        cout << 100 * max({a, b, c});
    }
}