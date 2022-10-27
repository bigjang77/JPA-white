package site.metacoding.white.dto;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.white.domain.User;

public class UserReqDto {

    @Setter
    @Getter
    public static class JoinReqDto {// 로그인 전 로직들은 전부다 앞에 엔티티 안붙임. POST /user -> /join
        private String username;
        private String password;

        public User toEntity() {
            return User.builder().username(username).password(password).build();
        }
    }

    @Setter
    @Getter
    public static class LoginReqDto {// 내부클래스를 외부에서 쓰고싶으면 static붙이기
        private String username;
        private String password;
    }

    @Setter
    @Getter
    public static class UserUpdateReqDto {// 내부클래스를 외부에서 쓰고싶으면 static붙이기
        private Long id;
        private String username;
        private String password;
    }
}
