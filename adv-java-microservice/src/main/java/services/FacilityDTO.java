package services;

public class FacilityDTO {
    private int facid;
    private String name;
    private double memberCost;
    private double guestCost;
    private double initialOutlay;
    private double monthlyMaintenance;

    public FacilityDTO(int facid, String name, double memberCost, double guestCost, double initialOutlay, double monthlyMaintenance) {
        this.facid = facid;
        this.name = name;
        this.memberCost = memberCost;
        this.guestCost = guestCost;
        this.initialOutlay = initialOutlay;
        this.monthlyMaintenance = monthlyMaintenance;
    }

    public FacilityDTO() {
    }

    public int getFacid() {
        return facid;
    }

    public void setFacid(int facid) {
        this.facid = facid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMemberCost() {
        return memberCost;
    }

    public void setMemberCost(double memberCost) {
        this.memberCost = memberCost;
    }

    public double getGuestCost() {
        return guestCost;
    }

    public void setGuestCost(double guestCost) {
        this.guestCost = guestCost;
    }

    public double getInitialOutlay() {
        return initialOutlay;
    }

    public void setInitialOutlay(double initialOutlay) {
        this.initialOutlay = initialOutlay;
    }

    public double getMonthlyMaintenance() {
        return monthlyMaintenance;
    }

    public void setMonthlyMaintenance(double monthlyMaintenance) {
        this.monthlyMaintenance = monthlyMaintenance;
    }

    @Override
    public String toString() {
        return "Facility{" +
                "facid=" + facid +
                ", name='" + name + '\'' +
                ", memberCost=" + memberCost +
                ", guestCost=" + guestCost +
                ", initialOutlay=" + initialOutlay +
                ", monthlyMaintenance=" + monthlyMaintenance +
                '}';
    }
}
