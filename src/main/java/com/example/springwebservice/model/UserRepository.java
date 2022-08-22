package com.example.springwebservice.model;

import com.example.springwebservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);//Id = User.java 中的 private int id;，首字母變大寫
    Long deleteById(int id);



    @Query(value = "SELECT *  FROM member WHERE age >= ?1",nativeQuery = true)
    List<User> findByAgeGreaterThanEqual(int age);

    @Query(value = "SELECT * FROM member ORDER BY age DESC, id DESC", nativeQuery = true)
    List<User> findByOrderByAgeDesc();

    //@Query(value = "SELECT * FROM member ORDER BY age ASC", nativeQuery = true)
    //List<User> findByAgeAsc();

    @Query(value = "SELECT * FROM member WHERE name = ?1 AND age = ?2", nativeQuery = true)//native query:告訴程式這個是原生的SQL語句
    List<User> findByNameAndAge(String name, int age);

    @Query(value = "INSERT INTO member(id, name, age) VALUES(?1, ?2, ?3)", nativeQuery = true)
    void createUserBySql(int id, String name, int age);
    @Transactional
    @Modifying
    @Query(value = "UPDATE member SET age = ?1, name = ?2 WHERE id = ?3", nativeQuery = true)
    int updateUserBySql(int age, String name, int id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM member WHERE name = ?1 AND age = ?2", nativeQuery = true)
    void deleteUserByNameAndAge(String name, int age);

    @Query(value = "SELECT * FROM member ORDER BY Age ASC, Id ASC", nativeQuery = true)
    List<User> findUserOrderByNameAndAge();

}
