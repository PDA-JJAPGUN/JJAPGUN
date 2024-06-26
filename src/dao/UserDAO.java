package dao;

import entity.UserEntity;

import java.util.List;

public interface UserDAO {
    // UserEntity를 저장하고, 저장된 UserEntity의 ID를 반환하는 메소드
    // 회원가입
    String signup(UserEntity userEntity);

    //id로 회원 찾기
    UserEntity getUser(String id);

    //모든 회원 찾기
    List<UserEntity> getUsers();

    void saveUser(UserEntity user);

    // ID 중복 검사 메서드
    boolean isIdDuplicate(String id);
}

