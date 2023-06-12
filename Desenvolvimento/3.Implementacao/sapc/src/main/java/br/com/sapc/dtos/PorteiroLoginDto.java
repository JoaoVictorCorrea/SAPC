package br.com.sapc.dtos;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.sapc.entities.Porteiro;
import br.com.sapc.enums.Role;

@SuppressWarnings("serial")
public class PorteiroLoginDto implements UserDetails{

	private String email;
	private String senha;
	private Role role;
//	private String nome;
	

	public PorteiroLoginDto(Porteiro porteiro) {
		this.email = porteiro.getEmail();
		this.senha = porteiro.getSenha();
		this.role = porteiro.getRole();
//		this.nome = porteiro.getNome();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
		
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

//	public String getNome() {
//		return nome;
//	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	
}
