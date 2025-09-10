package in.kunal.billingsoftware.controller;

import in.kunal.billingsoftware.io.UserRequest;
import in.kunal.billingsoftware.io.UserResponce;
import in.kunal.billingsoftware.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserController {

   private final UserService userService;

   @PostMapping("/register")
   @ResponseStatus(HttpStatus.CREATED)
   public UserResponce registerUser(@RequestBody UserRequest request) {
        try {
            return userService.createUser(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to create user"+ e.getMessage());
        }
   }

   @GetMapping("/users")
   public List<UserResponce> readUsers() {
        return userService.readUsers();
   }

   @DeleteMapping("/users/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT  )
   public void deleteUser(@PathVariable String id) {
       try {
           userService.deleteUser(id);
       } catch (Exception e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
       }
   }
}
