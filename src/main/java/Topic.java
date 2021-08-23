import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Topic {

    private final int capacidade;

    private final int qtdPrioritarios;

    private final int qtdNormais;

    private final List<Passageiro> assentosPrioritarios;

    private final List<Passageiro> assentosNormais;

    private int contadorPrioritarios = 0;

    private int contadorNormais = 0;

    public Topic(int capacidade, int qtdPrioritatios) {
        if(capacidade < qtdPrioritatios)
            throw new IllegalArgumentException();
        this.capacidade = capacidade;
        this.qtdPrioritarios = qtdPrioritatios;
        this.qtdNormais = capacidade - qtdPrioritatios;
        assentosPrioritarios = new ArrayList<>(Collections.nCopies(qtdPrioritarios, null));
        assentosNormais = new ArrayList<>(Collections.nCopies(qtdNormais, null));
    }

    public List<Passageiro> getAssentosPrioritarios() {
        return assentosPrioritarios;
    }
    public List<Passageiro> getAssentosNormais() {
        return assentosNormais;
    }

    public int getNumeroAssentosPrioritarios() {
        return qtdPrioritarios;
    }

    public int getNumeroAssentosNormais() {
        return qtdNormais;
    }

    public Passageiro getPassageiroAssentoNormal(int lugar) {
        return assentosNormais.get(lugar);
    }

    public Passageiro getPassageiroAssentoPrioritario(int lugar) {
        return assentosPrioritarios.get(lugar);
    }

    public int getVagas() {
        return capacidade - (contadorNormais + contadorPrioritarios);
    }

    public boolean subir(Passageiro passageiro) {
        if(capacidade > contadorNormais + contadorPrioritarios) {
            if(passageiro.ePrioritario()) {
                if(contadorPrioritarios < qtdPrioritarios) {
                    assentosPrioritarios.set(contadorPrioritarios++, passageiro);
                } else {
                    assentosNormais.set(contadorNormais++, passageiro);
                }
            } else {
                if (contadorNormais < qtdNormais) {
                    assentosNormais.set(contadorNormais++, passageiro);
                } else {
                    assentosPrioritarios.set(contadorPrioritarios++, passageiro);
                }
            }
            return true;
        }
        else
            return false;
    }
    public boolean descer(String nome) {
        for(int i = 0; i < contadorPrioritarios; i++) {
            if(Objects.equals(assentosPrioritarios.get(i).getNome(), nome)) {
                assentosPrioritarios.remove(i);
                assentosPrioritarios.add(null);
                contadorPrioritarios--;
                return true;
            }
        }
        for(int i = 0; i < contadorNormais; i++) {
            if(Objects.equals(assentosNormais.get(i).getNome(), nome)) {
                assentosNormais.remove(i);
                assentosNormais.add(null);
                contadorNormais--;
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (Passageiro assentoPrioritario : assentosPrioritarios) {
            s.append("@");
            if(assentoPrioritario != null)
                s.append(assentoPrioritario.getNome());
            s.append(" ");
        }
        for (Passageiro assentoNormal : assentosNormais) {
            s.append("=");
            if(assentoNormal != null)
                s.append(assentoNormal.getNome());
            s.append(" ");
        }
        s.append("]");
        return s.toString();
    }
}