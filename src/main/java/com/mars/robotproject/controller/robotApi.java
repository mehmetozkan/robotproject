package com.mars.robotproject.controller;

import com.mars.robotproject.model._inputClass;
import com.mars.robotproject.model._outputClass;
import com.mars.robotproject.util.MyRobotRat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/robot")
public class robotApi {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<_outputClass> getRobotRun(@RequestBody _inputClass input) {
        MyRobotRat sd=new MyRobotRat();
       _outputClass outputClass=sd.Run(input);
        return new ResponseEntity<>(outputClass, HttpStatus.OK);
    }

}
