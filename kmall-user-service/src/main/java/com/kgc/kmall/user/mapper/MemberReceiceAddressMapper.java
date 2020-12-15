package com.kgc.kmall.user.mapper;

import com.kgc.kmall.bean.MemberReceiceAddress;
import com.kgc.kmall.bean.MemberReceiceAddressExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberReceiceAddressMapper {
    int countByExample(MemberReceiceAddressExample example);

    int deleteByExample(MemberReceiceAddressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberReceiceAddress record);

    int insertSelective(MemberReceiceAddress record);

    List<MemberReceiceAddress> selectByExample(MemberReceiceAddressExample example);

    MemberReceiceAddress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MemberReceiceAddress record, @Param("example") MemberReceiceAddressExample example);

    int updateByExample(@Param("record") MemberReceiceAddress record, @Param("example") MemberReceiceAddressExample example);

    int updateByPrimaryKeySelective(MemberReceiceAddress record);

    int updateByPrimaryKey(MemberReceiceAddress record);
}