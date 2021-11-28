package com.arunas.blog.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

}
