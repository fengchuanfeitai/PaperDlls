import common.PhyParas;
import common.XmlAnalysis;

import java.util.List;

/**
 * 实验公式
 *
 * @author cp
 */
public class Function {

    /**
     * 求一组数据的算术平均值
     *
     * @param NumList 输入的一组数据
     * @return 返回数组的算术平均值
     */
    public static double PingJunZhi(double[] NumList) {
        double Sum = 0.0;
        for (int i = 0; i < NumList.length; i++) {
            Sum = Sum + NumList[i];
        }
        Sum = Sum / NumList.length;
        return Sum;
    }

    /**
     * 最小二乘法应用于线性回归:Y=mX+b；
     * 返回结果：ZuiXiaoErChengFa[0]是一次系数；ZuiXiaoErChengFa[1]是常数项；ZuiXiaoErChengFa[2]是相关系数。
     *
     * @param Xi X数据组
     * @param Yi Y数据组
     * @return 返回结果：ZuiXiaoErChengFa[0]是一次系数；ZuiXiaoErChengFa[1]是常数项；ZuiXiaoErChengFa[2]是相关系数。
     */
    public static double[] ZuiXiaoErChengFa(double[] Xi, double[] Yi) {
        //Y=mX+b
        double[] zxecf = new double[]{0.0, 0.0, 0.0};
        double m = 0.0;
        double b = 0.0;
        double r = 0.0;

        if (Xi.length == Yi.length) {
            double xp = PingJunZhi(Xi);
            double yp = PingJunZhi(Yi);
            double[] xynew = new double[Xi.length];
            double[] DXi = new double[Xi.length];
            double[] DYi = new double[Yi.length];

            for (int i = 0; i < Xi.length; i++) {
                xynew[i] = Xi[i] * Yi[i];
                DXi[i] = Xi[i] * Xi[i];
                DYi[i] = Yi[i] * Yi[i];
            }
            double xyp = PingJunZhi(xynew);

            double lxy = Xi.length * (xyp - xp * yp);

            double xdp = PingJunZhi(DXi);
            double ydp = PingJunZhi(DYi);

            double lxx = Xi.length * (xdp - xp * xp);
            m = lxy / lxx;
            b = yp - m * xp;
            r = (xyp - xp * yp) / Math.sqrt((xdp - xp * xp) * (ydp - yp * yp));
        }
        zxecf[0] = m;
        zxecf[1] = b;
        zxecf[2] = r;

        return zxecf;
    }

    // endregion


    // region 对表格中的数据进行有效位数的截取

