package com.altice.alticcisequence.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.altice.alticcisequence.caching.CacheMemoizationManager;
import com.altice.alticcisequence.util.TimerUtils;
import com.altice.alticcisequence.vo.AlticciResponseVO;

@Service
public class AlticciSequenceService {

	@Autowired
	TimerUtils timer;

	@Autowired
	CacheMemoizationManager manager;

	private Long cntSpringCacheCalls = 0L;
	private Long cntMemoCacheCalls = 0L;

    public ResponseEntity<AlticciResponseVO> calculateAlticciSequenceIndex(Long number) {
		validateNumber(number);

		BigInteger sprResult = calculateSequence(number);

		AlticciResponseVO resp = new AlticciResponseVO(timer.timeBreakFormat(), sprResult);
		timer.timeBreakPrint("DONE - CALLED ["+cntSpringCacheCalls+"] TIMES ");

		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

    public ResponseEntity<AlticciResponseVO> calculateAlticciSequenceIndexMemoization(Long number) {
		validateNumber(number);

		BigInteger memoResult = calculateWithMemoization(number);

		AlticciResponseVO resp = new AlticciResponseVO(timer.timeBreakFormat(), memoResult);
		timer.timeBreakPrint("> DONE - ["+cntMemoCacheCalls+"] TIMES ");

		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	private void validateNumber(Long number) {
		timer.start();
		if (isNumberLowerThanZero(number)) {
			createErrorResponse(number, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

    private BigInteger calculateSequence(Long number) {
		cntSpringCacheCalls++;
		if (isNumberEqualThanZero(number)) {
			return BigInteger.valueOf(0);
		}
		if (isNumberLowerThanTwo(number)) {
			return BigInteger.valueOf(1);
		}
		return calculateSequence(number - 3).add(calculateSequence(number - 2));
	}

    private BigInteger calculateWithMemoization(Long number) {
		cntMemoCacheCalls ++;
		BigInteger seqNumber = manager.get(number);
		if(seqNumber == null) {
			seqNumber = calculateWithMemoization(number - 3).add(calculateWithMemoization(number - 2));
			manager.save(number, seqNumber);
		}
		return seqNumber;
	}

	private ResponseEntity<String> createErrorResponse(Long number, HttpStatus status) {
		return new ResponseEntity<>("Invalid number: " + number, status);
	}

	private boolean isNumberLowerThanZero(Long number) {
		return number < 0;
	}

	private boolean isNumberEqualThanZero(Long number) {
		return number == 0;
	}

	private boolean isNumberLowerThanTwo(Long number) {
		return number <= 2;
	}
}
