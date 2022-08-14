import common.PhyParas;

import java.util.List;

/**
 * 实验公式
 *
 * @author cp
 */
public class Function {
    // region 定义

    /**
     * 三次测量中用到的tp/K½
     */
    private final double tp = 2.48;

    // endregion

    // region 调用计算方法
    // region 1.电压表改装

    /**
     * （2）记录微安表的满量程电流Ig（μA)
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas A_Ig_FH(PhyParas para, List<PhyParas> DataSourse) {
        double Ig = 500.0;
        para._StandardValue = Ig;
        return para;
    }

    /**
     * （3）记录微安表的表头内阻r(Ω)
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas A_r_FH(PhyParas para, List<PhyParas> DataSourse) {
        double r = 560.0;
        para._StandardValue = r;
        return para;
    }

    /**
     * （4）根据改装后的电压表满偏量程为5V的要求，计算出所需分压电阻值R1(Ω)
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas A_R1_FH(PhyParas para, List<PhyParas> DataSourse) {
        double R1 = 9440.0;
        para._StandardValue = R1;
        return para;
    }

    /**
     * （5）待测信号箱的未知直流电压值为（V）
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas A_V_FH(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double V = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            if (1 <= V && V <= 4) {
                /// DataConversion.CutDataDecimal(V.ToString(), 2);
                para._StandardValue = V;
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 2.电流表改装

    /**
     * （2）记录微安表的满量程电流Ig（μA）
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas B_Ig_FH(PhyParas para, List<PhyParas> DataSourse) {
        double Ig = 500.0;
        para._StandardValue = Ig;
        return para;
    }

    /**
     * （3）记录微安表的表头内阻r(Ω)
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas B_r_FH(PhyParas para, List<PhyParas> DataSourse) {
        double r = 560.0;
        para._StandardValue = r;
        return para;
    }

    /**
     * （4）根据改装后的电流表满偏量程为10mA的要求，计算出所需分流电阻值R1(Ω)
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas B_R1_FH(PhyParas para, List<PhyParas> DataSourse) {
        double R1 = 29.5;
        para._StandardValue = R1;
        return para;
    }

    /**
     * （5）待测信号箱的未知直流电流值为（A）
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas B_I_FH(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double A = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            if (0.002 <= A && A <= 0.008) {
                /// DataConversion.CutDataDecimal(A.ToString(), 5);
                para._StandardValue = A;
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 3.欧姆表改装

    /**
     * （2）记录微安表的满量程电流Ig（μA）
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas C_Ig_FH(PhyParas para, List<PhyParas> DataSourse) {
        double Ig = 500.0;
        para._StandardValue = Ig;
        return para;
    }

    /**
     * （3）记录微安表的表头内阻r(Ω)
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas C_r_FH(PhyParas para, List<PhyParas> DataSourse) {
        double r = 560.0;
        para._StandardValue = r;
        return para;
    }

    /**
     * （4）根据改装后的欧姆表100Ω校准为100Ω得要求，计算所需调零电阻(Ω)
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas C_R1_FH(PhyParas para, List<PhyParas> DataSourse) {
        double R1 = 2440.0;
        para._StandardValue = R1;
        return para;
    }

    /**
     * （5）根据改装后的欧姆表100Ω校准为100Ω得要求，计算所需校准电阻(Ω)
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas C_R2_FH(PhyParas para, List<PhyParas> DataSourse) {
        double R2 = 43.47;
        para._StandardValue = R2;
        return para;
    }

    /**
     * （6）待测信号箱的未知电阻值为（Ω）
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas C_R3_FH(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double R3 = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            if (50 <= R3 && R3 <= 250) {
                para._StandardValue = R3;
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 4.万用表改装

    /**
     * （2）记录微安表的满量程电流Ig（μA）
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas D_Ig_FH(PhyParas para, List<PhyParas> DataSourse) {
        double Ig = 500.0;
        para._StandardValue = Ig;
        return para;
    }

    /**
     * （3）记录微安表的表头内阻r(Ω)
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas D_r_FH(PhyParas para, List<PhyParas> DataSourse) {
        double r = 560.0;
        para._StandardValue = r;
        return para;
    }
    // endregion

    // region 5.根据改装后得电流表满偏量程为10mA,电压表满偏量程为5V和欧姆表100Ω校准为100Ω得要求，分别计算出所需电流当分流电阻值、电压档分压电阻值、欧姆档调零电阻值、欧姆档校准电阻值。

    /**
     * （1）直流电流档分流电阻值R1(Ω)
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas E_R1_FH(PhyParas para, List<PhyParas> DataSourse) {
        double R1 = 29.5;
        para._StandardValue = R1;
        return para;
    }

    /**
     * （2）直流电压档分压电阻值R2(Ω)
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas E_R2_FH(PhyParas para, List<PhyParas> DataSourse) {
        double R2 = 472.0;
        para._StandardValue = R2;
        return para;
    }

    /**
     * （3）欧姆档校准电阻值R3（Ω）
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas E_R3_FH(PhyParas para, List<PhyParas> DataSourse) {
        double R3 = 60.0;
        para._StandardValue = R3;
        return para;
    }

    /**
     * （4）欧姆档调零电阻值R4（Ω）
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas E_R4_FH(PhyParas para, List<PhyParas> DataSourse) {
        double R4 = 122.0;
        para._StandardValue = R4;
        return para;
    }

    /**
     * （5）待测信号箱的未知直流电流值（A）
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas E_I_FH(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double A = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            if (0.002 <= A && A <= 0.008) {
                para._StandardValue = A;
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * （6）待测信号箱的未知直流电压值（V）
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas E_V_FH(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double V = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            if (1 <= V && V <= 4) {
                para._StandardValue = V;
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * （7）待测信号箱的未知电阻值（Ω）
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas E_R_FH(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double R3 = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            if (50 <= R3 && R3 <= 250) {
                para._StandardValue = R3;
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion
    // endregion

}