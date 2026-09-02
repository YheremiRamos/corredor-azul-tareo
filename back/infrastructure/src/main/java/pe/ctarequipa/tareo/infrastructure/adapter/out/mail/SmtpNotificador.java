package pe.ctarequipa.tareo.infrastructure.adapter.out.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import pe.ctarequipa.tareo.application.port.out.Notificador;

@Slf4j
@Component
public class SmtpNotificador implements Notificador {

    private final JavaMailSender mailSender;
    private final String fromAddress;

    public SmtpNotificador(
            @Value("${spring.mail.username:}") String fromAddress,
            @Autowired(required = false) JavaMailSender mailSender) {
        this.fromAddress = fromAddress;
        this.mailSender = mailSender;
    }

    @Override
    public void enviar(String destinatario, String asunto, String cuerpo) {
        if (mailSender == null || fromAddress == null || fromAddress.isBlank()) {
            log.info("Notificacion (sin SMTP): to={}, asunto={}, cuerpo={}", destinatario, asunto, cuerpo);
            return;
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAddress);
        message.setTo(destinatario);
        message.setSubject(asunto);
        message.setText(cuerpo);
        mailSender.send(message);
        log.info("Notificacion enviada a {}", destinatario);
    }
}
