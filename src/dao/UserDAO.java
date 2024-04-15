package dao;

import entity.UserEntity;

public interface UserDAO {
    // UserEntity를 저장하고, 저장된 UserEntity의 ID를 반환하는 메소드
    // 회원가입
    String signup(UserEntity userEntity);
    // 로그인
    Boolean login(UserEntity userEntity);
}

