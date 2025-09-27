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

import static java.lang.Math.abs;
import static java.lang.Thread.sleep;

public class Counting extends Application {
    AnchorPane pane;
    Button botao_inicio;
    private Button[] vetl;
    private Button[] vetc;
    private Button[] vets;
    private final Label[] linha = new Label[20];
    private Text[] indices;
    private final Vetor vet_l = new Vetor();
    private Vetor vet_c;
    private final Vetor vet_s = new Vetor();
    private final int t = 400;

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
        titulo.setLayoutY(80); titulo.setLayoutX(250);
        pane.getChildren().add(titulo);

        //Botão de inicio
        botao_inicio = new Button();
        botao_inicio.setLayoutX(1138); botao_inicio.setLayoutY(385);
        botao_inicio.setText("Start");
        botao_inicio.setOnAction(e->{ principal(); });
        pane.getChildren().add(botao_inicio);

        //Gerar itens e indices do vetor principal
        vet_l.popularVet();
        gerarBotoes(vet_l, vetl,0);
        gerarIndices(vet_l, 0);

        //Gerar linhas do codigo
        gerarLinhas();

        Scene scene = new Scene(pane, 1300, 600);
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
                loopLine(6, vet_l.getTL(), execVetC());
            }

            public Runnable execVetC() throws InterruptedException {
                Platform.runLater(() -> {
                    for (int i = 0; i < vet_c.getTL(); i++) {
                        vet_c.setAt(vet_l.at(i), vet_c.at(i) + 1);
                        vetc[i].setText((vet_c.at(i) + 1) + "");
                    }
                });
                return null;
            }

            public void loopLine(int line, int tl, Runnable r) throws InterruptedException {
                for (int i = 0; i < tl; i++) {
                    background(linha[line], 1); sleep(t);
                    background(linha[line], 0); sleep(t);
                    r.run();
                }
            }

            public void gotoLine(int ini, int fim) throws InterruptedException {
                while (ini <= fim) {
                    background(linha[ini], 1); sleep(t);
                    background(linha[ini], 0); sleep(t);
                    ini++;
                }
            }

            public void criarVetC() throws InterruptedException {
                background(linha[2], 1); sleep(t);

                Platform.runLater(() -> {
                    //Gerar itens e indices vetor de contagem
                    vet_c = new Vetor(vet_l.getMax());
                    gerarBotoes(vet_c, vetc, 100);
                    gerarIndices(vet_c, 100);
                });

                background(linha[2], 0); sleep(t);
            }

            public void criarVetS() throws InterruptedException {
                background(linha[3], 1); sleep(t);

                Platform.runLater(() -> {
                    //Gerar itens e indices vetor de saida
                    gerarBotoes(vet_s, vets, 200);
                    gerarIndices(vet_s, 200);
                });

                background(linha[3], 0); sleep(t);
            }
        };

        Thread thread = new Thread(task);
        thread.start();
    }

    public void move_botoes(int x, int y) {
        //permutação na tela
        int sub = (y - x), sleep = 25;
        indices[x].setVisible(false);
        indices[y].setVisible(false);
        for (int i = 0; i < 10; i++) {
            Platform.runLater(() -> vetl[x].setLayoutY(vetl[x].getLayoutY() + 5));
            Platform.runLater(() -> vetl[y].setLayoutY(vetl[y].getLayoutY() - 5));
            try { sleep(sleep); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        for (int i = 0; i < abs(sub) * 12; i++) {
            int val = sub > 0 ? 1 : -1; //Inverter a direção
            Platform.runLater(() -> vetl[x].setLayoutX(vetl[x].getLayoutX() + (5 * val)));
            Platform.runLater(() -> vetl[y].setLayoutX(vetl[y].getLayoutX() - (5 * val)));
            try { sleep(sleep); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        for (int i = 0; i < 10; i++) {
            Platform.runLater(() -> vetl[x].setLayoutY(vetl[x].getLayoutY() - 5));
            Platform.runLater(() -> vetl[y].setLayoutY(vetl[y].getLayoutY() + 5));
            try { sleep(sleep); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        //permutação na memória
        Button aux = vetl[x];
        vetl[x] = vetl[y];
        vetl[y] = aux;

        indices[x].setVisible(true);
        indices[y].setVisible(true);
    }

    public void gerarBotoes(Vetor vetAux, Button[] vet, int offset) {
        vet = new Button[vetAux.getTL()];
        for (int i = 0; i < vetAux.getTL(); i++) {
            vet[i] = new Button(vetAux.at(i) + "");
            vet[i].setLayoutX(100 + i * 60); vet[i].setLayoutY(200 + offset);
            vet[i].setMinHeight(40); vet[i].setMinWidth(40);
            vet[i].setFont(new Font(14));
            pane.getChildren().add(vet[i]);
        }
    }

    public void gerarIndices(Vetor vetAux, int offset) {
        indices = new Text[vetAux.getTL()];
        for (int i = 0; i < vetAux.getTL(); i++) {
            indices[i] = new Text(i + "");
        }
        for (int i = 0; i < vetAux.getTL(); i++) {
            indices[i].setLayoutX(115 + i * 60); indices[i].setLayoutY(260 + offset);
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

    public void background(Label texto, int codigo) {
        Platform.runLater(() -> {
            switch (codigo) {
                // Padrão
                case 0:
                    texto.setStyle("-fx-text-fill: black; -fx-background-color: transparent; -fx-padding: 2;");
                break;

                // Cinza
                case 1:
                    texto.setStyle("-fx-text-fill: white; -fx-background-color: #c9c5c5; -fx-padding: 2;");
                break;

                default:
                    break;
            }
        });
    }
}

