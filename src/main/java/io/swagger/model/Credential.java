/**
* File: Credential.java
*
* Creation Date: Mar 2, 2019
* @authors Y. Fu
* 
* Description: credential model
*/

package io.swagger.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.*;


/**
 * set and get attributes of credential
 * @author Y. Fu
 *
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaResteasyEapServerCodegen", date = "2019-02-14T05:43:24.759Z")
public class Credential extends ArrayList<String>  {
	
	// attributes of credential
	private Integer credentialID = null;
	private String credentialName = null;

    /**
     * get credential id
     * @return credential id
     */
    @ApiModelProperty(value = "")
    @JsonProperty("credentialID")
    public Integer getCredentialID() {
        return credentialID;
    }

    /**set credential id
     * @param credentialID credential id
     */
    public void setCredentialID(Integer credentialID) {
        this.credentialID = credentialID;
    }
    
    /**
     * get credential name
     * @return credential name
     */
    @ApiModelProperty(value = "")
    @JsonProperty("credentialName")
    public String getCredentialName() {
        return credentialName;
    }

    /**
     * set credential name
     * @param credentialName credential name
     */
    public void setCredentialName(String credentialName) {
        this.credentialName = credentialName;
    }
    

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.AbstractList#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Credential credential = (Credential) o;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractList#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash();
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Credential {\n");
		sb.append("    ").append(toIndentedString(super.toString())).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 * @param o object
	 * @return object as string
	 */
	private String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}

