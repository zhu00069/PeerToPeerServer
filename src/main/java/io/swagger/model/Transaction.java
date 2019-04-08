/**
* File: Transaction.java
*
* Creation Date: Mar 2, 2019
* @authors Y. Fu
* 
* Description: transaction model
*/

package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

/**
 * set and get attributes of transaction
 * 
 * @author Y. Fu
 *
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaResteasyEapServerCodegen", date = "2019-02-14T05:43:24.759Z")
public class Transaction {

	// attributes of transaction
	private Integer transactionID = null;
	private Integer userID = null;
	private Integer opportunityID = null;
	private BigDecimal amount = null;
	private String tranType = null;
	private String tranDate = null;
	private String createDate = null;

    /**
     * get transaction id
     * @return transaction id
     */
	@ApiModelProperty(value = "")
	@JsonProperty("transactionID")
	public Integer getTransactionID() {
		return transactionID;
	}

	/**
	 * set transaciton id
	 * @param transactionID transaction id
	 */
	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
	}

    /**
     * get user id
     * @return user id
     */
	@ApiModelProperty(value = "")
	@JsonProperty("userID")
	public Integer getUserID() {
		return userID;
	}

	/**
	 * set user id
	 * @param userID user id
	 */
	public void setUserID(Integer userID) {
		this.userID = userID;
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
     * get transaction amount
     * @return transaction amount
     */
	@ApiModelProperty(value = "")
	@JsonProperty("amount")
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * set transaction amount
	 * @param amount transaciton amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

    /**
     * get transaction type
     * @return transaction type
     */
	@ApiModelProperty(value = "")
	@JsonProperty("tranType")
	public String getTranType() {
		return tranType;
	}

	/**
	 * set transaction type
	 * @param tranType transaction type
	 */
	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

    /**
     * get transaction date
     * @return transaction date
     */
	@ApiModelProperty(value = "")
	@JsonProperty("tranDate")
	public String getTranDate() {
		return tranDate;
	}

	/**
	 * set transaction date
	 * @param tranDate transaction date
	 */
	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

    /**
     * get transaction stamp
     * @return transaction stamp
     */
	@ApiModelProperty(value = "")
	@JsonProperty("createDate")
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * set transaction stamp
	 * @param createDate transaction stamp
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
		Transaction transaction = (Transaction) o;
		return Objects.equals(transactionID, transaction.transactionID) && Objects.equals(userID, transaction.userID)
				&& Objects.equals(opportunityID, transaction.opportunityID)
				&& Objects.equals(amount, transaction.amount) && Objects.equals(tranType, transaction.tranType)
				&& Objects.equals(tranDate, transaction.tranDate) && Objects.equals(createDate, transaction.createDate);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(transactionID, userID, opportunityID, amount, tranType, tranDate, createDate);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Transaction {\n");

		sb.append("    transactionID: ").append(toIndentedString(transactionID)).append("\n");
		sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
		sb.append("    opportunityID: ").append(toIndentedString(opportunityID)).append("\n");
		sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
		sb.append("    tranType: ").append(toIndentedString(tranType)).append("\n");
		sb.append("    tranDate: ").append(toIndentedString(tranDate)).append("\n");
		sb.append("    createDate: ").append(toIndentedString(createDate)).append("\n");
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
