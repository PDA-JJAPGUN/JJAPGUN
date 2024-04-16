package dao.impl;

import dao.UserDAO;
import entity.UserEntity;
import session.UserSession;

import java.util.HashMap;
import java.util.Map;

public class UserDAOImpl implements UserDAO{
    // repository 인스턴스 1개 생성
    private static final UserDAOImpl instance = new UserDAOImpl();
    private Map<String, UserEntity> store = new HashMap<>();

    // private 생성자를 통해 외부에서 인스턴스 생성을 방지
    private UserDAOImpl() {}

    // 싱글톤 인스턴스를 반환하는 메소드
    public static UserDAOImpl getInstance() {
        return instance;
    }

    // UserEntity를 저장하는 메소드
    @Override
    public String signup(UserEntity userEntity) {
        String id = userEntity.getId();
        store.put(id, userEntity);
        return id;
    }

    @Override
    public Boolean login(UserEntity userEntity) {
        // 전달 받은 UserDto에서 ID 가져오기
        String id = userEntity.getId();
        // store에서 id에 해당하는 값을 가져오기
        UserEntity storeUser = store.get(id);

        // store에서 id로 가져온 user가 null이 아니고,
        // 전달받은 user의 password와 Map에 저장되어있는 password가 같을 경우
        if (storeUser != null && userEntity.getPassword().equals(storeUser.getPassword())) {
            // 사용자 인증 성공
            UserSession.getInstance().setLoggedInUserId(id); // 로그인된 사용자의 ID를 UserSession에 저장
            // 사용자 인증 성공
            return true;
        } else {
            // 사용자 인증 실패
            return false;
        }
    }

    @Override
    public UserEntity findUser(String id) {
        return store.get(id);
    }


}
