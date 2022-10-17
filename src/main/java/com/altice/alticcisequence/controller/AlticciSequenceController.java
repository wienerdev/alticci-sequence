package com.altice.alticcisequence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altice.alticcisequence.caching.CacheMemoizationManager;
import com.altice.alticcisequence.service.AlticciSequenceService;
import com.altice.alticcisequence.vo.AlticciResponseDTO;
import com.altice.alticcisequence.vo.CacheResponseDTO;

@RestController
@RequestMapping("/alticci")
public class AlticciSequenceController {

	@Autowired
	AlticciSequenceService alticciSequenceService;

	@Autowired
	CacheMemoizationManager cacheManager;

	@CrossOrigin
	@Cacheable("sequence-value")
	@GetMapping("/springCache/{n}")
	public ResponseEntity<AlticciResponseDTO> returnAlticciSequenceValue(@PathVariable("n") Long number) {
		return alticciSequenceService.calculateAlticciSequenceIndex(number);
	}

	@CrossOrigin
	@GetMapping("/memoizationCache/{n}")
	public ResponseEntity<AlticciResponseDTO> returnAlticciSequenceValueMemoized(@PathVariable("n") Long number) {
		return alticciSequenceService.calculateAlticciSequenceIndexMemoization(number);
	}

	@CrossOrigin
	@GetMapping("/checkMemoCache")
	public ResponseEntity<CacheResponseDTO> checkMemoCache() {
		return alticciSequenceService.checkSequenceCache();
	}

    @CrossOrigin
    @PostMapping("/clearMemoCache")
    public ResponseEntity<CacheResponseDTO> clearCache() {
		return alticciSequenceService.deleteSequenceCache();
	}
}
