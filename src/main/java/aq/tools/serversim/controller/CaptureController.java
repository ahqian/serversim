package aq.tools.serversim.controller;

import aq.tools.serversim.dto.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/capture")
public class CaptureController {


    @RequestMapping(value = "/reflect", method = {RequestMethod.GET})
    public ResponseEntity<Request> reflectRequest(HttpServletRequest request) {
        try {
            return new ResponseEntity<>(new Request(request), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

