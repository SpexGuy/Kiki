/**
 * Created by Martin Wickham on 9/9/2015.
 */
public class DuplicateSymException extends Exception {
    public DuplicateSymException() { super(); }
    public DuplicateSymException(String msg) { super(msg); }
    public DuplicateSymException(String msg, Throwable cause) { super(msg, cause); }
    public DuplicateSymException(Throwable cause) { super(cause); }
}
