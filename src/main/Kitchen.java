package main;

public class Kitchen extends Thread{

  private static boolean isWorking = false;

    public static void setWorking(boolean working) {
        isWorking = working;
    }

    @Override
    public void run() {
        super.run();
    }
}
