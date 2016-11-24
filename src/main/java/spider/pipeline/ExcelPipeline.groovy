package spider.pipeline

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import spider.service.ExcelService
import us.codecraft.webmagic.ResultItems
import us.codecraft.webmagic.Task
import us.codecraft.webmagic.pipeline.Pipeline

/**
 *  Created by XD.Wang on 2016/11/23.
 */
class ExcelPipeline implements Pipeline{

    @Override
    void process(ResultItems resultItems, Task task) {
        HSSFWorkbook excel = ExcelService.getBlankExcel()

    }

}
