package EmailClient;

import java.io.Serializable;

public class Email implements Serializable {
    private String toEmail;
    private String subject;
    private String content;
    private static final long serialVersionUID = 806572676;

    public Email(String toEmail,String subject,String content){
        this.toEmail = toEmail;
        this.subject = subject;
        this.content = content;
    }
    public String getToEmail() {return toEmail;}

    public String getSubject() {return subject;}

    public String getContent() {return content;}
}

