package umc.domain.store.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long store_id;

    private String store_nm;
    private String region_nm;
    private String open_dt;
    private String close_dt;
    private String chg_dt;
    private String chg_tm;
}
