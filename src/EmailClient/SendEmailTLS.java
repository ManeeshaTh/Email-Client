package EmailClient;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Properties;

public class SendEmailTLS {

    public static void sendMail(String recipient,String subject,String content) {

        //Configure email address and password
        final String username = "useremail@gmail.com"; //Set sender's email address
        final String password = "**********";       //Set this to App Password generated (As Less Secure App Access is no longer available.)

        //Configure properties of mail server
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        //log in
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(
                    Message.RecipientType.TO,new InternetAddress(recipient));

            message.setSubject(subject);
            message.setText(content);

            System.out.println("sending...");
            //send message
            Transport.send(message);
            System.out.println("Sent message successfully...");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        //Storing Email in HardDisk
        Email email = new Email(recipient,subject,content);

        // .ser file stores all objects serialized on current day; named according to current date: yyyyMMdd
        //Create .ser filename based on current Date
        String a = Date.getDate();
        a = a.replace("/","");
        String filename = a + ".ser";

        try {// Make a FileOutputStream
            FileOutputStream fileStream = new FileOutputStream(filename, true);
            // Make a ObjectOutputStream
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            // Write the object
            os.writeObject(email);
            // Close the ObjectOutputStream
            os.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}