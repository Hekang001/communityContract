package com.hawki.app.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class WalletVo {
    //余额
    BigDecimal balance;
    //银行卡列表 银行  卡号
    Map<String, String> cardList;
}
