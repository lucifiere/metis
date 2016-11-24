package spider.pageprocessor

import spider.constant.Pattern
import us.codecraft.webmagic.Page
import us.codecraft.webmagic.ResultItems
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
        page.putField('pric', page.getHtml().xpath(Pattern.X_2016_PRICE).toString())
        page.putField('propC', page.getHtml().xpath(Pattern.X_PROPERTY_CATEGORY).toString())
        page.putField('projF', page.getHtml().xpath(Pattern.X_PROJECT_FEATURE).toString())
        page.putField('builC', page.getHtml().xpath(Pattern.X_BUILDING_CATEGORY).toString())
        page.putField('decoC', page.getHtml().xpath(Pattern.X_DECORATION_CONDITION).toString())
        page.putField('propL', page.getHtml().xpath(Pattern.X_PROPERTY_LIMIT).toString())
        page.putField('loopP', page.getHtml().xpath(Pattern.X_LOOP_POSITION).toString())
        page.putField('deve', page.getHtml().xpath(Pattern.X_DEVELOPER).toString())
        page.putField('addr', page.getHtml().xpath(Pattern.X_ADDRESS).toString())
        page.putField('saleS', page.getHtml().xpath(Pattern.X_SALE_STATUS).toString())
        page.putField('favo', page.getHtml().xpath(Pattern.X_FAVORABLE).toString())
        page.putField('slaeT', page.getHtml().xpath(Pattern.X_SALE_TIME).toString())
        page.putField('tranT', page.getHtml().xpath(Pattern.X_TRANSFER_TIME).toString())
        page.putField('saleA', page.getHtml().xpath(Pattern.X_SALE_ADDRESS).toString())
        page.putField('saleTe', page.getHtml().xpath(Pattern.X_SALE_TEL).toString())
        page.putField('saleTy', page.getHtml().xpath(Pattern.X_SALE_TYPE).toString())

        if (isSkip(page)) page.setSkip(true)

        page.addTargetRequests(page.getHtml().links().regex('(https://github\\.com/[\\w\\-]+/[\\w\\-]+)').all())
    }

    @Override
    Site getSite() {
        return site
    }

    private static boolean isSkip(Page page) {
        ResultItems res = page.getResultItems()
        return res.get('pric') == null && res.get('saleS') == null && res.get('propL') == null
    }

}
