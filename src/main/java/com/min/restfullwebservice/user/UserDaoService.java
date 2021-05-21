package com.min.restfullwebservice.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
/**
 * 일단 db와 jpa를 사용하지 않고 구현하기에 db역할을하는 List를 놓고 초기값을 미리 넣어놓음
 * 여기서 중요한게 static{ ...}이런 구현방식은 처음봐서 신선. 암튼 db가 없어서 서비스 단에서
 * 멈춤
 * */
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int userCount = 3;

    static {
        users.add(new User(1,"minyoung",new Date(),"pass1","961014-0000000"));
        users.add(new User(2,"jaeyoung",new Date(),"pass2","961014-0000001"));
        users.add(new User(3,"jeongyeon",new Date(),"pass3","000000-2222222"));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if (user.getId() == null){
            user.setId(++userCount);
        }

        users.add(user);
        return user;
    }

    public User findOne(int id){
        for (User user: users){
            if (user.getId() == id){
                return user;
            }
        }

        return null;
    }

    public User updateUser(int id,User user){
        Iterator<User> iterator = users.iterator();
        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getJoinDate());


        while(iterator.hasNext()){
            User user2 = iterator.next();
            if (user2.getId() == id){
                user2.setName(user.getName());
                return user2;
            }
        }
        return null;
    }

    public User deleteById(int id){
        Iterator<User> iterator = users.iterator();

        while(iterator.hasNext()){
            User user = iterator.next();

            if (user.getId() == id){
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
