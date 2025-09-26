package controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.awt.event.ActionEvent;
import java.util.Random;

public class CombSortController {

    //l para linha e v para coluna
    @FXML
    private Button bt_l1;

    @FXML
    private Button bt_l10;

    @FXML
    private Button bt_l11;

    @FXML
    private Button bt_l12;

    @FXML
    private Button bt_l13;

    @FXML
    private Button bt_l14;

    @FXML
    private Button bt_l15;

    @FXML
    private Button bt_l16;

    @FXML
    private Button bt_l2;

    @FXML
    private Button bt_l3;

    @FXML
    private Button bt_l4;

    @FXML
    private Button bt_l5;

    @FXML
    private Button bt_l6;

    @FXML
    private Button bt_l7;

    @FXML
    private Button bt_l8;

    @FXML
    private Button bt_l9;

    @FXML
    private Button btv_1;

    @FXML
    private Button btv_2;

    @FXML
    private Button btv_3;

    @FXML
    private Button btv_4;

    @FXML
    private Button btv_5;

    @FXML
    private Button btv_6;

    @FXML
    private Button btv_7;

    @FXML
    private Button btv_8;

    @FXML
    private Label lb_a;

    @FXML
    private Label lb_dist;

    @FXML
    private Label lb_p;

    @FXML
    private Label lb_temp;

    @FXML
    private Label lb_vl_dist;

    @FXML
    void onIniciar(ActionEvent event) {
        iniciarCombSort();
    }

    private Button[] valores = new Button[8];
    Random random = new Random();
    int a, p, max, dist, finalA, finalP;
    String temp;
    double aux;

    @FXML
    public void initialize() {
        valores = new Button[]{btv_1, btv_2, btv_3, btv_4, btv_5, btv_6, btv_7, btv_8};
        for(int i = 0; i<8;i++)
            valores[i].setText(""+random.nextInt(101));
        lb_dist.setText("8");
        lb_a.setText("0");
        mudarFundoBotaoCodigo(2);
    }

    public void mudarFundoBotaoValor(int botao)
    {
        switch(botao)
        {
            case 1: btv_1.setStyle("fx-background-color: #f491ba");break;
            case 2: btv_2.setStyle("fx-background-color: #f491ba");break;
            case 3: btv_3.setStyle("fx-background-color: #f491ba");break;
            case 4: btv_4.setStyle("fx-background-color: #f491ba");break;
            case 5: btv_5.setStyle("fx-background-color: #f491ba");break;
            case 6: btv_6.setStyle("fx-background-color: #f491ba");break;
            case 7: btv_7.setStyle("fx-background-color: #f491ba");break;
            case 8: btv_8.setStyle("fx-background-color: #f491ba");break;
        }
    }
    public void normalizarFundoBotaoValor(int botao)
    {
        switch(botao)
        {
            case 1: btv_1.setStyle("");break;
            case 2: btv_2.setStyle("");break;
            case 3: btv_3.setStyle("");break;
            case 4: btv_4.setStyle("");break;
            case 5: btv_5.setStyle("");break;
            case 6: btv_6.setStyle("");break;
            case 7: btv_7.setStyle("");break;
            case 8: btv_8.setStyle("");break;
        }
    }

    public void mudarFundoBotaoCodigo(int linha)
    {
        switch (linha)
        {
            case 1: bt_l1.setStyle("-fx-background-color: #6faeef;"); break;
            case 2: bt_l2.setStyle("-fx-background-color: #6faeef;"); break;
            case 3: bt_l3.setStyle("-fx-background-color: #6faeef;"); break;
            case 4: bt_l4.setStyle("-fx-background-color: #6faeef;"); break;
            case 5: bt_l5.setStyle("-fx-background-color: #6faeef;"); break;
            case 6: bt_l6.setStyle("-fx-background-color: #6faeef;"); break;
            case 7: bt_l7.setStyle("-fx-background-color: #6faeef;"); break;
            case 8: bt_l8.setStyle("-fx-background-color: #6faeef;"); break;
            case 9: bt_l9.setStyle("-fx-background-color: #6faeef;"); break;
            case 10: bt_l10.setStyle("-fx-background-color: #6faeef;"); break;
            case 11: bt_l11.setStyle("-fx-background-color: #6faeef;"); break;
            case 12: bt_l12.setStyle("-fx-background-color: #6faeef;"); break;
            case 13: bt_l13.setStyle("-fx-background-color: #6faeef;"); break;
            case 14: bt_l4.setStyle("-fx-background-color: #6faeef;"); break;
            case 15: bt_l15.setStyle("-fx-background-color: #6faeef;"); break;
            case 16: bt_l16.setStyle("-fx-background-color: #6faeef;"); break;
        }
    }

    public void normalizarFundoBotaoCodigo(int linha)
    {
        switch (linha)
        {
            case 1: bt_l1.setStyle(""); break;
            case 2: bt_l2.setStyle(""); break;
            case 3: bt_l3.setStyle(""); break;
            case 4: bt_l4.setStyle(""); break;
            case 5: bt_l5.setStyle(""); break;
            case 6: bt_l6.setStyle(""); break;
            case 7: bt_l7.setStyle(""); break;
            case 8: bt_l8.setStyle(""); break;
            case 9: bt_l9.setStyle(""); break;
            case 10: bt_l10.setStyle(""); break;
            case 11: bt_l11.setStyle(""); break;
            case 12: bt_l12.setStyle(""); break;
            case 13: bt_l13.setStyle(""); break;
            case 14: bt_l4.setStyle(""); break;
            case 15: bt_l15.setStyle(""); break;
            case 16: bt_l16.setStyle(""); break;
        }
    }


    public void iniciarCombSort() {
        initialize();
        a = 0;
        max = 8;
        dist = (int)(max / 1.3);
        p = dist;

        while (dist > 1) {
            while (p < max) {
                finalA = a;
                finalP = p;
                if (Integer.parseInt(valores[a].getText()) > Integer.parseInt(valores[p].getText())) {
                    aux = valores[a].getLayoutY();

                    Task<Void> task = new Task<Void>() {
                        protected Void call() {
                            double targetA = valores[finalP].getLayoutY();
                            double targetP = aux;

                            // animação passo a passo
                            while (valores[finalA].getLayoutY() < targetA || valores[finalP].getLayoutY() > targetP) {
                                Platform.runLater(() -> {
                                    if (valores[finalA].getLayoutY() < targetA) {
                                        valores[finalA].setLayoutY(valores[finalA].getLayoutY() + 2);
                                    }
                                    if (valores[finalP].getLayoutY() > targetP) {
                                        valores[finalP].setLayoutY(valores[finalP].getLayoutY() - 2);
                                    }
                                });

                                try {
                                    Thread.sleep(10); // velocidade da animação
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                            // troca os valores após a animação
                            String temp = valores[finalA].getText();
                            valores[finalA].setText(valores[finalP].getText());
                            valores[finalP].setText(temp);

                            return null;
                        }
                    };

                    Thread thread = new Thread(task);
                    thread.start();
                }

                a++;
                p = a + dist;
            }
            dist = (int)(dist / 1.3);
        }
    }

    public void onIniciar(javafx.event.ActionEvent actionEvent) {
    }
}

