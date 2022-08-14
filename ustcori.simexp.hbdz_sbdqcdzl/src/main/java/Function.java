import common.PhyParas;
import common.XmlAnalysis;

import java.util.List;

/**
 * 实验公式
 *
 * @author cp
 */
public class Function {
    // region 调用计算方法

    /**
     * R0平均=(R0[正]+R0[反])/2   单位：Ω    不评分
     * 待测电阻阻值: Rx=R0平均/100 单位：10E(-3)Ω  有效数字正确（保留5位或6位有效值）
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas Formula_R(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str0 = XmlAnalysis.GetMatrixString(para._StudentValue);
            String[][] str = XmlAnalysis.GetMatrixString(para._StudentValue);
            for (int i = 1; i < str0[0].length; i++) {
                if (!"".equals(str0[1][i]) && !"".equals(str0[2][i])) {
                    str[3][i] = DataConversion.CutDataDecimal(String.valueOf((Double.parseDouble(str0[1][i]) + Double.parseDouble(str0[2][i])) /
                            2), 2);
                    str[4][i] = DataConversion.CutDataEffective(String.valueOf(Double.parseDouble(str[3][i]) / 100), 6);
                } else {
                    str[3][i] = "";
                    str[4][i] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str, str.length, str[0].length);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 平均铜棒直径 单位：mm   4位或5位有效数字  增加前置条件
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas Formula_AvgCuZJ(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            double S = 0;
            int N = 0;
            for (int i = 1; i < str[0].length; i++) {
                if (!"".equals(str[5][i])) {
                    S += Double.parseDouble(str[5][i]);
                    N += 1;
                }
            }
            if (N == 0) {
                para._StandardValue = ";4.0000";
            } else {
                para._StandardValue = DataConversion.CutDataEffective(String.valueOf(S / N), 5) + ";4.0000";
            }
        } catch (Exception ex) {
            para._StandardValue = ";4.0000";
        }

        return para;
    }

    /**
     * 单位长度电阻平均值   单位：×10-3Ω/m     有效数字正确（保留3位或4位有效数字）
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas Formula_AvgRx(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double S = 0, j = 21.0;
            double N = 0;
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            for (int i = 1; i < str[0].length; i++) {
                if (!"".equals(str[4][i])) {
                    S += Double.parseDouble(str[4][i]) / j * 100;
                    j += 1;
                    N += 1;
                } else {
                    j += 1;
                }
            }
            if (N == 0) {
                para._StandardValue = ";6.000";
            } else {
                para._StandardValue = DataConversion.CutDataEffective(String.valueOf(S / N), 4) + ";6.000";
            }
        } catch (Exception ex) {
            para._StandardValue = ";6.000";
        }
        return para;
    }

    /**
     * 电阻率    单位：×10-8Ωm     有效数字正确（保留3位有效数字)
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas Formula_DZL(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            double S = 0, s = 0, j = 21.0;
            int N = 0, n = 0;
            for (int i = 1; i < str[0].length; i++) {
                if (!"".equals(str[5][i])) {
                    S += Double.parseDouble(str[5][i]);
                    N += 1;
                }
                if (!"".equals(str[4][i])) {
                    s += Double.parseDouble(str[4][i]) / j * 100;
                    j += 1;
                    n += 1;
                } else {
                    j += 1;
                }
            }
            if (N == 0 || n == 0) {
                para._StandardValue = "";
            } else {
                para._StandardValue = DataConversion.CutDataEffective(String.valueOf(Math.PI * S / N * S / N * s / n / 40), 3);
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 直径D的不确定度:u(D)=Math.sqrt(∑(D-D平均)/(5-1))  单位：mm   有效数字正确（保留1位或2位有效数字）
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas Formula_ZJUncertainty(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            double S = 0, s = 0;
            double N = 0;
            for (int i = 1; i < str[0].length; i++) {
                if (!"".equals(str[5][i])) {
                    S += Double.parseDouble(str[5][i]);
                    N += 1;
                }
            }
            if (N == 0) {
                para._StandardValue = "";
            } else if (N == 1) {
                para._StandardValue = "0";
            } else {
                for (int i = 1; i < str[0].length; i++) {
                    if (!"".equals(str[5][i])) {
                        s += Math.pow(Double.parseDouble(str[5][i]) - S / N, 2);
                    }
                }
                para._StandardValue = DataConversion.ToUncertainty(String.valueOf(Math.sqrt(s / (N - 1))), 2);
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 单位长度电阻不确定度u(Rx/L)   单位：×10-3Ω/m   有效数字正确（保留1位或2位有效数字）
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas Formula_DWCDRUncertainty(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            double S = 0, s = 0;
            double N = 0, j = 21.0;
            for (int i = 1; i < str[0].length; i++) {
                if (!"".equals(str[4][i])) {
                    S += Double.parseDouble(str[4][i]) / j * 100;
                    j += 1;
                    N += 1;
                } else {
                    j += 1;
                }
            }
            if (N == 0) {
                para._StandardValue = "";
            } else if (N == 1) {
                para._StandardValue = "0";
            } else {
                j = 21.0;
                for (int i = 1; i < str[0].length; i++) {
                    if (!"".equals(str[4][i])) {
                        s += Math.pow(Double.parseDouble(str[4][i]) / j * 100 - S / N, 2);
                        j += 1;
                    } else {
                        j += 1;
                    }
                }
                para._StandardValue = DataConversion.ToUncertainty(String.valueOf(Math.sqrt(s / (N - 1))), 2);
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }

        return para;
    }

    /**
     * 电阻率相对不确定度   单位：1      有效数字正确（保留2位有效数字）
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas Formula_RRUncertainty(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            double uD = XmlAnalysis.GetDouble(DataSourse.get(1)._StandardValue);
            double uR = XmlAnalysis.GetDouble(DataSourse.get(2)._StandardValue);
            double S = 0, s = 0, j = 21.0;
            int N = 0, n = 0;
            for (int i = 1; i < str[0].length; i++) {
                if (!"".equals(str[5][i])) {
                    S += Double.parseDouble(str[5][i]);
                    N += 1;
                }
                if (!"".equals(str[4][i])) {
                    s += Double.parseDouble(str[4][i]) / (Double.parseDouble(str[0][i])) * 100;
                    n += 1;
                }
            }
            if (S == 0 || s == 0) {
                para._StandardValue = "";
            } else {
                double U = Math.sqrt(Math.pow(2 * uD / (S / N), 2) + Math.pow(uR / (s / n), 2));
                para._StandardValue = DataConversion.ToUncertainty(String.valueOf(U * 100), 2);
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }

        return para;
    }

    /**
     * 电阻率ρ不确定度up   单位：10-8Ωm      有效数字正确（保留1或2位有效数字）
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas Formula_RUncertainty(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String Up = XmlAnalysis.GetString(DataSourse.get(0)._StandardValue);
            String p = XmlAnalysis.GetString(DataSourse.get(1)._StandardValue);
            double A = Double.parseDouble(Up);
            double B = Double.parseDouble(p);
            if (A != 0.0 && B != 0.0) {
                para._StandardValue = DataConversion.ToUncertainty(String.valueOf(A * B / 100), 2);
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 电阻率的完整表达形式: p+up ;
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas Formula_FinalDZL(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double p = XmlAnalysis.GetDouble(DataSourse.get(0)._StudentValue);
            double up = XmlAnalysis.GetDouble(DataSourse.get(1)._StudentValue);
            String s = DataConversion.GetExpressionBKD(DataSourse.get(0)._StudentValue.toString(), DataSourse.get(1)._StudentValue.toString(), 1);
            if (p == 0 || up == 0) {
                para._StandardValue = "";
            } else {
                para._StandardValue = s;
            }

        } catch (Exception ex) {
            para._StandardValue = "";
        }

        return para;
    }

    // endregion
}