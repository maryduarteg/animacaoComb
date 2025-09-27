package controller;

import counting.Counting;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {


    public void onCombSort(ActionEvent actionEvent) throws IOException
    {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/combSort-View.fxml"));
        Stage novaStage = new Stage();
        novaStage.setScene(new Scene(fxmlLoader.load()));
        novaStage.setTitle("CombSort");
        novaStage.show();
    }


    public void onCountingSort(ActionEvent actionEvent) throws Exception {
        Counting counting = new Counting();
        counting.start(new Stage());
    }

    public void onSair(ActionEvent actionEvent) {
        Platform.exit();
    }
}
