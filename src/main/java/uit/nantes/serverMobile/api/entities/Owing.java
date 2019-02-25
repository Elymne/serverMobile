package uit.nantes.serverMobile.api.entities;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
public class Owing {

    private String id;
    private double owing;

    public Owing(String id, double owing) {
        this.id = id;
        this.owing = owing;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getOwing() {
        return owing;
    }

    public void setOwing(double owing) {
        this.owing = owing;
    }

}
