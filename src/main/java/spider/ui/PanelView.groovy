package spider.ui

import javafx.application.Application
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.input.MouseEvent
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.stage.Stage
import spider.constant.Config
import spider.service.SpiderService

/**
 *  Created by Tyler.Wang on 2016/11/20.
 *  Description : panel
 */
class PanelView extends Application{

    private List todo = []

    void start(Stage primaryStage) throws Exception {
        def aj = new CheckBox(Config.AN_JU_KE)
        aj.setOnMouseClicked(new CheckEvent(aj))
        def lj = new CheckBox(Config.LIAN_JIA)
        lj.setOnMouseClicked(new CheckEvent(lj))
        def zy = new CheckBox(Config.ZHONG_YUAN)
        zy.setOnMouseClicked(new CheckEvent(zy))
        def sf = new CheckBox(Config.SO_FANG)
        sf.setOnMouseClicked(new CheckEvent(sf))

        def checkBoxPanel = new HBox()
        checkBoxPanel.setAlignment(Pos.CENTER)
        checkBoxPanel.getChildren().addAll(aj,lj,zy,sf)
        def confirm = new Button()
        confirm.setText('启动爬虫')
        confirm.setOnMouseClicked(new BootEvent())

        def root = new BorderPane()
        root.setCenter(confirm)
        root.setTop(checkBoxPanel)

        primaryStage.setTitle(Config.SPIDER_NAME)
        primaryStage.setScene(new Scene(root, 300, 250))
        primaryStage.show()
    }

    public static void main(String[] args) {
        launch(PanelView.class, args)
    }

    class BootEvent implements EventHandler<MouseEvent>{
        @Override
        void handle(MouseEvent mouseEvent) {
            def s = new SpiderService()
            s.start(todo)
        }
    }

    class CheckEvent implements EventHandler<MouseEvent>{

        private CheckBox c

        @Override
        void handle(MouseEvent mouseEvent) {
            if(c.isSelected()) todo << c.getText()
        }

        public CheckEvent(CheckBox c){
            this.c = c
        }
    }

}