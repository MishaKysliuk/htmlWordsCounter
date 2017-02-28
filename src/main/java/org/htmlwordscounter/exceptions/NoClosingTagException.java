package org.htmlwordscounter.exceptions;

/**
 * Created by MichaelMAC on 26.02.17.
 */
public class NoClosingTagException extends RuntimeException {

    public NoClosingTagException() {
    }

    public NoClosingTagException(String message) {
        super(message);
    }

    public NoClosingTagException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoClosingTagException(Throwable cause) {
        super(cause);
    }

}
