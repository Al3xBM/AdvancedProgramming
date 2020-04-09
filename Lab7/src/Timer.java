public class Timer implements Runnable{
    private long startTime;
    private long gameTimeLimit;
    private long endTime;
    private volatile boolean undergoing;
    private volatile boolean notYet;
    Object lock = new Object();

    public boolean getNotYet(){
        return notYet;
    }

    public void setEndTime(long time){
        endTime = time;
    }

    public void setUndergoing(boolean b){
        undergoing = b;
    }

    public boolean getUndergoing(){
        return undergoing;
    }

    public long getRunTime(){
        return System.nanoTime() - startTime;
    }

    public void setNotYet(boolean b){
        notYet = b;
    }

    @Override
    public synchronized void run(){
        startTime = System.nanoTime();
        while(true){


            while(undergoing) {
                if( !notYet )
                    break;
                try {
                    synchronized(lock) {
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if( !notYet )
                break;

            long currTime = this.getRunTime();

            synchronized(lock){
                if( currTime >= endTime ){
                    notYet = false;
                    System.out.println("time ran out");
                }
                else notYet = true;
                this.setUndergoing(true);
                lock.notifyAll();
            }

            if( !notYet){
                break;
            }

        }

    }
}
