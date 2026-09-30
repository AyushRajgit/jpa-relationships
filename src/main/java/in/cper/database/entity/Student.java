package in.cper.database.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30, updatable = false, nullable = false)
    private String firstName;

    @Column(length = 30, updatable = false, nullable = false)
    private String lastName;

    @Column(length = 30, nullable = false)
    private String email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "houseNo",
                    column = @Column(name = "current_houseNo")
            ),
            @AttributeOverride(
                    name = "street",
                    column = @Column(name = "current_street")
            ),
            @AttributeOverride(
                    name = "city",
                    column = @Column(name = "current_city")
            ),
            @AttributeOverride(
                    name = "state",
                    column = @Column(name = "current_state")
            )
    })
    @Column(nullable = false)
    private Address currentAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "houseNo",
                    column = @Column(name = "permanent_houseNo")
            ),
            @AttributeOverride(
                    name = "street",
                    column = @Column(name = "permanent_street")
            ),
            @AttributeOverride(
                    name = "city",
                    column = @Column(name = "permanent_city")
            ),
            @AttributeOverride(
                    name = "state",
                    column = @Column(name = "permanent_state")
            )
    })
    @Column(nullable = false)
    private Address permanentAddress;

    @Column(precision = 5, scale = 2)
    private BigDecimal percentage_Class10;

    @Column(precision = 5, scale = 2)
    private BigDecimal percentage_Class12;

    @ElementCollection
    @CollectionTable(
            name = "student_skill",
            joinColumns = @JoinColumn(name = "id")
    )
    @Column(nullable = false)
    private Set<String> skills = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(Address currentAddress) {
        this.currentAddress = currentAddress;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public BigDecimal getPercentage_Class10() {
        return percentage_Class10;
    }

    public void setPercentage_Class10(BigDecimal percentage_Class10) {
        this.percentage_Class10 = percentage_Class10;
    }

    public BigDecimal getPercentage_Class12() {
        return percentage_Class12;
    }

    public void setPercentage_Class12(BigDecimal percentage_Class12) {
        this.percentage_Class12 = percentage_Class12;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }
}
