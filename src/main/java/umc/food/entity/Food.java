package umc.food.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.food.enums.FoodName;
import umc.member.entity.mapping.MemberFood;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food")
public class Food {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private FoodName name;

    @OneToMany(mappedBy = "food")
    private List<MemberFood> memberFoodList = new ArrayList<>();
}
