package spider.pipeline

import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import spider.constant.Condition
import spider.constant.Config
import spider.constant.Pattern
import spider.constant.RentInfo
import spider.service.ExcelService
import spider.service.SpiderService
import us.codecraft.webmagic.ResultItems
import us.codecraft.webmagic.Task
import us.codecraft.webmagic.pipeline.Pipeline

import java.text.DecimalFormat

/**
 *  Created by XD.Wang on 2016/11/23.
 */
class ExcelPipeline implements Pipeline {

    private static Config config = Config.getConfig()
    private static Condition condition = Condition.getCondition()
    private static HSSFWorkbook excel = ExcelService.getBlankExcel()
    private static volatile int rowCount = 1
    private static ExcelPipeline pipeline
    private static RentInfo rentInfo = RentInfo.getRentInfo()
    private static String excelName = ''

    public static ExcelPipeline getExcelPipeline() {
        if (pipeline == null) {
            pipeline = new ExcelPipeline()
        }
        return pipeline
    }

    @Override
    void process(ResultItems resultItems, Task task) {
        if (excelName == '') {
            String today = new Date().format('yyyy-MM-dd')
            if (condition.getDistrict() == '') {
                excelName = condition.getCityName() + '-' + (resultItems.get('distr') as String)?.replaceAll('楼盘', '') + today
            } else {
                excelName = condition.getCityName() + '-全部-' + today
            }
        }
        if (!excel) return
        int origin = resultItems.get('origin') as int
        HSSFRow newRow = ExcelService.getBlankRow(excel.getSheet('Sheet1'), ++rowCount)
        newRow.getCell(0).setCellValue(resultItems.get('builN') as String)
        newRow.getCell(1).setCellValue(resultItems.get('builN') as String)
        newRow.getCell(2).setCellValue((resultItems.get('oName') as String)?.replaceAll('别名：', ''))
        newRow.getCell(3).setCellValue((resultItems.get('distr') as String)?.replaceAll('楼盘', ''))
        newRow.getCell(4).setCellValue(resultItems.get('street') as String)
        newRow.getCell(5).setCellValue(resultItems.get('loopP') as String)
        newRow.getCell(6).setCellValue(resultItems.get('addr') as String)
        newRow.getCell(9).setCellValue(resultItems.get('pric') as String)
        newRow.getCell(11).setCellValue(resultItems.get('viaY') as String)
        newRow.getCell(12).setCellValue(resultItems.get('viaM') as String)
        newRow.getCell(13).setCellValue(resultItems.get('propL') as String)
        newRow.getCell(14).setCellValue(resultItems.get('projF') as String)
        newRow.getCell(15).setCellValue(resultItems.get('propC') as String)
        newRow.getCell(16).setCellValue(resultItems.get('deve') as String)
        newRow.getCell(17).setCellValue(resultItems.get('builC') as String)
        newRow.getCell(18).setCellValue(resultItems.get('builH') as String)
        newRow.getCell(19).setCellValue(resultItems.get('builNu') as String)
        newRow.getCell(20).setCellValue(resultItems.get('saleTy') as String)
        newRow.getCell(21).setCellValue(resultItems.get('coveA') as String)
        newRow.getCell(22).setCellValue(resultItems.get('flooB') as String)
        newRow.getCell(23).setCellValue(resultItems.get('curR') as String)
        newRow.getCell(24).setCellValue(resultItems.get('allR') as String)
        newRow.getCell(26).setCellValue(resultItems.get('green') as String)
        newRow.getCell(27).setCellValue(resultItems.get('plotR') as String)
        newRow.getCell(28).setCellValue(resultItems.get('propF') as String)
        newRow.getCell(30).setCellValue(resultItems.get('water') as String)
        newRow.getCell(31).setCellValue(resultItems.get('warm') as String)
        newRow.getCell(32).setCellValue(resultItems.get('elec') as String)
        newRow.getCell(33).setCellValue(resultItems.get('gas') as String)
        newRow.getCell(34).setCellValue(resultItems.get('commu') as String)
        newRow.getCell(35).setCellValue(resultItems.get('hegi') as String)
        newRow.getCell(36).setCellValue(resultItems.get('commu') as String)
        newRow.getCell(37).setCellValue(resultItems.get('safe') as String)
        newRow.getCell(38).setCellValue(resultItems.get('entry') as String)
        newRow.getCell(39).setCellValue(resultItems.get('hegi') as String)
        newRow.getCell(40).setCellValue(resultItems.get('parkA') as String)
        newRow.getCell(41).setCellValue(resultItems.get('kin') as String)
        newRow.getCell(42).setCellValue(resultItems.get('school') as String)
        newRow.getCell(43).setCellValue(resultItems.get('college') as String)
        newRow.getCell(44).setCellValue(resultItems.get('mall') as String)
        newRow.getCell(45).setCellValue(resultItems.get('capital') as String)
        newRow.getCell(46).setCellValue(resultItems.get('post') as String)
        newRow.getCell(47).setCellValue(resultItems.get('bank') as String)
        newRow.getCell(48).setCellValue((resultItems.get('env') as String).replaceAll('\n', ';'))
        newRow.getCell(49).setCellValue(resultItems.get('bus') as String)
        newRow.getCell(50).setCellValue(resultItems.get('subWay') as String)
        newRow.getCell(52).setCellValue(resultItems.get('capt') as String)
        newRow.getCell(53).setCellValue(resultItems.get('res') as String)
        newRow.getCell(54).setCellValue(resultItems.get('mall') as String)
        newRow.getCell(55).setCellValue(resultItems.get('env') as String)
        newRow.getCell(56).setCellValue((resultItems.get('other') as String)?.replaceAll('\n', ';'))
        newRow.getCell(57).setCellValue(resultItems.get('courI') as String)
        newRow.getCell(58).setCellValue(resultItems.get('decoC') as String)
        newRow.getCell(59).setCellValue(resultItems.get('saleS') as String)
        newRow.getCell(60).setCellValue(resultItems.get('saleA') as String)
        newRow.getCell(61).setCellValue(resultItems.get('tel') as String)

        if (origin == Pattern.ORIGIN_OLD) {
            rentInfo.rentExcel.put(newRow.getRowNum(), resultItems.get('code') as String)
            newRow.getCell(7).setCellValue(resultItems.get('zip') as String)
            newRow.getCell(8).setCellValue(resultItems.get('compT') as String)
            newRow.getCell(29).setCellValue(resultItems.get('pDes') as String)
            try {
                float allR = (resultItems.get('allR') as String).replaceAll('户', '') as float
                float curR = (resultItems.get('curR') as String).replaceAll('户', '') as float
                DecimalFormat df = new DecimalFormat("######0.00")
                newRow.getCell(25).setCellValue(df.format(curR / allR))
            } catch (Exception ignored) {
                newRow.getCell(25).setCellValue('')
            }
        } else {
            String extraInfo = '开盘时间：' + resultItems.get('saleT')
            String age = '新房，' + resultItems.get('tranT')
            newRow.getCell(7).setCellValue(SpiderService.getPostCode(resultItems.get('distr') as String))
            newRow.getCell(8).setCellValue(age)
            newRow.getCell(29).setCellValue(extraInfo)
        }

        String edu = resultItems.get('kin') + '\t' + resultItems.get('school') + '\t' + resultItems.get('college')
        newRow.getCell(51).setCellValue(edu)
    }

