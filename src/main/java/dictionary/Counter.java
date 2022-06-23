package dictionary;

public class Counter implements Runnable{
    public static int i = 0;
    public static int total = 0;
    public static boolean continued = true;

    public Counter(){

    }
    @Override
    public void run() {
        while (continued) {
            try {
                Thread.sleep(1000);
                System.out.println(i +  " words translated... total: " + total);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("===================");
        System.out.println("process complete!!!");
    }
}
