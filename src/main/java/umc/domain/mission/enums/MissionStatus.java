package umc.domain.mission.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MissionStatus {

    PENDING("도전 중"),
    COMPLETE("완료"),
    SUCCESS_REQUESTED("성공 요청");

    private final String description;
}
