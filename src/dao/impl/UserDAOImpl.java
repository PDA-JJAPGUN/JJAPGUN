package dao.impl;

import dao.UserDAO;
import entity.UserEntity;
import session.UserSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAOImpl implements UserDAO{
    // repository 인스턴스 1개 생성
    private static final UserDAOImpl instance = new UserDAOImpl();
    private Map<String, UserEntity> store = new HashMap<>();

    // private 생성자를 통해 외부에서 인스턴스 생성을 방지
    private UserDAOImpl() {
        // Rank Test User
        store.put("testId1", new UserEntity("testId1", "2134", "test1", 234));
        store.put("testId2", new UserEntity("testId2", "2134", "test2", 1));
        store.put("testId3", new UserEntity("testId3", "2134", "test3", 100));
        store.put("testId4", new UserEntity("testId4", "2134", "test4", 300));
    }

    // 싱글톤 인스턴스를 반환하는 메소드
    public static UserDAOImpl getInstance() {
        return instance;
    }

    // UserEntity를 저장하는 메소드
    @Override
    public String signup(UserEntity userEntity) {
        String id = userEntity.getId();
        String nickname = userEntity.getNickname();
        store.put(id, userEntity);
        return nickname;
    }

    @Override
    public UserEntity getUser(String id) {
        return store.get(id);
    }

    @Override
    public List<UserEntity> getUsers() {
        return new ArrayList<>(store.values());
    }
    
    public void saveUser(UserEntity user) {
        store.put(user.getId(), user);
    }

    // ID 중복 검사 메서드
    public boolean isIdDuplicate(String id) {
        return store.containsKey(id);
    }
}
