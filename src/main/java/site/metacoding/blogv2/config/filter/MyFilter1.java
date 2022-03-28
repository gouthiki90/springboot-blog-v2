package site.metacoding.blogv2.config.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter; // 톰켓에서 있는 것
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter1 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("MyFilter 1");
        // web.xml에 등록해야 된다.

        HttpServletRequest req = (HttpServletRequest) request; // 다운캐스팅
        HttpServletResponse rep = (HttpServletResponse) response;

        String path = req.getRequestURI(); // URI를 toString

        if (path.contains("fuck")) {
            rep.setContentType("text/plain; charset=utf-8"); // MIME 타입으로 header에 알려줌

            PrintWriter out = rep.getWriter(); // BW로 보내기
            out.println("욕하지마"); // body가 됨
            out.flush();
        } else {
            chain.doFilter(request, response); // 메인 페이지로 가기
        }

        // chain.doFilter(request, response); 다음 필터를 불러낸다.

    }

}
