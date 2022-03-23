package site.metacoding.blogv2.web.api;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
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

    @PostMapping("/join") // 데이터 요청
    public ResponseDto<String> joinForm(@RequestBody JoinDto joinDto) {
        userService.회원가입(joinDto);
        return new ResponseDto<String>(1, "회원가입 성공", null);
    }

    @PostMapping("/login")
    public ResponseDto<String> loginForm(@RequestBody LoginDto loginDto, HttpServletResponse response, Model model) {
        User userEntity = userService.로그인(loginDto);
        System.out.println(userEntity);

        if (userEntity == null) {
            return new ResponseDto<String>(-1, "로그인 실패", null);
        }

        session.setAttribute("principal", userEntity);

        // 쿠키 로직 remember가 on이면 setcookie 키값에 ssar를 넣음

        response.addHeader("Set-Cookie", "remember=" + loginDto.getUsername() + "; path=/"); // 쿠키에 username 저장하기
        // response.addHeader("Set-Cookie", "remember=" + loginDto.getUsername() + ";
        // path=/; httpOnly=true"); httponly 속성 거는 방법
        // 쿠키를 저장할 때 path를 바꾸는 법

        return new ResponseDto<String>(1, "로그인 성공", null);
    }

}
