package com.dhr.repository;

import com.dhr.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends BaseRepository<User,String> {
           User findByUsernameAndPassword(String username,String password);

           @Query("update User u set u.username = :username,u.password = :password where u.id = :id")
           @Modifying
           Integer modifyUser(@Param("username") String username,@Param("password") String password, @Param("id") String id);

           @Query(value = "from User where username = ?")
           User findUserByUsername(String username);
}
