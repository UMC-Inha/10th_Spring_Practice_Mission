package umc.domain.Store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.domain.mission.entity.Mission;
import umc.domain.region.entity.City;
import umc.domain.review.entity.Review;

import javax.swing.plaf.synth.Region;
import java.time.LocalDateTime;
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

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "open_time", nullable = false)
    private LocalDateTime openTime;

    @Column(name = "score", nullable = false)
    private Float score;

    @ManyToOne(fetch = FetchType.LAZY) //가게가 속한 지역
    @JoinColumn(name = "region_id", nullable = false)
    private City region;

    //가게 영업시간
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<StoreOpenHour> storeOpenHourList = new ArrayList<>();
    //가게 사진들
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<StorePhoto> storePhotoList = new ArrayList<>();
    //가게에 등록된 미션들
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Mission> missionList = new ArrayList<>();
    //가게에 달린 리뷰들
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviewList;
}
