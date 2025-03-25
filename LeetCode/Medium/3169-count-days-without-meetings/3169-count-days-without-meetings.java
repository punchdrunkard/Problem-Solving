class Solution {
    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        int freeDays = meetings[0][0] - 1;
        int lastMeetingDay = meetings[0][1];
        
        for (int i = 1; i < meetings.length; i++) {
            int[] meeting = meetings[i];
    
            if (meeting[0] <= lastMeetingDay) {
                lastMeetingDay = Math.max(lastMeetingDay, meeting[1]);
            } else {
                freeDays += (meeting[0] - lastMeetingDay - 1);
                lastMeetingDay = meeting[1];
            }
        }

        freeDays += (days - lastMeetingDay);
        return freeDays;
    }
}