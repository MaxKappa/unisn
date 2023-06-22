package epix.app.unisn.repository;

import epix.app.unisn.model.FriendRequest;
import epix.app.unisn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {



        /*
        @Modifying(clearAutomatically = true)
        @Query("DELETE FROM Friendship f WHERE (f.userSender = :user AND f.userReceiver = :friend) " +
                "OR (f.userSender = :friend AND f.userReceiver = :user)")
        void deleteFriendRequests(@Param("user") User user, @Param("friend") User friend);

        @Modifying(clearAutomatically = true)
        @Query(value = "UPDATE friendship AS f SET f.accepted = true WHERE (f.userSender.)",
                nativeQuery = true)
        void addFriend(@Param("user") User user, @Param("friend") User friend);
        */
        @Query("SELECT f FROM FriendRequest AS f WHERE f.id = ?1")
        Optional<FriendRequest> getFriendRequestById(Long id);
        @Query(value = "SELECT f FROM FriendRequest AS f WHERE f.userReceiver = :user AND f.status = \"PENDING\"")
        List<FriendRequest> getAllPendingFriendRequest(@Param("user") User user);

        @Query(value = "SELECT f FROM FriendRequest AS f WHERE f.userReceiver = :userReciver and f.userSender = :userSender ")
        Optional<FriendRequest> getFriendRequestByUserReceiverAndUserSender(@Param("userReciver") User userReciver, @Param("userSender") User userSender);

        /*
        @Modifying(clearAutomatically = true)
        @Query(value = "INSERT INTO FriendRequest (userSender, userReceiver, status) VALUES (:user, :friend, \"PENDING\")",
                nativeQuery = true)
        void sendFriendRequest(@Param("user") Long user_id, @Param("friend") Long friend_id);

        @Query(value = "UPDATE FriendRequest AS f SET f.status = \"ACCEPTED\" WHERE (f.userSender = :friend AND f.userReceiver = :user)")
        void acceptFriendRequest(@Param("user") Long user_id, @Param("friend") Long friend_id);

        */
}
      /*  @Query(value = )
        void deleteFriendRequest(@Param("user") Long user_id);

}*/

        /*
        @Query("SELECT case when count(f)> 0 then true else false end FROM Friendship f WHERE (f.userSender = :user AND f.userReceiver = :friend) " +
                "OR (f.userSender = :friend AND f.userReceiver = :user)")
        boolean checkFriendshipExists(@Param("user") User user, @Param("friend") User friend);
}       */
