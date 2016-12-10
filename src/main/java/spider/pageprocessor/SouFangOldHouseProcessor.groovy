package spider.pageprocessor

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spider.constant.Condition
import spider.constant.Config
import spider.constant.Pattern
import spider.service.SpiderService
import us.codecraft.webmagic.Page
import us.codecraft.webmagic.ResultItems
import us.codecraft.webmagic.Site
import us.codecraft.webmagic.processor.PageProcessor
import us.codecraft.webmagic.selector.Selectable

/**
 *  Created by Tyler.Wang on 2016/12/10.
 *  搜房网新房页面处理器
 */
class SouFangOldHouseProcessor extends BasePageProcessor implements PageProcessor {

    private Site site = Site.me()
            .setRetryTimes(config.getRetryTime())
            .setSleepTime(config.getWait4Next())
            .setTimeOut(config.getTimeout())
            .setUserAgent(Config.AGENT)
            .setCharset('GBK')

    private final def Logger log = LoggerFactory.getLogger(SouFangNewHouseProcessor.class)
    private Condition condition = Condition.getCondition()

    SouFangOldHouseProcessor(String name, String url) {
        super(name, url)
    }

    @Override
    void process(Page page) {
        String url = page.getUrl()
        log.info('开始抓取：' + url)
        crawlPageInfo(page)
        if (isSkip(page)) page.setSkip(true)
        log.info('结束抓取：' + url)

        List courtFetchList = page.getHtml().xpath('//a[@class=\'plotTit\']').links().all()
        List detailFetchList = page.getHtml().xpath('//ul[@class=\'nav clearfix\']/li[2]').links().all()
        Set unfilteredSet = []
        unfilteredSet.addAll(courtFetchList)
        unfilteredSet.addAll(detailFetchList)

        List filteredList = []
        filteredList.addAll(unfilteredSet)
        1.times {
            filteredList << "http://esf.${condition.getCity()}.fang.com/housing/${condition.getDistrict()}__0_0_0_0_${it + 1}_0_0/".toString()
        }

        page.addTargetRequests(filteredList)
        log.info(filteredList.toString())
    }

    private static void crawlPageInfo(Page page) {
        page.putField('origin', Pattern.ORIGIN_OLD)
        String baseInfo = page.getHtml().xpath(Pattern.X_M_BASE_INFO).get().replaceAll('<.*?>', " ")
        page.putField("baseInfo", baseInfo)
        String courtIntroduce = page.getHtml().xpath(Pattern.X_M_COURT_INTRODUCE).get().replaceAll('<.*?>', " ")
        page.putField("courtIntroduce", courtIntroduce)
        String matingInfo = page.getHtml().xpath(Pattern.X_M_MATING_INFO).get().replaceAll('<.*?>', " ")
        page.putField("matingInfo", matingInfo)
        String surrounding = page.getHtml().xpath(Pattern.X_M_SURROUNDING).get().replaceAll('<.*?>', " ")
        page.putField("surrounding", surrounding)
        String traffic = page.getHtml().xpath(Pattern.X_M_TRAFFIC).get().replaceAll('<.*?>', " ")
        page.putField("traffic", traffic)
        SpiderService.analysisInfo(page, [baseInfo, courtIntroduce, matingInfo, surrounding, traffic])
    }

    @Override
    Site getSite() {
        return site
    }

    private static boolean isSkip(Page page) {
        ResultItems res = page.getResultItems()
        return res.get('builN') == null && res.get('baseInfo') == null
    }

}
