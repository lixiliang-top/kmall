package com.kgc.kmall.user.service;

import com.kgc.kmall.bean.PmsBaseAttrInfo;
import com.kgc.kmall.bean.PmsBaseAttrInfoExample;
import com.kgc.kmall.bean.PmsBaseAttrValue;
import com.kgc.kmall.bean.PmsBaseAttrValueExample;
import com.kgc.kmall.service.AttrService;
import com.kgc.kmall.user.mapper.PmsBaseAttrInfoMapper;
import com.kgc.kmall.user.mapper.PmsBaseAttrValueMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 李锡良
 * @create 2020-12-16 16:16
 */
@Component
@Service
public class AttrServiceImpl implements AttrService {

    @Resource
    PmsBaseAttrInfoMapper attrInfoMapper;

    @Resource
    PmsBaseAttrValueMapper attrValueMapper;

    @Override
    public List<PmsBaseAttrInfo> selectAttrInfoAll(Long catalog3Id) {
        PmsBaseAttrInfoExample example = new PmsBaseAttrInfoExample();
        PmsBaseAttrInfoExample.Criteria criteria = example.createCriteria();
        if (catalog3Id!=0&&catalog3Id!=null){
            criteria.andCatalog3IdEqualTo(catalog3Id);
        }
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = attrInfoMapper.selectByExample(example);
        for (PmsBaseAttrInfo pmsBaseAttrInfo : pmsBaseAttrInfos) {
            PmsBaseAttrValueExample example1=new PmsBaseAttrValueExample();
            PmsBaseAttrValueExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andAttrIdEqualTo(pmsBaseAttrInfo.getId());
            List<PmsBaseAttrValue> pmsBaseAttrValues = attrValueMapper.selectByExample(example1);
            pmsBaseAttrInfo.setAttrValueList(pmsBaseAttrValues);
        }
        return pmsBaseAttrInfos;
    }

    @Override
    public Integer add(PmsBaseAttrInfo pmsBaseAttrInfo) {
        int i = 0;
        //判断是添加还是修改
        if (pmsBaseAttrInfo.getId()==0){
            //添加
            i = attrInfoMapper.insertSelective(pmsBaseAttrInfo);
        }else{
            //修改
            i = attrInfoMapper.updateByPrimaryKeySelective(pmsBaseAttrInfo);
        }
        //删除原属性值
        PmsBaseAttrValueExample example = new PmsBaseAttrValueExample();
        PmsBaseAttrValueExample.Criteria criteria = example.createCriteria();
        criteria.andAttrIdEqualTo(pmsBaseAttrInfo.getId());
        i = attrValueMapper.deleteByExample(example);
        if (pmsBaseAttrInfo.getAttrValueList().size()>0){
            i = attrValueMapper.insertBatch(pmsBaseAttrInfo.getId(),pmsBaseAttrInfo.getAttrValueList());
        }
        return i;
    }

    @Override
    public List<PmsBaseAttrValue> selectAttrInfoId(Long attrId) {
        PmsBaseAttrValueExample example = new PmsBaseAttrValueExample();
        PmsBaseAttrValueExample.Criteria criteria = example.createCriteria();
        criteria.andAttrIdEqualTo(attrId);
        List<PmsBaseAttrValue> pmsBaseAttrValues = attrValueMapper.selectByExample(example);
        return pmsBaseAttrValues;
    }
}
