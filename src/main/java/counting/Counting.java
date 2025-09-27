package counting;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import static java.lang.Math.abs;

public class Counting extends Application {
    AnchorPane pane;
    Button botao_inicio;
    private Button vet[];
    private Text[] linha = new Text[20];
    private Vetor vetInt = new Vetor();

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
        botao_inicio.setLayoutX(10); botao_inicio.setLayoutY(210);
        botao_inicio.setText("Start");
        botao_inicio.setOnAction(e->{ principal(); });
        pane.getChildren().add(botao_inicio);

        //Gerar itens do vetor
        vetInt.popularVet();
        gerarBotoes();

        //Gerar linhas do codigo
        gerarLinhas();

        Scene scene = new Scene(pane, 1200, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void principal() {
        Task<Void> task = new Task<Void>(){
            @Override
            protected Void call() {
                move_botoes(0,6);
                move_botoes(0,1);
                move_botoes(8,3);
                move_botoes(2,6);
                move_botoes(9,4);
                move_botoes(1,7);
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    public void move_botoes(int x, int y) {
        //permutação na tela
        int sub = (y - x), sleep = 25;
        for (int i = 0; i < 10; i++) {
            Platform.runLater(() -> vet[x].setLayoutY(vet[x].getLayoutY() + 5));
            Platform.runLater(() -> vet[y].setLayoutY(vet[y].getLayoutY() - 5));
            try { Thread.sleep(sleep); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        for (int i = 0; i < abs(sub) * 12; i++) {
            int val = sub > 0 ? 1 : -1; //Inverter a direção
            Platform.runLater(() -> vet[x].setLayoutX(vet[x].getLayoutX() + (5 * val)));
            Platform.runLater(() -> vet[y].setLayoutX(vet[y].getLayoutX() - (5 * val)));
            try { Thread.sleep(sleep); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        for (int i = 0; i < 10; i++) {
            Platform.runLater(() -> vet[x].setLayoutY(vet[x].getLayoutY() - 5));
            Platform.runLater(() -> vet[y].setLayoutY(vet[y].getLayoutY() + 5));
            try { Thread.sleep(sleep); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        //permutação na memória
        Button aux = vet[x];
        vet[x] = vet[y];
        vet[y] = aux;
    }

    public void gerarBotoes() {
        vet = new Button[vetInt.getTL()];
        for (int i = 0; i < vetInt.getTL(); i++) {
            vet[i] = new Button(vetInt.at(i));
            vet[i].setLayoutX(100 + i * 60); vet[i].setLayoutY(200);
            vet[i].setMinHeight(40); vet[i].setMinWidth(40);
            vet[i].setFont(new Font(14));
            pane.getChildren().add(vet[i]);
        }
    }

    public void gerarLinhas() {
        linha[0] = new Text("public static int[] counting(int[] l) {");
        linha[1] = new Text(".\t int m = Arrays.stream(l).max().getAsInt();");
        linha[2] = new Text(".\t int[] c = new int[m + 1];");
        linha[3] = new Text(".\t int[] s = new int[l.length];");
        linha[4] = new Text(".");
        linha[5] = new Text(".\t for(int i = 0; i < l.length; i++) {");
        linha[6] = new Text(".\t .\t c[l[i]] += 1;");
        linha[7] = new Text(".\t }");
        linha[8] = new Text(".");
        linha[9] = new Text(".\t for (int i = 1; i <= m; i++) {");
        linha[10] = new Text(".\t .\t c[i] += c[i - 1];");
        linha[11] = new Text(".\t }");
        linha[12] = new Text(".");
        linha[13] = new Text(".\t for (int i = 0; i < l.length; i++) {");
        linha[14] = new Text(".\t .\t c[l[i]] = c[l[i]] - 1;");
        linha[15] = new Text(".\t .\t s[c[l[i]]] = l[i];");
        linha[16] = new Text(".\t }");
        linha[17] = new Text(".");
        linha[18] = new Text(".\t return s;");
        linha[19] = new Text("}");

        for (int i = 0; i < linha.length; i++) {
            linha[i].setLayoutX(700); linha[i].setLayoutY(30 + i * 20);
            linha[i].setStyle("-fx-fill: #000000;");
            linha[i].setVisible(true);
            linha[i].setFont(new Font("Consolas", 15));
            pane.getChildren().add(linha[i]);
        }
    }
}

