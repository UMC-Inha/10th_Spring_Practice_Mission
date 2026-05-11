package umc.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.food.enums.FoodName;
import umc.location.entity.Location;
import umc.mission.entity.Mission;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FoodName foodCategory;

    @Column(nullable = false)
    private Double storeAverage;

    @Column(nullable = false)
    private Integer reviewCount;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Integer openTime;   

    @Column(nullable = false)
    private Integer closeTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Mission> missionList = new ArrayList<>();
}
