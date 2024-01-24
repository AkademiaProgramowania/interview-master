package pl.pop.interview.master.account;

public class AccountDTO {
    private String email;
    private String password;
    private Long practitionerId;

    public AccountDTO(String email, String password, Long practitionerId) {
        this.email = email;
        this.password = password;
        this.practitionerId = practitionerId;
    }

    public AccountDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AccountDTO() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Long getPractitionerId() {
        return practitionerId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPractitionerId(Long practitionerId) {
        this.practitionerId = practitionerId;
    }


}
