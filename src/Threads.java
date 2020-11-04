public class Threads {
   static String str = "A";


    public static void main(String[] args)  {

        Object lock = new Object();

        class Task implements Runnable{
            private String sym;
            private String nextSym;

            public Task(String sym, String nextSym) {
                this.sym = sym;
                this.nextSym = nextSym;
            }

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    synchronized (lock){
                        try{
                            while (!str.equals(sym))
                                lock.wait();
                                System.out.print(sym);
                                str = nextSym;
                                Thread.sleep(5);
                                lock.notifyAll();

                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        new Thread(new Task("A","B")).start();
        new Thread(new Task("B","C")).start();
        new Thread(new Task("C","A")).start();



    }
}
