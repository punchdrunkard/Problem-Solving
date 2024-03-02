import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static int p, memberLimit;

    static Map<String, Integer> players = new HashMap<>(); // 플레이어 정보
    static List<Room> rooms = new ArrayList<>();// 현재 존재하는 방의 정보

    static class Room {
        int id; // 만들어진 순서?
        int level; // 기준 레벨
        List<String> players; // 소속 플레이어 닉네임들

        Room(int id, int level, List<String> players) {
            this.id = id;
            this.level = level;
            this.players = players;
        }

        @Override
        public String toString() {
            return "Room{" +
                    "id=" + id +
                    ", level=" + level +
                    ", players=" + players +
                    '}';
        }
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        p = scan.nextInt();
        memberLimit = scan.nextInt();

        for (int i = 0; i < p; i++) {
            int level = scan.nextInt();
            String name = scan.next();
            players.put(name, level);

            // 가능한 방이 있는지 확인
            // 입장 가능한 조건 -> 방이 존재해야 함, 방이 꽉 차있지 않아야 함, 해당 사람이 레벨 조건에 맞야야 함
            int roomNumber = shouldMakeNewRoom(name);

            if (roomNumber == -1) { // 새 방을 만든다.
                Room newRoom = new Room(rooms.size(), level, new ArrayList<>());
                newRoom.players.add(name);
                rooms.add(newRoom);
            } else { // 해당 방에 배치한다.
                rooms.get(roomNumber).players.add(name);
            }
        }

        // 정답 출력
        for (Room room: rooms) {
            String started = room.players.size() == memberLimit ? "Started!\n" : "Waiting!\n";
            sb.append(started);

            Collections.sort(room.players);

            for (String player: room.players) {
                sb.append(players.get(player)).append(' ').append(player).append('\n');
            }
        }

        System.out.println(sb);
    }

    // 가능한 경우, 방번호 리턴 안되면
    static int shouldMakeNewRoom(String playerName) {
        if (rooms.isEmpty()) return -1;

        for (Room room : rooms) {
            if (isAvailable(room.id, playerName)) {
                return room.id;
            }
        }

        return -1;
    }

    // 해당 방에 입장 가능한가?
    static boolean isAvailable(int roomId, String playerName) {
        Room current = rooms.get(roomId);
        int base = current.level;

        return current.players.size() < memberLimit && base - 10 <= players.get(playerName) && players.get(playerName) <= base + 10;
    }


    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
