/**
 * Created by Martin Wickham on 9/13/2015.
 */
public class P1 {
    public static void main(String[] args) throws EmptySymTableException, DuplicateSymException {
        Sym sym = new Sym("Test");
        assert(sym.getType().equals("Test"));
        assert(sym.toString().equals("Test"));

        // Make sure a new sym table has one scope
        SymTable table = new SymTable();
        table.removeScope();
        try {
            table.removeScope();
            assert(false);
        } catch (EmptySymTableException e) {} // this is expected
        try {
            table.addDecl("Test", sym);
            assert(false);
        } catch (EmptySymTableException e) {} // this is expected


        table = new SymTable();
        table.addDecl("Foo", new Sym("Bar"));
        table.addDecl("Baz", new Sym("Quux"));
        try {
            table.addDecl("Foo", new Sym("Baz"));
            assert(false);
        } catch (DuplicateSymException e) {}
        assert(table.lookupLocal("Foo").getType().equals("Bar"));
        assert(table.lookupGlobal("Baz").getType().equals("Quux"));
        table.addScope();
        table.addDecl("Foo", new Sym("Quuux"));
        assert(table.lookupLocal("Baz") == null);
        assert(table.lookupGlobal("Baz").getType().equals("Quux"));
        assert(table.lookupLocal("Foo").getType().equals("Quuux"));
        assert(table.lookupGlobal("Foo").getType().equals("Quuux"));
        table.print();
        table.removeScope();
        assert(table.lookupGlobal("Foo").getType().equals("Bar"));
        assert(table.lookupLocal("Baz").getType().equals("Quux"));
        System.out.println("All tests passed!");
    }
}
