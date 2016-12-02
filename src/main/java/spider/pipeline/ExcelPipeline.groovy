package spider.pipeline

import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import spider.constant.Config
import spider.service.ExcelService
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
            newRow.getCell(10).setCellValue(resultItems.get('pric') as String)
            newRow.getCell(15).setCellValue(resultItems.get('propC') as String)
            newRow.getCell(14).setCellValue(resultItems.get('projF') as String)
            newRow.getCell(62).setCellValue(resultItems.get('builC') as String)
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
