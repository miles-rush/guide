package com.miles.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select s from User s where s.account = ?1 and s.password = ?2")
    public User findByUserAccountAndPassword(String account,String password);

    @Query("select s from User s where  s.account = ?1")
    public User findByUserAccount(String account);
}
