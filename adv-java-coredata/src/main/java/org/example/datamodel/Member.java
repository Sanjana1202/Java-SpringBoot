package org.example.datamodel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="members")
public class Member {
    @Id
    private int memid;

    private String surname;
    private String firstname;
    private String address;
    private int zipcode;
    private String telephone;
    @Column(name = "recommendedby")
    private Integer recommendedBy;
    @Column(name = "joindate")
    private Timestamp joinDate;

    public Member(int memid, String surname, String firstname, String address, int zipcode, String telephone,
                  Integer recommendedBy, Timestamp joinDate) {
        this.memid = memid;
        this.surname = surname;
        this.firstname = firstname;
        this.address = address;
        this.zipcode = zipcode;
        this.telephone = telephone;
        this.recommendedBy = recommendedBy;
        this.joinDate = joinDate;
    }

    public Member(){}

    public int getMemid() {
        return memid;
    }

    public void setMemid(int memid) {
        this.memid = memid;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getRecommendedBy() {
        return recommendedBy;
    }

    public void setRecommendedBy(Integer recommendedBy) {
        this.recommendedBy = recommendedBy;
    }

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memid=" + memid +
                ", surname='" + surname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", address='" + address + '\'' +
                ", zipcode=" + zipcode +
                ", telephone='" + telephone + '\'' +
                ", recommendedBy=" + recommendedBy +
                ", joinDate=" + joinDate +
                '}';
    }
}

