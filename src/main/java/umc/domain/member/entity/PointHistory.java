package umc.domain.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import umc.domain.member.enums.PointState;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "point_history")
@EntityListeners(AuditingEntityListener.class)
public class PointHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;

    @Column(name = "point_change", nullable = false)
    private Integer pointChange;

    @Column(name = "point_state", nullable = false)
    @Enumerated(EnumType.STRING)
    private PointState pointState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
