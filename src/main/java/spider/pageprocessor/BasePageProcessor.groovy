package spider.pageprocessor

import spider.pipeline.ExcelPipeline
import us.codecraft.webmagic.Spider
import us.codecraft.webmagic.pipeline.FilePipeline
import us.codecraft.webmagic.processor.PageProcessor
import us.codecraft.webmagic.scheduler.QueueScheduler
import us.codecraft.webmagic.scheduler.component.BloomFilterDuplicateRemover

/**
 *  Created by XD.Wang on 2016/11/23.
 */
abstract class BasePageProcessor {

    private String name
    private String url
    private int THREAD_COUNT = 5

    public void start(PageProcessor p){
        Spider.create(p).
        setScheduler(new QueueScheduler().
                setDuplicateRemover(new BloomFilterDuplicateRemover(10000000))).
                addPipeline(new FilePipeline()).
                addPipeline(new ExcelPipeline()).
                addUrl(url).
                thread(THREAD_COUNT).
                run()
    }

    BasePageProcessor(String name, String url){
        this.name = name
        this.url = url
    }

}
