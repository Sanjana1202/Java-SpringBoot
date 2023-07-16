package org.example.datamodel;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookid;

    @ManyToOne
    @JoinColumn(name = "memid", referencedColumnName = "memid")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "facid", referencedColumnName = "facid")
    private Facility facility;

    @Column(name = "starttime", nullable = false)
    private Timestamp startTime;

    @Column(name = "slots", nullable = false)
    private Integer slots;

    public Booking() {
    }

    public Booking(Member member, Facility facility, String bookingDateTime, int slotsTaken) {

    }


    public Long getId() {
        return bookid;
    }

    public void setId(Long bookid) {
        this.bookid = bookid;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + bookid +
                ", member=" + member +
                ", facility=" + facility +
                ", startTime=" + startTime +
                ", slots=" + slots +
                '}';
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Integer getSlots() {
        return slots;
    }

    public void setSlots(Integer slots) {
        this.slots = slots;
    }
}
