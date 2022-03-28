package site.metacoding.blogv2.web.api;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.domain.user.User;
import site.metacoding.blogv2.service.UserService;
import site.metacoding.blogv2.web.api.dto.ResponseDto;
import site.metacoding.blogv2.web.api.dto.user.JoinDto;
import site.metacoding.blogv2.web.api.dto.user.LoginDto;
import site.metacoding.blogv2.web.api.dto.user.UpdateDto;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final HttpSession session;

    // password, email, addr 받아서 Dto로 리턴하기
    @PutMapping("/s/api/user/{id}")
    public ResponseDto<?> update(@PathVariable Integer id, @RequestBody UpdateDto updateDto) {
        
        // 세션의 아이디와 {id}를 비교하기 모든 것에서 하지 않기 때문에 컨트롤러에서 한다.
        User principal = (User) session.getAttribute("principal"); // 세션 얻기

        if(principal.getId() != id){ // 사용자에게 받은 아이디와 세션 아이디 비교하기
            throw new RuntimeException("권한이 없습니다.");
        }
        
        User userEntity = userService.회원수정(id, updateDto);
        session.setAttribute("principal", userEntity); // 세션 생성하기
        return new ResponseDto<>(1, "성공", null); // 서비스에서 이미 예외 처리했기 때문에 성공했을 때만 성공 메시지 보내기
    }

    // 우리 웹브라우저에서는 현재 사용안함. 추후 앱에서 요청시에 사용할 예정
    @GetMapping("/s/api/user/{id}")
    public ResponseDto<?> userInfo(@PathVariable Integer id) {
        User userEntity = userService.회원정보(id);
        return new ResponseDto<>(1, "성공", userEntity);
    }

    // 회원가입 페이지, 회원가입, 로그인 페이지, 로그인 응답
    // 로그아웃 하기, 인증 프로세스이기 때문에 api 안 붙임
    @GetMapping("/logout")
    public ResponseDto<?> logout() {
        session.invalidate(); // 세션 무효화
        return new ResponseDto<>(1, "성공", null);
        // 핸들러가 잡아갈 것이기 때문에 성공 프로세스만 응답해주면 된다.
    }

    @PostMapping("/join") // 데이터 요청
    public ResponseDto<?> joinForm(@RequestBody JoinDto joinDto) {
        userService.회원가입(joinDto);
        return new ResponseDto<>(1, "회원가입 성공", null);
    }

    @PostMapping("/login")
    public ResponseDto<?> loginForm(@RequestBody LoginDto loginDto, HttpServletResponse response, Model model) {
        User userEntity = userService.로그인(loginDto);
        System.out.println(userEntity);

        if (userEntity == null) {
            return new ResponseDto<>(-1, "로그인 실패", null);
        }

        session.setAttribute("principal", userEntity);

        // 쿠키 로직 remember가 on이면 setcookie 키값에 ssar를 넣음
        if (loginDto.getUsername() != null && loginDto.getRemember().equals("on")) { // on이면
            response.addHeader("Set-Cookie", "remember=" + loginDto.getUsername() + "; path=/"); // 쿠키에 username 저장하기
            // response.addHeader("Set-Cookie", "remember=" + loginDto.getUsername() + ";
            // path=/; httpOnly=true"); httponly 속성 거는 방법
            // 쿠키를 저장할 때 path를 바꾸는 법
        }

        return new ResponseDto<>(1, "로그인 성공", null);
    }

}
