package indi.pc.sc.processor;

import indi.pc.sc.base.Response;
import indi.pc.sc.base.Result;
import indi.pc.sc.base.TaskQueue;

public class Processor implements Runnable {

    @Override
    public void run() {
        try {
            Response response = null;
            while ((response = TaskQueue.ProcessQueue.take()) != null) {
                // TODO 处理返回
                // response
                // TODO 插入结果
                Result result = null;
                TaskQueue.ResultQueue.put(result);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
