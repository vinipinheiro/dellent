package com.challenge.alticci.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.alticci.service.AlticciSequenceService;
import com.challenge.alticci.dto.CacheResponseDTO;

@RestController
@RequestMapping("/alticci")
@Api(tags = "Parameters of index number for Alticci Sequence.",
		value = "Controller Rest to calculate Alticci Sequence.")
public class AlticciSequenceController {

	@Autowired
	AlticciSequenceService alticciSequenceService;

	@ApiOperation(value = "Calculate Alticci", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucess"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Cacheable("seq-memoization")
	@GetMapping("/{n}")
	public ResponseEntity<Object> returnAlticciSequenceValue(
			@ApiParam(name = "n", required = true, value = "Sequence Index", example = "1", type = "Long")
			@PathVariable(value = "n", required = true) Long n) {
		return alticciSequenceService.calculateAlticciSequenceIndex(n);
	}

	@ApiOperation(value = "Show values of cache", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucess")})
	@GetMapping("/checkMemoCache")
	public ResponseEntity<CacheResponseDTO> checkMemoCache() {
		return alticciSequenceService.checkSequenceCache();
	}
}
