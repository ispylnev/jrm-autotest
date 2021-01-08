package jrm.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserTaskId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "task_id")
    private Long taskId;

}