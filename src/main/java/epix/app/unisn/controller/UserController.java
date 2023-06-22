package epix.app.unisn.controller;


import epix.app.unisn.model.User;
import epix.app.unisn.service.JwtService;
import epix.app.unisn.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    //Testing
    @GetMapping("whoami")
    public ResponseEntity<?> whoami(HttpServletRequest request){
        try{
            return new ResponseEntity<>(userService.getUserFromRequest(request), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping(path = "{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long id){
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>("User delete successfully", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId, @RequestParam(required = false) String username, @RequestParam(required = false) String email, @RequestParam(required = false) String password ){
        try {
            userService.updateUser(userId, username, email, password);
            return new ResponseEntity<>("User update successfully", HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
