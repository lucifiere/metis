package spider.spider

import us.codecraft.webmagic.Spider
import us.codecraft.webmagic.processor.PageProcessor

/**
 *  Created by XD.Wang on 2016/12/1.
 */
class MySpider extends Spider{

    MySpider(PageProcessor pageProcessor) {
        super(pageProcessor)
    }

    public List getPipelines(){
        return pipelines
    }

}
