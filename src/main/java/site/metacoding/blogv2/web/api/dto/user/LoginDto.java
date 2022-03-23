package site.metacoding.blogv2.web.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDto {
    
    private String username;
    private String password;
    private String remember;

    // INSERT를 하지 않으니까 set한 entity를 줄 필요가 없음

}
