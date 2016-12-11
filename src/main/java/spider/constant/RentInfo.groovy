package spider.constant

import org.apache.poi.hssf.usermodel.HSSFSheet
import us.codecraft.webmagic.Site

/**
 *  Created by Tyler.Wang on 2016/12/11.
 */
class RentInfo {

    public static Map rentPage = [:]
    public static Map rentExcel = [:]

    private RentInfo() {}
    private static RentInfo r

    public static RentInfo getRentInfo() {
        if (r == null) {
            r = new RentInfo()
            return r
        }
        r
    }
}
