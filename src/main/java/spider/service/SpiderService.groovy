package spider.service

import spider.constant.Pattern
import spider.pageprocessor.SouFangNewHouseProcessor
import spider.pageprocessor.SouFangOldHouseProcessor
import spider.pipeline.ExcelPipeline
import us.codecraft.webmagic.Page

/**
 *  Created by XD.Wang on 2016/11/23.
 */
class SpiderService {

    public static void crawl() {
        SouFangOldHouseProcessor souFangNewHouseProcessor = new SouFangOldHouseProcessor('旧房', 'http://www.baidu.com')
        souFangNewHouseProcessor.start(souFangNewHouseProcessor);
        ExcelPipeline.combine()
        SouFangNewHouseProcessor souFangPageProcessor = new SouFangNewHouseProcessor('新房', 'http://www.baidu.com')
        souFangPageProcessor.start(souFangPageProcessor)
        System.out.print("---------------------------爬行结束---------------------------")
    }

    public static String getPropertyRight(String p) {
        def m = java.util.regex.Pattern.compile("40").matcher(p)
        while (m.find()) return '3'
        def m1 = java.util.regex.Pattern.compile("50").matcher(p)
        while (m1.find()) return '4'
        def m2 = java.util.regex.Pattern.compile("70").matcher(p)
        while (m2.find()) return '6'
        def m3 = java.util.regex.Pattern.compile("公产").matcher(p)
        while (m3.find()) return '1'
        def m4 = java.util.regex.Pattern.compile("企业").matcher(p)
        while (m4.find()) return '2'
        return ''
    }

    public static int getPostCode(String name) {
        name = name.replaceAll('楼盘', '')
        switch (name) {
            case '河北': return 300143
            case '河西': return 300202
            case '和平': return 300041
            case '南开': return 300100
            case '河东': return 300171
            case '东丽': return 300300
            case '北辰': return 300400
            case '西青': return 300380
            case '津南': return 300350
            case '滨海新区': return 300450
            case '红桥': return 300131
            case '宝坻': return 301800
            case '蓟州': return 301900
            case '宁河': return 301500
            case '静海': return 301600
        //
            case '武清': return 301700
            case '青羊': return 610031
            case '锦江': return 610011
            case '武侯': return 610041
            case '成华': return 610066
            case '金牛': return 610036
            case '高新区': return 610041
            case '高新西区': return 611730
            case '郫县': return 611730
            case '龙泉驿': return 610100
            case '新都': return 610500
            case '温江': return 611130
            case '都江堰市': return 611830
            case '青白江': return 610300
            case '金堂县': return 610400
            case '崇州市': return 611230
            case '邛崃市': return 611530
            case '浦江县': return 611630
            case '双流': return 610200
            case '天府新区': return 610000
        //
            case '渝中': return 400010
            case '江北': return 400020
            case '渝北': return 401147
            case '南岸': return 400060
            case '沙坪坝': return 400030
            case '九龙坡': return 400050
            case '巴南': return 401320
            case '大渡口': return 400080
            case '北碚': return 400700
            case '重庆郊县': return 404100
        //
            case '龙岗': return 518100
            case '龙华新区': return 518109
            case '宝安': return 518100
            case '南山': return 518000
            case '福田': return 518000
            case '罗湖': return 518000
            case '坪山新区': return 518118
            case '光明新区': return 518107
            case '盐田': return 518000
            case '大鹏新区': return 518108
            case '东莞': return 523000
            case '惠州': return 516000
            case '中山': return 528400
            case '珠海': return 519000
        //
            case '东湖高新区': return 430079
            case '江岸区': return 430014
            case '洪山区': return 430070
            case '东西湖区': return 430040
            case '汉阳区': return 430050
            case '武昌区': return 430000
            case '汉江区': return 430000
            case '经济开发区': return 430056
            case '硚口区': return 430000
            case '黄陂区': return 432200
            case '江夏区': return 430200
            case '青山区': return 430080
            case '蔡甸区': return 430100
            case '新洲区': return 431400
            case '汉南区': return 430090
            default: return 000000
            //
            case '江干': return 310002
            case '拱墅': return 310011
            case '下沙': return 310018
            case '西湖': return 310013
            case '下城': return 310006
            case '滨江': return 310051
            case '之江': return 310013
            case '上城': return 311500
            case '余杭': return 311300
            case '萧山': return 311201
            case '富阳': return 311400
            case '桐庐': return 311500
            case '临安': return 311300
            case '淳安': return 311700
            case '建德': return 311600
        }
    }

    // 抽取周边信息
    public static void analysisInfoWithoutFormat(Page page, String traffic, String mating) {
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
        if (capital != '') page.putField('capt', capital)
        if (kindergarten != '') page.putField('kin', kindergarten)
        if (school != '') page.putField('school', school)
        if (college != '') page.putField('college', college)
        if (mall != '') page.putField('mall', mall)
        if (post != '') page.putField('post', post)
        if (bank != '') page.putField('bank', bank)
        if (restaurant != '') page.putField('res', restaurant)
        if (subWay != '') page.putField('subWay', subWay)
        if (bus != '') page.putField('bus', bus)
        if (matingClean != '') page.putField('other', matingClean)
        if (trafficClean != '') page.putField('env', trafficClean)
    }

