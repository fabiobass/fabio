package br.com.fabio.curso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fabio.curso.domain.Cliente;
import br.com.fabio.curso.repositories.ClienteRepository;
import br.com.fabio.curso.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClienteRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cli1 = repo.findByEmail(email);
		if (cli1 == null) {
			throw new UsernameNotFoundException(email);
		}

		return new UserSS(cli1.getId(), cli1.getEmail(), cli1.getSenha(), cli1.getPerfis());
	}

}