    /**
     * 对表格中的数据进行有效位数的截取
     *
     * @param para
     * @param DataSourse 数据
     * @return 表格中的数据进行有效位数的截取
     */
    public static PhyParas CalculateYXWS(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(para._StudentValue);
            String[][] yxws = XmlAnalysis.GetMatrixString(para._StandardValue);
            if (str.length > 1) {
                for (int i = 1; i < yxws[0].length; i++) {
                    try {
                        yxws[1][i] = DataConversion.CutDataDecimal(str[1][i], 3);
                    } catch (Exception ex) {
                        yxws[1][i] = "";
                    }
                }
                para._StandardValue = XmlAnalysis.SetMatrixString(yxws, yxws.length, yxws[0].length);
            } else {
                //表格有误时，计算结果为无效数据
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion


    // region 关于数据d的计算方法

    // region 用逐差法计算数据

    /**
     * 用逐差法计算数据
     *
     * @param para
     * @param DataSourse 数据
     * @return 结果
     */
    public static PhyParas CalculateZCF(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            String[][] zcf = XmlAnalysis.GetMatrixString(para._StandardValue);
            if (str.length > 1) {
                for (int i = 1; i < zcf[0].length; i++) {
                    try {
                        zcf[1][i] = DataConversion.CutDataEffective(String.valueOf(Math.abs(Double.parseDouble(str[1][i + 3]) - Double.parseDouble(str[1][i]))), 3);
                    } catch (Exception ex) {
                        zcf[1][i] = "";
                    }
                }
                para._StandardValue = XmlAnalysis.SetMatrixString(zcf, zcf.length, zcf[0].length);
            } else {
                //表格有误时，计算结果为无效数据
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 逐差法数据平均值

    /**
     * 逐差法数据平均值
     *
     * @param para
     * @param DataSourse 数据
     * @return 结果
     */
    public static PhyParas CalculatePJZ(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            double sum = 0.0;
            int count = str[0].length - 1;

            for (int i = 1; i < str[0].length; i++) {
                try {
                    double str1 = Double.parseDouble(str[1][i]);
                    sum += str1;
                } catch (Exception ex) {
                    count = count - 1;
                }
            }
            if (count > 0) {
                para._StandardValue = DataConversion.CutDataEffective(String.valueOf(sum / count), 3);
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 计算逐差法数据的A类不确定度

    /**
     * 计算逐差法数据的A类不确定度
     *
     * @param para
     * @param DataSourse 数据
     * @return 结果
     */
    public static PhyParas CalculateUA(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            //平均值
            double avg = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());

            if (str.length > 1) {
                double sum = 0.0;
                double xi = 0.0;
                int num = str[0].length - 1;

                for (int i = 1; i <= num; i++) {
                    try {
                        xi = Double.parseDouble(str[1][i]) - avg;
                    } catch (Exception ex) {
                        xi = 0.0;
                        num = num - 1;
                    }
                    sum = sum + xi * xi;
                }

                if (num > 1) {
                    double dUA = Math.sqrt(sum / (num - 1));
                    para._StandardValue = DataConversion.ToUncertainty(String.valueOf(dUA), 1);
                } else if (num == 1) {
                    para._StandardValue = "0";
                } else {
                    para._StandardValue = "";
                }
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 计算逐差法数据的B类不确定度

    /**
     * 计算逐差法数据的B类不确定度
     *
     * @param para
     * @param DataSourse 数据
     * @return 结果
     */
    public static PhyParas CalculateUB(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //B类不确定度的值
            double dd = 0.004 / Math.sqrt(3);
            double dUB = Math.sqrt(2.0 * dd * dd);
            para._StandardValue = DataConversion.ToUncertainty(String.valueOf(dUB), 1);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 计算逐差法数据的合成不确定度

    /**
     * 计算逐差法数据的合成不确定度
     *
     * @param para
     * @param DataSourse 数据
     * @return 结果
     */
    public static PhyParas CalculateU(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double dUA = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double dUB = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());

            double dU = Math.sqrt(dUA * dUA + dUB * dUB);
            para._StandardValue = DataConversion.ToUncertainty(String.valueOf(dU), 1);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 计算逐差法数据的相对不确定度

    /**
     * 计算逐差法数据的相对不确定度
     *
     * @param para
     * @param DataSourse 数据
     * @return 结果
     */
    public static PhyParas CalculateUr(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double d = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double dU = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());

            if (Math.abs(d) > Math.pow(10, -6)) {
                double dUr = dU / d * 100.0;
                para._StandardValue = DataConversion.ToUncertainty(String.valueOf(dUr), 1);
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


    // region 关于氦氖激光器波长λ的计算方法

    // region 计算氦氖激光器的波长λ

    /**
     * 计算氦氖激光器的波长λ
     *
     * @param para
     * @param DataSourse 数据
     * @return 结果
     */
    public static PhyParas CalculateL(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double d = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double N = 50.0;

            double L = 2.0 * (d / 20.0) / (3.0 * N) * Math.pow(10, 6);
            para._StandardValue = DataConversion.CutDataEffective(String.valueOf(L), 3);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 计算氦氖激光器的波长λ的相对不确定度

    /**
     * 计算氦氖激光器的波长λ的相对不确定度
     *
     * @param para
     * @param DataSourse 数据
     * @return 结果
     */
    public static PhyParas CalculateUr2(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double dUr = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double LUr = dUr;
            para._StandardValue = DataConversion.ToUncertainty(String.valueOf(LUr), 1);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 计算氦氖激光器的波长λ的不确定度

    /**
     * 计算氦氖激光器的波长λ的不确定度
     *
     * @param para
     * @param DataSourse 数据
     * @return 结果
     */
    public static PhyParas CalculateU2(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double L = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double LUr = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            double LU = L * LUr / 100.0;
            para._StandardValue = DataConversion.ToUncertainty(String.valueOf(LU), 1);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 计算氦氖激光器的波长λ的相对误差

    /**
     * 计算氦氖激光器的波长λ的相对误差
     *
     * @param para
     * @param DataSourse 数据
     * @return 结果
     */
    public static PhyParas CalculateEr(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double L = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double L0 = 632.8;

            double LEr = Math.abs(L - L0) / L0 * 100.0;
            para._StandardValue = DataConversion.CutDataEffective(String.valueOf(LEr), 1);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region  氦氖激光器的波长λ的最终表达式

    /**
     * 氦氖激光器的波长λ的最终表达式
     *
     * @param para
     * @param DataSourse 数据
     * @return 结果
     */
    public static PhyParas GetFinal(PhyParas para, List<PhyParas> DataSourse) {
        try {
            if (!DataConversion.isNullOrEmpty(DataSourse.get(0)._StudentValue.toString()) && !DataConversion.isNullOrEmpty(DataSourse.get(1)._StudentValue.toString())) {
                String s1 = DataConversion.GetExpressionBKD(DataSourse.get(0)._StudentValue.toString(), DataSourse.get(1)._StudentValue.toString(), 1);
                para._StandardValue = s1;

            } else if (!DataConversion.isNullOrEmpty(DataSourse.get(0)._StandardValue.toString()) && !DataConversion.isNullOrEmpty(DataSourse.get(1)._StandardValue.toString())) {
                String s1 = DataConversion.GetExpressionBKD(DataSourse.get(0)._StandardValue.toString(), DataSourse.get(1)._StandardValue.toString(), 1);
                para._StandardValue = s1;
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