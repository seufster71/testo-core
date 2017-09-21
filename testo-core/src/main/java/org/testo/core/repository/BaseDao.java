package org.testo.core.repository;

import org.testo.core.utils.Request;
import org.testo.core.utils.Response;

public interface BaseDao {

	void item(Request request, Response response);
	void list(Request request, Response response);
	void save(Request request, Response response);
}
