/**
 * 
 */
package com.amitesh.pms.domainobject;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author SG0300747
 *
 */
public class UnlockDoorResponseDo implements PmsDomainObject {
	
	private static final long serialVersionUID = -9209433903143509352L;

	private String response;

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("response", response).toString();
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof UnlockDoorResponseDo)) {
			return false;
		}
		UnlockDoorResponseDo castOther = (UnlockDoorResponseDo) other;
		return new EqualsBuilder().append(response, castOther.response).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(response).toHashCode();
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
