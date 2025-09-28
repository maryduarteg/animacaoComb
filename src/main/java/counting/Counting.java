package counting;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import static java.lang.Thread.sleep;

public class Counting extends Application {
    AnchorPane pane;
    Button botao_inicio;
    private Button[] buttons_vetl;
    private Button[] buttons_vetc;
    private Button[] buttons_vets;
    private final Label[] linha = new Label[20];
    private Text[] indices;
    private final Vetor vet_l = new Vetor();
    private Vetor vet_c;
    private final Vetor vet_s = new Vetor();
    private final int t = 350;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Pesquisa e Ordenacao - Counting");
        pane = new AnchorPane();
        pane.setStyle("-fx-background-color: #8b9caf;");

        //Texto
        Text titulo = new Text("Counting Sort");
        titulo.setFont(new Font("Papyrus", 32));
        titulo.setLayoutY(80); titulo.setLayoutX(275);
        pane.getChildren().add(titulo);

        //Botão de inicio
        botao_inicio = new Button();
        botao_inicio.setLayoutX(1175); botao_inicio.setLayoutY(400);
        botao_inicio.setText("Start");
        botao_inicio.setOnAction(e->{
            botao_inicio.setDisable(true);
            principal();
        });
        pane.getChildren().add(botao_inicio);

        //Gerar itens e indices do vetor principal
        vet_l.popularVet();
        texto("l", 65, 175);
        gerarBotoesVetL(0);
        gerarIndices(vet_l, 0);

        //Gerar linhas do codigo
        gerarLinhas();

