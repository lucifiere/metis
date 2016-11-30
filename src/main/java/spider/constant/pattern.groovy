package spider.constant

/**
 *  Created by XD.Wang on 2016/11/21.
 *  Pattern centralized management
 */

class Pattern {

    /** 搜房网 **/
//    public static String R_COURT = '.*\\.fang\\.com/house/\\d+/housedetail.htm'  // 小区信息地址
    public static String R_COURT = '.*.fang.com/house/*'  // 小区信息地址
    public static String X_2016_PRICE = 'html/body/div[8]/div/div[1]/div[1]/div/div[1]/em'  // 2016年6月均价
    public static String X_PROPERTY_CATEGORY = 'html/body/div[8]/div/div[1]/div[1]/ul/li[1]/div[2]'  // 物业类别
    public static String X_PROJECT_FEATURE = 'html/body/div[8]/div/div[1]/div[1]/ul/li[2]/div[2]/span[@class=\'tag\']'  // 项目特色
    public static String X_BUILDING_CATEGORY = 'html/body/div[8]/div/div[1]/div[1]/ul/li[3]/div[2]/span'  // 建筑类别
    public static String X_DECORATION_CONDITION = 'html/body/div[8]/div/div[1]/div[1]/ul/li[4]/div[2]'  // 装修状况
    public static String X_PROPERTY_LIMIT = 'html/body/div[8]/div/div[1]/div[1]/ul/li[5]/div[2]'  // 产权年限
    public static String X_LOOP_POSITION = 'html/body/div[8]/div/div[1]/div[1]/ul/li[6]/div[2]'  // 环线位置
    public static String X_DEVELOPER ='html/body/div[8]/div/div[1]/div[1]/ul/li[7]/div[2]/a'  // 开发商
    public static String X_ADDRESS = 'html/body/div[8]/div/div[1]/div[1]/ul/li[8]/div[2]'  // 楼盘地址
    public static String X_SALE_STATUS = 'html/body/div[8]/div/div[1]/div[2]/ul/li[1]/div[2]'  // 销售状态
    public static String X_FAVORABLE = 'html/body/div[8]/div/div[1]/div[2]/ul/li[2]/div[2]'  // 楼盘优惠
    public static String X_SALE_TIME = 'html/body/div[8]/div/div[1]/div[2]/ul/li[3]/div[2]'  // 开盘时间
    public static String X_TRANSFER_TIME = 'html/body/div[8]/div/div[1]/div[2]/ul/li[4]/div[2]'  // 交房时间
    public static String X_SALE_ADDRESS = 'html/body/div[8]/div/div[1]/div[2]/ul/li[5]/div[2]'  // 售楼地址
    public static String X_SALE_TEL = 'html/body/div[8]/div/div[1]/div[2]/ul/li[6]/div[2]'  // 售楼电话
    public static String X_SALE_TYPE = 'html/body/div[8]/div/div[1]/div[2]/ul/li[7]/div[2]/a'  // 在售户型

}
