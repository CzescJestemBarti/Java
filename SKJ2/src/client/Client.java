package client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Client {
    private static final String mess = "serwus";
    private static InetSocketAddress myAdress;
    private static InetSocketAddress serversAdress;
    private static File ClientFile;
    private BufferedWriter bw;

    private Client(InetSocketAddress myAdress, InetSocketAddress serversAdress) throws IOException{

        this.myAdress=myAdress;
        this.serversAdress=serversAdress;


        byte[] bytemess = mess.getBytes();
        DatagramPacket packet = new DatagramPacket(bytemess, bytemess.length, serversAdress );
        DatagramSocket socket = new DatagramSocket(myAdress);

        socket.send(packet);

        DatagramPacket repacket = new DatagramPacket(new byte[1024], 1024);

        socket.setSoTimeout(420);
        socket.receive(repacket);

        int count = repacket.getLength();
        String reply = new String(repacket.getData()).substring(0, count);

        makeFile(reply.split("\\n"));


        socket.close();
    }

    public static void main(String[] args) throws IOException {
        InetSocketAddress server = new InetSocketAddress("localhost",4040);
        InetSocketAddress client = new InetSocketAddress("localhost", 4250);

        Client c1 = new Client(client,server);
    }
    private void makeFile(String[] strings){
        ClientFile=new File("Client_File.txt");
        try {
            bw=new BufferedWriter(new FileWriter(ClientFile));
            for (int i = 2; i <strings.length ; i++) {
                bw.write(strings[i]);
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}