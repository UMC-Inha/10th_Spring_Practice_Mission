package umc.domain.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.domain.common.BaseEntity;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id", nullable = false)
    private Long id;

    @Column(name = "store_nm", nullable = false, length = 50)
    private String store_nm;

    @Column(name = "region_nm", nullable = false, length = 20)
    private String region_nm;

    @Column(name = "open_dt", nullable = false, length = 6)
    private String open_dt;

    @Column(name = "close_dt", nullable = false, length = 6)
    private String close_dt;
}
