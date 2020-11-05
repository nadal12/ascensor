public class Planta {

    private boolean pujar;
    private boolean baixar;
    private int numPlanta;
    private boolean ascensorAturat;

    public Planta(int numPlanta, boolean pujar, boolean baixar,  boolean ascensorAturat) {
        this.pujar = pujar;
        this.baixar = baixar;
        this.numPlanta = numPlanta;
        this.ascensorAturat = ascensorAturat;
    }

    public boolean isPujar() {
        return pujar;
    }

    public void setPujar(boolean pujar) {
        this.pujar = pujar;
    }

    public boolean isBaixar() {
        return baixar;
    }

    public void setBaixar(boolean baixar) {
        this.baixar = baixar;
    }

    public int getNumPlanta() {
        return numPlanta;
    }

    public void setNumPlanta(int numPlanta) {
        this.numPlanta = numPlanta;
    }

    public boolean isAscensorAturat() {
        return ascensorAturat;
    }

    public void setAscensorAturat(boolean ascensorAturat) {
        this.ascensorAturat = ascensorAturat;
    }
}
