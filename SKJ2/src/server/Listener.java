package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Listener extends Thread {
    private static final int len = 1024;
    private int port;
    private static InetSocketAddress allowed;
    private static File file;

    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;

    Listener(int port, File file) {

        this.port = port;
        this.file = file;
        allowed = new InetSocketAddress("localhost", 4250);


    }

    public void run() {

        try {
            datagramSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            System.out.println("DatagramSocket exception in Receiver, line 34");
            e.printStackTrace();
        }
        while (true) {

            byte[] buffer = new byte[len];
            datagramPacket = new DatagramPacket(buffer, 0, len);
            try {
                datagramSocket.receive(datagramPacket);
            } catch (IOException e) {
                System.err.println("Problem in receiving a packet, line 42/44, class Receiver");
            }

            if (allowed.equals(datagramPacket.getSocketAddress())) {

                int newPort = 4300 + (int) (Math.random() * 5001);

                try {
                    byte[] message = MyMessage();
                    DatagramSocket newSocket = new DatagramSocket(newPort);

                    DatagramPacket newPacket = new DatagramPacket(message, message.length, allowed);
                    newSocket.send(newPacket);
                    this.sleep(10);
                } catch (SocketException e) {
                    System.out.println("newSocket error, line 55, class Receiver");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("newSocket send error, line 58, class Receiver");
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    System.out.println("something went wrong with sleep");
                    e.printStackTrace();
                }

            } else {
                datagramSocket.disconnect();
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("something went wrong with sleep");
                    e.printStackTrace();
                }
            }
        }

    }

    private byte[] MyMessage() {
        String returnmessage = file.getName() + "\n" + file.length() + "\n";
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(file));
            String s = "";
            while ((s = br.readLine()) != null) {
                returnmessage += s;
            }
            returnmessage += "\n";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnmessage.getBytes();
    }
}