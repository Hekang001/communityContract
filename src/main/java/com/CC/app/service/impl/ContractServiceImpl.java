package com.CC.app.service.impl;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.common.utils.Query;
import com.CC.app.dao.ContractDao;
import com.CC.app.entity.ContractEntity;
import com.CC.app.vo.ContractInfoVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.CC.app.service.ContractService;


@Service("contractService")
public class ContractServiceImpl extends ServiceImpl<ContractDao, ContractEntity> implements ContractService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ContractEntity> page = this.page(
                new Query<ContractEntity>().getPage(params),
                new QueryWrapper<ContractEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public ContractInfoVo getByDebtId(Long id) {

        List<ContractEntity> contractEntities = baseMapper.selectList(new QueryWrapper<ContractEntity>().eq("debt_id", id));
        ContractInfoVo contractInfoVo = new ContractInfoVo();
        contractInfoVo.setLoanCount(contractEntities.size());
        int finishCount = 0;
        for(ContractEntity contractEntity :contractEntities){
            if(contractEntity.getStatus() >= 5) finishCount++;
        }
        contractInfoVo.setFinishCount(finishCount);
        contractInfoVo.setProcessCount(contractEntities.size() - finishCount);
        return contractInfoVo;
    }

}