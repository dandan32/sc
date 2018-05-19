package indi.pc.sc.resultworker;

import indi.pc.sc.base.ResultTask;
import indi.pc.sc.base.TaskQueue;

public class ResultWorker implements Runnable {

    public void run() {
        try {
            ResultTask resultTask = null;
            while ((resultTask = TaskQueue.resultTaskQueue.take()) != null) {
                // TODO 保存结果
                //　保存逻辑
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
