package BestGymEver;

public enum MembershipStatus {
    CURRENT_MEMBER("Aktivit medlemskap"),
    FORMER_MEMBER("Avslutad medlemskap") ,
    NON_MEMBER("Inte medlem");

    private String status;
    MembershipStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
