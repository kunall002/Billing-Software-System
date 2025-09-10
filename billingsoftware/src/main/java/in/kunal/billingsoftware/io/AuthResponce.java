package in.kunal.billingsoftware.io;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponce {

    private String email;
    private String role;
    private String token;


}
