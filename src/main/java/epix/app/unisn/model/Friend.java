package epix.app.unisn.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_1")
    private User user_1;
    @ManyToOne
    @JoinColumn(name = "user_2")
    private User user_2;

    @Column(name  = "date")
    private LocalDate date;

    public Friend(User user_1, User user_2, LocalDate date) {
        this.user_1 = user_1;
        this.user_2 = user_2;
        this.date = date;
    }

    public User getUser1() {

        return user_1;
    }

    public void setUser1(User user) {
        this.user_1 = user;
    }

    public User getUser2() {
        return user_2;
    }

    public void setUser2(User friend) {
        this.user_2 = friend;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
