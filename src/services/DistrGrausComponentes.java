package services;

public class DistrGrausComponentes {
    private int grauComponente;
    private int quantidadeComponentes = 1;

    //cria uma representação da componente com o grau recebido
    public DistrGrausComponentes(int grauComponente) {
        this.grauComponente = grauComponente;
    }

    public int getGrauComponente() {
        return grauComponente;
    }

    public void setGrauComponentes(int grauComponente) {
        this.grauComponente = grauComponente;
    }

    public int getQuantidadeEncontros() {
        return quantidadeComponentes;
    }
    
    public void incrementaQuantComponentes(){
        this.quantidadeComponentes++;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.grauComponente;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DistrGrausComponentes other = (DistrGrausComponentes) obj;
        if (this.grauComponente != other.grauComponente) {
            return false;
        }
        return true;
    }    
}
