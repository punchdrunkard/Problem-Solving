#include <bits/stdc++.h>

using namespace std;

struct Point {
  int x;
  int y;
};

bool compare(Point a, Point b) {
  if (a.x == b.x) return a.y < b.y;
  return a.x < b.x;
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >> n;

  Point points[n];

  for (int i = 0; i < n; i++) {
    cin >> points[i].x >> points[i].y;
  }

  sort(points, points + n, compare);

  for (int i = 0; i < n; i++) {
    cout << points[i].x << ' ' << points[i].y << '\n';
  }

  return 0;
}
