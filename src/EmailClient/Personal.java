package EmailClient;

public class Personal extends Recipient implements BDayGreetable{
    private String nickname;
    private String email;
    private String DOB;  //LocalDate DOB

    public Personal( String name, String nickname, String email, String DOB){
        super(name);
        this.nickname = nickname;
        this.email = email;
        this.DOB = DOB;  
    }

    public String getNickname() {
        return nickname;
    }
    public String getEmail() {
        return email;
    }
    public String getDOB() {
        return DOB;
    }

    //
    public String getBDAY(){
        return DateFormatter.DateToMMdd(DOB);
    }
}
