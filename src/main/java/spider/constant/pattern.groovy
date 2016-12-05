package spider.constant

/**
 *  Created by XD.Wang on 2016/11/21.
 *  Pattern centralized management
 */

class Pattern {
    /** 公共 **/
    public static final String TIAN_JIN = 'tj'
    public static final String CHENG_DU = 'cd'
    public static final String WU_HAN = 'wuhan'

    public static final String ALL = ''
    public static final String TJ_HE_PING = 'heping/'
    public static final String TJ_HE_XI = 'hexi/'
    public static final String TJ_HE_BEI = 'hebei/'
    public static final String TJ_DONG_LI = 'dongli/'
    public static final String TJ_BIN_HAI_XIN_QU = 'binhaixinqu/'
    public static final String TJ_JIN_NAN = 'jinnan/'
    public static final String TJ_NAN_KAI = 'nankai/'
    public static final String TJ_BEI_CHEN = 'beichen/'
    public static final String TJ_JING_HAI = 'jinghai/'
    public static final String TJ_HONG_QIAO = 'hongqiao/'
    public static final String TJ_JI_ZHOU = 'jizhou/'
    public static final String TJ_XI_QING = 'xiqing/'
    public static final String TJ_NING_HE = 'ninghe/'
    public static final String TJ_BAO_DI = 'baodi/'
    public static final String TJ_HE_DONG = 'hedong/'
    public static final String TJ_WU_QING = 'wuqing/'
    public static final String TE_NING_HE = 'ninghe/'
    public static final String OTHER = 'qita2/'

    /** 搜房网 **/
    public static final String R_COURT_DETAIL = '.*\\.fang\\.com/house/\\d+/housedetail.htm'  // 小区信息地址
    public static final String X_BUILDING_NAME = 'html/body/div[4]/div/div[1]/dl/dd/div[1]/h1/a'  // 楼盘名称/社区名称
    public static final String X_2016_PRICE = 'html/body/div[8]/div/div[1]/div[1]/div/div[1]/em'  // 2016年6月均价
    public static final String X_PROPERTY_CATEGORY = 'html/body/div[8]/div/div[1]/div[1]/ul/li[1]/div[2]'  // 物业类别

    public static final String X_BUILDING_CATEGORY = 'html/body/div[8]/div/div[1]/div[1]/ul/li[3]/div[2]/span'  // 建筑类别
    public static final String X_DECORATION_CONDITION = 'html/body/div[8]/div/div[1]/div[1]/ul/li[4]/div[2]'  // 装修状况
    public static final String X_PROPERTY_LIMIT = 'html/body/div[8]/div/div[1]/div[1]/ul/li[5]/div[2]'  // 产权年限
    public static final String X_LOOP_POSITION = 'html/body/div[8]/div/div[1]/div[1]/ul/li[6]/div[2]'  // 环线位置
    public static final String X_DEVELOPER = 'html/body/div[8]/div/div[1]/div[1]/ul/li[7]/div[2]/a'  // 开发商
    public static final String X_ADDRESS = 'html/body/div[8]/div/div[1]/div[1]/ul/li[8]/div[2]'  // 楼盘地址
    public static final String X_SALE_STATUS = 'html/body/div[8]/div/div[1]/div[2]/ul/li[1]/div[2]'  // 销售状态
    public static final String X_FAVORABLE = 'html/body/div[8]/div/div[1]/div[2]/ul/li[2]/div[2]'  // 楼盘优惠
    public static final String X_SALE_TIME = 'html/body/div[8]/div/div[1]/div[2]/ul/li[3]/div[2]'  // 开盘时间
    public static final String X_TRANSFER_TIME = 'html/body/div[8]/div/div[1]/div[2]/ul/li[4]/div[2]'  // 交房时间
    public static final String X_SALE_ADDRESS = 'html/body/div[8]/div/div[1]/div[2]/ul/li[5]/div[2]'  // 售楼地址
    public static final String X_SALE_TEL = 'html/body/div[8]/div/div[1]/div[2]/ul/li[6]/div[2]'  // 售楼电话
    public static final String X_SALE_TYPE = 'html/body/div[8]/div/div[1]/div[2]/ul/li[7]/div[2]/a'  // 在售户型
    public static final String X_GREEN = 'html/body/div[8]/div/div[1]/div[4]/ul/li[4]/div[2]'  // 绿化率
    public static final String X_BUILDING_NUM = 'html/body/div[8]/div/div[1]/div[4]/ul/li[6]/div[2]'  // 楼栋数
    public static final String X_PLOT_RATIO = 'html/body/div[8]/div/div[1]/div[4]/ul/li[3]/div[2]'  // 容积率
    public static final String X_ALL_RESIDENT = 'html/body/div[8]/div/div[1]/div[4]/ul/li[7]/div[2]'  // 总户数
    public static final String X_FLOOR_BASE = 'html/body/div[8]/div/div[1]/div[4]/ul/li[1]/div[2]'  // 占地面积
    public static final String X_COVERED_AREA = 'html/body/div[8]/div/div[1]/div[4]/ul/li[2]/div[2]'  // 建筑面积
    public static final String X_PROPERTY_FEE = 'html/body/div[8]/div/div[1]/div[4]/ul/li[9]/div[2]'  // 物业费
    public static final String X_COURT_INTRODUCE = 'html/body/div[8]/div/div[1]/div[6]/p'  // 小区简介
    public static final String X_PARKING_AREA = 'html/body/div[8]/div/div[1]/div[4]/ul/li[5]/div[2]'  // 停车位
    public static
    final String X_PROJECT_FEATURE = 'html/body/div[8]/div/div[1]/div[1]/ul/li[2]/div[2]/span[@class=\'tag\']'
    // 项目特色final

    public static final String C_TIANJIN_ORIGIN = 'http://newhouse.tj.fang.com/house/s/b91/'  // 天津房源列表
    public static final String C_CHENGDU_ORIGIN = 'http://newhouse.cd.fang.com/house/s/b91/'  // 成都房源列表
    public static final String C_WUHAN_ORIGIN = 'http://newhouse.wuhan.fang.com/house/s/b91/'  // 武汉房源列表

}
