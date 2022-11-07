package br.com.impacta.moedinhas.domain.service.impl;

import br.com.impacta.moedinhas.configuration.properties.EmailProperties;
import br.com.impacta.moedinhas.domain.exception.InternalErrorException;
import br.com.impacta.moedinhas.domain.model.User;
import br.com.impacta.moedinhas.domain.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    private final EmailProperties emailProperties;

    @Override
    public void sendEmail(final User user) {
        log.info("Sending email to {}", user.getEmail());
        try {

            final SimpleMailMessage simpleMailMessage = this.createEmailMessage(user);
            mailSender.send(simpleMailMessage);
            log.info("Email to {} sent successfully", user.getEmail());

        } catch (final MailException exception) {
            log.error("An error occurred on trying to send email to {}. Error {}", user.getEmail(), exception.getMessage());
            throw new InternalErrorException("Error on trying to send email");
        }
    }

    private SimpleMailMessage createEmailMessage(final User user) {
        final String redirectUrl = emailProperties.getRedirectUrl() + "/?token=" + user.getResetToken();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailProperties.getFrom());
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject(emailProperties.getSubject());
        simpleMailMessage.setText("To reset your password, click the link below: \n" + redirectUrl);

        return simpleMailMessage;
    }
}
