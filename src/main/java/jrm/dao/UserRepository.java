package jrm.dao;


import jrm.exeptions.UserNotFoundException;
import jrm.user.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


    @Modifying
    @Transactional
    @Query(value = "UPDATE jrm_user SET  created_on = ?1 WHERE id = ?2", nativeQuery = true)
    void updateUserCreatedDate(@Param("created_on") Timestamp createdOn, @Param("id") Long id);


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
