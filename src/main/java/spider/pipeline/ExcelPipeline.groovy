package spider.pipeline

import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Workbook
import spider.constant.Config
import spider.service.ExcelService
import us.codecraft.webmagic.ResultItems
import us.codecraft.webmagic.Task
import us.codecraft.webmagic.pipeline.Pipeline

/**
 *  Created by XD.Wang on 2016/11/23.
 */
class ExcelPipeline implements Pipeline {

    private Config config = Config.getConfig()

    @Override
    void process(ResultItems resultItems, Task task) {
        HSSFWorkbook excel = ExcelService.getBlankExcel()
        if (excel != null) {
            HSSFRow newRow = ExcelService.getBlankRow(excel.getSheet('Sheet1'), 2)
            newRow.getCell(12).setCellValue(resultItems.get('pric') as String)
            newRow.getCell(15).setCellValue(resultItems.get('propC') as String)
            newRow.getCell(14).setCellValue(resultItems.get('projF') as String)
            newRow.getCell(62).setCellValue(resultItems.get('builC') as String)
        }
        def os = new FileOutputStream(config.getPath() + config.getExcelName() + ".xls")
        excel.write(os)
        os.close()
    }

}
