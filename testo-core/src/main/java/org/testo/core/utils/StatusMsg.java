package org.testo.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * This class holds all messages returned to client
 * Only one element in the HashMap is need, set capacity to 1 the default is 16
 */

public final class StatusMsg {
	
	@SuppressWarnings("unchecked")
	public static void addMsg(Response response, String code, String message) {
		Map<String,String> msg = new HashMap<String,String>(1);
		msg.put(code, message);
		if (response.getParams().containsKey(GlobalConstant.STATUSES)) {
			((List<Map<String, String>>) response.getParams().get(GlobalConstant.STATUSES)).add(msg);
		} else {
			List<Map<String,String>> messages = new ArrayList<Map<String,String>>();
			messages.add(msg);
			response.getParams().put(GlobalConstant.STATUSES, messages);
		}
	}
	
}
