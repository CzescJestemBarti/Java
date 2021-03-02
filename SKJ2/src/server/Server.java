package server;

import java.io.*;

public class Server {
    private static File myFile;
    private static int ports[];
    private static Listener receiver[];

    Server(int n) {
        myFile = new File("myFile.txt");
        insertToFile();
        ports = new int[n];
        setPorts(4040);
        receiver = new Listener[n];
        begin();
    }

    public static void main(String[] args) {
        Server server = new Server(2);


    }

    private static void setPorts(int n) {
        for (int i = 0; i < ports.length; i++) {
            ports[i] = n+i;
        }

    }

    private void begin() {
        for (int i = 0; i < receiver.length; i++) {
            receiver[i] = new Listener(ports[i], myFile);
            receiver[i].start();
        }

    }

    private void insertToFile() {
        String s = "The message inside is too hard to get, try after gaining some more levels";
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(myFile));
            bw.write(s);
            bw.flush();
        } catch (IOException e) {
            System.out.println("BufferedWriter");
            e.printStackTrace();
        }
    }
}