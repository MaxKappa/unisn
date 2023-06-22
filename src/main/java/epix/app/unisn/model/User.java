package epix.app.unisn.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;


@Entity
@Table
public class User{

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long Id;
    private String first_name;
    private String surname;
    private LocalDate birth_date;
    @Column(unique = true)
    private String username;
    private String email;
    @Transient
    private Integer age;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String username,
                String email,
                LocalDate date,
                String password,
                Role role,
                String first_name,
                String surname) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.birth_date = date;
        this.first_name = first_name;
        this.surname = surname;
    }

    public User() {
    }
    public User(User user){
        this.username = user.username;
        this.email = user.email;
        this.password = user.password;
        this.role = user.role;
        this.birth_date = user.birth_date;
        this.first_name = user.first_name;
        this.surname = user.surname;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", first_name='" + first_name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth_date=" + birth_date +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", age=" + getAge() +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return Id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return Period.between(this.birth_date, LocalDate.now()).getYears();
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role.name();
    }
    public String getFirst_name() {
        return first_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }
}
