package spider.service

import spider.pageprocessor.SoufangPageProcessor

/**
 *  Created by XD.Wang on 2016/11/23.
 */
class SpiderService {

    public static void start(List todo){

        SoufangPageProcessor soufangPageProcessor = new SoufangPageProcessor('搜房网', 'http://runfenglingshang.fang.com/house/1010684881/housedetail.htm')
        soufangPageProcessor.start(soufangPageProcessor)
    }

}
