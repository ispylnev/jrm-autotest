package jrm.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jrm_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String name;
    private String password;
    private String email;
    private String provider;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "created_on")
    private Date createdOn = new Date();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<UserTask> tasks = new HashSet<>();

    @Enumerated(value = EnumType.STRING)
    private UserStatus status;

}
