package epix.app.unisn.service;


import epix.app.unisn.model.Friend;
import epix.app.unisn.model.FriendRequest;
import epix.app.unisn.model.User;
import epix.app.unisn.repository.FriendRepository;
import epix.app.unisn.repository.FriendRequestRepository;
import epix.app.unisn.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendRequestService {
    private final FriendRequestRepository friendRequestRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    private final FriendRepository friendRepository;

    public List<String> getAllFriendRequest(HttpServletRequest request) throws IllegalStateException{
        User user = userService.getUserFromRequest(request);
        List<FriendRequest> lista = friendRequestRepository.getAllPendingFriendRequest(user);
        if (lista.isEmpty()){
            throw new IllegalStateException("User " + user.getUsername()+ " doesn't have friend requests");
        }
        List<String> usernames = new ArrayList<String>();
        for (FriendRequest friendRequest : lista) {
            usernames.add(friendRequest.getUserSender().getUsername());
        }
        return usernames;
    }

    public int countFriendRequest(User user){
        return friendRequestRepository.getAllPendingFriendRequest(user).size();
    }

    public void sendFriendRequest(HttpServletRequest request, String friendUsername){
        User user = userService.getUserFromRequest(request);
        Optional<User> friend = userRepository.findUserByUsername(friendUsername);
        if (!friend.isPresent()){
            throw new UsernameNotFoundException("Username " + friendUsername + " doesn't exists");
        }
        FriendRequest friendRequest = new FriendRequest(user, friend.get(), "PENDING");
        friendRequestRepository.save(friendRequest);
    }

    public void acceptFriendRequest(HttpServletRequest request, String friendUsername) throws IllegalStateException{
        User user = userService.getUserFromRequest(request);
        Optional<User> optfriend = userRepository.findUserByUsername(friendUsername);
        if (!optfriend.isPresent()){
            throw new IllegalStateException("Friend " + friendUsername + " doesn't exists");
        }
        Optional<FriendRequest> friendRequest = friendRequestRepository.getFriendRequestByUserReceiverAndUserSender(user, optfriend.get());
        if (!friendRequest.isPresent()){
            throw new IllegalStateException("Friend request doesn't exists");
        }
        Friend friend = new Friend(user, friendRequest.get().getUserSender(), LocalDate.now());
        String status = friendRequest.get().getStatus();
        if (status == "ACCEPTED"){
            throw new IllegalStateException("You are already friend");
        } else if (status == "REFUSED"){
            throw new IllegalStateException("You can't accept this friend request");
        }
        friendRequest.get().setStatus("ACCEPTED");
        friendRepository.save(friend);
    }
    public void refuseFriendRequest(HttpServletRequest request, String friendUsername) throws IllegalStateException{

    }

}
