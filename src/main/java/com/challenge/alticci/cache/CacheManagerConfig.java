package com.challenge.alticci.cache;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@ApplicationScope
@Component
public class CacheManagerConfig {
	
	final Map<Long, BigInteger> cache = new HashMap<>();

	public void save(Long index, BigInteger value) {
		cache.put(index, value);
	}

	public BigInteger get(Long index) {
		return cache.get(
			Optional.ofNullable(index)
			.orElseThrow(() -> new InvalidParameterException("Cannot retrieve index of null"))
		);
	}

    public String checkCacheStr() {
		return cache.entrySet().stream()
			.map(val -> "[ " + val.getKey() + " - " + val.getValue() + " ]")
			.collect(Collectors.joining("\n"));
	}

	@PostConstruct
	void startUp() {
		cache.put(0L, BigInteger.valueOf(0));
		cache.put(1L, BigInteger.valueOf(1));
		cache.put(2L, BigInteger.valueOf(1));
	}

}
