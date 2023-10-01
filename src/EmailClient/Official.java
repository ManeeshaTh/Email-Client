package EmailClient;

public class Official extends Recipient {

    private String email;
    private String designation;

    public Official(String name, String email, String designation){
        super(name);
        this.email = email;
        this.designation = designation;
    }
    public String getEmail() {
        return email;
    }
    public String getDesignation() {
        return designation;
    }
}
