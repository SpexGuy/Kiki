/**
 * Created by Martin Wickham on 9/9/2015.
 */
public class Perf {
    public static void main(String[] args) throws EmptySymTableException {
        SymTable table = new SymTable();
        long startTime = System.currentTimeMillis();
        for (int c = 0; c < 10000000; c++) {
            table.addScope();
            table.addScope();
            table.addScope();
            table.addScope();
            table.removeScope();
            table.removeScope();
            table.removeScope();
            table.addScope();
            table.removeScope();
            table.removeScope();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Complete in " + (endTime - startTime) + " ms");
    }
}
