package spider.pageprocessor

import spider.constant.Config
import spider.pipeline.ExcelPipeline
import us.codecraft.webmagic.Spider
import us.codecraft.webmagic.processor.PageProcessor
import us.codecraft.webmagic.scheduler.QueueScheduler
import us.codecraft.webmagic.scheduler.component.BloomFilterDuplicateRemover

/**
 *  Created by XD.Wang on 2016/11/23.
 */
abstract class SouFangBasePageProcessor {

    private String name
    private String url
    protected Config config = Config.getConfig()

    public Spider start(PageProcessor p) {
        Spider s = Spider.create(p).
                setScheduler(new QueueScheduler().
                setDuplicateRemover(new BloomFilterDuplicateRemover(config.getPredictPageNum()))).
                addPipeline(ExcelPipeline.getExcelPipeline()).
                addUrl(url).
                thread(config.getThreadCount())
        s.run()
        s
    }

    SouFangBasePageProcessor(String name, String url) {
        this.name = name
        this.url = url
    }
}
