package com.revature.RevSpeed.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.RevSpeed.models.UserServiceLink;
import org.springframework.stereotype.Repository;

@Repository
public interface UserServiceLinkRepository extends JpaRepository<UserServiceLink,Long> {
}
