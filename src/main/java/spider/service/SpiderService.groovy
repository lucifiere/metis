package spider.service

import spider.constant.Pattern
import spider.pageprocessor.SouFangOldHouseProcessor
import spider.pageprocessor.SouFangNewHouseProcessor
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
    public static void analysisSurroundingInfo(Page page, String traffic, String mating) {
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
