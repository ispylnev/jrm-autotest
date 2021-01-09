package jrm.task;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "task")

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"steps", "parents"})
@ToString(exclude = {"steps"})
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "article", columnDefinition = "TEXT")
    private String article;


    // relations

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "task_parents",
            joinColumns = {@JoinColumn(name = "task_id")},
            inverseJoinColumns = {@JoinColumn(name = "parent_task_id")})
    private Set<Task> parents = new HashSet<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<TaskStep> steps = new HashSet<>();

    @ManyToOne
    private Module module;


    // system properties

    @Column(name = "task_folder")
    private String taskFolder;

    @Column(name = "task_archive_name")
    private String taskArchiveName = "task-template.zip";

    @Column(name = "task_golden_archive_name")
    private String taskGoldenArchiveName = "golden-task.zip";

    public String getTaskLocation() {
        return module == null ? null : getModule().getCode() + File.separator + getTaskFolder();
    }

    public String getTaskArchiveLocation() {
        return getTaskLocation() == null ? null : getTaskLocation() + File.separator + getTaskArchiveName();
    }

    public String getGoldenTaskArchiveLocation() {
        return getTaskLocation() == null ? null : getTaskLocation() + File.separator + getTaskGoldenArchiveName();
    }

    public boolean isParent() {
        return CollectionUtils.isEmpty(parents);
    }

}