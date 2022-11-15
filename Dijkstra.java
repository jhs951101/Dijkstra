package pkg;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {
	
	public int INF = 1000000000;
    // INF: 무한대 값

	public void main() {
		int n = 6;
		int start = 1;
		int[][] roots = {{1,5,3}, {1,6,4}, {1,3,7}, {2,3,3}, {2,4,2}, {3,5,1}, {4,5,6}, {4,6,8}, {5,6,5}};

		// n: 총 노드의 수
		// start: 중심이 될 노드
		// roots: 간선 정보들 - {노드A, 노드B, 간선 비용}

		List<List<int[]>> graph = new ArrayList<>();
		// graph: 최단 거리를 담는 표 - {도착지, 비용} = graph[출발지]

		List<Integer> distance  = new ArrayList<>();
		// distance: 중심 노드부터 각 도착점까지의 모든 최단 거리
		// 최단 거리를 모두 무한 값(INF)으로 초기화 후 진행
		
		for(int i=0; i<n+1; i++){
	        List<int[]> empty = new ArrayList<>();
	        graph.add(empty);
	        
	        distance.add(INF);
	    }

		// 간선 정보를 표에 삽입
		// 양방향으로 이동 가능하므로, '노드A -> 노드B', '노드B -> 노드A', 이렇게 2번 삽입
		for(int i=0; i<roots.length; i++){
			List<int[]> obj1 = graph.get( roots[i][0] );
			obj1.add( new int[]{roots[i][1], roots[i][2]} );
			
			List<int[]> obj2 = graph.get( roots[i][1] );
			obj2.add( new int[]{roots[i][0], roots[i][2]} );
		}

		List<int[]> queue = new ArrayList<>();
		queue.add( new int[]{start, 0} );
		distance.set(start, 0);

		while(queue.size() >= 1){
			int node = queue.get(0)[0];
			int dis = queue.get(0)[1];
			queue.remove(0);

			// 둘러보기를 끝낸 후 무효 처리 되었을 경우 제외
			if(distance.get(node) < dis)
				continue;

			for(int[] info: graph.get(node)){
				int cost = dis + info[1];

				// '포커스된 노드 값 + 간선 값 < 발견된 노드 값' 이 성립하면 대체
				// 더한 값이 더 작으면 더 작은 값으로 바꾼다는 뜻
				if(cost < distance.get(info[0])){
					distance.set(info[0], cost);
					queue.add( new int[]{info[0], cost} );
				}
			}
		}

		// 각 도착점까지 모든 최단 거리 출력
		for(int i=1; i<n+1; i++){
			if(start != i){
				System.out.print("Node " + start + " → " + i + " : ");
				
				if(distance.get(i) == INF)
					System.out.print("IMPOSSIBLE");
				else
					System.out.print(distance.get(i) + " costs");
				
				System.out.println();
			}
		}
    }

    public static void main(String[] args) {
    	Dijkstra main = new Dijkstra();
    	main.main();
    }
}