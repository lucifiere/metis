package spider.ui

import javafx.application.Application
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.concurrent.Task
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.control.ChoiceBox
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.DirectoryChooser
import javafx.stage.DirectoryChooserBuilder
import javafx.stage.Stage
import spider.constant.BuildingNumberInfo
import spider.constant.Condition
import spider.constant.Config
import spider.constant.Pattern
import spider.constant.RentInfo
import spider.pipeline.ExcelPipeline
import spider.service.SpiderService

/**
 *  Created by Tyler.Wang on 2016/11/20.
 *  Description : panel
 */
class PanelView extends Application {

    private static Config config = Config.getConfig()
    public static Condition condition = Condition.getCondition()
    public static boolean crawling = false
    public static Thread crawlingThread = null
    public static int currentDistrictListenerIndex = 0

    void start(Stage primaryStage) throws Exception {
        // 1. 搜索过滤设置
        Label tip = new Label('欢迎使用房产爬虫')

        TianjinSelectEvent tjListener = new TianjinSelectEvent()
        ChengduSelectEvent cdListener = new ChengduSelectEvent()
        WuhanSelectEvent whListener = new WuhanSelectEvent()
        HangZhouSelectEvent hzListener = new HangZhouSelectEvent()
        ShenZhenSelectEvent szListener = new ShenZhenSelectEvent()
        ChongQingSelectEvent cqListener = new ChongQingSelectEvent()

        def district = new ChoiceBox(FXCollections.observableArrayList(
                '全部',
                '河北',
                '河西',
                '和平',
                '南开',
                '河东',
                '东丽',
                '北辰',
                '西青',
                '津南',
                '滨海新区',
                '红桥',
                '宝坻',
                '蓟州',
                '宁河',
                '静海',
                '武清',
                '其他')
        )
        district.getSelectionModel().selectedIndexProperty().addListener(tjListener)
        def cityLabel = new Label('城市')
        def city = new ChoiceBox(FXCollections.observableArrayList(
                '天津',
                '成都',
                '武汉',
                '杭州',
                '深圳',
                '重庆')
        )
        city.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            void changed(ObservableValue<? extends Number> observableValue, Number s, Number t1) {
                switch (t1) {
                    case 0:
                        condition.setCity(Pattern.TIAN_JIN)
                        condition.setCityName(Pattern.CNAME_TIAN_JIN)
                        district.setItems(FXCollections.observableArrayList(
                                '全部',
                                '河北',
                                '河西',
                                '和平',
                                '南开',
                                '河东',
                                '东丽',
                                '北辰',
                                '西青',
                                '津南',
                                '滨海新区',
                                '红桥',
                                '宝坻',
                                '蓟州',
                                '宁河',
                                '静海',
                                '武清',
                                '其他')
                        )
                        if (currentDistrictListenerIndex == 0) district.getSelectionModel().selectedIndexProperty().removeListener(tjListener)
                        if (currentDistrictListenerIndex == 1) district.getSelectionModel().selectedIndexProperty().removeListener(cdListener)
                        if (currentDistrictListenerIndex == 2) district.getSelectionModel().selectedIndexProperty().removeListener(whListener)
                        if (currentDistrictListenerIndex == 3) district.getSelectionModel().selectedIndexProperty().removeListener(hzListener)
                        if (currentDistrictListenerIndex == 4) district.getSelectionModel().selectedIndexProperty().removeListener(szListener)
                        if (currentDistrictListenerIndex == 5) district.getSelectionModel().selectedIndexProperty().removeListener(cqListener)
                        district.getSelectionModel().selectedIndexProperty().addListener(tjListener)
                        currentDistrictListenerIndex = 0
                        break
                    case 1:
                        condition.setCity(Pattern.CHENG_DU)
                        condition.setCityName(Pattern.CNAME_CHENG_DU)
                        district.setItems(FXCollections.observableArrayList(
                                '全部',
                                '成华',
                                '锦江',
                                '金牛',
                                '龙泉驿',
                                '其他',
                                '青白江',
                                '新都',
                                '青羊',
                                '武侯',
                                '温江',
                                '双流')
                        )
                        if (currentDistrictListenerIndex == 0) district.getSelectionModel().selectedIndexProperty().removeListener(tjListener)
                        if (currentDistrictListenerIndex == 1) district.getSelectionModel().selectedIndexProperty().removeListener(cdListener)
                        if (currentDistrictListenerIndex == 2) district.getSelectionModel().selectedIndexProperty().removeListener(whListener)
                        if (currentDistrictListenerIndex == 3) district.getSelectionModel().selectedIndexProperty().removeListener(hzListener)
                        if (currentDistrictListenerIndex == 4) district.getSelectionModel().selectedIndexProperty().removeListener(szListener)
                        if (currentDistrictListenerIndex == 5) district.getSelectionModel().selectedIndexProperty().removeListener(cqListener)
                        district.getSelectionModel().selectedIndexProperty().addListener(cdListener)
                        currentDistrictListenerIndex = 1
                        break
                    case 2:
                        condition.setCity(Pattern.WU_HAN)
                        condition.setCityName(Pattern.CNAME_WU_HAN)
                        district.setItems(FXCollections.observableArrayList(
                                '全部',
                                '蔡甸',
                                '东西湖',
                                '汉南',
                                '汉阳',
                                '洪山',
                                '黄陂',
                                '江岸',
                                '江汉',
                                '江夏',
                                '其他',
                                '硚口',
                                '青山',
                                '武昌')
                        )
                        if (currentDistrictListenerIndex == 0) district.getSelectionModel().selectedIndexProperty().removeListener(tjListener)
                        if (currentDistrictListenerIndex == 1) district.getSelectionModel().selectedIndexProperty().removeListener(cdListener)
                        if (currentDistrictListenerIndex == 2) district.getSelectionModel().selectedIndexProperty().removeListener(whListener)
                        if (currentDistrictListenerIndex == 3) district.getSelectionModel().selectedIndexProperty().removeListener(hzListener)
                        if (currentDistrictListenerIndex == 4) district.getSelectionModel().selectedIndexProperty().removeListener(szListener)
                        if (currentDistrictListenerIndex == 5) district.getSelectionModel().selectedIndexProperty().removeListener(cqListener)
                        district.getSelectionModel().selectedIndexProperty().addListener(whListener)
                        currentDistrictListenerIndex = 2
                        break
                // 杭州
                    case 3:
                        condition.setCity(Pattern.HANG_ZHOU)
                        condition.setCityName(Pattern.CNAME_HANG_ZHOU)
                        district.setItems(FXCollections.observableArrayList(
                                '全部',
                                '江干',
                                '拱墅',
                                '西湖',
                                '下沙',
                                '下城',
                                '滨江',
                                '之江',
                                '上城',
                                '余杭',
                                '萧山',
                                '富阳',
                                '桐庐',
                                '临安',
                                '淳安',
                                '建德',
                                '其他')
                        )
                        if (currentDistrictListenerIndex == 0) district.getSelectionModel().selectedIndexProperty().removeListener(tjListener)
                        if (currentDistrictListenerIndex == 1) district.getSelectionModel().selectedIndexProperty().removeListener(cdListener)
                        if (currentDistrictListenerIndex == 2) district.getSelectionModel().selectedIndexProperty().removeListener(whListener)
                        if (currentDistrictListenerIndex == 3) district.getSelectionModel().selectedIndexProperty().removeListener(hzListener)
                        if (currentDistrictListenerIndex == 4) district.getSelectionModel().selectedIndexProperty().removeListener(szListener)
                        if (currentDistrictListenerIndex == 5) district.getSelectionModel().selectedIndexProperty().removeListener(cqListener)
                        district.getSelectionModel().selectedIndexProperty().addListener(hzListener)
                        currentDistrictListenerIndex = 3
                        break
                // 深圳
                    case 4:
                        condition.setCity(Pattern.SHEN_ZHEN);
                        condition.setCityName(Pattern.CNAME_SHEN_ZHEN)
                        district.setItems(FXCollections.observableArrayList(
                                '全部',
                                '龙岗',
                                '龙华新区',
                                '宝安',
                                '南山',
                                '福田',
                                '罗湖',
                                '坪山新区',
                                '光明新区',
                                '盐田',
                                '大鹏新区',
                                '其他')
                        )
                        if (currentDistrictListenerIndex == 0) district.getSelectionModel().selectedIndexProperty().removeListener(tjListener)
                        if (currentDistrictListenerIndex == 1) district.getSelectionModel().selectedIndexProperty().removeListener(cdListener)
                        if (currentDistrictListenerIndex == 3) district.getSelectionModel().selectedIndexProperty().removeListener(hzListener)
                        if (currentDistrictListenerIndex == 4) district.getSelectionModel().selectedIndexProperty().removeListener(szListener)
                        if (currentDistrictListenerIndex == 2) district.getSelectionModel().selectedIndexProperty().removeListener(whListener)
                        if (currentDistrictListenerIndex == 5) district.getSelectionModel().selectedIndexProperty().removeListener(cqListener)
                        district.getSelectionModel().selectedIndexProperty().addListener(szListener)
                        currentDistrictListenerIndex = 4
                        break

                    case 5:
                        condition.setCity(Pattern.CHONG_QING)
                        condition.setCityName(Pattern.CNAME_CHONG_QING)
                        district.setItems(FXCollections.observableArrayList(
                                '全部',
                                '渝中',
                                '江北',
                                '渝北',
                                '南岸',
                                '沙坪坝',
                                '九龙坡',
                                '巴南',
                                '大渡口',
                                '北碚',
                                '其他',
                        )
                        )
                        if (currentDistrictListenerIndex == 0) district.getSelectionModel().selectedIndexProperty().removeListener(tjListener)
                        if (currentDistrictListenerIndex == 1) district.getSelectionModel().selectedIndexProperty().removeListener(cdListener)
                        if (currentDistrictListenerIndex == 2) district.getSelectionModel().selectedIndexProperty().removeListener(whListener)
                        if (currentDistrictListenerIndex == 3) district.getSelectionModel().selectedIndexProperty().removeListener(hzListener)
                        if (currentDistrictListenerIndex == 4) district.getSelectionModel().selectedIndexProperty().removeListener(szListener)
                        if (currentDistrictListenerIndex == 5) district.getSelectionModel().selectedIndexProperty().removeListener(cqListener)
                        district.getSelectionModel().selectedIndexProperty().addListener(cqListener)
                        currentDistrictListenerIndex = 0
                        break
                }
            }
        })
        def resetExcel = new CheckBox('拼接结果')
        def districtLabel = new Label('城区')
        def filterBoxPanel = new HBox()
        filterBoxPanel.setSpacing(15)
        filterBoxPanel.getChildren().addAll(cityLabel, city, districtLabel, district, resetExcel)
        filterBoxPanel.setAlignment(Pos.CENTER)
        // 2. 操作设置
        def confirm = new Button()
        confirm.setText('启动爬虫')
        Task<Integer> task
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            void handle(ActionEvent event) {
                if (!crawling) {
                    ExcelPipeline.resetExcelName()
                    if (!resetExcel.selected){
                        ExcelPipeline.resetExcel()
                        RentInfo.reset()
                        BuildingNumberInfo.reset()
                    }
                    task = new SuccessTask()
                    crawlingThread = new Thread(task).start()
                } else {
                    String info = '\t爬虫正在工作中，请等待本次任务结束后再启动下次任务！'
                    new AlertBox().display("请等待！", info)
                }
            }
        })
        def export = new Button()
        export.setText('导出Excel')
        export.setOnAction(new EventHandler<ActionEvent>() {
            void handle(ActionEvent event) {
                if (crawling) {
                    String info = '\t爬虫正在工作中，Excel导出功能必须等待抓取任务结束后方可使用！\n\t爬虫的处理速度取决于您的网速、电脑性能等诸多因素，所需时间无法准确估计。\n\t由于抓取的内容较多，程序需要更多的时间去处理相关数据，建议您稍后再试。'
                    new AlertBox().display("请等待！", info)
                } else {
                    ExcelPipeline.export()
                }
            }
        })
        def path = new Button()
        path.setText('选择保存路径')
        path.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            void handle(ActionEvent mouseEvent) {
                DirectoryChooserBuilder builder = DirectoryChooserBuilder.create()
                builder.title('选择Excel的保存位置')
                String cwd = System.getProperty('user.dir')
                File file = new File(cwd)
                builder.initialDirectory(file)
                DirectoryChooser chooser = builder.build()
                File chosenDir = chooser.showDialog(primaryStage)
                if (chosenDir == null) return
                String dirPath = chosenDir.getAbsolutePath()
                config.setPath(dirPath)
            }
        })
        def interrupt = new Button()
        interrupt.setText('终止任务')
        interrupt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            void handle(ActionEvent mouseEvent) {
                if (crawlingThread) crawlingThread.stop()
                if (crawling) {
                    crawling = false
                    crawlingThread = null
                }
            }
        })
        def functionBoxPanel = new HBox()
        functionBoxPanel.setAlignment(Pos.CENTER)
        functionBoxPanel.getChildren().addAll(confirm, path, export, interrupt)

        def centerBoxPanel = new VBox()
        centerBoxPanel.setSpacing(10)
        centerBoxPanel.setAlignment(Pos.CENTER)
        centerBoxPanel.getChildren().addAll(filterBoxPanel, functionBoxPanel, tip)
        def root = new BorderPane()
        root.setCenter(centerBoxPanel)

        primaryStage.setTitle(config.getSpiderName())
        primaryStage.setScene(new Scene(root, 550, 175))
        primaryStage.show()
    }

    public static void main(String[] args) {
        launch(PanelView.class, args)
    }

    class TianjinSelectEvent implements ChangeListener<Number> {
        @Override
        void changed(ObservableValue<? extends Number> observableValue, Number s, Number t1) {
            switch (t1) {
                case 0: condition.setDistrict(Pattern.ALL); condition.setOhDistrict(''); break
                case 1: condition.setDistrict(Pattern.TJ_HE_BEI); condition.setOhDistrict('44'); break
                case 2: condition.setDistrict(Pattern.TJ_HE_XI); condition.setOhDistrict('43'); break
                case 3: condition.setDistrict(Pattern.TJ_HE_PING); condition.setOhDistrict('37'); break
                case 4: condition.setDistrict(Pattern.TJ_NAN_KAI); condition.setOhDistrict('41'); break
                case 5: condition.setDistrict(Pattern.TJ_HE_DONG); condition.setOhDistrict('42'); break
                case 6: condition.setDistrict(Pattern.TJ_DONG_LI); condition.setOhDistrict('49'); break
                case 7: condition.setDistrict(Pattern.TJ_BEI_CHEN); condition.setOhDistrict('39'); break
                case 8: condition.setDistrict(Pattern.TJ_XI_QING); condition.setOhDistrict('38'); break
                case 9: condition.setDistrict(Pattern.TJ_JIN_NAN); condition.setOhDistrict('45'); break
                case 10: condition.setDistrict(Pattern.TJ_BIN_HAI_XIN_QU); condition.setOhDistrict('55'); break
                case 11: condition.setDistrict(Pattern.TJ_HONG_QIAO); condition.setOhDistrict('46'); break
                case 12: condition.setDistrict(Pattern.TJ_BAO_DI); condition.setOhDistrict('615'); break
                case 13: condition.setDistrict(Pattern.TJ_JI_ZHOU); condition.setOhDistrict('51'); break
                case 14: condition.setDistrict(Pattern.TJ_NING_HE); condition.setOhDistrict('53'); break
                case 15: condition.setDistrict(Pattern.TJ_JING_HAI); condition.setOhDistrict('54'); break
                case 16: condition.setDistrict(Pattern.TJ_WU_QING); condition.setOhDistrict('52'); break
                case 17: condition.setDistrict(Pattern.TJ_OTHER); condition.setOhDistrict('47'); break
            }
        }
    }

    class ChengduSelectEvent implements ChangeListener<Number> {
        @Override
        void changed(ObservableValue<? extends Number> observableValue, Number s, Number t1) {
            switch (t1) {
                case 0: condition.setDistrict(Pattern.ALL); condition.setOhDistrict(''); break
                case 1: condition.setDistrict(Pattern.CD_CHENG_HUA); condition.setOhDistrict('133'); break
                case 2: condition.setDistrict(Pattern.CD_JIN_JIANG); condition.setOhDistrict('130'); break
                case 3: condition.setDistrict(Pattern.CD_JIN_NIU); condition.setOhDistrict('131'); break
                case 4: condition.setDistrict(Pattern.CD_LONG_QUAN_YI); condition.setOhDistrict('13065'); break
                case 5: condition.setDistrict(Pattern.CD_OTHER); condition.setOhDistrict('136'); break
                case 6: condition.setDistrict(Pattern.CD_QING_BAI_JIANG); condition.setOhDistrict('1157'); break
                case 7: condition.setDistrict(Pattern.CD_XIN_DU); condition.setOhDistrict('138'); break
                case 8: condition.setDistrict(Pattern.CD_QING_YANG); condition.setOhDistrict('129'); break
                case 9: condition.setDistrict(Pattern.CD_WU_HOU); condition.setOhDistrict('132'); break
                case 10: condition.setDistrict(Pattern.CD_WEN_JIANG); condition.setOhDistrict('939'); break
                case 11: condition.setDistrict(Pattern.CD_SHUANG_LIU); condition.setOhDistrict('13066'); break
            }
        }
    }

    class WuhanSelectEvent implements ChangeListener<Number> {
        @Override
        void changed(ObservableValue<? extends Number> observableValue, Number s, Number t1) {
            switch (t1) {
                case 0: condition.setDistrict(Pattern.ALL); condition.setOhDistrict(''); break
                case 1: condition.setDistrict(Pattern.WH_CAI_DIAN); condition.setOhDistrict('1158'); break
                case 2: condition.setDistrict(Pattern.WH_DONG_XI_HU); condition.setOhDistrict('497'); break
                case 3: condition.setDistrict(Pattern.WH_HAN_NAN); condition.setOhDistrict('759'); break
                case 4: condition.setDistrict(Pattern.WH_HAN_YANG); condition.setOhDistrict('493'); break
                case 5: condition.setDistrict(Pattern.WH_HONG_SHAN); condition.setOhDistrict('495'); break
                case 6: condition.setDistrict(Pattern.WH_HUANG_PI); condition.setOhDistrict('651'); break
                case 7: condition.setDistrict(Pattern.WH_JIANG_AN); condition.setOhDistrict('491'); break
                case 8: condition.setDistrict(Pattern.WH_JIANG_HAN); condition.setOhDistrict('490'); break
                case 9: condition.setDistrict(Pattern.WH_JIANG_XIA); condition.setOhDistrict('652'); break
                case 10: condition.setDistrict(Pattern.WH_OTHER); condition.setOhDistrict('13126'); break
                case 11: condition.setDistrict(Pattern.WH_QIAO_KOU); condition.setOhDistrict('492'); break
                case 12: condition.setDistrict(Pattern.WH_QING_SHAN); condition.setOhDistrict('615'); break
                case 13: condition.setDistrict(Pattern.WH_WU_CHANG); condition.setOhDistrict('494'); break
            }
        }
    }

    class HangZhouSelectEvent implements ChangeListener<Number> {
        @Override
        void changed(ObservableValue<? extends Number> observableValue, Number s, Number t1) {
            switch (t1) {
                case 0: condition.setDistrict(Pattern.ALL); condition.setOhDistrict(''); break
                case 1: condition.setDistrict(Pattern.HZ_JIANG_GAN); condition.setOhDistrict('153'); break
                case 2: condition.setDistrict(Pattern.HZ_GONG_SHU); condition.setOhDistrict('152'); break
                case 3: condition.setDistrict(Pattern.HZ_XI_HU); condition.setOhDistrict('151'); break
                case 4: condition.setDistrict(Pattern.HZ_XIA_SHA); condition.setOhDistrict('153'); break
                case 5: condition.setDistrict(Pattern.HZ_XIA_CHENG); condition.setOhDistrict('150'); break
                case 6: condition.setDistrict(Pattern.HZ_BIN_JIANG); condition.setOhDistrict('154'); break
                case 7: condition.setDistrict(Pattern.HZ_ZHI_JIANG); condition.setOhDistrict(''); break
                case 8: condition.setDistrict(Pattern.HZ_SHANG_CHENG); condition.setOhDistrict('149'); break
                case 9: condition.setDistrict(Pattern.HZ_YU_HANG); condition.setOhDistrict('155'); break
                case 10: condition.setDistrict(Pattern.HZ_XIAO_SHAN); condition.setOhDistrict('156'); break
                case 11: condition.setDistrict(Pattern.HZ_FU_YANG); condition.setOhDistrict('157'); break
                case 12: condition.setDistrict(Pattern.HZ_TONG_LU); condition.setOhDistrict('158'); break
                case 13: condition.setDistrict(Pattern.HZ_LIN_AN); condition.setOhDistrict('159'); break
                case 14: condition.setDistrict(Pattern.HZ_CHUN_AN); condition.setOhDistrict('160'); break
                case 15: condition.setDistrict(Pattern.HZ_JIAN_DE); condition.setOhDistrict('161'); break
                case 16: condition.setDistrict(Pattern.HZ_OTHER); condition.setOhDistrict(''); break
            }
        }
    }

    class ShenZhenSelectEvent implements ChangeListener<Number> {
        @Override
        void changed(ObservableValue<? extends Number> observableValue, Number s, Number t1) {
            switch (t1) {
                case 0: condition.setDistrict(Pattern.ALL); condition.setOhDistrict(''); break
                case 1: condition.setDistrict(Pattern.SZ_LONG_GANG); condition.setOhDistrict('90'); break
                case 2: condition.setDistrict(Pattern.SZ_LONG_HUAXINQU); condition.setOhDistrict('13080'); break
                case 3: condition.setDistrict(Pattern.SZ_BAO_AN); condition.setOhDistrict('89'); break
                case 4: condition.setDistrict(Pattern.SZ_NAN_SHAN); condition.setOhDistrict('87'); break
                case 5: condition.setDistrict(Pattern.SZ_FU_TIAN); condition.setOhDistrict('85'); break
                case 6: condition.setDistrict(Pattern.SZ_LUO_HU); condition.setOhDistrict('86'); break
                case 7: condition.setDistrict(Pattern.SZ_PING_SHANXINQU); condition.setOhDistrict('13081'); break
                case 8: condition.setDistrict(Pattern.SZ_GUANG_MINGXINQU); condition.setOhDistrict('13079'); break
                case 9: condition.setDistrict(Pattern.SZ_YAN_TIAN); condition.setOhDistrict('88'); break
                case 10: condition.setDistrict(Pattern.SZ_DA_PENGXINQU); condition.setOhDistrict('13082'); break
                case 11: condition.setDistrict(Pattern.SZ_OTHER); condition.setOhDistrict(''); break
            }
        }
    }

    class ChongQingSelectEvent implements ChangeListener<Number> {
        @Override
        void changed(ObservableValue<? extends Number> observableValue, Number s, Number t1) {
            switch (t1) {
                case 0: condition.setDistrict(Pattern.ALL); condition.setOhDistrict(''); break
                case 1: condition.setDistrict(Pattern.CQ_YU_ZHONG); condition.setOhDistrict('56'); break
                case 2: condition.setDistrict(Pattern.CQ_JIANG_BEI); condition.setOhDistrict('57'); break
                case 3: condition.setDistrict(Pattern.CQ_YU_BEI); condition.setOhDistrict('58'); break
                case 4: condition.setDistrict(Pattern.CQ_NAN_AN); condition.setOhDistrict('59'); break
                case 5: condition.setDistrict(Pattern.CQ_SHA_PINGBA); condition.setOhDistrict('60'); break
                case 6: condition.setDistrict(Pattern.CQ_JIU_LONGPO); condition.setOhDistrict('61'); break
                case 7: condition.setDistrict(Pattern.CQ_BA_NAN); condition.setOhDistrict('64'); break
                case 8: condition.setDistrict(Pattern.CQ_DA_DUKOU); condition.setOhDistrict('62'); break
                case 9: condition.setDistrict(Pattern.CQ_BEI_BEI); condition.setOhDistrict('63'); break
                case 10: condition.setDistrict(Pattern.CQ_OTHER); condition.setOhDistrict('13195'); break
            }
        }
    }

    public class SuccessTask extends Task<Integer> {
        @Override
        protected Integer call() throws Exception {
            crawling = true
            def s = new SpiderService()
            s.crawl()
            crawling = false
            return 1
        }

        @Override
        protected void succeeded() {
            super.succeeded();
            new AlertBox().display("通知", "爬行完毕！")
        }
    }
}