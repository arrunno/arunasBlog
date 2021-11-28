package com.arunas.blog.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="posts")
public class Post {
    @Id
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false)
    private String id;
    private LocalDateTime postDate;
    private String topic;
    private String contents;
    private String authorEmail;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("postDate")
    private Set<Comment> comments = new HashSet<>(0);

    @PreRemove
    public void deleteCommentFromPost(){
        this.comments.forEach(comment -> comment.dismissPost());
        this.comments.clear();
    }

    public void dismissComment(Comment comment){
        this.comments.remove(comment);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
