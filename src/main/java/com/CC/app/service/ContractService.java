package com.CC.app.service;

import com.CC.app.common.utils.PageUtils;
import com.CC.app.entity.ContractEntity;
import com.CC.app.vo.ContractInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface ContractService extends IService<ContractEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ContractInfoVo getByDebtId(Long id);
}

