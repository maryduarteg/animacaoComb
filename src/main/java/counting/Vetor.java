package counting;

import java.util.Arrays;
import java.util.Random;

public class Vetor {
    private int TL = 10;
    private int[] vet;

    public Vetor() {
        vet = new int[TL];
    }

    public Vetor(int TL) {
        this.TL = TL;
        vet = new int[TL];
    }

    public void popularVet() {
        Random rand = new Random();
        for (int i = 0; i < TL; i++) {
            vet[i] = rand.nextInt(10);
        }
    }

    public int getMax() {
        return Arrays.stream(vet).max().getAsInt();
    }

    public int at(int i) {
        return vet[i];
    }

    public void setAt(int i, int valor) {
        vet[i] = valor;
    }

    public int getTL() {
        return TL;
    }

    public void setTL(int TL) {
        this.TL = TL;
    }

    public int[] getVet() {
        return vet;
    }

    public void setVet(int[] vet) {
        this.vet = vet;
    }
}
