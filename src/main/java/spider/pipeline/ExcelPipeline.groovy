package spider.pipeline

import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import spider.constant.Config
import spider.service.ExcelService
import spider.service.SpiderService
import us.codecraft.webmagic.ResultItems
import us.codecraft.webmagic.Task
import us.codecraft.webmagic.pipeline.Pipeline

/**
 *  Created by XD.Wang on 2016/11/23.
 */
class ExcelPipeline implements Pipeline {

    private static Config config = Config.getConfig()
    private static HSSFWorkbook excel = ExcelService.getBlankExcel()
    private static volatile int rowCount = 1

    @Override
    void process(ResultItems resultItems, Task task) {
        if (excel != null) {
            HSSFRow newRow = ExcelService.getBlankRow(excel.getSheet('Sheet1'), ++rowCount)
            newRow.getCell(1).setCellValue(resultItems.get('builN') as String)
            newRow.getCell(2).setCellValue(resultItems.get('builN') as String)
            newRow.getCell(4).setCellValue((resultItems.get('distr') as String).replaceAll('楼盘', ''))
            newRow.getCell(6).setCellValue(resultItems.get('loopP') as String)
            newRow.getCell(7).setCellValue(resultItems.get('addr') as String)
            newRow.getCell(10).setCellValue(resultItems.get('pric') as String)
            newRow.getCell(13).setCellValue(resultItems.get('propL') as String)
            newRow.getCell(14).setCellValue(resultItems.get('projF') as String)
            newRow.getCell(15).setCellValue(resultItems.get('propC') as String)
            newRow.getCell(16).setCellValue(resultItems.get('deve') as String)
            newRow.getCell(17).setCellValue(resultItems.get('builC') as String)
            newRow.getCell(18).setCellValue(resultItems.get('builH') as String)
            newRow.getCell(19).setCellValue(resultItems.get('builNu') as String)
            newRow.getCell(20).setCellValue(resultItems.get('saleTy') as String)
            newRow.getCell(21).setCellValue(resultItems.get('coveA') as String)
            newRow.getCell(24).setCellValue(resultItems.get('flooB') as String)
            newRow.getCell(27).setCellValue(resultItems.get('allR') as String)
            newRow.getCell(29).setCellValue(resultItems.get('green') as String)
            newRow.getCell(30).setCellValue(resultItems.get('plotR') as String)
            newRow.getCell(31).setCellValue(resultItems.get('propF') as String)
            newRow.getCell(44).setCellValue(resultItems.get('parkA') as String)
            newRow.getCell(45).setCellValue(resultItems.get('kin') as String)
            newRow.getCell(46).setCellValue(resultItems.get('school') as String)
            newRow.getCell(47).setCellValue(resultItems.get('college') as String)
            newRow.getCell(48).setCellValue(resultItems.get('mall') as String)
            newRow.getCell(49).setCellValue(resultItems.get('capital') as String)
            newRow.getCell(50).setCellValue(resultItems.get('post') as String)
            newRow.getCell(51).setCellValue(resultItems.get('bank') as String)
            newRow.getCell(52).setCellValue((resultItems.get('env') as String).replaceAll('\n',';'))
            newRow.getCell(53).setCellValue(resultItems.get('bus') as String)
            newRow.getCell(54).setCellValue(resultItems.get('subWay') as String)
            newRow.getCell(56).setCellValue(resultItems.get('capt') as String)
            newRow.getCell(57).setCellValue(resultItems.get('res') as String)
            newRow.getCell(58).setCellValue(resultItems.get('mall') as String)
            newRow.getCell(59).setCellValue(resultItems.get('env') as String)
            newRow.getCell(60).setCellValue((resultItems.get('other') as String).replaceAll('\n',';'))
            newRow.getCell(61).setCellValue(resultItems.get('courI') as String)
            newRow.getCell(62).setCellValue(resultItems.get('decoC') as String)
            newRow.getCell(63).setCellValue(resultItems.get('saleS') as String)
            newRow.getCell(64).setCellValue(resultItems.get('saleA') as String)
            newRow.getCell(65).setCellValue(resultItems.get('saleTe') as String)

            String edu = resultItems.get('kin') + '\t' + resultItems.get('school') + '\t' + resultItems.get('college')
            String extraInfo = '开盘时间：' + resultItems.get('saleT')
            String age = '新房，' + resultItems.get('tranT')
            newRow.getCell(8).setCellValue(SpiderService.getPostCode(resultItems.get('distr') as String))
            newRow.getCell(9).setCellValue(age)
            newRow.getCell(32).setCellValue(extraInfo)
            newRow.getCell(55).setCellValue(edu)
        }
    }

    static void export() {
        String path = config.getPath().endsWith('\\') ? config.getPath() + config.getExcelName() + ".xls" :
                config.getPath() + '\\' + config.getExcelName() + ".xls"
        def os = new FileOutputStream(path)
        excel.write(os)
        os.close()
    }

}
