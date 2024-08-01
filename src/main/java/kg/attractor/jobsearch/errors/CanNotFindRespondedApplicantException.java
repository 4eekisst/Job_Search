package kg.attractor.jobsearch.errors;

import java.util.NoSuchElementException;

public class CanNotFindRespondedApplicantException extends NoSuchElementException {
    public CanNotFindRespondedApplicantException() {
    }

    public CanNotFindRespondedApplicantException(String message){
        super(message);
    }
}
