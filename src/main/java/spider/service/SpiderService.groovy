package spider.service

import spider.pageprocessor.SoufangPageProcessor

/**
 *  Created by XD.Wang on 2016/11/23.
 */
class SpiderService {

    public void start(List todo){
        SoufangPageProcessor soufangPageProcessor = new SoufangPageProcessor('搜房网', 'http://www.fang.com/')
        soufangPageProcessor.start(soufangPageProcessor)
    }

}
