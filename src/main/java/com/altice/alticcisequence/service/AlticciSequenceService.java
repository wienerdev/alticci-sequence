package com.altice.alticcisequence.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AlticciSequenceService {

    public ResponseEntity<Integer> calculateAlticciSequenceIndex(Integer number) {
        if (isNumberLowerThanZero(number)) {
            creationErrorResponse(number, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(calculateSequence(number), HttpStatus.OK);
    }

    private ResponseEntity<String> creationErrorResponse(Integer number, HttpStatus status) {
        return new ResponseEntity<>("Invalid number: " + number, status);
    }

    private Integer calculateSequence(Integer number) {
        if (isNumberEqualThanZero(number)) {
            return 0;
        }
        if (isNumberLowerThanTwo(number)) {
            return 1;
        }
        return calculateSequence(number - 3) + calculateSequence(number - 2);
    }

    private boolean isNumberLowerThanZero(Integer number) {
        return number < 0;
    }

    private boolean isNumberEqualThanZero(Integer number) {
        return number == 0;
    }

    private boolean isNumberLowerThanTwo(Integer number) {
        return number <= 2;
    }
}
