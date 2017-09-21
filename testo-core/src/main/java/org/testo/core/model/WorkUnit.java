package org.testo.core.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkUnit {

	private String id;
    private String definition;
    private Map<String,Object> params;

    public WorkUnit() {
        //for Spring-Web binding
    }
  /*  @JsonCreator
    public WorkUnit(@JsonProperty("id") String id,
                    @JsonProperty("definition") String definition) {
        this.id = id;
        this.definition = definition;
    }
*/
    @JsonCreator
    public WorkUnit(@JsonProperty("id") String id,
                    @JsonProperty("definition") String definition,
                    @JsonProperty("params") Map<String,Object> params) {
        this.id = id;
        this.definition = definition;
        this.params = params;
    }
    
    public String getId() {
        return id;
    }


    public String getDefinition() {
        return definition;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
    
	public Map<String,Object> getParams() {
		return params;
	}
	public void setParams(Map<String,Object> params) {
		this.params = params;
	}
}
