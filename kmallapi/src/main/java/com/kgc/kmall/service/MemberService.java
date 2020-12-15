package com.kgc.kmall.service;

import com.kgc.kmall.bean.Member;

import java.util.List;

/**
 * @author 李锡良
 * @create 2020-12-15 16:17
 */
public interface MemberService {

    List<Member> selectMemberAll();

}
