package spider.pageprocessor

import spider.constant.Config
import spider.pipeline.ExcelPipeline
import us.codecraft.webmagic.Site
import us.codecraft.webmagic.Spider
import us.codecraft.webmagic.pipeline.FilePipeline
import us.codecraft.webmagic.processor.PageProcessor

/**
 *  Created by XD.Wang on 2016/11/23.
 */
abstract class BasePageProcessor {

    protected Site site = Site.me().setRetryTimes(Config.RETRY_TIME).setSleepTime(Config.WAIT_FOR_NEXT)
    private String name
    private String url
    private int THREAD_COUNT = 5

    public void start(PageProcessor p){
        Spider.create(p).
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
