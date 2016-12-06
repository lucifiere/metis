package spider.service

import spider.constant.Pattern
import spider.pageprocessor.SoufangPageProcessor

/**
 *  Created by XD.Wang on 2016/11/23.
 */
class SpiderService {

    public static void crawl(){
        SoufangPageProcessor soufangPageProcessor = new SoufangPageProcessor('搜房网', Pattern.C_TIANJIN_ORIGIN)
        soufangPageProcessor.start(soufangPageProcessor)
    }

}
