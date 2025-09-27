package counting;

import java.util.Random;

public class Vetor {
    private int TL = 10;
    private int[] vet = new int[TL];

    public Vetor() {}

    public void popularVet() {
        Random rand = new Random();
        for (int i = 0; i < TL; i++) {
            vet[i] = rand.nextInt(50);
        }
    }

    public String at(int i) {
        return vet[i] + "";
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
