package br.com.fabio.curso.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.fabio.curso.domain.Pedido;

public interface EmailService {

	void sendOrderCinfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
