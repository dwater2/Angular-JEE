package br.com.desafio.dto;

public class UserDto {

	private String cpf;
	private String name;
	private String email;
	private String situation;
	private Integer profile;
	private String phone;
	private Long role;

	public UserDto() {

	}

	public UserDto(String name, String email, Integer profile, String cpf, String phone, Long role, String situation) {

		this.name = name;
		this.profile = profile;
		this.email = email;
		this.cpf = cpf;
		this.phone = phone;
		this.role = role;
		this.situation = situation;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public Integer getProfile() {
		return profile;
	}

	public void setProfile(Integer profile) {
		this.profile = profile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}

}
