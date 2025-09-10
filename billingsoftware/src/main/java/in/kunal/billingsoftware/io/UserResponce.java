package in.kunal.billingsoftware.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponce {
    private String userId;
    private String email;
    private String role;
    private String name;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
