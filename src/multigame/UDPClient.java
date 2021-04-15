package multigame;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();

        System.out.println("Choose your player: A, B, C, D, a, b, c or d");

        char player = 'p';

        while (player == 'p')
        {
            BufferedReader playerReader = new BufferedReader(new InputStreamReader(System.in));
            String playerStr = playerReader.readLine();

            switch (playerStr) {
                case "A":
                    player = 'A';
                    break;
                case "B":
                    player = 'B';
                    break;
                case "C":
                    player = 'C';
                    break;
                case "D":
                    player = 'D';
                    break;
                case "a":
                    player = 'a';
                    break;
                case "b":
                    player = 'b';
                    break;
                case "c":
                    player = 'c';
                    break;
                case "d":
                    player = 'd';
                    break;
                default:
                    System.out.println("Invalid player: " + (playerStr.isEmpty() ? "empty" : playerStr));
            }
        }

        System.out.println("Use W, A, S and D to play:");

        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String data = player + "|" + ( reader.readLine().isEmpty() ? "empty" : reader.readLine() );
            
            byte[] b = data.getBytes();
            InetAddress ia = InetAddress.getLocalHost();
            DatagramPacket dp = new DatagramPacket(b,b.length,ia,9990);

            byte[] b1 = new byte[1024];
            DatagramPacket dp1 = new DatagramPacket(b1,b1.length);

            ds.send(dp);
            ds.receive(dp1);

            String error = new String(dp1.getData());

            if (!error.trim().isEmpty()) {
                System.out.println(error);
            }
        }
    }
}