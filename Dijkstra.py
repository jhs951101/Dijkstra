import heapq

# n: 총 노드의 수
# start: 출발점, 중심이 될 노드
# roots: 간선 정보들 - [노드A, 노드B, 간선 비용]
n = 6
start = 1
roots = [[1,5,3], [1,6,4], [1,3,7], [2,3,3], [2,4,2], [3,5,1], [4,5,6], [4,6,8], [5,6,5]]

INF = int(1e9)

# distance: (출발점부터) 각 도착점까지 모든 최단 거리
# 최단 거리를 모두 무한 값(INF)으로 초기화 후 진행
distance = [INF] * (n+1)  
graph = [ [] for _ in range(n+1) ]

# 간선 정보를 그래프에 삽입
# 양방향으로 이동 가능하므로 '노드A -> 노드B' & '노드B -> 노드A' 로 2번 삽입
for info in roots:
    graph[ info[0] ].append( (info[1], info[2]) )
    graph[ info[1] ].append( (info[0], info[2]) )

q = []
heapq.heappush(q, (start, 0)) 
distance[start] = 0

while q:
    now, dis = heapq.heappop(q)

    # 둘러보기를 끝낸 후 무효 처리 되었을 경우 제외
    if distance[now] < dis:
        continue

    for info in graph[now]:
        cost = dis + info[1]

        # '포커스된 노드 값 + 간선 값 < 발견된 노드 값' 이 성립하면 대체
        # 더한 값이 더 작으면 더 작은 값으로 바꾼다는 뜻
        if cost < distance[ info[0] ]:
            distance[ info[0] ] =  cost
            heapq.heappush(q, (info[0], cost))

# 각 도착점까지 모든 최단 거리 출력
for i in range(1, n+1):
    print('Node', start, '→', i, ':', end=' ')
    
    if distance[i] == INF:
        print('IMPOSSIBLE')
    else:
        print(distance[i], 'costs')
