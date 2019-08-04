package com.example.datejpa01.domain;


import com.example.datejpa01.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

}
