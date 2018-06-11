package indi.pc.sc.processor;

import indi.pc.sc.base.ResponseTask;
import indi.pc.sc.base.ResultTask;
import indi.pc.sc.base.TaskQueue;

public class Processor implements Runnable {

    @Override
    public void run() {
        try {
            ResponseTask responseTask = null;
            while ((responseTask = TaskQueue.ProcessQueue.take()) != null) {
                // TODO 处理返回
                // responseTask
                // TODO 插入结果
                ResultTask resultTask = null;
                TaskQueue.resultTaskQueue.put(resultTask);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
