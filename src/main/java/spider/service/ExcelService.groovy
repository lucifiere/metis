package spider.service

import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.util.CellRangeAddress

/**
 *  Created by XD.Wang on 2016/11/18.
 *  Excel utils
 */

class ExcelService {

    /**
     * To generate a blank excel template
     * @return HSSFWorkbook
     */
    public static HSSFWorkbook getBlankExcel() {
        HSSFWorkbook excel = new HSSFWorkbook()
        HSSFSheet sheet = excel.createSheet("Sheet1")
        HSSFRow categoryRow = sheet.createRow(0)
        HSSFRow explainRow = sheet.createRow(1)
        HSSFRow detailRow = sheet.createRow(2)

        List categoryDes = []
        (0..61).each {
            switch (it) {
                case 0: categoryDes << '编号'; break
                case 1: categoryDes << '名称'; break
                case 4: categoryDes << '基本信息'; explainRow << '所在区域'; break
                case 33: categoryDes << '配套设施'; break
                case 45: categoryDes << '周边信息'; break
                case 53: categoryDes << '地理位置'; break
                default: categoryRow << ''; explainRow << ''; detailRow << ''
            }
        }
        def row4Merge = 1
        sheet.addMergedRegion(new CellRangeAddress(row4Merge, row4Merge, 1, 3))
        sheet.addMergedRegion(new CellRangeAddress(row4Merge, row4Merge, 4, 32))
        sheet.addMergedRegion(new CellRangeAddress(row4Merge, row4Merge, 33, 44))
        sheet.addMergedRegion(new CellRangeAddress(row4Merge, row4Merge, 45, 52))
        sheet.addMergedRegion(new CellRangeAddress(row4Merge, row4Merge, 53, 61))

        List detailDes = ['编号：省市-市区-街道-000', '社区名称', '楼盘名称', '别名', '所属辖区', '所属街道', '环线位置', '地址',
                          '邮编', '建筑年代', '6月均价', '同比去年', '6月出租均价', '产权描述', '项目特色', '物业类别', '建筑形式',
                          '建筑高度', '楼栋数', '建筑结构形式', '总建筑面积', '配套公建面积', '住宅面积', '占地面积', '套型比', '当期户数',
                          '总户数', '入住率', '绿化率', '容积率', '物业费', '附加信息', '供水', '供暖', '供电', '厨房热源', '通讯设备',
                          '卫生服务', '通讯设备', '安全管理', '社区布局方式', '出入口数量', '卫生服务', '停车位', '幼儿园', '中小学', '大学',
                          '商场', '医院', '邮局', '银行', '其他', '公交', '地铁', '教育', '医疗', '餐饮', '购物', '环境', '小区内部配套', '小区简介']
        detailDes.each {
            int i = 0
            categoryRow.createCell(i).setCellValue(it as String)
        }

        excel
    }

}
