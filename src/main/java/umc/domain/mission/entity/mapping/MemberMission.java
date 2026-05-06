package umc.domain.mission.entity.mapping;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.domain.member.entity.Member;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.enums.MissionStatus;
import umc.global.entity.BaseEntity;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_mission")
public class MemberMission extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "mission_status", nullable = false)
    private MissionStatus missionStatus;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "completed_date")
    private LocalDate completedDate;

}
