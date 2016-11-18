package spider.utils

import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook

/**
 *  Created by XD.Wang on 2016/11/18.
 *  Excel 工具类
 */

class ExcelUtil {

    public static HSSFWorkbook getBlankExcel() {

        HSSFWorkbook excel = new HSSFWorkbook()
        HSSFSheet sheet = excel.createSheet("Sheet1")
        HSSFRow categoryRow = sheet.createRow(0)
        HSSFRow explainRow = sheet.createRow(1)
        HSSFRow detailRow = sheet.createRow(2)

        List categoryDes = []
        List explainDes = []
        List detailDes = []
        String PRdes = '产权描述 1=公产房 2=企业产房 3=商业40年 4=商业50年 6=居住70年'
        String FEdes = '项目特色:1=地铁沿线；2=教育地产；3=经济住宅；4=投资地产；5=复合地产；6=景观居所；7=宜居生态地产；8=国际化社区；9=低密居所'
        String Pdes = '物业类别：1=普通住宅；2=公寓；3=廉租；4=限价房；5=公租房；6=经适房；7=非普通住宅；8=商业商铺；9=写字楼'


        (0..61).each {
            switch (it) {
                case 0: categoryDes << '编号'; break
                case 1: categoryDes << '名称'; break
                case 4: categoryDes << '基本信息';explainRow << '所在区域';break
                case 33: categoryDes << '配套设施'; break
                case 45: categoryDes << '周边信息'; break
                case 53: categoryDes << '地理位置'; break
                default: categoryRow << ''; explainRow << ''; detailRow << ''
            }

        }

        (0..61).each {
            setCel(categoryRow, it,)
        }

        return null

    }

    static def setCel(HSSFRow row, int cellNo, String des) {
        row.createCell(cellNo).setCellValue(des)
    }


}
