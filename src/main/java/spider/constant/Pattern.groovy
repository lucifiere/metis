package spider.constant

/**
 *  Created by XD.Wang on 2016/11/21.
 *  Pattern centralized management
 */

class Pattern {
    /** 公共 **/
    public static final String R_TOKEN = '[【】，,.。；;：:、"?!]'  //  标点
    public static final String R_BLANK = '\\s{1,5}'
    public static final int ORIGIN_NEW = 0
    public static final int ORIGIN_OLD = 1

    /** 城市 **/
    public static final String TIAN_JIN = 'tj'
    public static final String CHENG_DU = 'cd'
    public static final String WU_HAN = 'wuhan'
    /** 天津城区 **/
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
    public static final String OTHER = 'qita2/'
    /** 搜房网新房 **/
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
    public static final String X_DISTRICT = 'html/body/div[3]/div[1]/p/a[3]'  //  所属辖区
    public static final String X_TRAFFIC = 'html/body/div[8]/div/div[1]/div[3]/div[1]/p'  //  交通状况
    public static final String X_MATING = 'html/body/div[8]/div/div[1]/div[3]/div[2]/p'  //  项目配套
    public static final String X_BUILDING_HIGH = 'html/body/div[8]/div/div[1]/div[4]/ul/li[10]/div[2]'  // 建筑高度
    public static
    final String X_PROJECT_FEATURE = 'html/body/div[8]/div/div[1]/div[1]/ul/li[2]/div[2]/span[@class=\'tag\']'  // 特色
    /** 起始位置 **/
    public static final String O_TIANJIN_ORIGIN = 'http://newhouse.tj.fang.com/house/s/b91/'  // 天津房源列表
    public static final String O_TIANJIN_OH_ORIGIN = 'http://esf.tj.fang.com/housing/__0_0_0_0_1_0_0/'  // 天津老房子列表
    public static final String C_CHENGDU_ORIGIN = 'http://newhouse.cd.fang.com/house/s/b91/'  // 成都房源列表
    public static final String C_WUHAN_ORIGIN = 'http://newhouse.wuhan.fang.com/house/s/b91/'  // 武汉房源列表
    /** 搜房网二手房 **/
    public static final String X_OH_BUILDING_NAME = 'html/body/div[4]/div[2]/div[2]/h1/a'  // 楼盘名称/社区名称
    public static final String X_OH_MONTH_PRICE = 'html/body/div[4]/div[4]/div[1]/div[1]/dl[1]/dd/span'  // 本月均价
    public static final String X_OH_VIA_BEFORE_M = 'html/body/div[4]/div[4]/div[1]/div[1]/dl[2]/dd/span'  // 环比上月
    public static final String X_OH_VIA_BEFORE_Y = 'html/body/div[4]/div[4]/div[1]/div[1]/dl[3]/dd/span'  // 同比上年
    public static final String X_OH_OTHER_NAME = 'html/body/div[4]/div[2]/div[2]/h1/span/span'  // 别名
    public static final String X_OH_DISTRICT = 'html/body/div[4]/div[4]/div[1]/div[2]/div[2]/dl/dd[3]'  // 所属区域
    public static final String X_OH_ADDRESS = 'html/body/div[4]/div[4]/div[1]/div[2]/div[2]/dl/dd[1]'  // 小区地址
    public static final String X_OH_PROJECT_FEATURE = 'html/body/div[4]/div[4]/div[1]/div[2]/div[2]/dl/dd[2]'  // 项目特色
    public static final String X_OH_ZIP_CODE = 'html/body/div[4]/div[4]/div[1]/div[2]/div[2]/dl/dd[4]'  // 邮编
    public static final String X_OH_LOOP_POSITION = 'html/body/div[4]/div[4]/div[1]/div[2]/div[2]/dl/dd[5]'  // 环线位置
    public static final String X_OH_PROPERTY_DES = 'html/body/div[4]/div[4]/div[1]/div[2]/div[2]/dl/dd[6]'  // 产权描述
    public static final String X_OH_PROPERTY_CATEGORY = 'html/body/div[4]/div[4]/div[1]/div[2]/div[2]/dl/dd[7]'  // 物业类别
    public static final String X_OH_COMPLETION = 'html/body/div[4]/div[4]/div[1]/div[2]/div[2]/dl/dd[8]'  // 竣工时间
    public static final String X_OH_DEVELOPER = 'html/body/div[4]/div[4]/div[1]/div[2]/div[2]/dl/dd[9]'  // 开发商
    public static final String X_OH_BUILDING_CATEGORY = 'html/body/div[4]/div[4]/div[1]/div[2]/div[2]/dl/dd[10]'  // 建筑类别
    public static final String X_OH_COVERED_AREA = 'html/body/div[4]/div[4]/div[1]/div[2]/div[2]/dl/dd[11]'  // 建筑面积
    public static final String X_OH_FLOOR_SPACE = 'html/body/div[4]/div[4]/div[1]/div[2]/div[2]/dl/dd[12]'  // 占地面积
    public static final String X_OH_RESIDENT_NUM = 'html/body/div[4]/div[4]/div[1]/div[2]/div[2]/dl/dd[13]' // 当前户数
    public static final String X_OH_ALL_RESIDENT_NUM = 'html/body/div[4]/div[4]/div[1]/div[2]/div[2]/dl/dd[14]'  // 总户数
    public static final String X_OH_GREEN = 'html/body/div[4]/div[4]/div[1]/div[2]/div[2]/dl/dd[15]' // 绿化率
    public static final String X_OH_PLOT_RATIO = 'html/body/div[4]/div[4]/div[1]/div[2]/div[2]/dl/dd[16]'  // 容积率
    public static final String X_OH_PROPERTY_FEE = 'html/body/div[4]/div[4]/div[1]/div[2]/div[2]/dl/dd[17]'  // 物业费
    public static final String X_OH_ADDITIONAL = 'html/body/div[4]/div[4]/div[1]/div[2]/div[2]/dl/dd[18]'  // 附加信息
    public static final String X_OH_COURT_INTRODUCE = './/*[@id=\'jjShow\']'  // 小区简介
    public static final String X_OH_INTRODUCE = 'html/body/div[4]/div[4]/div[1]/div[4]/div[2]/dl/dt'  // 交通情况
    public static final String X_OH_KINDERG = 'html/body/div[4]/div[4]/div[1]/div[5]/div[2]/dl/dt[1]'  // 幼儿园
    public static final String X_OH_SCHOOL = 'html/body/div[4]/div[4]/div[1]/div[5]/div[2]/dl/dt[2]'  // 中小学
    public static final String X_OH_COLLEGE = 'html/body/div[4]/div[4]/div[1]/div[5]/div[2]/dl/dt[3]'  // 大学
    public static final String X_OH_MALL = 'html/body/div[4]/div[4]/div[1]/div[5]/div[2]/dl/dt[4]'  // 商场
    public static final String X_OH_CAPTIAL = 'html/body/div[4]/div[4]/div[1]/div[5]/div[2]/dl/dt[5]'  // 医院
    public static final String X_OH_POST = 'html/body/div[4]/div[4]/div[1]/div[5]/div[2]/dl/dt[6]'  // 邮局
    public static final String X_OH_BANK = 'html/body/div[4]/div[4]/div[1]/div[5]/div[2]/dl/dt[7]'  // 银行
    public static final String X_OH_OTHER = 'html/body/div[4]/div[4]/div[1]/div[5]/div[2]/dl/dt[8]'  // 其他
    public static final String X_OH_MATING = 'html/body/div[4]/div[4]/div[1]/div[5]/div[2]/dl/dt[9]'  // 配套
    public static final String X_M_BASE_INFO = 'html/body/div[4]/div[4]/div[1]/div[2]/div[2]/dl'  // 基本信息
    public static final String X_M_MATING_INFO = 'html/body/div[4]/div[4]/div[1]/div[3]/div[2]/dl'  // 配套信息
    public static final String X_M_COURT_INTRODUCE = 'html/body/div[4]/div[4]/div[1]/div[4]/dl'  // 小区介绍
    public static final String X_M_TRAFFIC = 'html/body/div[4]/div[4]/div[1]/div[5]/div[2]/dl' // 交通情况
    public static final String X_M_SURROUNDING = 'html/body/div[4]/div[4]/div[1]/div[6]/div[2]/dl'  // 周边信息

}
