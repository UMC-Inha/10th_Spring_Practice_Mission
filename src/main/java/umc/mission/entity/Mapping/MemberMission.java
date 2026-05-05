package umc.mission.entity.Mapping;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.apiPayload.entity.BaseEntity;
import umc.member.entity.Member;
import umc.mission.entity.Mission;
import umc.mission.enums.MissionStatus;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member_mission")
public class MemberMission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isComplete", nullable = false)
    @Enumerated(EnumType.STRING)
    private MissionStatus isComplete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    public void completeMission() {
        this.isComplete = MissionStatus.COMPLETE;
    }
}
