package epix.app.unisn.controller;


import epix.app.unisn.service.FriendRequestService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/friendRequest")
@RequiredArgsConstructor
public class FriendRequestController {
    private final FriendRequestService friendRequestService;
    @GetMapping
    public ResponseEntity<?> getPendingFriendRequest(HttpServletRequest request){
        try{
            return new ResponseEntity<>(friendRequestService.getAllFriendRequest(request), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }
    @PostMapping("send")
    public ResponseEntity<?> sendFriendRequest(HttpServletRequest request, @RequestBody String friendUsername){
        try{
            friendRequestService.sendFriendRequest(request, friendUsername);
            return new ResponseEntity<>("Friend request successfully send", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("accept")
    public ResponseEntity<?> acceptFriendRequest(HttpServletRequest request, @RequestBody String senderUsername){
        try {
            friendRequestService.acceptFriendRequest(request, senderUsername);
            return new ResponseEntity<>("Friend request successfully accepted", HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
