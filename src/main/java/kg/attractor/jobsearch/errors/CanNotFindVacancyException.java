package kg.attractor.jobsearch.errors;

import java.util.NoSuchElementException;

public class CanNotFindVacancyException extends NoSuchElementException {
    public CanNotFindVacancyException() {
    }

    public CanNotFindVacancyException(String message){
        super(message);
    }
}
