package aq.tools.serversim.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class Request {

    @JsonIgnore
    private final HttpServletRequest httpServletRequest;

    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> parameters = new HashMap<>();
    private String method;
    private String body;


    public Request(HttpServletRequest httpServletRequest) throws IOException {
        this.httpServletRequest = httpServletRequest;
        processHeaders(httpServletRequest);
        processBody(httpServletRequest);
        processParams(httpServletRequest);
        method = httpServletRequest.getMethod();
    }

    private void processHeaders(HttpServletRequest request) {
        if (request.getHeaderNames() == null) return;
        for (String headerName : Collections.list(request.getHeaderNames())) {
            headers.put(headerName, request.getHeader(headerName));
        }
    }

    private void processBody(HttpServletRequest request) throws IOException {
        body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
    }

    private void processParams(HttpServletRequest request) {
        for (String paramName : Collections.list(request.getParameterNames())) {
            parameters.put(paramName, request.getParameter(paramName));
        }
    }


}
