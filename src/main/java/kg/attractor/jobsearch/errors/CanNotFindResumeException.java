package kg.attractor.jobsearch.errors;

import java.util.NoSuchElementException;

public class CanNotFindResumeException extends NoSuchElementException {
    public CanNotFindResumeException() {
    }

    public CanNotFindResumeException(String message){
        super(message);
    }
}
