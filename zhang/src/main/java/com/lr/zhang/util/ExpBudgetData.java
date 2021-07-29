package com.lr.zhang.util;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * FrExpBudgetModel（直接对应数据库的配置 由生成工具产生）
 */

public class ExpBudgetData implements Serializable {

    /**
     * 版本号
     */
    private static final long serialVersionUID = 1L;
    /**
     * 科目编码
     */
    private String proCode;
    /**
     * 科目分类
     */
    private String proType;
    /**
     * 科目名称
     */
    private String proName;
    /**
     * 支出年初预算数
     */
    private BigDecimal expStartBgt;


    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public BigDecimal getExpStartBgt() {
        return expStartBgt;
    }

    public void setExpStartBgt(BigDecimal expStartBgt) {
        this.expStartBgt = expStartBgt;
    }
}


