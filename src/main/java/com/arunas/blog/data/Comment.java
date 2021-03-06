package com.arunas.blog.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String contents;
    private LocalDateTime postDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="post_id", referencedColumnName = "id") //// post table id
    @OrderBy("id")
    private Post post;

    @PreRemove
    public void dismissPost(){
        this.post.dismissComment(this);
        this.post = null;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", contents='" + contents + '\'' +
                ", postDate=" + postDate +
                '}';
    }
}
