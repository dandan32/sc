package indi.pc.sc.base;

import java.util.concurrent.ArrayBlockingQueue;

public class TaskQueue {
    public static ArrayBlockingQueue<RequestTask> FetchQueue = new ArrayBlockingQueue(1000, true);
    public static ArrayBlockingQueue<ResponseTask> ProcessQueue = new ArrayBlockingQueue(1000, true);
    public static ArrayBlockingQueue<ResultTask> resultTaskQueue = new ArrayBlockingQueue(1000, true);
}
