package spider.service

import spider.pageprocessor.SoufangPageProcessor

/**
 *  Created by XD.Wang on 2016/11/23.
 */
class SpiderService {

    public static void start(List todo){
        SoufangPageProcessor soufangPageProcessor = new SoufangPageProcessor('搜房网', 'http://newhouse.fang.com/house/s/')
        soufangPageProcessor.start(soufangPageProcessor)
    }

}