    static void export() {
        String path = config.getPath().endsWith('\\') ? config.getPath() + config.getExcelName() + ".xls" :
                config.getPath() + '\\' + excelName + ".xls"
        def os = new FileOutputStream(path)
        excel.write(os)
        os.close()
    }

    public static void combine() {
        HSSFSheet sheet = excel.getSheet('Sheet1')
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            String code = RentInfo.rentExcel.get(i)
            String rentPrice = RentInfo.rentPage.get(code)
            if (rentPrice != null){
                sheet.getRow(i).getCell(10).setCellValue(rentPrice.trim())
                def m = java.util.regex.Pattern.compile('一居：.*二居').matcher(rentPrice)
                while (m.find()) sheet.getRow(i).getCell(62).setCellValue(m.group()?.replaceAll('一居：','')?.replaceAll('二居','')?.trim())
                def m2 = java.util.regex.Pattern.compile('二居：.*三居').matcher(rentPrice)
                while (m2.find()) sheet.getRow(i).getCell(63).setCellValue(m2.group()?.replaceAll('二居：','')?.replaceAll('三居','')?.trim())
                def m1 = java.util.regex.Pattern.compile('三居：.*单间').matcher(rentPrice)
                while (m1.find()) sheet.getRow(i).getCell(64).setCellValue(m1.group()?.replaceAll('三居：','')?.replaceAll('单间','')?.trim())
                def m3 = java.util.regex.Pattern.compile('单间：.*').matcher(rentPrice)
                while (m3.find()) sheet.getRow(i).getCell(64).setCellValue(m3.group()?.replaceAll('单间：','')?.trim())
            }
        }
    }

}
