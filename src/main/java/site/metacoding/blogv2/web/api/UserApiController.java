package site.metacoding.blogv2.web.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.domain.user.User;
import site.metacoding.blogv2.service.UserService;
import site.metacoding.blogv2.web.api.dto.ResponseDto;
import site.metacoding.blogv2.web.api.dto.user.JoinDto;
import site.metacoding.blogv2.web.api.dto.user.LoginDto;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    
    private final UserService userService;
    private final HttpSession session;

    @PostMapping("/api/join") // 데이터 요청
    public ResponseDto<String> joinForm(@RequestBody JoinDto joinDto){
        userService.회원가입(joinDto);
        return new ResponseDto<String>(1, "회원가입 성공", null);
    }

    @PostMapping("/api/login")
    public ResponseDto<String> loginForm(@RequestBody LoginDto loginDto){
        User userEntity = userService.로그인(loginDto);
        System.out.println(userEntity);

        if(userEntity == null){
            return new ResponseDto<String>(-1, "로그인 실패", null);
        }

        session.setAttribute("principal", userEntity);
        return new ResponseDto<String>(1, "로그인 성공", null);
    }

}
