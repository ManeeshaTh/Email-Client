package EmailClient;

public class OfficeFriend extends Official implements BDayGreetable {
    private String DOB;

    public OfficeFriend(String name, String email, String designation,String DOB){
        super(name,email,designation);
        this.DOB = DOB;
    }

    public String getDOB() {return DOB;}

    public String getBDAY(){
        return DateFormatter.DateToMMdd(DOB);
    }
}
