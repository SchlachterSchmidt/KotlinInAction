package chapter5.java;

public class PostponeComputation {

    public void postponeComputation(int delay, Runnable computation) {
        computation.run();
    }
}
