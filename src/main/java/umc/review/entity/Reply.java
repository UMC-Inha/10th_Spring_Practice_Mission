package umc.review.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.apiPayload.entity.BaseEntity;
import umc.mission.entity.Mapping.MemberMission;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "reply")
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @OneToOne(mappedBy = "reply", fetch = FetchType.LAZY)
    private Review review;
}
