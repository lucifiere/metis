package spider.service

import spider.constant.Pattern
import spider.pageprocessor.SouFangOldHouseProcessor
import us.codecraft.webmagic.Page

/**
 *  Created by XD.Wang on 2016/11/23.
 */
class SpiderService {

    public static void crawl() {
//        SouFangNewHouseProcessor souFangPageProcessor = new SouFangNewHouseProcessor('新房', Pattern.O_TIANJIN_ORIGIN)
//        souFangPageProcessor.start(souFangPageProcessor)
        SouFangOldHouseProcessor souFangNewHouseProcessor = new SouFangOldHouseProcessor('旧房', Pattern.O_TIANJIN_OH_ORIGIN)
        souFangNewHouseProcessor.start(souFangNewHouseProcessor);
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
            case '武清': return 301700
            default: return 300000
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
            match(page, origin, '小区地址', 'addr')
            match(page, origin, '所属区域', 'distr')
            match(page, origin, '环线位置', 'loopP')
            match(page, origin, '竣工时间', 'compT')
            match(page, origin, '邮    编', 'zip')
            match(page, origin, '产权描述', 'propL')
            match(page, origin, '物业类别', 'propC')
            match(page, origin, '竣工时间', 'compT')
            match(page, origin, '开 发 商', 'deve')
            match(page, origin, '建筑类别', 'builC')
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
            match(page, origin, '小区简介', 'courI')
            match(page, origin, '项目特色', 'projF')
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
            match(page, origin, '建筑结构形式', 'saleTy')
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

//            def m0 = java.util.regex.Pattern.compile('小区地址\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m0.find()) page.putField('address', m0.group())
//            def m1 = java.util.regex.Pattern.compile('所属区域\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m1.find()) page.putField('district', m1.group())
//            def m2 = java.util.regex.Pattern.compile('邮    编\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m2.find()) page.putField('zip', m2.group())
//            def m3 = java.util.regex.Pattern.compile('产权描述\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m3.find()) page.putField('pDes', m3.group())
//            def m4 = java.util.regex.Pattern.compile('物业类别\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m4.find()) page.putField('pCategory', m4.group())
//            def m5 = java.util.regex.Pattern.compile('竣工时间\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m5.find()) page.putField('completeT', m5.group())
//            def m6 = java.util.regex.Pattern.compile('开 发 商\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m6.find()) page.putField('deve', m6.group())
//            def m7 = java.util.regex.Pattern.compile('建筑类别\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m7.find()) page.putField('bCategory', m7.group())
//            def m8 = java.util.regex.Pattern.compile('建筑面积\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m8.find()) page.putField('cArea', m8.group())
//            def m9 = java.util.regex.Pattern.compile('占地面积\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m9.find()) page.putField('space', m9.group())
//            def m10 = java.util.regex.Pattern.compile('当前户数\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m10.find()) page.putField('rNum', m10.group())
//            def m11 = java.util.regex.Pattern.compile('总 户 数\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m11.find()) page.putField('arNum', m11.group())
//            def m12 = java.util.regex.Pattern.compile('容 积 率\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m12.find()) page.putField('pRation', m12.group())
//            def m13 = java.util.regex.Pattern.compile('物 业 费\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m13.find()) page.putField('pFee', m13.group())
//            def m14 = java.util.regex.Pattern.compile('绿 化 率\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m14.find()) page.putField('green', m14.group())
//            def m15 = java.util.regex.Pattern.compile('附加信息\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m15.find()) page.putField('additional', m15.group())
//            def m16 = java.util.regex.Pattern.compile('燃    气\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m16.find()) page.putField('gas', m16.group())
//            def m17 = java.util.regex.Pattern.compile('供    水\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m17.find()) page.putField('water', m17.group())
//            def m18 = java.util.regex.Pattern.compile('供    暖\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m18.find()) page.putField('warm', m18.group())
//            def m19 = java.util.regex.Pattern.compile('供    电\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m19.find()) page.putField('elec', m19.group())
//            def m20 = java.util.regex.Pattern.compile('通讯设备\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m20.find()) page.putField('commun', m20.group())
//            def m21 = java.util.regex.Pattern.compile('卫生服务\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m21.find()) page.putField('hygi', m21.group())
//            def m22 = java.util.regex.Pattern.compile('小区入口\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m22.find()) page.putField('entry', m22.group())
//            def m23 = java.util.regex.Pattern.compile('小区简介\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m23.find()) page.putField('intro', m23.group())
//            def m24 = java.util.regex.Pattern.compile('本月走势\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m24.find()) page.putField('tend', m24.group())
//            def m25 = java.util.regex.Pattern.compile('项目特色\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m25.find()) page.putField('feature', m25.group())
//            def m26 = java.util.regex.Pattern.compile('物业办公电话\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m26.find()) page.putField('tel', m26.group())
//            def m27 = java.util.regex.Pattern.compile('建筑结构\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m27.find()) page.putField('structure', m27.group())
//            def m28 = java.util.regex.Pattern.compile('物业办公地点\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m28.find()) page.putField('office', m28.group())
//            def m29 = java.util.regex.Pattern.compile('别名\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m29.find()) page.putField('oName', m29.group())
//            def m30 = java.util.regex.Pattern.compile('出租本月均价\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m30.find()) page.putField('rentP', m30.group())
//            def m31 = java.util.regex.Pattern.compile('出租本月走势\\s{0,5}：\\s{0,5}.*?\\s').matcher(origin)
//            while (m31.find()) page.putField('rentT', m31.group())
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

}