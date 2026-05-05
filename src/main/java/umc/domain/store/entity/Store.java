package umc.domain.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(nullable = false)
    private Double rate;

    @Column(name = "open_time", nullable = false)
    private String openTime;

    @Column(name = "close_time", nullable = false)
    private String closeTime;

    @Column(name = "manage_number", nullable = false)
    private String manageNumber;
}
