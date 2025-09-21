package com.libraryapp.librarymanagementapp.mapper;

import com.libraryapp.librarymanagementapp.dto.member.request.CreateMemberRequest;
import com.libraryapp.librarymanagementapp.dto.member.request.UpdateMemberByIdRequest;
import com.libraryapp.librarymanagementapp.dto.member.response.*;
import com.libraryapp.librarymanagementapp.entity.Fine;
import com.libraryapp.librarymanagementapp.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface MemberMapper {
    // dışarıdan erişim için
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    Member createMemberRequestToMember(CreateMemberRequest createMemberRequest);
    CreatedMemberResponse memberToCreateResponseMember(Member member);

    GetMemberByIdResponse toGetByIdMemberResponse(Member member);
    GetMemberByStatusAndEmailResponse toGetByStatusAndEmailResponse(Member member);

    Member updateMemberByIdRequestToMember(UpdateMemberByIdRequest updateMemberByIdRequest);
    UpdatedMemberByIdResponse memberToUpdatedMemberByIdResponse(Member member);

    GetFinesByIdResponse memberToGetFinesByIdResponse(Member member);

    List<GetFineListByIdResponse> toGetFineListByIdResponse(List<Fine> fineList);

}
