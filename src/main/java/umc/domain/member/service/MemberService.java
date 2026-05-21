package umc.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.category.entity.FoodCategory;
import umc.domain.category.entity.mapping.MemberPreferredCategory;
import umc.domain.category.entity.mapping.StoreCategory;
import umc.domain.category.enums.CategoryName;
import umc.domain.category.repository.CategoryRepository;
import umc.domain.category.repository.MemberPreferredCategoryRepository;
import umc.domain.category.repository.StoreCategoryRepository;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.mapping.TermAgreement;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.member.repository.TermAgreementRepository;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.enums.MemberMissionStatus;
import umc.domain.mission.repository.MemberMissionRepository;
import umc.domain.mission.repository.MissionRepository;
import umc.domain.store.entity.Region;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.RegionRepository;
import umc.domain.term.entity.Term;
import umc.domain.term.enums.TermName;
import umc.domain.term.repository.TermRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final RegionRepository regionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final StoreCategoryRepository storeCategoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;
    private final TermRepository termRepository;
    private final MemberPreferredCategoryRepository memberPreferredCategoryRepository;
    private final TermAgreementRepository termAgreementRepository;

    @Transactional(readOnly = true)
    public MemberResDTO.GetInfo getInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toGetInfo(member);
    }

    @Transactional(readOnly = true)
    public MemberResDTO.Home getHome(Long memberId, Long regionId, Integer page){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new StoreException((StoreErrorCode.REGION_NOT_FOUND)));

        Pageable pageable = PageRequest.of(page, 10);
        Page<Mission> missions = missionRepository.findHomeMissions(regionId, pageable);

        List<Long> storeIds = missions.getContent().stream()
                .map(mission -> mission.getStore().getId())
                .distinct()
                .toList();

        Map<Long, String> storeCategoryMap = Map.of();

        if(!storeIds.isEmpty()){
            List<StoreCategory> storeCategories =
                    storeCategoryRepository.findAllByStoreIdsWithFoodCategory(storeIds);

            storeCategoryMap = storeCategories.stream()
                    .collect(Collectors.groupingBy(
                            storeCategory -> storeCategory.getStore().getId(),
                            Collectors.mapping(
                                    storeCategory -> storeCategory.getFoodCategory().getName().name(),
                                    Collectors.joining(", ")
                            )
                    ));
        }

        int completedMissionCount = memberMissionRepository.countByMemberIdAndStatus(
                memberId,
                MemberMissionStatus.COMPLETED
        );

        return MemberConverter.toHome(member, region, missions, completedMissionCount, storeCategoryMap);
    }

    @Transactional
    public MemberResDTO.CreateMember createMember(MemberReqDTO.CreateMember request) {

        validateEmailNotExist(request.email());

        Member savedMember = saveMember(request);

        saveMemberPreferredCategory(savedMember, request.categoryNameList());

        saveTermAgreement(savedMember, request.agree());

        return MemberConverter.toCreateMember(savedMember);
    }

    private void validateEmailNotExist(String email){
        if(memberRepository.existsByEmail(email)){
            throw new MemberException(MemberErrorCode.EMAIL_ALREADY_EXIST);
        }
    }

    private Member saveMember(MemberReqDTO.CreateMember request){
        Member member = Member.builder()
                .name(request.name())
                .gender(request.gender())
                .birth(request.birth())
                .address(request.address())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .currentPoint(0L)
                .build();

        return memberRepository.save(member);
    }

    private void saveMemberPreferredCategory(Member member, List<CategoryName> categoryNameList){
        List<FoodCategory> categoryList = categoryRepository.findAllByNameIn(categoryNameList);

        List<MemberPreferredCategory> memberPreferredCategoryList = categoryList.stream()
                .map(category -> MemberPreferredCategory.builder()
                        .member(member)
                        .foodCategory(category)
                        .build())
                .toList();

        memberPreferredCategoryRepository.saveAll(memberPreferredCategoryList);
    }

    private void saveTermAgreement(Member member, MemberReqDTO.CreateMember.Agree agree){
        Map<TermName, Boolean> agreementMap = Map.of(
                TermName.AGE, agree.age(),
                TermName.SERVICE, agree.service(),
                TermName.LOCATION, agree.location(),
                TermName.PRIVACY, agree.privacy(),
                TermName.MARKETING, agree.marketing()
        );

        List<TermName> termNameList = agreementMap.keySet().stream().toList();

        List<Term> termList = termRepository.findAllByNameIn(termNameList);

        List<TermAgreement> termAgreementList = termList.stream()
                .map(term -> TermAgreement.builder()
                        .member(member)
                        .term(term)
                        .isAgreed(agreementMap.get(term.getName()))
                        .build())
                .toList();

        termAgreementRepository.saveAll(termAgreementList);
    }
}
