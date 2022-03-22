package site.metacoding.blogv2.web.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.service.UserService;
import site.metacoding.blogv2.web.api.dto.ResponseDto;
import site.metacoding.blogv2.web.api.dto.user.JoinDto;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    
    private final UserService userService;

    @PostMapping("/api/join") // 데이터 요청
    public ResponseDto<String> joinForm(@RequestBody JoinDto joinDto){
        userService.회원가입(joinDto);
        return new ResponseDto<String>(1, "회원가입 성공", null);
    }
}
