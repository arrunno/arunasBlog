package com.arunas.blog.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Posts")
public class Post {
    @Id
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
//    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private UUID id;
    private LocalDateTime postDate;
    private String topic;
    private String contents;
    private String authorEmail;
    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL)
    @JoinColumn(name="fk_post", referencedColumnName = "id")
    Set<Comment> comments = new HashSet<>();

//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
//    private Collection<Comment> comments;

}
