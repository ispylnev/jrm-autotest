package jrm.user;

import jrm.task.TaskStep;
import jrm.task.TaskStepStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "jrm_user_task_step")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTaskStep {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStep taskStep;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserTask userTask;

    @Enumerated(value = EnumType.STRING)
    private TaskStepStatus status;

}
