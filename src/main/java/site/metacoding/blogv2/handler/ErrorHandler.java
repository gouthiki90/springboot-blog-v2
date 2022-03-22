package site.metacoding.blogv2.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import site.metacoding.blogv2.web.api.dto.ResponseDto;

// 모든 예외를 낚아챈다.
@RestControllerAdvice
public class ErrorHandler {
    
    // 어떤 예외가 날 때 핸들링할 것인가
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseDto<String> error1(RuntimeException e){
        System.out.println("오류 내용 : " + e.getMessage());

        return new ResponseDto<String>(-1, "실패", e.getMessage()); // body 데이터를 돌려준다.
        // 개발자가 알기 위해서 msg는 띄워줘야 된다.
    }
}
