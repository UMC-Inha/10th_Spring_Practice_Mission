package umc.domain.mission.dto;

public class HomeResDTO {
    public record Home(
            String regionName, //상단 지역 이름

            Integer completeMissionList, //원형 프로그레스 바
            Integer targetMissCount, //서비스에서 받아오기

            MissionResDTO.MissionPreviewListDTO missionPreviewListDTO //기존 DTO 재사용
            ){}
}
