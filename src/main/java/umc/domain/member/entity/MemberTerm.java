package umc.domain.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="term")
public class MemberTerm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id", nullable = false)
    private Long id;

    @Column(name = "term1_yn", nullable = false, length = 1)
    private Boolean term1_yn;

    @Column(name = "term2_yn", nullable = false, length = 1)
    private Boolean term2_yn;

    @Column(name = "term3_yn", nullable = false, length = 1)
    private Boolean term3_yn;

    @Column(name = "term4_yn", nullable = false, length = 1)
    private Boolean term4_yn;

    @Column(name = "term5_yn", nullable = false, length = 1)
    private Boolean term5_yn;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}