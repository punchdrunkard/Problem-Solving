import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        // 1단계: 모든 로봇의 전체 이동 경로 계산
        List<List<int[]>> allRobotPaths = new ArrayList<>();
        int maxTime = 0;

        for (int[] route : routes) {
            List<int[]> currentRobotPath = new ArrayList<>();
            // 첫 포인트 추가
            int[] startPoint = points[route[0] - 1];
            currentRobotPath.add(startPoint);

            // 경로의 각 구간에 대해 이동 좌표 생성
            for (int i = 0; i < route.length - 1; i++) {
                int[] startPos = points[route[i] - 1];
                int[] endPos = points[route[i+1] - 1];

                int r = startPos[0];
                int c = startPos[1];

                // r좌표 이동
                while (r != endPos[0]) {
                    r += (endPos[0] > r) ? 1 : -1;
                    currentRobotPath.add(new int[]{r, c});
                }
                // c좌표 이동
                while (c != endPos[1]) {
                    c += (endPos[1] > c) ? 1 : -1;
                    currentRobotPath.add(new int[]{r, c});
                }
            }
            allRobotPaths.add(currentRobotPath);
            maxTime = Math.max(maxTime, currentRobotPath.size());
        }

        // 2단계: 시뮬레이션 및 충돌 감지
        int totalDangerCount = 0;
        for (int t = 0; t < maxTime; t++) {
            Map<String, Integer> locationsAtT = new HashMap<>();
            for (List<int[]> path : allRobotPaths) {
                if (t < path.size()) {
                    int[] pos = path.get(t);
                    String posKey = pos[0] + "," + pos[1];
                    locationsAtT.put(posKey, locationsAtT.getOrDefault(posKey, 0) + 1);
                }
            }

            for (int count : locationsAtT.values()) {
                if (count > 1) {
                    totalDangerCount++;
                }
            }
        }

        return totalDangerCount;
    }
}