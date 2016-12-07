package spider.service

import spider.constant.Pattern
import spider.pageprocessor.SoufangPageProcessor

/**
 *  Created by XD.Wang on 2016/11/23.
 */
class SpiderService {

    public static void crawl() {
        SoufangPageProcessor soufangPageProcessor = new SoufangPageProcessor('搜房网', Pattern.C_TIANJIN_ORIGIN)
        soufangPageProcessor.start(soufangPageProcessor)
    }

    public static int getPostCode(String name) {
        name = name.replaceAll('楼盘', '')
        switch (name) {
            case '河北': return 300143
            case '河西': return 300202
            case '和平': return 300041
            case '南开': return 300100
            case '河东': return 300171
            case '东丽': return 300300
            case '北辰': return 300400
            case '西青': return 300380
            case '津南': return 300350
            case '滨海新区': return 300450
            case '红桥': return 300131
            case '宝坻': return 301800
            case '蓟州': return 301900
            case '宁河': return 301500
            case '静海': return 301600
            case '武清': return 301700
            default: return 300000
        }
    }

}
