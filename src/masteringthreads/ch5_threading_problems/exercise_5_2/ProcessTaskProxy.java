package masteringthreads.ch5_threading_problems.exercise_5_2;

import java.util.*;

@SuppressWarnings("rawtypes")
public class ProcessTaskProxy {
    public void printRCEKey() {
        Map map = (Map) ThreadLocalContextHolder.get();
        Object o = map.get(Thread.currentThread().getName());
        if (o == null) o = map;
        System.out.println("Thread Name : " + Thread.currentThread().getName() +
            "\t TL Value : " + o);
        ThreadLocalContextHolder.cleanupThread();
    }
}
