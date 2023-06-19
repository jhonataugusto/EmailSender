package util;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class SenderUtil {

    public static void sendEmail(String remetente, String destinatario, String usuario, String senha, String assunto, String corpo, String anexo){
        String servidorSMTP = "smtp.gmail.com";
        String portaSMTP = "587";

        // Configura as propriedades do servidor SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", servidorSMTP);
        props.put("mail.smtp.port", portaSMTP);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Cria uma sessão com autenticação
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, senha);
            }
        });

        try {

            MimeBodyPart corpoEmail = new MimeBodyPart();
            corpoEmail.setText(corpo);

            // Cria uma mensagem de e-mail
            MimeMessage mensagem = new MimeMessage(session);
            mensagem.setFrom(new InternetAddress(remetente));
            mensagem.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mensagem.setSubject(assunto);
            mensagem.setContent(corpoEmail, "text/html");


            MimeBodyPart parteAnexo = new MimeBodyPart();
            FileDataSource fileDataSource = new FileDataSource(anexo);

            parteAnexo.setDataHandler(new DataHandler(fileDataSource));
            parteAnexo.setFileName(fileDataSource.getName());

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(corpoEmail);
            multipart.addBodyPart(parteAnexo);

            mensagem.setContent(multipart);

            // Envia o e-mail
            Transport.send(mensagem);

            System.out.println("E-mail enviado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
