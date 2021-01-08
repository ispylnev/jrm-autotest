package jrm.dao;


import jrm.exeptions.UserNotFoundException;
import jrm.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    default User fetchUserById(Long id) {
        return findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    default User fetchUserByUsername(String username) {
        return findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    Optional<User> findByUsername(String username);

    Optional<User> findBySessionId(String sessionId);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
