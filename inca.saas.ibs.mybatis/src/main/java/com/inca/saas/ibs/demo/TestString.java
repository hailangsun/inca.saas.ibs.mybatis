package com.inca.saas.ibs.demo;

import java.util.HashMap;
import java.util.Map;

public class TestString {
	public static void main(String[] args) {
		Map<String, Object> invdoc = new HashMap<>();
		invdoc.put("accountset_id", Integer.valueOf(5));
		
		String accountset_id = invdoc.get("accountset_id")+"";
		System.out.println(accountset_id);
		
	}
}
