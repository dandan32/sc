package indi.pc.sc.resultworker;

import indi.pc.sc.base.Result;
import indi.pc.sc.base.TaskQueue;

public class ResultWorker implements Runnable {

    public void run() {
        try {
            Result result = null;
            while ((result = TaskQueue.ResultQueue.take()) != null) {
                // TODO 保存结果
                //　保存逻辑
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
