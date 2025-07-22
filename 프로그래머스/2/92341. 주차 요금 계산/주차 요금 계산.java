import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        int n = records.length;
        Map<String, Integer> parkingTime = getCumulativeParkingTime(fees, records);
        
        // 주차 요금 계산 -> 계산 후 [차량 번호, 값] 의 형태 
        List<int[]> parkingFees = new ArrayList<>();
        for (String key: parkingTime.keySet()) {
            int[] res = {Integer.parseInt(key), calcuateFee(parkingTime.get(key), fees)};
            parkingFees.add(res);
        }
        
        // 차량 번호가 작은 자동차부터
        parkingFees.sort((a, b) -> Integer.compare(a[0], b[0]));
        return parkingFees.stream().mapToInt(a -> a[1]).toArray();
    }
    
    Map<String, Integer> getCumulativeParkingTime(int[] fees, String[] records) {
        Map<String, Boolean> doesLeave = new HashMap<>();
        Map<String, Integer> lastEnterTime = new HashMap<>();
        Map<String, Integer> parkingTime = new HashMap<>(); // 누적 주차시간
        
        for (String record: records) {
            String[] splited = record.split(" ");
            int currentTime = convertToMinute(splited[0]);
            String carId = splited[1];
            String type = splited[2];
            
            if ("IN".equals(type)) {
                lastEnterTime.put(carId, currentTime);
                doesLeave.put(carId, false);
            } else {
                // 현재까지의 주차시간 계산
                int currentParkingTime = currentTime - lastEnterTime.get(carId);
                parkingTime.put(carId, parkingTime.getOrDefault(carId, 0) 
                                + currentParkingTime);
                doesLeave.put(carId, true);
            }
        }
        
        // 출차 기록이 없으면 23:59에 출차된거로 가정하기
        for (String key: doesLeave.keySet()) {
            if (!doesLeave.get(key)) {
                int currentParkingTime = convertToMinute("23:59") 
                    - lastEnterTime.get(key);
                parkingTime.put(key, parkingTime.getOrDefault(key, 0) + currentParkingTime);
            }
        }
        
        return parkingTime;
    }
    
    int calcuateFee(int currentParkingTime, int[] fees) {
        int basicTime = fees[0];
        int basicFee = fees[1];
        int timeUnit = fees[2];
        int feeUnit = fees[3];
        
        if (currentParkingTime <= basicTime) {
            return basicFee;
        }
        
        int extendedFee = (int) Math.ceil(
            (currentParkingTime - basicTime) / (double) timeUnit) * feeUnit;
        
        return basicFee + extendedFee;
    }
    
    int convertToMinute(String time) {
        int[] spilted = Arrays.stream(time.split(":"))
            .mapToInt(Integer::parseInt)
            .toArray();
        
        return spilted[0] * 60 + spilted[1];
    }

    void sout(Object o) {
        System.out.println(o);
    }
}