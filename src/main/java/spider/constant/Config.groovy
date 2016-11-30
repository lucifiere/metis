package spider.constant

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 *  Created by XD.Wang on 2016/11/21.
 *  Config centralized management
 */

class Config {

    public static final String SO_FANG = '搜房网'
    public static final String AN_JU_KE = '安居客'
    public static final String ZHONG_YUAN = '中原地产'
    public static final String LIAN_JIA = '链家网'
    private static final Logger log = LoggerFactory.getLogger(Config.class)

    private static Config c
    private String spiderName = '房产信息爬虫'  // 爬虫名称
    private int retryTime = 3 // 重试次数
    private int wait4Next = 2000   // 抓取频率
    private int threadCount = 3   // 抓取线程数
    private int predictPageNum = 100000 // 预估页面数

    public static Config getConfig() {
        try {
            if (c == null) {
                Properties p = new Properties().load(new FileInputStream("config.properties"))
                c = new Config()
                c.setSpiderName(p.getProperty('spider_name'))
                c.setRetryTime(p.getProperty('retry_time') as int)
                c.setWait4Next(p.getProperty('wait_time') as int)
                c.setThreadCount(p.getProperty('thread') as int)
                c.setPredictPageNum(p.getProperty('pageNum') as int)
                return c
            } else {
                c
            }
        } catch (Exception ex) {
            log.error("配置加载失败，将使用默认配置：", ex)
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
}
