/**
* File: User.java
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
import io.swagger.model.Credential;

/**
 * set and get attributes of user
 * @author Ryan
 *
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaResteasyEapServerCodegen", date = "2019-02-14T05:43:24.759Z")
public class User {

	// attributes of user
	private Integer id = null;
	private Credential credential = null;
	private String username = null;
	private String firstName = null;
	private String lastName = null;
	private String dobDate = null;
	private String email = null;
	private String password = null;
	private String phone = null;
	private Integer creditScore = null;
	private String createDate = null;

    /**
     * get user id
     * @return user id
     */
	@ApiModelProperty(value = "")
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	/**
	 * set user id
	 * @param id user id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

    /**
     * get credential id
     * @return credential id
     */
	@ApiModelProperty(value = "")
	@JsonProperty("credential")
	public Credential getCredential() {
		return credential;
	}

	/**
	 * set credential id
	 * @param credential credential id
	 */
	public void setCredential(Credential credential) {
		this.credential = credential;
	}

    /**
     * get user name
     * @return user name
     */
	@ApiModelProperty(value = "")
	@JsonProperty("username")
	public String getUsername() {
		return username;
	}

	/**
	 * set user name
	 * @param username user name
	 */
	public void setUsername(String username) {
		this.username = username;
	}

    /**
     * get  first name
     * @return first name
     */
	@ApiModelProperty(value = "")
	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * set first name
	 * @param firstName first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

    /**
     * get last name
     * @return last name
     */
	@ApiModelProperty(value = "")
	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	/**
	 * set last name
	 * @param lastName last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

    /**
     * get user date of birth
     * @return user date of birth
     */
	@ApiModelProperty(value = "")
	@JsonProperty("dobDate")
	public String getDobDate() {
		return dobDate;
	}

	/**
	 * set date of birth
	 * @param dobDate date of birth
	 */
	public void setDobDate(String dobDate) {
		this.dobDate = dobDate;
	}

    /**
     * get user email
     * @return user email
     */
	@ApiModelProperty(value = "")
	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	/**
	 * set email
	 * @param email email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

    /**
     * get user password
     * @return user password
     */
	@ApiModelProperty(value = "")
	@JsonProperty("password")
	public String getPassword() {
		return password;
	}

	/**
	 * set password
	 * @param password password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

    /**
     * get phone number
     * @return phone number
     */
	@ApiModelProperty(value = "")
	@JsonProperty("phone")
	public String getPhone() {
		return phone;
	}

	/**
	 * set phone number
	 * @param phone phone number
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

    /**
     * get user credit score
     * @return user credit score
     */
	@ApiModelProperty(value = "")
	@JsonProperty("creditScore")
	public Integer getCreditScore() {
		return creditScore;
	}

	/**
	 * set credit score
	 * @param creditScore credit score
	 */
	public void setCreditScore(Integer creditScore) {
		this.creditScore = creditScore;
	}

    /**
     * get stamp
     * @return stamp
     */
	@ApiModelProperty(value = "")
	@JsonProperty("createDate")
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * set stamp
	 * @param createDate stamp
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
		User user = (User) o;
		return Objects.equals(id, user.id) && Objects.equals(credential, user.credential)
				&& Objects.equals(username, user.username) && Objects.equals(firstName, user.firstName)
				&& Objects.equals(lastName, user.lastName) && Objects.equals(dobDate, user.dobDate)
				&& Objects.equals(email, user.email) && Objects.equals(password, user.password)
				&& Objects.equals(phone, user.phone) && Objects.equals(creditScore, user.creditScore)
				&& Objects.equals(createDate, user.createDate);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, credential, username, firstName, lastName, dobDate, email, password, phone, creditScore,
				createDate);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class User {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    credential: ").append(toIndentedString(credential)).append("\n");
		sb.append("    username: ").append(toIndentedString(username)).append("\n");
		sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
		sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
		sb.append("    dobDate: ").append(toIndentedString(dobDate)).append("\n");
		sb.append("    email: ").append(toIndentedString(email)).append("\n");
		sb.append("    password: ").append(toIndentedString(password)).append("\n");
		sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
		sb.append("    creditScore: ").append(toIndentedString(creditScore)).append("\n");
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
