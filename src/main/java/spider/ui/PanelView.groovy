package spider.ui

import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.input.MouseEvent
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import spider.constant.Config
import spider.service.SpiderService

/**
 *  Created by Tyler.Wang on 2016/11/20.
 *  Description : panel
 */
class PanelView extends Application{

    void start(Stage primaryStage) throws Exception {
        def confirm = new Button()
        confirm.setText('启动爬虫')
        confirm.setOnMouseClicked(new BootEvent())

        StackPane root = new StackPane()
        root.getChildren().add(confirm)
        Scene scene = new Scene(root, 300, 250)
        primaryStage.setTitle(Config.SPIDER_NAME)
        primaryStage.setScene(scene)
        primaryStage.show()
    }

    public static void main(String[] args) {
        launch(PanelView.class, args)
    }

    class BootEvent implements EventHandler<MouseEvent>{
        @Override
        void handle(MouseEvent mouseEvent) {
            def s = new SpiderService()
            s.start()
        }
    }

}