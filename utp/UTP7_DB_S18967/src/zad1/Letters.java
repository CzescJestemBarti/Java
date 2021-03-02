package zad1;

import java.util.ArrayList;
import java.util.List;

public class Letters {
    private String litery;
    private boolean run=true;

    Letters(String litery){
        this.litery=litery;
    }

    public List<Thread> getThreads(){
        List<Thread> threadList=new ArrayList<>();
        int len = litery.toCharArray().length;
        for(int i=0; i<len; i++){
            int finalI = i;
            Thread thread = new Thread(()->{
                while (run) {
                    System.out.print(litery.charAt(finalI));
                    try {
                        Thread.sleep(1250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.setName("Thread "+litery.charAt(i));
            threadList.add(thread);
        }
        return threadList;
    }


    public void setRun(){
        run=false;
    }
}

