package br.com.desafio.models;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="usuario_externo")
@Access(AccessType.PROPERTY)
public class User implements Serializable {
    
	private static final long serialVersionUID = 3826118552917265621L;

    private String cpf;
    private String name;
    private String email;
    private String situation;
    private Integer profile;
    private String phone;
    private Role role;
    
    public User() {
    
    }
    
    public User(String name, String email, Integer profile,
                    String cpf, String phone, Role role, String situation) {
                    
        this.name = name;
        this.profile = profile;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.role = role;
        this.situation = situation;
    }
    
    @Id
    @Column(name="nu_cpf")
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    @Column(name="no_usuario", length=60, nullable=false)
    @NotEmpty
    @Size(min=2)
    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    @Column(name="de_email", length=255, nullable=false)
    @NotEmpty
    @Size(min=8)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="nu_telefone", length=11)
	@Size(min=6)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@OneToOne
    public Role getRole() {
    	return role;
    }
        
    public void setRole(Role role) {
    	this.role = role;
    }
    
    @Column(name="ic_perfil_acesso", nullable=false)
    public Integer getProfile() {
    	return profile;
    }
    
    public void setProfile(Integer profile) {
    	this.profile = profile;
    }
    
	@Column(name="ic_situacao", length=1, nullable=false)
	@NotEmpty
	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}
	
}
    
    
