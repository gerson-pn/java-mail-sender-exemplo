package com.email.sender.dominio;

import java.io.UnsupportedEncodingException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/* Esta classe, chamda Carteiro, foi criada para enviar o e-mail propriamente.
 * Ela recebe as configurações em seu construtor e as utiliza para conectar no servidor de e-mail e enviar o e-mail*/
public class Carteiro {
	private Configuracao configuracao;

	/* Estas variaveis, armazem o assunto e o corpo (texto) do e-mail */
	private String assunto;
	private String corpo;

	public Carteiro(Configuracao configuracao, String assunto, String corpo) {
		this.configuracao = configuracao;
		this.assunto = assunto;
		this.corpo = corpo;
	}

	/*
	 * IMPORTANTE! podem ocorrer exceções durante o envio do e-mail, por isso foram
	 * colocados os throws:
	 */
	public void enviarEmail() throws UnsupportedEncodingException, MessagingException {
		/*
		 * Para iniciar o envio é necessário abrir uma sessão (uma conecção, com o
		 * serviço de e-mail desejado). Isto pode deve ser feito com a classe Session.
		 * Perceba que a Session é uma classe que pertecen ao pacote javax.mail
		 * (importado acima e que recebe as propriedades).
		 */
		Session sessao = Session.getDefaultInstance(configuracao.obterPropriedades());

		/*
		 * Após criar a sessão, o próximo passo é criar a mensagem com suas informações.
		 * Para isto deve-se utilizar uma classe específica, chamada de MimeMessage, que
		 * também pertence ao pacote javax.mail.
		 */
		MimeMessage mensagem = new MimeMessage(sessao);
		mensagem.setFrom(new InternetAddress(configuracao.getRemetente(), configuracao.getRemetente_nome()));
		mensagem.setRecipient(Message.RecipientType.TO, new InternetAddress(configuracao.getDestinatario()));
		mensagem.setSubject(assunto);
		mensagem.setContent(corpo, "text/html");

		/*
		 * Tudo preparado, deve-se agora iniciar o envio propriamente dito. Isto é feito
		 * com a classe transport.
		 */
		Transport transporte = sessao.getTransport();
		transporte.connect(configuracao.getHostname(), configuracao.getUsuario_smtp(), configuracao.getSenha());
		transporte.sendMessage(mensagem, mensagem.getAllRecipients());
		System.out.println("Se tudo deu certo, e-mail enviado!");
	}
}
