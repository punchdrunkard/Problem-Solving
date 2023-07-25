#include <bits/stdc++.h>

using namespace std;

bool isOper(string token) {
  if (token == "+" || token == "-" || token == "*" || token == "/") {
    return true;
  }

  return false;
}

double getOperResult(string type, double op1, double op2) {
  if (type == "+") {
    return op1 + op2;
  }

  if (type == "-") {
    return op1 - op2;
  }

  if (type == "*") {
    return op1 * op2;
  }

  if (type == "/") {
    return op1 / op2;
  }

  return -1;
}

struct TreeNode {
  string data;
  int left_index;
  int right_index;
};

void postOrder(int idx, int n, stack<string> &stk, vector<TreeNode> &tree) {
  if (idx < 1 || idx > n) return;

  postOrder(tree[idx].left_index, n, stk, tree);
  postOrder(tree[idx].right_index, n, stk, tree);

  string data = tree[idx].data;

  if (isOper(data)) {
    double oper2 = stod(stk.top());
    stk.pop();
    double oper1 = stod(stk.top());
    stk.pop();

    double res = getOperResult(data, oper1, oper2);
    stk.push(to_string(res));

  } else {
    stk.push(data);
  }
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  for (int test_case = 1; test_case <= 10; test_case++) {
    int n;  // 정점의 갯수
    cin >> n;
    cin.ignore();

    vector<TreeNode> tree(n + 1);

    for (int i = 0; i < n; i++) {
      string line;
      getline(cin, line);
      istringstream iss = istringstream(line);

      vector<string> tokens;
      string token;

      while (getline(iss, token, ' ')) {
        tokens.push_back(token);
      }

      int index = stoi(tokens[0]);

      // data가 연산자라면 2개의 child를 가지고, 숫자라면 child를 가지지 않는다.
      // (= 숫자라면 leaf 노드이다.)

      if (tokens.size() == 4) {
        tree[index].data = tokens[1];
        tree[index].left_index = stoi(tokens[2]);
        tree[index].right_index = stoi(tokens[3]);
      }

      if (tokens.size() == 2) {
        tree[index].data = tokens[1];
      }
    }

    stack<string> stk;
    postOrder(1, n, stk, tree);
    cout << "#" << test_case << " " << stoi(stk.top()) << "\n";
  }

  return 0;
}
