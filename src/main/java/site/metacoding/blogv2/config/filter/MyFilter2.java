package site.metacoding.blogv2.config.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import site.metacoding.blogv2.web.api.dto.user.LoginDto;

public class MyFilter2 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("MyFilter 2");
        // web.xml에 등록해야 된다.

        HttpServletRequest req = (HttpServletRequest) request; // 다운캐스팅
        HttpServletResponse rep = (HttpServletResponse) response;

        LoginDto loginDto = null;

        if (req.getMethod().equals("POST")) { // POST 요청일 때만 실행

            String contentType = req.getHeader("Content-Type"); // header의 컨텐트 타입 알기
            System.out.println(contentType);



            if (contentType.equals("application/x-www-form-urlencoded")) {
                // 스프링 내부에서 이렇게 작동함
                String username = req.getParameter("username"); // 버퍼 읽으면 파라매터 작동 안함
                String password = req.getParameter("password"); // x-ww로 파라매터 잡기

                loginDto = new LoginDto();

                loginDto.setUsername(username); // 받은 데이터 set해서 집어넣기
                loginDto.setPassword(password);

                System.out.println(loginDto); // LoginDto(username=ssar, password=1234, remember=null)
            } else if (contentType.equals("application/json")) {
                BufferedReader br = req.getReader(); // readLine으로 순수한 바디 데이터 읽기
                String body = "";

                while (true) {
                    String input = br.readLine(); // 버퍼를 비운다.
                    // 파싱해야 됨

                    if (input == null) {
                        break;
                    }
                    body = body + input; // 읽어서 바디에 넣기

                }
                ObjectMapper om = new ObjectMapper();
                loginDto = om.readValue(body, LoginDto.class); // 오브젝트로 파싱하기
                System.out.println(loginDto);

                System.out.println("=====================");
                System.out.println(body); // useranme=ssar&pssword=1234
            }

        } else {
            System.out.println("POST 요청이 아닙니다.");
        }

        if(loginDto.getPassword() == null || loginDto.getPassword().equals("")){ // password가 공백이거나 null이면
            rep.setContentType("text/plain; charset=utf-8");
            PrintWriter out = rep.getWriter();
            out.println("비밀번호 없으니 못 온다.");
            out.flush();
        } else {
            chain.doFilter(request, response);
        }

    }

}