    public static void analysisInfoWithFormat(Page page, List origins) {
        for (String origin in origins) {
            if (!origin) continue
            analysisInfoWithoutFormat(page, origin, origin)
        }
        for (String origin in origins) {
            if (!origin) continue
            matchStreet(page, origin)
            matchBuilding(page, origin)
            match(page, origin, '小区地址', 'addr')
            match(page, origin, '所属区域', 'distr')
            match(page, origin, '环线位置', 'loopP')
            match(page, origin, '竣工时间', 'compT')
            match(page, origin, '邮    编', 'zip')
            match(page, origin, '产权描述', 'propL')
            match(page, origin, '物业类别', 'propC')
            match(page, origin, '竣工时间', 'compT')
            match(page, origin, '开 发 商', 'deve')
            match(page, origin, '建筑面积', 'coveA')
            match(page, origin, '占地面积', 'flooB')
            match(page, origin, '当期户数', 'curR')
            match(page, origin, '总 户 数', 'allR')
            match(page, origin, '容 积 率', 'plotR')
            match(page, origin, '物 业 费', 'propF')
            match(page, origin, '绿 化 率', 'green')
            match(page, origin, '附加信息', 'pDes')
            match(page, origin, '燃    气', 'gas')
            match(page, origin, '供    水', 'water')
            match(page, origin, '供    暖', 'warm')
            match(page, origin, '供    电', 'elec')
            match(page, origin, '安全管理', 'safe')
            match(page, origin, '社区布局方式', 'layout')
            match(page, origin, '项目特色', 'projF')
            match(page, origin, '楼栋总数', 'builNu')
            match(page, origin, '物业办公电话', 'tel')
            match(page, origin, '通讯设备', 'commu')
            match(page, origin, '卫生服务', 'hegi')
            match(page, origin, '小区入口', 'entry')
            match(page, origin, '本月走势', 'tend')
            match(page, origin, '建筑结构', 'strut')
            match(page, origin, '物业办公地点', 'office')
            match(page, origin, '别名', 'oName')
            match(page, origin, '出租本月均价', 'rentP')
            match(page, origin, '出租本月走势', 'rentT')
            match(page, origin, '停车位', 'parkA')
            match(page, origin, '建筑高度', 'builH')
            match(page, origin, '楼栋数', 'builNu')
            match(page, origin, '建筑结构', 'saleTy')
            match(page, origin, ['医院'], 'capt')
            match(page, origin, ['幼儿园'], 'kin')
            match(page, origin, ['中小学'], 'school')
            match(page, origin, ['大学'], 'college')
            match(page, origin, ['商场'], 'mall')
            match(page, origin, ['邮局'], 'post')
            match(page, origin, ['银行'], 'bank')
            match(page, origin, ['餐饮', '餐馆', '饮食'], 'res')
            match(page, origin, ['地铁', '轨道交通'], 'subWay')
            match(page, origin, ['公交', '公共交通'], 'bus')
            match(page, origin, '其他', 'env')
            match(page, origin, '小区内部配套', 'other')
        }
    }

    private static void match(Page page, String origin, String item, String key) {
        def m = java.util.regex.Pattern.compile("${item}\\s{0,5}：\\s{0,5}.*?\\s").matcher(origin)
        while (m.find()) page.putField(key, m.group()?.replaceAll("${item}\\s{0,5}：\\s{0,5}", ''))
    }

    private static void match(Page page, String origin, List items, String key) {
        String tmp = ''
        for (String s : items) {
            def m = java.util.regex.Pattern.compile("${s}\\s{0,5}：\\s{0,5}.*?\\s").matcher(origin)
            while (m.find()) {
                tmp += m.group()?.replaceAll("${s}\\s{0,5}：\\s{0,5}", '') + ';'
            }
        }
        if (tmp != '')
            page.putField(key, tmp)
    }

    private static void matchStreet(Page page, String origin) {
        String tmp = ''
        def m = java.util.regex.Pattern.compile("所属区域\\s{0,5}：\\s{0,5}.*?\\s{0,2}.*\\s").matcher(origin)
        while (m.find()) tmp = m.group()?.replaceAll("所属区域\\s{0,5}：\\s{0,5}", '')
        if (tmp != '') {
            String[] street = tmp.split(' ')
            if (street.size() > 1)
                page.putField('street', street[1])
        }
    }

    private static void matchBuilding(Page page, String origin) {
        String tmp = ''
        def m = java.util.regex.Pattern.compile("建筑类别\\s{0,5}：\\s{0,5}.*?\\s{0,2}.*\\s").matcher(origin)
        while (m.find()) tmp = m.group()?.replaceAll("建筑类别\\s{0,5}：\\s{0,5}", '')
        if (tmp != '') {
            String[] street = tmp.split(' ')
            if (street.size() == 1) page.putField('buildC', street[0])
            if (street.size() > 1) {
                page.putField('builC', street[0])
                String height = ''
                for (int i = 1; i < street.size(); i++) {
                    if (street[i] == "") break
                    height += street[i] + ' '
                }
                page.putField('builH', street[1])
            }
        }
    }


}