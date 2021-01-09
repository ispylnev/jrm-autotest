package jrm.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "task_step")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskStep {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "recommendation")
    private String recommendation;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    private Task task;
}
