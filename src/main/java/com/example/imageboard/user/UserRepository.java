package com.example.imageboard.user;

//import org.example.imageboard.dtos.projections.UserSummaryDto;
import com.example.imageboard.entity.EntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends EntityRepository<User, Long> {
    User findByUsernameIgnoreCase(String username);
    User findByEmailIgnoreCase(String email);
    User findByResetToken(String resetToken);

    // Find all active users (full entity) with sorting
    @Query("SELECT u FROM User u WHERE u.status = com.example.imageboard.user.AccountStatus.ACTIVE")
    Page<User> findAllActiveUsersWithDetails(Pageable pageable, Sort sort);

    // Find active userEntity summaries (projection)
    @Query("SELECT u.id as id, u.username as username, u.avatarUrl as avatarUrl FROM User u WHERE u.status = com.example.imageboard.user.AccountStatus.ACTIVE")
    Page<UserDto> findActiveUsers(Pageable pageable);
    List<User> findByStatus(AccountStatus status);
    List<User> findByRole(UserRole role);
    // Sort by join date
    Page<User> findAllActiveUsersSortedByJoinDate(Pageable pageable);

    // Entity Graph (Optional)
    @EntityGraph(attributePaths = {"comments", "forumThreads"}) // Example: Eagerly fetch comments and forumThreads
    Optional<User> findByIdWithCommentsAndForumThreads(Long id);

    // Custom Queries (for complex scenarios)
    @Query("SELECT u FROM User u JOIN u.comments p WHERE p.createdAt >= :startDate")
    Page<User> findUsersWithRecentComments(LocalDateTime startDate, Pageable pageable);

    // ... (other UserRepository methods)

    @Query("SELECT u FROM User u WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(u.email) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<User> searchUsers(@Param("query") String query);

}
