package elevator.model;

public class Planta {

    private boolean pujar;
    private boolean baixar;
    private boolean pendent;
    private int numPlanta;
    private boolean ascensorAturat;

    public Planta(int numPlanta, boolean pujar, boolean baixar, boolean ascensorAturat) {
        this.pujar = pujar;
        this.baixar = baixar;
        this.numPlanta = numPlanta;
        this.ascensorAturat = ascensorAturat;
    }

    void obri_portes() throws InterruptedException {
        Thread.sleep(500);
        this.ascensorAturat = true;

    }

    public boolean isPendent() {
        return pendent;
    }

    public void setPendent(boolean pendent) {
        this.pendent = pendent;
    }

    public int getNumPlanta() {
        return numPlanta;
    }

}
