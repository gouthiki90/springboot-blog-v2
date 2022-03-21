package site.metacoding.blogv2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    // 모든 페이지 잘 작동하는 지 확인 후 푸시하기 - 2. 컨트롤러 페이지 작동 테스트

    @GetMapping("/")
    public String main(){

        return "post/list";
    }

    @GetMapping("/s/post/detail")
    public String detail(){

        return "post/detail";
    }

    @GetMapping("/s/post/writeForm")
    public String writeForm(){

        return "post/writeForm";
    }

    @GetMapping("/s/user/detail")
    public String detailUser(){
        return "user/detail";
    }

    @GetMapping("/user/joinForm")
    public String joinForm(){
        return "user/joinForm";
    }

    @GetMapping("/user/loginForm")
    public String loginForm(){
        return "user/loginForm";
    }

    @GetMapping("/s/user/updateForm")
    public String updateForm(){
        return "user/updateForm";
    }
}
