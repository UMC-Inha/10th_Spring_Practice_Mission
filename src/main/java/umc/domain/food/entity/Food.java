package umc.domain.food.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.domain.member.entity.mapping.MemberFood;
import umc.domain.mission.entity.Mission;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false) //음식 이름
    private String name;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<MemberFood> memberFood = new ArrayList<>();
}
