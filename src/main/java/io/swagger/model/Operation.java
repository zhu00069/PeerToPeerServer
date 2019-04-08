/**
* File: Operation.java
*
* Creation Date: Mar 2, 2019
* @authors Y. Fu
* 
* Description: operation model
*/

package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;


/**
 * set and get attributes of loan
 * 
 * @author Y. Fu
 *
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaResteasyEapServerCodegen", date = "2019-02-14T05:43:24.759Z")

public class Operation {

	// attributes of loan
	private Integer operationID = null;
	private BigDecimal amount = null;
	private BigDecimal amountRepaid = null;
	private String dueDate = null;
	private BigDecimal duePenalty = null;
	private BigDecimal interest = null;
	private String createDate = null;

    /**
     * get loan id
     * @return loan id
     */
	@ApiModelProperty(value = "")
	@JsonProperty("operationID")
	public Integer getOperationID() {
		return operationID;
	}

	/**
	 * set loan id
	 * @param operationID loan id
	 */
	public void setOperationID(Integer operationID) {
		this.operationID = operationID;
	}

    /**
     * get loan amount
     * @return loan amount
     */
	@ApiModelProperty(value = "")
	@JsonProperty("amount")
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * set loan amount
	 * @param amount loan amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

    /**
     * get loan amount repaid
     * @return loan amount repaid
     */
	@ApiModelProperty(value = "")
	@JsonProperty("amountRepaid")
	public BigDecimal getAmountRepaid() {
		return amountRepaid;
	}

	/**
	 * set loan amount repaid
	 * @param amountRepaid loan amount repaid
	 */
	public void setAmountRepaid(BigDecimal amountRepaid) {
		this.amountRepaid = amountRepaid;
	}

    /**
     * get loan due date
     * @return loan due date
     */
	@ApiModelProperty(value = "")
	@JsonProperty("dueDate")
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * set loan due date
	 * @param dueDate loan due date
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

    /**
     * get loan duel penalty
     * @return loan due penalty
     */
	@ApiModelProperty(value = "")
	@JsonProperty("duePenalty")
	public BigDecimal getDuePenalty() {
		return duePenalty;
	}

	/**
	 * set loan due penalty
	 * @param duePenalty loan due penalty
	 */
	public void setDuePenalty(BigDecimal duePenalty) {
		this.duePenalty = duePenalty;
	}

    /**
     * get loan interest
     * @return loan interest
     */
	@ApiModelProperty(value = "")
	@JsonProperty("interest")
	public BigDecimal getInterest() {
		return interest;
	}

	/**
	 * set loan interest
	 * @param interest loan interest
	 */
	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

    /**
     * get loan stamp
     * @return loan stamp
     */
	@ApiModelProperty(value = "")
	@JsonProperty("createDate")
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * set loan stamp
	 * @param createDate loan stamp
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
		Operation operation = (Operation) o;
		return Objects.equals(operationID, operation.operationID) && Objects.equals(amount, operation.amount)
				&& Objects.equals(amountRepaid, operation.amountRepaid) && Objects.equals(dueDate, operation.dueDate)
				&& Objects.equals(duePenalty, operation.duePenalty) && Objects.equals(interest, operation.interest)
				&& Objects.equals(createDate, operation.createDate);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(operationID, amount, amountRepaid, dueDate, duePenalty, interest, createDate);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Operation {\n");

		sb.append("    operationId: ").append(toIndentedString(operationID)).append("\n");
		sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
		sb.append("    amountRepaid: ").append(toIndentedString(amountRepaid)).append("\n");
		sb.append("    dueDate: ").append(toIndentedString(dueDate)).append("\n");
		sb.append("    duePenalty: ").append(toIndentedString(duePenalty)).append("\n");
		sb.append("    interest: ").append(toIndentedString(interest)).append("\n");
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
