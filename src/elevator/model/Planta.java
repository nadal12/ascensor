package elevator.model;

public class Planta {


    private boolean pendent;
    private int numPlanta;
    private boolean ascensorAturat;
    private int dir = 0;

    public Planta(int numPlanta, boolean ascensorAturat, int dir) {

        this.numPlanta = numPlanta;
        this.ascensorAturat = ascensorAturat;
        this.dir = dir;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
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
