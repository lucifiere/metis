package spider.pageprocessor

import us.codecraft.webmagic.Page
import us.codecraft.webmagic.Site
import us.codecraft.webmagic.processor.PageProcessor

/**
 *  Created by XD.Wang on 2016/11/23.
 */
class SoufangPageProcessor extends BasePageProcessor implements PageProcessor {

    SoufangPageProcessor(String name, String url) {
        super(name, url)
    }

    @Override
    void process(Page page) {

    }

    @Override
    Site getSite() {
        return site
    }

}
