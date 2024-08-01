package kg.attractor.jobsearch.errors.handler;

import java.util.List;
import java.util.Map;

public class ErrorResponseBody {
    private String error;
    private Map<String, List<String>> reasons;
}
