package epix.app.unisn.repository;

import epix.app.unisn.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {


    @Query("select case when f.user_1 = :userId then f.user_2 when f.user_2 = :userId then f.user_1 end from Friend AS f")
    List<Friend> getFriendsById(@Param("userId") Long userId);

    /*
    @Query(value = "INSERT INTO Friend (user_1, user_2, data) VALUES (:user_1, :user_2, current_date)", nativeQuery = true)
    void addFriend(@Param("user_1") Long user1_id, @Param("user_2") Long user2_id);
*/
}
