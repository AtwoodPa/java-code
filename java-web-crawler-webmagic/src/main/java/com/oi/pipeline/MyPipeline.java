package com.oi.pipeline;

import com.oi.process.TestPageProcessor;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author supanpan
 * @date 2024/07/20
 */
public class MyPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        String title = resultItems.get("title");
        System.out.println("获取标题 = " + title);
        // 在Pipeline中可以进行持久化操作
    }

    public static void main(String[] args) {
        Spider.create(new TestPageProcessor())
                .addPipeline(new FilePipeline())
                .run();
    }
}
