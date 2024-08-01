package kg.attractor.jobsearch.errors;

import java.util.NoSuchElementException;

public class CanNotFindUserException extends NoSuchElementException {
    public CanNotFindUserException() {
    }

    public CanNotFindUserException(String message){
        super(message);
    }
}
