/**
 * Created by Martin Wickham on 9/9/2015.
 */
public class Sym {
    private String type;

    public Sym(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
