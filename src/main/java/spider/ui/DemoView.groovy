package spider.ui

import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.StackPane
import javafx.stage.Stage

/**
 *  Created by Tyler.Wang on 2016/11/20.
 *  Description : the demo of javaFX for test
 */
class DemoView extends Application{

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button()
        def text = 'Hello World'
        btn.setText(text)
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(text)
            }
        })

        StackPane root = new StackPane()
        root.getChildren().add(btn)
        Scene scene = new Scene(root, 300, 250)

        primaryStage.setTitle(text)
        primaryStage.setScene(scene)
        primaryStage.show()
    }

    public static void main(String[] args) {
        launch(DemoView.class, args)
    }

}
