package com.example.imageboard.user;

import com.example.imageboard.enums.UserRole;
import com.example.imageboard.enums.UserStatus;
//import org.example.imageboard.dtos.projections.UserSummaryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameIgnoreCase(String username);
    User findByEmailIgnoreCase(String email);
    User findByResetToken(String resetToken);

    // Find all active users (full entity) with sorting
    @Query("SELECT u FROM User u WHERE u.status = com.example.imageboard.enums.UserStatus.ACTIVE")
    Page<User> findAllActiveUsersWithDetails(Pageable pageable, Sort sort);

    // Find active user summaries (projection)
    @Query("SELECT u.id as id, u.username as username, u.avatarUrl as avatarUrl FROM User u WHERE u.status = com.example.imageboard.enums.UserStatus.ACTIVE")
    Page<UserDto> findActiveUserSummaries(Pageable pageable);
    List<User> findByStatus(UserStatus status);
    List<User> findByRole(UserRole role);
    // Sort by join date
    Page<User> findAllActiveUsersSortedByJoinDate(Pageable pageable);

    // Entity Graph (Optional)
    @EntityGraph(attributePaths = {"comments", "forumThreads"}) // Example: Eagerly fetch comments and forumThreads
    Optional<User> findByIdWithPostsAndThreads(Long id);

    // Custom Queries (for complex scenarios)
    @Query("SELECT u FROM User u JOIN u.posts p WHERE p.createdAt >= :startDate")
    Page<User> findUsersWithRecentPosts(LocalDateTime startDate, Pageable pageable);
}
