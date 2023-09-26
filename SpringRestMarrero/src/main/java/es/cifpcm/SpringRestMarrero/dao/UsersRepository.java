package es.cifpcm.SpringRestMarrero.dao;

import es.cifpcm.SpringRestMarrero.modelo.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsuname(String usuname);

}