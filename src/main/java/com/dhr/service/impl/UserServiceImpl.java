package com.dhr.service.impl;

import java.util.*;

import javax.persistence.criteria.*;

import com.dhr.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhr.entity.User;
import com.dhr.repository.UserRepository;

/**
 * Class description
 *
 * @author donghuarui.
 * @version $version$, $date$, 18/12/16
 */
@Service
@Transactional(
        rollbackFor = Exception.class
)
public class UserServiceImpl extends BaseServiceImpl<User, UserRepository> implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public static final String USER_REDIS_SESSION = "user:redis:session:";

    /**
     * Method description
     *
     * @param user
     * @return
     */
    @Override
    public Integer changeOne(User user) {
        return repository.modifyUser(user.getUsername(), user.getId());
    }

    /**
     * Method description
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        User userTemp = repository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        String uniqueToken = UUID.randomUUID().toString();
        if (null != userTemp) {
            redisUtil.set(USER_REDIS_SESSION + userTemp.getId(), uniqueToken);
            //System.err.print(redisUtil.hasKey("myname"));
        }
        return userTemp;
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @Override
    public Map<String, Object> register(User user) {
        List<User> userlist = findListByExample(user);
        Map<String, Object> map = new HashMap<>();
        if (userlist.size() == 0) {
            User new_user = repository.save(user);
            map.put("status", true);
            map.put("msg", new_user.getId());
        } else {
            map.put("status", false);
            map.put("msg", "该用户名已被占用");
        }
        return map;
    }

    /**
     * Method description
     *
     * @param user
     * @return
     */
    @Override
    public List<User> tiaojian(User user) {
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder cb) {
                Path path = root.get("username");
                Predicate predicate = cb.like(path, "%" + user.getUsername() + "%");
                return predicate;
            }
        };
        return repository.findAll(spec);
    }

    @Override
    public List<User> tiaojian2(User user) {
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("username"), user.getUsername());
            }
        };
        return repository.findAll(spec);
    }


    /**
     * 获取所有用户
     *
     * @param page
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

    @Override
    public List<User> getAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public User findUserByUsername(String username) {
        return repository.findUserByUsername(username);
    }
}
