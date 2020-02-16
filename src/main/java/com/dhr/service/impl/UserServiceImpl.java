package com.dhr.service.impl;

import java.util.*;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpSession;

import com.dhr.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    @Autowired
    private HttpSession session;

    @Override
    public Integer changeOne(User user) {
        super.preSave(user);
        return repository.modifyUser(user.getUsername(), user.getValid(), user.getId(), user.getUpdaterId());
    }

    @Override
    public User login(User user) {
        User userTemp = repository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        String uniqueToken = UUID.randomUUID().toString();
        if (null != userTemp) {
            session.setAttribute("userId", userTemp.getId());
            redisUtil.set(USER_REDIS_SESSION + userTemp.getId(), uniqueToken);
        }
        return userTemp;
    }

    @Override
    public Map<String, Object> register(User user) {
        List<User> userlist = super.findListByExample(user);
        Map<String, Object> map = new HashMap<>();
        if (userlist.size() == 0) {
            User new_user = super.save(user);
            map.put("status", true);
            map.put("msg", new_user.getId());
        } else {
            map.put("status", false);
            map.put("msg", "该用户名已被占用");
        }
        return map;
    }

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

    @Override
    public List<User> tiaojian3(User user) {
        return repository.findAll((root, query, cb) -> cb.equal(root.get("username"), user.getUsername()));
    }

    @Override
    public List<User> tiaojian4(User user) {
        Specification spe = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return getPredicate(user, root, criteriaBuilder);
            }
        };
        return repository.findAll(spe);
    }

    @Override
    public List<User> tiaojian5(User user) {
        return repository.findAll(((root, criteriaQuery, criteriaBuilder) -> getPredicate(user, root, criteriaBuilder)));
    }

    /**
     * 组装条件
     *
     * @param user
     * @param root
     * @param criteriaBuilder
     * @return
     */
    private Predicate getPredicate(User user, Root<User> root, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();
        List<Expression<Boolean>> expressions = predicate.getExpressions();
        if (3 > 1) {
            expressions.add(criteriaBuilder.like(root.get("username"), user.getUsername() + "%"));
        }
        if (2 > 0) {
            expressions.add(criteriaBuilder.equal(root.get("valid"), user.getValid()));
        }
        return predicate;
    }

    @Override
    public Page<User> getAll(Integer pageNum, Integer pageSize, String sortParams) {
        Sort sort = new Sort(Sort.Direction.DESC, sortParams);
        PageRequest pr = new PageRequest(pageNum - 1, pageSize, sort);
        return repository.findAll(pr);
    }

    @Override
    public User findUserByUsername(String username) {
        return repository.findUserByUsername(username);
    }
}
