import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Martin Wickham on 9/9/2015.
 */
public class SymTable {
    private List<HashMap<String, Sym>> table = new LinkedList<>();

    private HashMap<String, Sym> getLocalMap() throws EmptySymTableException {
        // Use try/catch for efficiency - exception condition is exceedingly rare.
        try {
            return table.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new EmptySymTableException(e);
        }
    }

    public SymTable() {
        table.add(new HashMap<>());
    }

    public void addDecl(String name, Sym sym)
            throws EmptySymTableException, DuplicateSymException {
        if (name == null || sym == null)
            throw new NullPointerException();

        if (getLocalMap().putIfAbsent(name, sym) != null)
            throw new DuplicateSymException("Duplicate Symbol: " + name);
    }

    public void addScope() {
        table.add(0, new HashMap<>());
    }

    public Sym lookupLocal(String name) throws EmptySymTableException {
        return getLocalMap().get(name);
    }

    public Sym lookupGlobal(String name) throws EmptySymTableException {
        if (table.isEmpty()) throw new EmptySymTableException();

        for (HashMap<String, Sym> scope : table) {
            Sym result = scope.get(name);
            if (result != null) return result;
        }
        return null;
    }

    void removeScope() throws EmptySymTableException {
        // Use try/catch for efficiency - exception condition is exceedingly rare.
        try {
            table.remove(0);
        } catch (IndexOutOfBoundsException e) {
            throw new EmptySymTableException(e);
        }
    }

    void print() {
        System.out.println("\nSym Table");
        for (HashMap<String, Sym> scope : table) {
            System.out.println(scope);
        }
        System.out.println();
    }
}