        Scene scene = new Scene(pane, 1250, 450);
        stage.setScene(scene);
        stage.show();
    }

    public void principal() {
        Task<Void> task = new Task<Void>(){
            @Override
            protected Void call() throws InterruptedException {
                counting();
                return null;
            }

            public void counting() throws InterruptedException {
                gotoLine(0, 1);
                criarVetC();
                criarVetS();
                gotoLine(4, 5);
                execLoop1();
                gotoLine(7, 9);
                execLoop2();
                gotoLine(11, 13);
                execLoop3();
                gotoLine(16, 20);
            }

            public void execLoop3() throws InterruptedException {
                int[] tempVetC = vet_c.getVet();
                int[] tempVetL = vet_l.getVet();
                int[] tempVetS = vet_s.getVet();

                for (int i = 0; i < vet_l.getTL(); i++) {
                    int idx = i;
                    int valor = tempVetL[idx];

                    backgroundL(linha[14], "#c9c5c5");
                    backgroundL(linha[15], "#c9c5c5");
                    sleep(t);

                    Platform.runLater(() -> {
                        backgroundB(buttons_vetl[idx], "#009dff");
                        backgroundB(buttons_vetc[valor], "#009dff");
                    });
                    sleep(t);

                    tempVetC[valor] = tempVetC[valor] - 1;
                    int pos = tempVetC[valor];
                    tempVetS[pos] = valor;

                    Platform.runLater(() -> {
                        buttons_vetc[valor].setText(tempVetC[valor] + "");
                        buttons_vets[pos].setText(valor + "");
                        backgroundB(buttons_vets[pos], "#00ff00");
                    });
                    sleep(t);

                    Platform.runLater(() -> {
                        buttons_vetl[idx].setStyle("");
                        buttons_vetc[valor].setStyle("");
                        buttons_vets[pos].setStyle("");
                    });
                    backgroundL(linha[14], "transparent");
                    backgroundL(linha[15], "transparent");
                    sleep(t);
                }

                vet_c.setVet(tempVetC);
                vet_s.setVet(tempVetS);
            }

            public void execLoop2() throws InterruptedException {
                int[] tempVetC = vet_c.getVet();

                for (int i = 1; i <= vet_l.getMax(); i++) {
                    int idx = i;

                    backgroundL(linha[10], "#c9c5c5");
                    sleep(t);

                    Platform.runLater(() -> {
                        backgroundB(buttons_vetc[idx], "#009dff");
                        backgroundB(buttons_vetc[idx - 1], "#009dff");
                        tempVetC[idx] += tempVetC[idx - 1];
                        buttons_vetc[idx].setText(tempVetC[idx] + "");
                    });
                    sleep(t);

                    Platform.runLater(() -> {buttons_vetc[idx].setStyle("");});
                    Platform.runLater(() -> {buttons_vetc[idx - 1].setStyle("");});
                    backgroundL(linha[10], "transparent");
                    sleep(t);
                }

                vet_c.setVet(tempVetC);
            }

            public void execLoop1() throws InterruptedException {
                int[] tempVetC = vet_c.getVet();
                int[] tempVetL = vet_l.getVet();

                for (int i = 0; i < vet_l.getTL(); i++) {
                    int idx = i; //Precisa pro compilador nao reclamar

                    backgroundL(linha[6], "#c9c5c5");
                    sleep(t);

                    Platform.runLater(() -> {
                        backgroundB(buttons_vetl[idx], "#009dff");
                        backgroundB(buttons_vetc[tempVetL[idx]], "#009dff");
                        tempVetC[tempVetL[idx]] += 1;
                        buttons_vetc[tempVetL[idx]].setText(tempVetC[tempVetL[idx]] + "");
                    });
                    sleep(t);

                    Platform.runLater(() -> buttons_vetl[idx].setStyle(""));
                    Platform.runLater(() -> buttons_vetc[tempVetL[idx]].setStyle(""));
                    backgroundL(linha[6], "transparent");
                    sleep(t);
                }

                vet_c.setVet(tempVetC);
            }

            public void gotoLine(int ini, int fim) throws InterruptedException {
                while (ini <= fim) {
                    backgroundL(linha[ini], "#c9c5c5"); sleep(t);
                    backgroundL(linha[ini], "transparent"); sleep(t);
                    ini++;
                }
            }

            public void criarVetC() throws InterruptedException {
                backgroundL(linha[2], "#c9c5c5"); sleep(t);

                Platform.runLater(() -> {
                    //Gerar itens e indices vetor de contagem
                    vet_c = new Vetor(vet_l.getMax() + 1);
                    texto("c", 65, 275);
                    gerarBotoesVetC(100);
                    gerarIndices(vet_c, 100);
                });

                backgroundL(linha[2], "transparent"); sleep(t);
            }

            public void criarVetS() throws InterruptedException {
                backgroundL(linha[3], "#c9c5c5"); sleep(t);

                Platform.runLater(() -> {
                    //Gerar itens e indices vetor de saida
                    texto("s", 65, 375);
                    gerarBotoesVetS(200);
                    gerarIndices(vet_s, 200);
                });

                backgroundL(linha[3], "transparent"); sleep(t);
            }
        };

        Thread thread = new Thread(task);
        thread.start();
    }

    public void texto(String str, int x, int y) {
        Text tx = new Text(str);
        tx.setLayoutX(x); tx.setLayoutY(y);
        tx.setStyle("-fx-fill: #000000;");
        tx.setFont(new Font("Consolas", 15));
        tx.setVisible(true);
        pane.getChildren().add(tx);
    }

    public void gerarBotoesVetC(int offset) {
        buttons_vetc = new Button[vet_c.getTL()];
        for (int i = 0; i < vet_c.getTL(); i++) {
            buttons_vetc[i] = new Button(vet_c.at(i) + "");
            buttons_vetc[i].setLayoutX(100 + i * 60); buttons_vetc[i].setLayoutY(150 + offset);
            buttons_vetc[i].setMinHeight(40); buttons_vetc[i].setMinWidth(40);
            buttons_vetc[i].setFont(new Font(14));
            pane.getChildren().add(buttons_vetc[i]);
        }
    }

    public void gerarBotoesVetL(int offset) {
        buttons_vetl = new Button[vet_l.getTL()];
        for (int i = 0; i < vet_l.getTL(); i++) {
            buttons_vetl[i] = new Button(vet_l.at(i) + "");
            buttons_vetl[i].setLayoutX(100 + i * 60); buttons_vetl[i].setLayoutY(150 + offset);
            buttons_vetl[i].setMinHeight(40); buttons_vetl[i].setMinWidth(40);
            buttons_vetl[i].setFont(new Font(14));
            pane.getChildren().add(buttons_vetl[i]);
        }
    }

    public void gerarBotoesVetS(int offset) {
        buttons_vets = new Button[vet_s.getTL()];
        for (int i = 0; i < vet_s.getTL(); i++) {
            buttons_vets[i] = new Button(vet_s.at(i) + "");
            buttons_vets[i].setLayoutX(100 + i * 60); buttons_vets[i].setLayoutY(150 + offset);
            buttons_vets[i].setMinHeight(40); buttons_vets[i].setMinWidth(40);
            buttons_vets[i].setFont(new Font(14));
            pane.getChildren().add(buttons_vets[i]);
        }
    }

    public void gerarIndices(Vetor vetAux, int offset) {
        indices = new Text[vetAux.getTL()];
        for (int i = 0; i < vetAux.getTL(); i++) {
            indices[i] = new Text(i + "");
        }
        for (int i = 0; i < vetAux.getTL(); i++) {
            indices[i].setLayoutX(115 + i * 60); indices[i].setLayoutY(210 + offset);
            indices[i].setStyle("-fx-fill: #000000;");
            indices[i].setVisible(true);
            indices[i].setFont(new Font("Consolas", 15));
            pane.getChildren().add(indices[i]);
        }
    }

    public void gerarLinhas() {
        linha[0] = new Label("public static int[] counting(int[] l) {");
        linha[1] = new Label(".\t int m = Arrays.stream(l).max().getAsInt();");
        linha[2] = new Label(".\t int[] c = new int[m + 1];");
        linha[3] = new Label(".\t int[] s = new int[l.length];");
        linha[4] = new Label(".");
        linha[5] = new Label(".\t for(int i = 0; i < l.length; i++) {");
        linha[6] = new Label(".\t .\t c[l[i]] += 1;");
        linha[7] = new Label(".\t }");
        linha[8] = new Label(".");
        linha[9] = new Label(".\t for (int i = 1; i <= m; i++) {");
        linha[10] = new Label(".\t .\t c[i] += c[i - 1];");
        linha[11] = new Label(".\t }");
        linha[12] = new Label(".");
        linha[13] = new Label(".\t for (int i = 0; i < l.length; i++) {");
        linha[14] = new Label(".\t .\t c[l[i]] = c[l[i]] - 1;");
        linha[15] = new Label(".\t .\t s[c[l[i]]] = l[i];");
        linha[16] = new Label(".\t }");
        linha[17] = new Label(".");
        linha[18] = new Label(".\t return s;");
        linha[19] = new Label("}");

        for (int i = 0; i < linha.length; i++) {
            linha[i].setLayoutX(800); linha[i].setLayoutY(30 + i * 20);
            linha[i].setStyle("-fx-fill: #000000;");
            linha[i].setVisible(true);
            linha[i].setFont(new Font("Consolas", 15));
            pane.getChildren().add(linha[i]);
        }
    }

    public void backgroundL(Label lb, String color) {
        Platform.runLater(() -> {
            lb.setStyle("-fx-text-fill: black; -fx-background-color: " + color + "; -fx-padding: 2;");
        });
    }

    public void backgroundB(Button bt, String color) {
        Platform.runLater(() -> {
            bt.setStyle("-fx-text-fill: black; -fx-background-color: " + color + "; -fx-padding: 2;");
        });
    }
}