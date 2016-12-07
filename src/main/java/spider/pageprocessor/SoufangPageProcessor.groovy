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

import java.util.regex.Matcher

/**
 *  Created by XD.Wang on 2016/11/23.
 */
class SoufangPageProcessor extends BasePageProcessor implements PageProcessor {

    private Site site = Site.me()
            .setRetryTimes(config.getRetryTime())
            .setSleepTime(config.getWait4Next())
            .setTimeOut(config.getTimeout())
            .setUserAgent(Config.AGENT)
            .setCharset('GBK')

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
        30.times {
            filteredList << "http://newhouse.${condition.getCity()}.fang.com/house/s/${condition.getDistrict()}b9${it + 1}/".toString()
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
        page.putField('saleT', clearValue(page.getHtml().xpath(Pattern.X_SALE_TIME)))
        page.putField('tranT', clearValue(page.getHtml().xpath(Pattern.X_TRANSFER_TIME)))
        page.putField('saleA', clearValue(page.getHtml().xpath(Pattern.X_SALE_ADDRESS)))
        page.putField('saleS', clearValue(page.getHtml().xpath(Pattern.X_SALE_STATUS)))
        page.putField('saleTe', clearValue(page.getHtml().xpath(Pattern.X_SALE_TEL)))
        page.putField('saleTy', clearValue(page.getHtml().xpath(Pattern.X_SALE_TYPE)))
        page.putField('green', clearValue(page.getHtml().xpath(Pattern.X_GREEN)))
        page.putField('builNu', clearValue(page.getHtml().xpath(Pattern.X_BUILDING_NUM)))
        page.putField('plotR', clearValue(page.getHtml().xpath(Pattern.X_PLOT_RATIO)))
        page.putField('allR', clearValue(page.getHtml().xpath(Pattern.X_ALL_RESIDENT)))
        page.putField('flooB', clearValue(page.getHtml().xpath(Pattern.X_FLOOR_BASE)))
        page.putField('coveA', clearValue(page.getHtml().xpath(Pattern.X_COVERED_AREA)))
        page.putField('propF', clearValue(page.getHtml().xpath(Pattern.X_PROPERTY_FEE)))
        page.putField('courI', clearValue(page.getHtml().xpath(Pattern.X_COURT_INTRODUCE)))
        page.putField('parkA', clearValue(page.getHtml().xpath(Pattern.X_PARKING_AREA)))
        page.putField('distr', clearValue(page.getHtml().xpath(Pattern.X_DISTRICT)))
        page.putField('builH', clearValue(page.getHtml().xpath(Pattern.X_BUILDING_HIGH)))
        setSurroundingInfo(page, page.getHtml().xpath(Pattern.X_TRAFFIC).toString(), page.getHtml().xpath(Pattern.X_MATING).toString())
    }

    private static boolean isSkip(Page page) {
        ResultItems res = page.getResultItems()
        return res.get('pric') == null && res.get('saleS') == null && res.get('propL') == null
    }

    private static String clearValue(Selectable selectable) {
        selectable.regex('>.*<').regex('[^><]+').toString()?.replaceAll('&nbsp', '')
    }

    private static void setSurroundingInfo(Page page, String traffic, String mating) {
        if (!traffic || !mating) return
        String trafficClean = traffic.replaceAll('<br>', '\n').replaceAll('<p>', '\n')
        String matingClean = mating.replaceAll('<br>', '\n').replaceAll('<p>', '\n')
        mating = matingClean.replaceAll(Pattern.R_TOKEN, '\n')
        traffic = trafficClean.replaceAll(Pattern.R_TOKEN, '\n')
        mating = mating.replaceAll(Pattern.R_BLANK, '\n')
        traffic = traffic.replaceAll(Pattern.R_BLANK, '\n')
        String capital = '', kindergarten = '', school = '', college = '', mall = '', post = '', bank = '', restaurant = '', subWay = '', bus = ''
        ['.*?医院', '.*?诊所', '.*?卫生院'].each {
            def m = java.util.regex.Pattern.compile(it).matcher(mating)
            while (m.find()) {
                capital += m.group() + ';'
            }
        }
        ['.*?w*?幼儿园'].each {
            def m = java.util.regex.Pattern.compile(it).matcher(mating)
            while (m.find()) kindergarten += m.group() + ';'
        }
        ['.*?w*?小学', '.*?w*?中学', '.*?w*?初级中学', '.*?w*?高级中学', '.*?w*?高中', '.*?w*?初中'].each {
            def m = java.util.regex.Pattern.compile(it).matcher(mating)
            while (m.find()) school += m.group() + ';'
        }
        ['.*?w*?大学', '.*?w*?学院', '.*?w*?研究所', '.*?w*?研究院', '.*?w*?学校'].each {
            def m = java.util.regex.Pattern.compile(it).matcher(mating)
            while (m.find()) college += m.group() + ';'
        }
        ['.*?w*?商场', '.*?w*?mall', '.*?w*?购物中心', '.*?w*?商城', '.*?w*?超市',
         '.*?w*?商业街', '.*?w*?步行街', '.*?w*?生活广场', '万达广场', '大悦城', '华润万家'].each {
            def m = java.util.regex.Pattern.compile(it).matcher(mating)
            while (m.find()) mall += m.group() + ';'
        }
        ['.*?w*?邮局'].each {
            def m = java.util.regex.Pattern.compile(it).matcher(mating)
            while (m.find()) post += m.group() + ';'
        }
        ['.*?w*?银行', '工行', '中行', '农行', '工行', '交行', '建行', '华夏', '民生', '信合',
         '招行', '广大', '邮储', '兴业', '浦发', '中信', '深发展', '农商'].each {
            def m = java.util.regex.Pattern.compile(it).matcher(mating)
            while (m.find()) bank += m.group() + ';'
        }
        ['.*?w*?餐馆', '.*?w*?餐厅', '.*?w*?酒店', '.*?w*?茶楼', '.*?w*?饭店'].each {
            def m = java.util.regex.Pattern.compile(it).matcher(mating)
            while (m.find()) restaurant += m.group() + ';'
        }
        ['\\w号线'].each {
            def m = java.util.regex.Pattern.compile(it).matcher(traffic)
            while (m.find()) subWay += m.group() + ';'
        }
        ['\\d{1,3}路'].each {
            def m = java.util.regex.Pattern.compile(it).matcher(traffic)
            while (m.find()) bus += m.group() + ';'
        }
        page.putField('capt', capital)
        page.putField('kin', kindergarten)
        page.putField('school', school)
        page.putField('college', college)
        page.putField('mall', mall)
        page.putField('post', post)
        page.putField('bank', bank)
        page.putField('res', restaurant)
        page.putField('subWay', subWay)
        page.putField('bus', bus)
        page.putField('other', matingClean)
        page.putField('env', trafficClean)
    }

}
