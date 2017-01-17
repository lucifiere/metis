package spider.constant
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

    public static reset(){
        rentPage = [:]
        rentExcel = [:]
    }
}
