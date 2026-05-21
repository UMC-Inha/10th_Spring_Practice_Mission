package umc.domain.member.converter;

import org.springframework.data.domain.Page;
import umc.domain.member.dto.MemberRequestDTO;
import umc.domain.member.dto.MemberResponseDTO;
import umc.domain.member.entity.Member;
import umc.domain.mission.entity.Mission;
import umc.domain.region.entity.Region;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.store.entity.Store;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


public class MemberConverter {
   public static Member toMember(MemberRequestDTO.SignUpDTO dto, String encodedPassword) {

      return Member.builder()
              .email(dto.email())
              .password(encodedPassword)
              .name(dto.name())
              .gender(dto.gender())
              .birth(dto.birth())
              .address(dto.address())
              .point(0)
              .isPhoneVerified(false)
              .build();
   }

   public static MemberResponseDTO.SignUpDTO toSignUpResponseDTO(Member member) {

      return new MemberResponseDTO.SignUpDTO(
              member.getId(),
              member.getCreatedAt()
      );
   }

    public static MemberResponseDTO.HomeDTO.MissionDTO toMissionDTO(Mission mission) {
        Store store = mission.getStore();
        return MemberResponseDTO.HomeDTO.MissionDTO.builder()
                .missionCondition(mission.getMissionCondition())
                .rewardPoint(mission.getRewardPoint())
                .storeName(store.getName())
                .storeFood(store.getFood().getName())
                .deadLine(mission.getDeadLine())
                .build();
    }

    public static MemberResponseDTO.HomeDTO toHomeDTO(
            Member member,
            Region region,
            int completedCount,
            Page<Mission> missionPage
    ) {
        List<MemberResponseDTO.HomeDTO.MissionDTO> missions = missionPage.getContent()
                .stream()
                .map(MemberConverter::toMissionDTO)
                .toList();

        return MemberResponseDTO.HomeDTO.builder()
                .region(region.getName())
                .point(member.getPoint())
                .missionCompletedCount(completedCount)
                .missionGoalCount(10)
                .missionPoint(1000)
                .mission(missions)
                .build();
    }

   public static MemberResponseDTO.MyPageDTO toMyPageViewDTO(Member member) {
      return MemberResponseDTO.MyPageDTO.builder()
              .id(member.getId())
              .name(member.getName())
              .profileUrl(member.getProfileUrl())
              .points(member.getPoint())
              .phoneNumber(member.getPhoneNumber())
              .email(member.getEmail())
              .build();
   }
}