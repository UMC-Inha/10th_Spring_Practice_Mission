package umc.domain.mission.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.domain.common.BaseEntity;
import umc.domain.store.entity.Store;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id", nullable = false)
    private Long id;

    @Column(name = "conditional", nullable = false, length = 200)
    private String conditional;

    @Column(name = "start_dt", nullable = false)
    private LocalDate start_dt;

    @Column(name = "end_dt", nullable = false)
    private LocalDate end_dt;

    @Column(name = "reward_point", nullable = false)
    private Integer reward_point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
}
