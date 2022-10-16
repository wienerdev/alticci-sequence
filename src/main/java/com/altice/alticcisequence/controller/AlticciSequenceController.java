package com.altice.alticcisequence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altice.alticcisequence.caching.CacheMemoizationManager;
import com.altice.alticcisequence.service.AlticciSequenceService;
import com.altice.alticcisequence.vo.AlticciResponseVO;

@RestController
@RequestMapping("/alticci")
public class AlticciSequenceController {

	@Autowired
	AlticciSequenceService alticciSequenceService;

	@Autowired
	CacheMemoizationManager cacheManager;

	@Cacheable("sequence-value")
	@GetMapping("/springCache/{n}")
	public ResponseEntity<AlticciResponseVO> returnAlticciSequenceValue(@PathVariable("n") Long number) {
		return alticciSequenceService.calculateAlticciSequenceIndex(number);
	}

	@GetMapping("/memoizationCache/{n}")
	public ResponseEntity<AlticciResponseVO> returnAlticciSequenceValueMemoized(@PathVariable("n") Long number) {
		return alticciSequenceService.calculateAlticciSequenceIndexMemoization(number);
	}

	@GetMapping("/checkMemoCache")
	public ResponseEntity<String> checkMemoCache() {
		return new ResponseEntity<String>(cacheManager.checkCacheStr(), HttpStatus.OK);
	}

    @PostMapping("/clearMemoCache")
    public ResponseEntity<String> clearCache() {
        return new ResponseEntity<String>(cacheManager.clearCache(), HttpStatus.OK);
    }
}
