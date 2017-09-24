package org.testo.core.service;

import org.testo.core.utils.Request;
import org.testo.core.utils.Response;

public interface MicroServiceClient {

	public void process(Request request, Response response);
}
