#include<iostream>
#include<vector>

#define INF 1000000000
// INF: 무한대 값

using namespace std;

int main(){
    int n = 6;
    int start = 1;
    vector<vector<int>> roots = {{1,5,3}, {1,6,4}, {1,3,7}, {2,3,3}, {2,4,2}, {3,5,1}, {4,5,6}, {4,6,8}, {5,6,5}};

    // n: 총 노드의 수
    // start: 중심이 될 노드
    // roots: 간선 정보들 - {노드A, 노드B, 간선 비용}

    vector<vector<vector<int>>> graph(n+1);
    // graph: 최단 거리를 담는 표 - {도착지, 비용} = graph[출발지]

    vector<int> distance(n+1, INF);
    // distance: 중심 노드부터 각 도착점까지의 모든 최단 거리
    // 최단 거리를 모두 무한 값(INF)으로 초기화 후 진행

    // 간선 정보를 표에 삽입
    // 양방향으로 이동 가능하므로, '노드A -> 노드B', '노드B -> 노드A', 이렇게 2번 삽입
    for(int i=0; i<roots.size(); i++){
        graph[ roots[i][0] ].push_back( {roots[i][1], roots[i][2]} );
        graph[ roots[i][1] ].push_back( {roots[i][0], roots[i][2]} );
    }

    vector<vector<int>> queue;
    queue.push_back({start, 0});
    distance[start] = 0;

    while(queue.size() >= 1){
        int node = queue[0][0];
        int dis = queue[0][1];
        queue.erase(queue.begin());

        // 둘러보기를 끝낸 후 무효 처리 되었을 경우 제외
        if(distance[node] < dis)
            continue;

        for(vector<int> info: graph[node]){
            int cost = dis + info[1];

            // '포커스된 노드 값 + 간선 값 < 발견된 노드 값' 이 성립하면 대체
            // 더한 값이 더 작으면 더 작은 값으로 바꾼다는 뜻
            if(cost < distance[info[0]]){
                distance[info[0]] = cost;
                queue.push_back({info[0], cost});
            }
        }
    }

    // 각 도착점까지 모든 최단 거리 출력
    for(int i=1; i<n+1; i++){
        if(start != i){
            cout << "Node " << start << " → " << i << " : ";
            
            if(distance[i] == INF)
                cout << "IMPOSSIBLE";
            else
                cout << distance[i] << " costs";
            
            cout << endl;
        }
    }

    return 0;
}