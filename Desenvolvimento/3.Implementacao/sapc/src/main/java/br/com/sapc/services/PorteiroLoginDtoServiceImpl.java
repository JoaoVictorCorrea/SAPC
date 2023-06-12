package br.com.sapc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.sapc.dtos.PorteiroLoginDto;
import br.com.sapc.entities.Porteiro;
import br.com.sapc.repositories.PorteiroRepository;

@Component
public class PorteiroLoginDtoServiceImpl implements UserDetailsService{

	@Autowired
	private PorteiroRepository porteiroRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Porteiro> porteiroOptional = porteiroRepository.findByEmail(email);
		return porteiroOptional.map(PorteiroLoginDto::new)
				.orElseThrow(() -> new UsernameNotFoundException("Email" + email + "não encontrado"));
	}

}
