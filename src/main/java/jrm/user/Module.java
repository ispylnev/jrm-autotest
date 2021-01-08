package jrm.user;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
public class Module {

    @RequiredArgsConstructor
    @Getter
    public enum Spring {
        SPRING_CORE("spring-core"),
        SPRING_JDBC("spring-jdbc"),
        SPRING_HIBERNATE("spring-hibernate"),
        SPRING_WEB("spring-web");

        private final String name;
    }

    @RequiredArgsConstructor
    @Getter
    public enum Java {
        JAVA_CORE("java-core"),
        JAVA_ADVANCED("java-advanced"),
        JAVA_REFACTORING("java-refactoring");

        private final String name;
    }

    @RequiredArgsConstructor
    @Getter
    public enum General {
        IT("it"),
        INSTRUMENTS("instruments"),
        PLATFORM("platform");

        private final String name;
    }

    @RequiredArgsConstructor
    @Getter
    public enum QualityAssurance {
        UNIT("unit");

        private final String name;
    }

    @RequiredArgsConstructor
    @Getter
    public enum Database {
        DATABASE_BASIC("database-basic");

        private final String name;
    }

    @RequiredArgsConstructor
    @Getter
    public enum Devops {
        JENKINS("jenkins"),
        ANSIBLE("ansible"),
        DOCKER("docker"),
        LINUX("linux");

        private final String name;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "available")
    private Boolean available;

}
