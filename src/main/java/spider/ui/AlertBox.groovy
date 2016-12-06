package spider.ui

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.stage.Modality
import javafx.stage.Stage

/**
 * Created by Tyler.Wang on 2016/12/6.
 */
class AlertBox {
    
    public void display(String title, String message) {
        Stage window = new Stage()
        window.setTitle(title)
        //modality要使用Modality.APPLICATION_MODEL
        window.initModality(Modality.APPLICATION_MODAL)
        window.setMinWidth(350)
        window.setMinHeight(170)

        Button button = new Button("Close the window")
        button.setOnAction(new EventHandler<ActionEvent>() {
            void handle(ActionEvent event) {
                window.close()
            }
        })

        Label label = new Label(message)
        VBox layout = new VBox(10)
        layout.getChildren().addAll(label, button)
        layout.setAlignment(Pos.CENTER)
        Scene scene = new Scene(layout)
        window.setScene(scene)
        //使用showAndWait()先处理这个窗口，而如果不处理，main中的那个窗口不能响应
        window.showAndWait()
    }
}
