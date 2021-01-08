package jrm.user;


import jrm.task.Task;
import jrm.task.TaskStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "task", "steps"})
@EqualsAndHashCode(exclude = {"user", "task", "steps"})

public class UserTask {

    @EmbeddedId
    UserTaskId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("taskId")
    private Task task;

    @Column(name = "generated_task_id")
    private String generatedTaskId = UUID.randomUUID().toString();

    @Column(name = "created_on")
    private Date createdOn = new Date();

    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;

    private Integer rating;

    @OneToMany(
            mappedBy = "userTask",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<UserTaskStep> steps = new HashSet<>();

    public UserTask(User user, Task task) {
        this.user = user;
        this.task = task;
        this.id = new UserTaskId(user.getId(), task.getId());
    }

}