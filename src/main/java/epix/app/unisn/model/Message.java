package epix.app.unisn.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "sender")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver")
    private User receiver;

}

