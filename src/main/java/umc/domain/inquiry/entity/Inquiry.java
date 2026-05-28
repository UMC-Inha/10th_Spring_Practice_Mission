package umc.domain.inquiry.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import umc.domain.inquiry.enums.InquiryStatus;
import umc.domain.inquiry.enums.InquiryType;
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
@EntityListeners(AuditingEntityListener.class)
public class Inquiry extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "inquiry_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private InquiryType inquiryType;

    @Column(name = "inquiry_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private InquiryStatus inquiryStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "inquiry", cascade = CascadeType.REMOVE)
    private List<InquiryPhoto> inquiryPhotoList = new ArrayList<>();

}
