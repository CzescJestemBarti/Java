package zad1;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadA extends Thread {

    final String FILENAME = "Towary.txt";
    Lock lock = new ReentrantReadWriteLock().writeLock();
    ListaTowarow listaTowarow;
    int iloscTowarow=0;

    ThreadA(ListaTowarow listaTowarow) {
        this.listaTowarow = listaTowarow;
    }

    public void run() {
            File plik = new File("Towary.txt");
            Scanner scanner = null;
            try {
                scanner = new Scanner(plik);
                while(scanner.hasNext()) {
                    int id = scanner.nextInt();
                    int waga = scanner.nextInt();
                    iloscTowarow++;
                    Towar towar = new Towar(id, waga);
                    listaTowarow.addTowar(towar);
                    if(iloscTowarow % 200 == 0) {
                        System.out.println("utworzono " + iloscTowarow + " obiektow");
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("nie ma pliku z towarami");
                System.out.println(e);
            }
        listaTowarow.ustawKoniec();
        }
    }