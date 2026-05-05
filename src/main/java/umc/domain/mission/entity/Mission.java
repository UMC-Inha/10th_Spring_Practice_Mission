package umc.domain.mission.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.domain.store.entity.Store;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "point", nullable = false)
    private Integer point;

    @Column(name = "condition", nullable = false)
    private Integer condition;

    @Column(name = "due_date",  nullable = false)
    private LocalDate dueDate;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
