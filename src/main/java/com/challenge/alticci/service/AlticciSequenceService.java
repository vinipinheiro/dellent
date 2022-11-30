package com.challenge.alticci.service;

import com.challenge.alticci.cache.CacheManagerConfig;
import com.challenge.alticci.dto.AlticciResponseDTO;
import com.challenge.alticci.dto.CacheResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class AlticciSequenceService {

	@Autowired
	CacheManagerConfig manager;

	private Long cntSpringCacheCalls = 0L;
	private Long cntMemoCacheCalls = 0L;

	public ResponseEntity<Object> calculateAlticciSequenceIndex(Long number) {

		if(number >= 0){
			BigInteger result = calculateWithCache(number);
			AlticciResponseDTO resp = new AlticciResponseDTO(result);

			return new ResponseEntity<>(resp, HttpStatus.OK);
		}else{
			return new ResponseEntity<Object>("Value not allowed.", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	private BigInteger calculateWithCache(Long number) {
		cntMemoCacheCalls ++;
		BigInteger seqNumber = manager.get(number);
		if(seqNumber == null) {
			seqNumber = calculateWithCache(number - 3).add(calculateWithCache(number - 2));
			manager.save(number, seqNumber);
		}
		return seqNumber;
	}

	public ResponseEntity<CacheResponseDTO> checkSequenceCache() {
		return new ResponseEntity<>(new CacheResponseDTO(manager.checkCacheStr()), HttpStatus.OK);
	}
}
