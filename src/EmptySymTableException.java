/**
 * Created by Martin Wickham on 9/9/2015.
 */
public class EmptySymTableException extends Exception {
    public EmptySymTableException() { super(); }
    public EmptySymTableException(String msg) { super(msg); }
    public EmptySymTableException(String msg, Throwable cause) { super(msg, cause); }
    public EmptySymTableException(Throwable cause) { super(cause); }
}
