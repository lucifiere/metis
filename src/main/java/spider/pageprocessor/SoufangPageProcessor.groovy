package spider.pageprocessor

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spider.constant.Condition
import spider.constant.Config
import spider.constant.Pattern
import us.codecraft.webmagic.Page
import us.codecraft.webmagic.ResultItems
import us.codecraft.webmagic.Site
import us.codecraft.webmagic.processor.PageProcessor
import us.codecraft.webmagic.selector.Selectable

/**
 *  Created by XD.Wang on 2016/11/23.
 */
class SoufangPageProcessor extends BasePageProcessor implements PageProcessor {

    private Site site = Site.me()
            .setRetryTimes(config.getRetryTime())
            .setSleepTime(config.getWait4Next())
            .setTimeOut(config.getTimeout())
            .setUserAgent(Config.AGENT)

    private final def Logger log = LoggerFactory.getLogger(SoufangPageProcessor.class)
    private Condition condition = Condition.getCondition()

    SoufangPageProcessor(String name, String url) {
        super(name, url)
    }

    @Override
    void process(Page page) {

        String url = page.getUrl()
        log.info('开始抓取：' + url)
        crawlPageInfo(page)
        if (isSkip(page)) page.setSkip(true)
        log.info('结束抓取：' + url)

        List courtFetchList = page.getHtml().xpath('//div[@class=\'nlcd_name\']').links().all()
        List detailFetchList = page.getHtml().xpath('//div[@class=\'navleft tf\']').links().regex(Pattern.R_COURT_DETAIL).all()
        Set unfilteredSet = []
        unfilteredSet.addAll(courtFetchList)
        unfilteredSet.addAll(detailFetchList)

        List filteredList = []
        filteredList.addAll(unfilteredSet)
        1.times {
            filteredList << "http://newhouse.${condition.getCity()}.fang.com/house/s/b9${it + 1}/".toString()
        }

        page.addTargetRequests(filteredList)

        log.info(filteredList.toString())
    }

    @Override
    Site getSite() {
        return site
    }

    private static void crawlPageInfo(Page page) {
        page.putField('builN', clearValue(page.getHtml().xpath(Pattern.X_BUILDING_NAME)))
        page.putField('pric', clearValue(page.getHtml().xpath(Pattern.X_2016_PRICE)))
        page.putField('propC', clearValue(page.getHtml().xpath(Pattern.X_PROPERTY_CATEGORY)))
        page.putField('projF', clearValue(page.getHtml().xpath(Pattern.X_PROJECT_FEATURE)))
        page.putField('builC', clearValue(page.getHtml().xpath(Pattern.X_BUILDING_CATEGORY)))
        page.putField('decoC', clearValue(page.getHtml().xpath(Pattern.X_DECORATION_CONDITION)))
        page.putField('propL', clearValue(page.getHtml().xpath(Pattern.X_PROPERTY_LIMIT)))
        page.putField('loopP', clearValue(page.getHtml().xpath(Pattern.X_LOOP_POSITION)))
        page.putField('deve', clearValue(page.getHtml().xpath(Pattern.X_DEVELOPER)))
        page.putField('addr', clearValue(page.getHtml().xpath(Pattern.X_ADDRESS)))
        page.putField('favo', clearValue(page.getHtml().xpath(Pattern.X_FAVORABLE)))
        page.putField('slaeT', clearValue(page.getHtml().xpath(Pattern.X_SALE_TIME)))
        page.putField('tranT', clearValue(page.getHtml().xpath(Pattern.X_TRANSFER_TIME)))
        page.putField('saleA', clearValue(page.getHtml().xpath(Pattern.X_SALE_ADDRESS)))
        page.putField('saleS', clearValue(page.getHtml().xpath(Pattern.X_SALE_STATUS)))
        page.putField('saleTe', clearValue(page.getHtml().xpath(Pattern.X_SALE_TEL)))
        page.putField('saleTy', clearValue(page.getHtml().xpath(Pattern.X_SALE_TYPE)))

    }

    private static boolean isSkip(Page page) {
        ResultItems res = page.getResultItems()
        return res.get('pric') == null && res.get('saleS') == null && res.get('propL') == null
    }

    private static String clearValue(Selectable selectable) {
        selectable.regex('>.*<').regex('[^><]+').toString()
    }

}
