package com.email.sender;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.email.sender.dominio.Carteiro;
import com.email.sender.dominio.Configuracao;

/* Este projeto mostra como usar o java mail sender
 * O JavaMail é uma excelente API que fornece uma estrutura independente de plataforma e de protocolo,
 * para enviar, receber e manipular correio eletrônico (não necessariamente internet e-mail) e
 * messaging como clientes.*/

/* As classes utilizadas para testar o envio de e-mail estão no pacote chamado dominio.
 * Todas as classes estão comentadas.*/

/* IMPORTANTE: Antes de usar o java mail sender, você deve configurar o serviço de e-mail e
 * anotar suas informações de envio SMTP e credenciais SMTP. Por exemplo, se você estiver usando um gmail é preciso
 * acessar a conta e liberar a conexão externa, ou conexão de aplicativos externos. Além disto, verificar qual
 * é o número da porta SMPT usada para conexão e envio de e-mail, pois isto pode variar entre os provedores.*/

@SpringBootApplication
public class SenderApplication {

	public static void main(String[] args) throws UnsupportedEncodingException, MessagingException {
		SpringApplication.run(SenderApplication.class, args);

		/*
		 * Aqui executa-se o envio de e-mail. Antes veja as classes desenvolvidas e seus
		 * comentários. Será necessário, também, substituir as configurações de envio
		 * pelas suas.
		 */

		/*
		 * O código abaixo mostra como usar o exemplo. Contudo, por uma questão de
		 * sigilo, as informações colocadas são fictícias, ou seja, você terá que
		 * substiuir pelas suas
		 */

		Configuracao configuracao = new Configuracao("remetente@gmail.com (e-mail do remetente)",
				"remetente nome (nome do remetente, como: João)", "destinatário@gmail.com (e-mail do destinatário)",
				"remetente@gmail.com (repetir remetente, porque aqui é usado como login de usuário)", "12QWOP90 (senha do usuário)",
				"smtp.gmail.com (servidor, no exemplo aqui é o gmail)", "465 (porta)");
		Carteiro carteiro = new Carteiro(configuracao, "Assunto", "Corpo do e-mail...");
		carteiro.enviarEmail();
	}

}
