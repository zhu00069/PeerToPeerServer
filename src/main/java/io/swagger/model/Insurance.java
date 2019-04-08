/**
* File: Insurance.java
*
* Creation Date: Mar 2, 2019
* @authors Y. Fu
* 
* Description: insurance model
*/

package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;


/**
 * set and get attributes of insurance
 * @author Y. Fu
 *
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaResteasyEapServerCodegen", date = "2019-02-14T05:43:24.759Z")
public class Insurance   {
  
	// attributes of insurance
	private Integer insuranceID = null;
	private Integer opportunityID = null;
	private String insuranceNo = null;
	private String insuranceCompany = null;
	private BigDecimal insuranceAmount = null;
	private String effectiveDate = null;

    /**
     * get insurance id
     * @return insurance id
     */
	@ApiModelProperty(value = "")
	@JsonProperty("insurance")
	public Integer getInsuranceID() {
		return insuranceID;
	}

	/**
	 * set insurance id
	 * @param insuranceID insurance id
	 */
	public void setInsuranceID(Integer insuranceID) {
		this.insuranceID = insuranceID;
	}

    /**
     * get opportunity id
     * @return opportunity id
     */
	@ApiModelProperty(value = "")
	@JsonProperty("opportunityID")
	public Integer getOpportunityID() {
		return opportunityID;
	}

	/**
	 * set opportunity id
	 * @param opportunityID opportunity id
	 */
	public void setOpportunityID(Integer opportunityID) {
		this.opportunityID = opportunityID;
	}

    /**
     * get insurance no
     * @return insurance no
     */
	@ApiModelProperty(value = "")
	@JsonProperty("insuranceNo")
	public String getInsuranceNo() {
		return insuranceNo;
	}

	/**
	 * set insurance no
	 * @param insuranceNo insurance no
	 */
	public void setInsuranceNo(String insuranceNo) {
		this.insuranceNo = insuranceNo;
	}

    /**
     * get insurance company
     * @return insurance company
     */
	@ApiModelProperty(value = "")
	@JsonProperty("insuranceCompany")
	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	/**
	 * set insurance company
	 * @param insuranceCompany insurance company
	 */
	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

    /**
     * get insurance amount
     * @return insurance amount
     */
	@ApiModelProperty(value = "")
	@JsonProperty("insuranceAmount")
	public BigDecimal getInsuranceAmount() {
		return insuranceAmount;
	}

	/**
	 * set insurance amount
	 * @param insuranceAmount insurance amount
	 */
	public void setInsuranceAmount(BigDecimal insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}

    /**
     * get insurance effective date
     * @return insurance effective date
     */
	@ApiModelProperty(value = "")
	@JsonProperty("effectiveDate")
	public String getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * set insurance effective date
	 * @param effectiveDate insurance effective date
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Insurance insurance = (Insurance) o;
		return Objects.equals(insuranceID, insurance.insuranceID)
				&& Objects.equals(opportunityID, insurance.opportunityID)
				&& Objects.equals(insuranceCompany, insurance.insuranceCompany)
				&& Objects.equals(insuranceAmount, insurance.insuranceAmount)
				&& Objects.equals(effectiveDate, insurance.effectiveDate);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(insuranceID, opportunityID, insuranceCompany, insuranceAmount, effectiveDate);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Insurance {\n");

		sb.append("    insuranceID: ").append(toIndentedString(insuranceID)).append("\n");
		sb.append("    opportunityID: ").append(toIndentedString(opportunityID)).append("\n");
		sb.append("    insuranceCompany: ").append(toIndentedString(insuranceCompany)).append("\n");
		sb.append("    insuranceAmount: ").append(toIndentedString(insuranceAmount)).append("\n");
		sb.append("    effectiveDate: ").append(toIndentedString(effectiveDate)).append("\n");
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

