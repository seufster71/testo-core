package org.testo.core.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class EnvironmentCache {

	Map<String,String> env = new ConcurrentHashMap<String,String>();
	
	public String getVar(String key) {
		return this.env.get(key);
	}
}
