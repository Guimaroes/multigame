package multigame;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        
        Game game = new Game();
        
        System.out.println( game.getField() );
        
        InetAddress ia = InetAddress.getByName("177.44.248.11");
        DatagramSocket ds = new DatagramSocket(9990,ia);

        while (true) {
            byte[] b1 = new byte[1024];

            DatagramPacket dp = new DatagramPacket(b1,b1.length);
            ds.receive(dp);

            String str = new String(dp.getData(),0,dp.getLength());

            String playerStr    = str.split("|")[0];
            String directionStr = str.split("|")[2];
            
            char player;
            char direction;
            
            String result = "";
            String error = "";

            if (playerStr.length() == 1 && 
                directionStr.length() == 1)
            {
                try {
                    player = playerStr.charAt(0);
                    direction = directionStr.charAt(0);
                    
                    game.movePlayer(player, direction);
                    
                    result = game.getField();
                    
                } catch (Exception e) {
                    error = "Error: " + e;
                    result = "";
                }
            }
            
            else {
                error = "Invalid direction: " + (directionStr.trim().isEmpty() ? "empty" : directionStr);
                result = "";
            }
            
            if (!result.isEmpty()) {
                
                System.out.print("\033[H\033[2J");
                System.out.println(result);
            }

//            byte[] b2 = error.getBytes();
//            
//            DatagramPacket dp1 = new DatagramPacket(b2,b2.length,ia,dp.getPort());
//            ds.send(dp1);
        }
    }
}