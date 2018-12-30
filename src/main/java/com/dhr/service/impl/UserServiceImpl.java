package com.dhr.service.impl;

import java.util.*;

import javax.persistence.criteria.*;

import com.dhr.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhr.entity.User;
import com.dhr.repository.UserRepository;

/**
 * Class description
 *
 *
 * @version        $version$, $date$, 18/12/16
 * @author         donghuarui.
 */
@Service
@Transactional(
    rollbackFor = Exception.class
)
public class UserServiceImpl extends BaseServiceImpl<User,UserRepository> implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public static final String USER_REDIS_SESSION = "user:redis:session:";

    /**
     * Method description
     *
     *
     * @param user
     *
     * @return
     */
    @Override
    public Integer changeOne(User user) {
        return repository.modifyUser(user.getUsername(), user.getPassword(), user.getId());
    }

    /**
     * Method description
     *
     *
     * @param user
     *
     * @return
     */
    @Override
    public User login(User user) {
        User userTemp = repository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        String uniqueToken = UUID.randomUUID().toString();
        if(null != userTemp){
            redisUtil.set(USER_REDIS_SESSION+userTemp.getId(),uniqueToken);
            System.err.print(redisUtil.hasKey("myname"));
        }
        return userTemp;
    }

    /**
     * 注册
     *
     *
     * @param user
     *
     * @return
     */
    @Override
    public User register(User user) {
        User temp = new User();
        temp.setUsername(user.getUsername());
        List<User> userlist = findListByExample(temp);
        System.err.print(userlist.size());
        if(userlist.size()==0){
            User uu =  repository.save(user);
            System.err.print(uu);
            return uu;
        }else {
            return null;
        }

    }

    /**
     * Method description
     *
     *
     * @param user
     *
     * @return
     */
    @Override
    public List<User> tiaojian(User user) {
        Specification querySpecifi = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path path = root.get("username");

                return criteriaBuilder.like(path, "%" + user.getUsername() + "%");
            }
        };
        return repository.findAll(querySpecifi);
    }
    /**
     * 获取所有用户
     *
     *
     * @param page
     *
     * @return
     */
    @Override
    public Page<User> getAll(Pageable page) {
        Page<User> res = repository.findAll(page);
//        redisTemplate.opsForValue().set("myname",666);
//        String s = redisTemplate.opsForValue().get("dubbo")+"";
//        String a =   redisTemplate.opsForHash().get("dubbo","name")+"";
//         Map<Object,Object> map = redisTemplate.opsForHash().entries("dubbo");
//        System.out.println(map);
//        System.err.print(s);
//        logger.info(s);
        return res;
    }
}
