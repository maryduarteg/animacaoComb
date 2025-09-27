package controller;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CombSortController {

    //l para linha e v para coluna
    @FXML
    private Button bt_l1;

    @FXML
    private Button bt_init;

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
    private Label lb_seta1;

    @FXML
    private Label lb_seta2;



    private Button[] valores = new Button[8];
    private Button[] linhas = new Button[12];
    private int vet[] = new int[8];
    Random random = new Random();


    @FXML
    public void iniciar() {
        valores = new Button[]{btv_1, btv_2, btv_3, btv_4, btv_5, btv_6, btv_7, btv_8};
        linhas = new Button[]{bt_l1, bt_l2, bt_l3, bt_l4, bt_l5, bt_l6, bt_l7, bt_l8, bt_l9, bt_l10, bt_l11, bt_l12};
        int aux;
        for(int i = 0; i<8;i++)
        {
            aux = random.nextInt(101);
            valores[i].setText(""+aux);
            vet[i] = aux;
        }

        lb_dist.setText("8");
        lb_a.setText("0");
        mudarFundoBotaoCodigo(2);
    }

    public void mudarFundoBotaoValor(int botao) {
        if (botao >= 1 && botao <= valores.length) {
            int idx = botao - 1;
            Platform.runLater(() -> valores[idx].setStyle("-fx-background-color: #f491ba;"));
        }
    }

    public void normalizarFundoBotaoValor(int botao) {
        if (botao >= 1 && botao <= valores.length) {
            int idx = botao - 1;
            Platform.runLater(() -> valores[idx].setStyle("-fx-background-color: #e8e9eb"));
        }
    }

    public void normalizarLinha(int botao) {
        if (botao >= 1 && botao <= linhas.length) {
            int idx = botao - 1;
            Platform.runLater(() -> linhas[idx].setStyle("-fx-background-color: #e8e9eb"));

        }
    }

    public void mudarFundoBotaoCodigo(int botao) {
        if (botao >= 1 && botao <= linhas.length) {
            int idx = botao - 1;
            Platform.runLater(() -> linhas[idx].setStyle("-fx-background-color: #6faeef;"));
        }
    }

    public void iniciarCombSort() {
        iniciar();
        final int max = valores.length;
        normalizarLinha(2);
        long sleepMs = 100;

        Task<Void> sortTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int dist = max;

                while (dist >= 1) {



                    // linha de debug
                    mudarFundoBotaoCodigo(3);
                    Thread.sleep(sleepMs * 2);
                    normalizarLinha(3);

                    int a = 0;
                    int p = a + dist;

                    // linha de debug
                    mudarFundoBotaoCodigo(4);
                    Thread.sleep(sleepMs * 2);
                    normalizarLinha(4);

                    while (p < max) {
                        mudarFundoBotaoCodigo(5);
                        Thread.sleep(sleepMs * 2);
                        normalizarLinha(5);

                        final int fa = a;
                        final int fp = p;
                        final int d1 = dist;

                        // atualiza labels e setas
                        Platform.runLater(() -> {
                            lb_dist.setText("" + d1);
                            lb_a.setText("" + fa);
                            lb_p.setText("" + fp);
                            lb_seta1.setLayoutX(valores[fa].getLayoutX() + 20);
                            lb_seta2.setLayoutX(valores[fp].getLayoutX() + 20);
                        });

                        mudarFundoBotaoCodigo(6);
                        Thread.sleep(sleepMs * 2);
                        normalizarLinha(6);

                        if (vet[fa] > vet[fp]) {


                            // troca na memória
                            int temp = vet[fa];
                            vet[fa] = vet[fp];
                            vet[fp] = temp;

                            // destaca os botões
                            CountDownLatch highlightLatch = new CountDownLatch(1);
                            Platform.runLater(() -> {
                                mudarFundoBotaoValor(fa + 1);
                                mudarFundoBotaoValor(fp + 1);
                                highlightLatch.countDown();
                            });
                            highlightLatch.await();

                            final double xsA = valores[fa].getLayoutX();
                            final double xsP = valores[fp].getLayoutX();

                            // animação deslizando
                            double distance = xsP - xsA;
                            int steps = 20;
                            double step = distance / steps;

                            for (int s = 0; s < steps; s++) {
                                final double inc = step;
                                Platform.runLater(() -> {
                                    valores[fa].setTranslateX(valores[fa].getTranslateX() + inc);
                                    valores[fp].setTranslateX(valores[fp].getTranslateX() - inc);
                                });
                                Thread.sleep(sleepMs);
                            }

                            // fixa posição e troca no vetor de botões
                            CountDownLatch placeLatch = new CountDownLatch(1);
                            Platform.runLater(() -> {
                                valores[fa].setLayoutX(xsP);
                                valores[fp].setLayoutX(xsA);
                                valores[fa].setTranslateX(0);
                                valores[fp].setTranslateX(0);

                                // troca referências no vetor de botões
                                Button tmpBtn = valores[fa];
                                valores[fa] = valores[fp];
                                valores[fp] = tmpBtn;

                                // remove destaque
                                normalizarFundoBotaoValor(fa + 1);
                                normalizarFundoBotaoValor(fp + 1);

                                placeLatch.countDown();
                            });
                            placeLatch.await();
                        }

                        a++;
                        p = a + dist;

                        // linhas de debug
                        mudarFundoBotaoCodigo(10);
                        Thread.sleep(sleepMs * 2);
                        normalizarLinha(10);

                        mudarFundoBotaoCodigo(11);
                        Thread.sleep(sleepMs * 2);
                        normalizarLinha(11);
                    }

                    dist = (int) (dist / 1.3);

                    // linha de debug
                    mudarFundoBotaoCodigo(12);
                    Thread.sleep(sleepMs * 2);
                    normalizarLinha(12);
                }

                return null;
            }

            @Override
            protected void succeeded() {
                Platform.runLater(() -> bt_init.setDisable(false));
            }

            @Override
            protected void failed() {
                Platform.runLater(() -> bt_init.setDisable(false));
            }

            @Override
            protected void cancelled() {
                Platform.runLater(() -> bt_init.setDisable(false));
            }
        };

        // desabilita botão antes de iniciar
        bt_init.setDisable(true);

        Thread t = new Thread(sortTask);
        t.setDaemon(true);
        t.start();
    }




    @FXML
    public void onIniciar(javafx.event.ActionEvent actionEvent) {
        iniciarCombSort();
    }

    public void onFechar(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}

