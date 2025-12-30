import java.util.List;

public class Storia {

    private String titolo;
    private List<String> chiavi;
    private String testo_storia;

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public List<String> getChiavi() {
        return chiavi;
    }

    public void setChiavi(List<String> chiavi) {
        this.chiavi = chiavi;
    }

    public String getTesto_storia() {
        return testo_storia;
    }

    public void setTesto_storia(String testo_storia) {
        this.testo_storia = testo_storia;
    }

    @Override
    public String toString() {
        return (
            "Storia{" +
            "titolo='" +
            titolo +
            '\'' +
            ", chiavi='" +
            chiavi +
            '\'' +
            ", testo_storia='" +
            testo_storia +
            '\''
        );
    }
}
