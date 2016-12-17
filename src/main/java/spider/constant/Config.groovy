package spider.constant

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 *  Created by XD.Wang on 2016/11/21.
 *  Global Config centralized management
 */

class Config {

    public static final String SO_FANG = '搜房网'
    public static final String AN_JU_KE = '安居客'
    public static final String ZHONG_YUAN = '中原地产'
    public static final String LIAN_JIA = '链家网'
    public static
    final String AGENT = 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31'

    private static Config c
    private String spiderName = '房产信息爬虫'  // 爬虫名称
    private int retryTime = 3 // 重试次数
    private int wait4Next = 2000   // 抓取频率
    private int threadCount = 3   // 抓取线程数
    private int predictPageNum = 100000 // 预估页面数
    private int timeout = 3 * 1000 // 超时时间
    private String path = 'D:\\spider\\'
    private String excelName = '房产信息'

//    private static final Logger log = LoggerFactory.getLogger(Config.class)

    public static Config getConfig() {
        try {
            if (c == null) {
                Properties p = new Properties()
                p.load(new FileInputStream("config.properties"))
                c = new Config()
                c.setSpiderName(p.getProperty('spider_name'))
                c.setRetryTime(p.getProperty('retry_time') as int)
                c.setWait4Next(p.getProperty('wait_time') as int)
                c.setThreadCount(p.getProperty('thread') as int)
                c.setPredictPageNum(p.getProperty('page_num') as int)
                c.setTimeout(p.getProperty('timeout') as int)
                c.setPath(p.getProperty('file_path'))
                c.setExcelName(p.getProperty('excel_name'))
                return c
            } else {
                c
            }
        } catch (Exception ignored) {
//            log.error("配置加载失败，将使用默认配置：", ex)
            c = new Config()
            c
        }
    }

    private Config() {}

    int getWait4Next() {
        return wait4Next
    }

    void setWait4Next(int wait4Next) {
        this.wait4Next = wait4Next
    }

    int getRetryTime() {
        return retryTime
    }

    void setRetryTime(int retryTime) {
        this.retryTime = retryTime
    }

    String getSpiderName() {
        return spiderName
    }

    void setSpiderName(String spiderName) {
        this.spiderName = spiderName
    }

    int getThreadCount() {
        return threadCount
    }

    void setThreadCount(int threadCount) {
        this.threadCount = threadCount
    }

    int getPredictPageNum() {
        return predictPageNum
    }

    void setPredictPageNum(int predictPageNum) {
        this.predictPageNum = predictPageNum
    }

    int getTimeout() {
        return timeout
    }

    void setTimeout(int timeout) {
        this.timeout = timeout
    }

    String getPath() {
        return path
    }

    void setPath(String path) {
        this.path = path
    }

    String getExcelName() {
        return excelName
    }

    void setExcelName(String excelName) {
        this.excelName = excelName
    }
}
