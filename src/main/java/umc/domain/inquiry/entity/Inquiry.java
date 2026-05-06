package umc.domain.inquiry.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.domain.inquiry.enums.InquiryType;
import umc.domain.inquiry.enums.ReplyStatus;
import umc.domain.member.entity.Member;
import umc.global.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inquiry")

public class Inquiry extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 50)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ReplyStatus replyStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private InquiryType inquiryType;

    @OneToMany(mappedBy = "inquiry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InquiryPhoto> inquiryPhotos = new ArrayList<>();
}
