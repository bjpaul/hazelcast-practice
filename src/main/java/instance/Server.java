package instance;


import java.util.UUID;

/**
 * Created by bijoy on 15/6/16.
 */
public class Server {

    private static Runnable task;

    static {
        task = defineTask();
    }
    public static void main(String[] args) {
        for (int i =1 ; i <= 3; i++) {
            new Thread(task, "Thread "+i).start();
        }
    }

    private static Runnable defineTask() {
        return new Runnable() {
            String msg;
            @Override
            public void run() {
                while (true){
                    try {
                        msg = ServerInstance.getInstanceName() + " : " +Thread.currentThread().getName()+" -> { Message = "+ UUID.randomUUID()+" }";
                        System.out.println("Publish ==>> "+msg);
                        ServerInstance.getQueue().put(msg);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }
}
