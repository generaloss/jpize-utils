package jpize.util.time;

public class UpsCounter {

    private long prevTime;
    private int counter, ups;

    public void update() {
        final long currentTime = System.currentTimeMillis();

        final long difference = currentTime - prevTime;
        if(difference > 1000){
            prevTime = currentTime;

            ups = counter;
            counter = 0;
        }else
            counter++;
    }

    public int get() {
        return ups;
    }

}
