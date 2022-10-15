package com.altice.alticcisequence.controller;

import com.altice.alticcisequence.service.AlticciSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alticci")
public class AlticciSequenceController {

    @Autowired
    AlticciSequenceService alticciSequenceService;

    @PostMapping("/{n}")
    public ResponseEntity<Integer> returnAlticciSequenceValue(@PathVariable("n") Integer number) {
        return alticciSequenceService.calculateAlticciSequenceIndex(number);
    }

}
