package indi.pc.sc.base;

import java.util.concurrent.ArrayBlockingQueue;

public class TaskQueue {
    public static ArrayBlockingQueue<Request> FetchQueue = new ArrayBlockingQueue(1000, true);
    public static ArrayBlockingQueue<Response> ProcessQueue = new ArrayBlockingQueue(1000, true);
    public static ArrayBlockingQueue<Result> ResultQueue = new ArrayBlockingQueue(1000, true);
}
