package com.example.tacocloud.data;

import com.example.tacocloud.domain.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users,Long> {
  Users findByUsername(String username);
}
