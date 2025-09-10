package in.kunal.billingsoftware.service;

import in.kunal.billingsoftware.io.UserRequest;
import in.kunal.billingsoftware.io.UserResponce;

import java.util.List;

public interface UserService {

    UserResponce createUser(UserRequest request);

    String getUserRole(String email);

    List<UserResponce> readUsers();

    void deleteUser(String id);
}
