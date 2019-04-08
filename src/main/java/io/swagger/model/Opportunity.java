/**
* File: Opportunity.java
*
* Creation Date: Feb 12, 2019
* @authors Ryan
* 
* Description: user model
*/

package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

/**
 * set and get attributes of opportunity
 * 
 * @author Ryan
 *
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaResteasyEapServerCodegen", date = "2019-02-14T05:43:24.759Z")
public class Opportunity {

	// attributes of opportunity
	private Integer opportunityID = null;
	private Integer borrowerID = null;
	private Integer lenderID = null;
	private Integer operationID = null;
	private String title = null;
	private String description = null;
	private String status = null;
	private BigDecimal fundAmount = null;
	private String fundExpDate = null;
	private String createDate = null;

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
     * get borrower id 
     * @return borrower id 
     */
	@ApiModelProperty(value = "")
	@JsonProperty("borrowerID")
	public Integer getBorrowerID() {
		return borrowerID;
	}

	/**
	 * set borrower id
	 * @param borrowerID borrower id
	 */
	public void setBorrowerID(Integer borrowerID) {
		this.borrowerID = borrowerID;
	}

    /**
     * get lender id
     * @return lender id 
     */
	@ApiModelProperty(value = "")
	@JsonProperty("lenderID")
	public Integer getLenderID() {
		return lenderID;
	}

	/**
	 * set lender id
	 * @param lenderID lender id
	 */
	public void setLenderID(Integer lenderID) {
		this.lenderID = lenderID;
	}

    /**
     * get opportunity title
     * @return opportunity title
     */
	@ApiModelProperty(value = "")
	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	/**
	 * set opportunity title
	 * @param title opportunity title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

    /**
     * get opportunity description
     * @return opportunity description
     */
	@ApiModelProperty(value = "")
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	/**
	 * set opportunity description
	 * @param description opportunity description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

    /**
     * get opportunity status
     * @return opportunity status
     */
	@ApiModelProperty(value = "")
	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	/**
	 * set opportunity status
	 * @param status opportunity status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

    /**
     * get opportunity fund amount
     * @return opportunity fund amount
     */
	@ApiModelProperty(value = "")
	@JsonProperty("fundAmount")
	public BigDecimal getFundAmount() {
		return fundAmount;
	}

	/**
	 * set opportunity fund amount
	 * @param fundAmount opportunity fund amount
	 */
	public void setFundAmount(BigDecimal fundAmount) {
		this.fundAmount = fundAmount;
	}

    /**
     * get opportunity fund expected date
     * @return opportunity fund expected date
     */
	@ApiModelProperty(value = "")
	@JsonProperty("fundExpDate")
	public String getFundExpDate() {
		return fundExpDate;
	}

	/**
	 * set opportunity fund expected date
	 * @param fundExpDate opportunity fund expected date
	 */
	public void setFundExpDate(String fundExpDate) {
		this.fundExpDate = fundExpDate;
	}

    /**
     * get opportunity stamp
     * @return opportunity stamp
     */
	@ApiModelProperty(value = "")
	@JsonProperty("createDate")
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * set opportunity stamp
	 * @param createDate opportunity stamp
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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
		Opportunity opportunity = (Opportunity) o;
		return Objects.equals(opportunityID, opportunity.opportunityID)
				&& Objects.equals(borrowerID, opportunity.borrowerID) && Objects.equals(lenderID, opportunity.lenderID)
				&& Objects.equals(title, opportunity.title) && Objects.equals(description, opportunity.description)
				&& Objects.equals(status, opportunity.status) && Objects.equals(fundAmount, opportunity.fundAmount)
				&& Objects.equals(fundExpDate, opportunity.fundExpDate)
				&& Objects.equals(createDate, opportunity.createDate);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(opportunityID, borrowerID, lenderID, title, description, status, fundAmount, fundExpDate,
				createDate);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Opportunity {\n");

		sb.append("    opportunityID: ").append(toIndentedString(opportunityID)).append("\n");
		sb.append("    borrowerID: ").append(toIndentedString(borrowerID)).append("\n");
		sb.append("    lenderID: ").append(toIndentedString(lenderID)).append("\n");
		sb.append("    operationID: ").append(toIndentedString(operationID)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    fundAmount: ").append(toIndentedString(fundAmount)).append("\n");
		sb.append("    fundExpDate: ").append(toIndentedString(fundExpDate)).append("\n");
		sb.append("    createDate: ").append(toIndentedString(createDate)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 * 
	 * @param o object
	 * @return object as string
	 */
	private String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

	/**
	 * set loan id
	 * @param int1 loan id
	 */
	public void setOperationID(int int1) {
		this.operationID = int1;
	}

    /**
     * get loan id 
     * @return loan id 
     */
	public int getOperationID() {
		return operationID;
	}
}
