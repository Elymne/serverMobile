package uit.nantes.serverMobile.api.pojo;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
public class IdPojo {

    private final String idObject;
    private final String typeObject;

    public IdPojo(String idObject, String typeObject) {
        this.idObject = idObject;
        this.typeObject = typeObject;
    }

    public String getIdObject() {
        return idObject;
    }

    public String getTypeObject() {
        return typeObject;
    }

}
