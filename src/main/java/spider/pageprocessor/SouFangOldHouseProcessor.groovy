package spider.pageprocessor

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spider.constant.Condition
import spider.constant.Config
import spider.constant.Pattern
import spider.constant.RentInfo
import spider.service.SpiderService
import us.codecraft.webmagic.Page
import us.codecraft.webmagic.ResultItems
import us.codecraft.webmagic.Site
import us.codecraft.webmagic.processor.PageProcessor

/**
 *  Created by Tyler.Wang on 2016/12/10.
 *  搜房网新房页面处理器
 */
class SouFangOldHouseProcessor extends SouFangBasePageProcessor implements PageProcessor {

    private Site site = Site.me()
            .setRetryTimes(config.getRetryTime())
            .setSleepTime(config.getWait4Next())
            .setTimeOut(config.getTimeout())
            .setUserAgent(Config.AGENT)
            .setCharset('GBK')

    private final def Logger log = LoggerFactory.getLogger(SouFangOldHouseProcessor.class)
    private Condition condition = Condition.getCondition()
    private static RentInfo rentInfo = RentInfo.getRentInfo()

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
        List rentFetchList = page.getHtml().xpath('//ul[@class=\'nav clearfix\']/li[4]').links().all()
//        String frameUrl = page.getHtml().xpath('//div[@class=\'trendIframe2\']/iframe/@src').get()
        Set unfilteredSet = []
        unfilteredSet.addAll(courtFetchList)
        unfilteredSet.addAll(detailFetchList)
        unfilteredSet.addAll(rentFetchList)

        List filteredList = []
        filteredList.addAll(unfilteredSet)
        1.times {
            filteredList << "http://esf.${condition.getCity()}.fang.com/housing/${condition.getDistrict()}__0_0_0_0_${it + 1}_0_0/".toString()
        }

//        filteredList << frameUrl
        page.addTargetRequests(filteredList)
        log.info(filteredList.toString())
    }

    private static void crawlPageInfo(Page page) {
        String buildingName = cleanValue(page.getHtml().xpath(Pattern.X_OH_BUILDING_NAME).get())?.replaceAll('网', '')
        String rentPrice = page.getHtml().xpath(Pattern.X_OH_RENT_PRICE).get()
        if(rentPrice != null && rentPrice != ''){
            rentInfo.rentPage.put(buildingName, rentPrice)
            return
        }

        page.putField('builN', buildingName)
        page.putField('pric', cleanValue(page.getHtml().xpath(Pattern.X_OH_MONTH_PRICE).get()))
        page.putField('viaM', cleanValue(page.getHtml().xpath(Pattern.X_OH_VIA_BEFORE_M).get()))
        page.putField('viaY', cleanValue(page.getHtml().xpath(Pattern.X_OH_VIA_BEFORE_Y).get()))
        page.putField('oName', cleanValue(page.getHtml().xpath(Pattern.X_OH_OTHER_NAME).get()))

        page.putField('origin', Pattern.ORIGIN_OLD)
        String baseInfo = cleanValue(page.getHtml().xpath(Pattern.X_M_BASE_INFO).get())
        page.putField("baseInfo", baseInfo)
        String courtIntroduce = cleanValue(page.getHtml().xpath(Pattern.X_M_COURT_INTRODUCE).get())
        page.putField("courtIntroduce", courtIntroduce)
        String matingInfo = cleanValue(page.getHtml().xpath(Pattern.X_M_MATING_INFO).get())
        page.putField("matingInfo", matingInfo)
        String surrounding = cleanValue(page.getHtml().xpath(Pattern.X_M_SURROUNDING).get())
        page.putField("surrounding", surrounding)
        String traffic = cleanValue(page.getHtml().xpath(Pattern.X_M_TRAFFIC).get())
        page.putField("traffic", traffic)
        SpiderService.analysisInfoWithFormat(page, [baseInfo, courtIntroduce, matingInfo, surrounding, traffic])
    }

    @Override
    Site getSite() {
        return site
    }

    private static boolean isSkip(Page page) {
        ResultItems res = page.getResultItems()
        return res.get('builN') == null || res.get('baseInfo') == null
    }

    // 去除HTML换行不闭合标签、HTML普通标签、HTML占位符
    private static String cleanValue(String info) {
        info?.replaceAll("<*?\\n", '')?.replaceAll('<.*?>', "")?.
                replaceAll("&nbsp;", ' ')?.
                replaceAll("&gt;", '')?.
                replaceAll("&lt;", '')
    }

}
