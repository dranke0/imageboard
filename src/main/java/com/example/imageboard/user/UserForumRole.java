package com.example.imageboard.user;

import com.example.imageboard.forum.Forum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "user_forum_roles")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(UserForumRoleId.class) // Use a composite primary key
public class UserForumRole {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "forum_id")
    private Forum forum;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}

