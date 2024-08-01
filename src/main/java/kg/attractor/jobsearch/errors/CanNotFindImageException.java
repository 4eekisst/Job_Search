package kg.attractor.jobsearch.errors;

import java.util.NoSuchElementException;

public class CanNotFindImageException extends NoSuchElementException {
    public CanNotFindImageException() {
    }

    public CanNotFindImageException(String message){
        super(message);
    }
}
