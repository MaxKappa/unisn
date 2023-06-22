package epix.app.unisn.model;

import jakarta.persistence.*;


@Table
@Entity
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_sender")
    private User userSender;

    @ManyToOne
    @JoinColumn(name = "user_receiver")
    private User userReceiver;


    @Column(name = "status")
    private String status;

    public FriendRequest() {}


    public String getStatus() {
        return status;
    }

    public FriendRequest(User userSender, User userReceiver, String status) {
        this.userSender = userSender;
        this.userReceiver = userReceiver;
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserSender() {
        return userSender;
    }

    public void setUserSender(User userSender) {
        this.userSender = userSender;
    }

    public User getUserReceiver() {
        return userReceiver;
    }

    public void setUserReceiver(User userReceiver) {
        this.userReceiver = userReceiver;
    }
}