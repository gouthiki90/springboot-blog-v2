package site.metacoding.blogv2.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.domain.user.User;
import site.metacoding.blogv2.domain.user.UserRepository;
import site.metacoding.blogv2.web.api.dto.user.JoinDto;
import site.metacoding.blogv2.web.api.dto.user.LoginDto;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User 회원정보(Integer id){
        Optional<User> userOp = userRepository.findById(id);

        if(userOp.isPresent()){
            return userOp.get();
        } else {
          throw new RuntimeException("아이디를 찾을 수 없습니다."); // 강제로 예외 터뜨리기
          // 핸들러가 실행됨
        }
    }

    @Transactional
    public void 회원가입(JoinDto joinDto) {
        // save하면 DB에 insert하고 결과를 다시 return해준다.
        // 받을 필요가 없기 때문에 void로 한다.
        userRepository.save(joinDto.toEntity());
    }

    public User 로그인(LoginDto loginDto){
        // 로그인 처리 쿼리를 JPA에서 제공해주지 않는다.
        System.out.println(loginDto);
        User userEntity = userRepository.mLogin(loginDto.getUsername(), loginDto.getPassword());
        // 똑같은 String이기 때문에 Dto에 있는 username과 password get하기
        // DTO에 있는 필드를 가져와야 됨

        return userEntity; // 사용자에게 들어온 값을 확인해야 되기 때문에 받은 값 리턴하기
    }

}
