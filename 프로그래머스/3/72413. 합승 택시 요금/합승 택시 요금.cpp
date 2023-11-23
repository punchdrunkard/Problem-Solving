#include <bits/stdc++.h>
using namespace std;

typedef pair<int, int> pii;
vector<vector<pii>> adj; // {가중치, 도착점}

// fares = [ [시작, 도착, cost], ]
void init(int n, vector<vector<int>> fares){
    adj.clear();  // 그래프 초기화
    adj.resize(n + 1);
    
    for (auto &fare: fares){
        // 양방향 그래프
        int st = fare[0];
        int en = fare[1];
        int cost = fare[2];
        
        adj[st].push_back({ cost, en });
        adj[en].push_back({ cost, st });
    }
}

vector<int> dijkstra(int st){
    int n = adj.size();
    
    priority_queue<pii, vector<pii>, greater<pii>> pq;
    vector<int> dist(n, INT_MAX);
    
    pq.push({0, st});
    dist[st] = 0;
    
    while (!pq.empty()){
        pii current = pq.top();
        pq.pop();
        
        int nowEn = current.second;
        int nowCost = current.first;
        
        if (nowCost > dist[nowEn]){
            continue;
        }
        
        for (auto &edge: adj[nowEn]){
            int nextEn = edge.second;
            int nextCost = edge.first + nowCost;
            
            if (nextCost < dist[nextEn]){
                dist[nextEn] = nextCost;
                pq.push({ nextCost, nextEn });
            }
        }
    }
    
    return dist;
}

int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
    init(n, fares);
    
    vector<int> s_dist = dijkstra(s);
    vector<int> a_dist = dijkstra(a);
    vector<int> b_dist = dijkstra(b);

    int answer = INT_MAX;
    
    // 모든 지점을 순회하며 최소 비용 계산
    for (int i = 1; i <= n; ++i) {
        if (s_dist[i] != INT_MAX && a_dist[i] != INT_MAX && b_dist[i] != INT_MAX) {
            answer = min(answer, s_dist[i] + a_dist[i] + b_dist[i]);
        }
    }
    
    return answer;
}
