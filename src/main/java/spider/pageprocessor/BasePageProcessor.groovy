package spider.pageprocessor

import spider.constant.Config
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
    protected Config config = Config.getConfig()

    public void start(PageProcessor p){
        Spider.create(p).
        setScheduler(new QueueScheduler().
                setDuplicateRemover(new BloomFilterDuplicateRemover(10000000))).
                addPipeline(new FilePipeline('D:\\spider\\')).
                addPipeline(new ExcelPipeline()).
                addUrl(url).
                thread(config.getThreadCount()).
                run()
    }

    BasePageProcessor(String name, String url){
        this.name = name
        this.url = url
    }
}
