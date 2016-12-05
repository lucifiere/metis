package spider.ui

import javafx.application.Application
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.Label
import javafx.scene.input.MouseEvent
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.DirectoryChooser
import javafx.stage.DirectoryChooserBuilder
import javafx.stage.Stage
import spider.constant.Condition
import spider.constant.Config
import spider.constant.Pattern
import spider.pipeline.ExcelPipeline
import spider.service.SpiderService

/**
 *  Created by Tyler.Wang on 2016/11/20.
 *  Description : panel
 */
class PanelView extends Application {

    private static Config config = Config.getConfig()
    public static Condition condition = Condition.getCondition()

    void start(Stage primaryStage) throws Exception {
        // 1. 搜索过滤设置
        def cityLabel = new Label('城市')
        def city = new ChoiceBox(FXCollections.observableArrayList(
                '天津',
                '成都',
                '武汉')
        )
        def districtLabel = new Label('城区')
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
        district.getSelectionModel().selectedIndexProperty().addListener(new SelectEvent())
        def filterBoxPanel = new HBox()
        filterBoxPanel.setSpacing(15)
        filterBoxPanel.getChildren().addAll(cityLabel, city, districtLabel, district)
        filterBoxPanel.setAlignment(Pos.CENTER)
        // 2. 操作设置
        def confirm = new Button()
        confirm.setText('启动爬虫')
        confirm.setOnMouseClicked(new BootEvent())
        def export = new Button()
        export.setText('导出Excel')
        export.setOnMouseClicked(new ExportEvent())
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
                if(chosenDir == null) return
                String dirPath = chosenDir.getAbsolutePath()
                config.setPath(dirPath)
            }
        })
        def functionBoxPanel = new HBox()
        functionBoxPanel.setAlignment(Pos.CENTER)
        functionBoxPanel.getChildren().addAll(confirm, path, export)

        def centerBoxPanel = new VBox()
        centerBoxPanel.setSpacing(10)
        centerBoxPanel.setAlignment(Pos.CENTER)
        centerBoxPanel.getChildren().addAll(filterBoxPanel, functionBoxPanel)
        def root = new BorderPane()
        root.setCenter(centerBoxPanel)

        primaryStage.setTitle(config.getSpiderName())
        primaryStage.setScene(new Scene(root, 300, 250))
        primaryStage.show()
    }

    public static void main(String[] args) {
        launch(PanelView.class, args)
    }

    class BootEvent implements EventHandler<MouseEvent> {
        @Override
        void handle(MouseEvent mouseEvent) {
            def s = new SpiderService()
            s.crawl()
        }
    }

    class ExportEvent implements EventHandler<MouseEvent> {
        @Override
        void handle(MouseEvent mouseEvent) {
            ExcelPipeline.export()
        }
    }

    class SelectEvent implements ChangeListener<Number> {
        @Override
        void changed(ObservableValue<? extends Number> observableValue, Number s, Number t1) {
            switch (t1) {
                case 0: condition.setDistrict(Pattern.ALL)
                    break
                case 1: condition.setDistrict(Pattern.TJ_HE_BEI)
                    break
                case 2: condition.setDistrict(Pattern.TJ_HE_XI)
                    break
                case 3: condition.setDistrict(Pattern.TJ_HE_PING)
                    break
                case 4: condition.setDistrict(Pattern.TJ_NAN_KAI)
                    break
                case 5: condition.setDistrict(Pattern.TJ_HE_DONG)
                    break
                case 6: condition.setDistrict(Pattern.TJ_DONG_LI)
                    break
                case 7: condition.setDistrict(Pattern.TJ_BEI_CHEN)
                    break
                case 8: condition.setDistrict(Pattern.TJ_XI_QING)
                    break
                case 9: condition.setDistrict(Pattern.TJ_JIN_NAN)
                    break
                case 10: condition.setDistrict(Pattern.TJ_BIN_HAI_XIN_QU)
                    break
                case 11: condition.setDistrict(Pattern.TJ_HONG_QIAO)
                    break
                case 12: condition.setDistrict(Pattern.TJ_BAO_DI)
                    break
                case 13: condition.setDistrict(Pattern.TJ_JI_ZHOU)
                    break
                case 14: condition.setDistrict(Pattern.TJ_NING_HE)
                    break
                case 15: condition.setDistrict(Pattern.TJ_JING_HAI)
                    break
                case 16: condition.setDistrict(Pattern.TJ_WU_QING)
                    break
                case 17: condition.setDistrict(Pattern.OTHER)
                    break
            }
        }
    }

}