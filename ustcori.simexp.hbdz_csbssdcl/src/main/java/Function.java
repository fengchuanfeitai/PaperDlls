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
     * 逐差法处理数据，计算相差8个λ/2的△Li值
     *
     * @param para
     * @param DataSourse 数据
     * @return 结果
     */
    public static PhyParas DataListTwo(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            String[][] result = XmlAnalysis.GetMatrixString(para._StandardValue);
            //逐差间隔
            int dsnum = 8;

            for (int i = 1; i <= dsnum; i++) {
                try {
                    double number = Math.abs(Double.parseDouble(str[1][i]) - Double.parseDouble(str[1][i + dsnum]));
                    result[1][i] = DataConversion.CutDataEffective(String.valueOf(number), 5);
                } catch (Exception ex) {
                    result[1][i] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(result, result.length, result[0].length);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 相差8个λ/2的△Li的平均值
     * 带有评分前置条件：34.500
     *
     * @param para
     * @param DataSourse 数据
     * @return 相差8个λ/2的△Li的平均值
     */
    public static PhyParas AverageLi(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            //表格总列数
            int rownum = 9;
            //表的列头占用列数
            int rowhead = 1;
            //要用到的数据所在行数
            int dsnum = 1;

            if (str.length > 1) {
                double sum = 0.0;
                double xi = 0.0;
                int num = rownum - 1;
                for (int i = rowhead; i < rownum; i++) {
                    try {
                        xi = Double.parseDouble(str[dsnum][i]);
                    } catch (Exception ex) {
                        xi = 0.0;
                        num = num - 1;
                    }
                    sum = sum + xi;
                }

                if (num > 0) {
                    sum = sum / num;
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(sum), 6) + ";34.5000";
                } else {
                    para._StandardValue = "" + ";34.5000";
                }
            } else {
                para._StandardValue = "" + ";34.5000";
            }
        } catch (Exception ex) {
            para._StandardValue = "" + ";34.5000";
        }
        return para;
    }

    /**
     * 计算波长平均值；公式：λ=△L/4
     *
     * @param para
     * @param DataSourse 数据
     * @return 波长平均值
     */
    public static PhyParas AverageWavelength(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //获取根据学生数据 计算的相差8个λ/2的△Li的平均值
            double averageLi = Double.parseDouble(DataSourse.get(0)._StandardValue.toString().split(";")[0]);
            para._StandardValue = DataConversion.CutDataEffective(String.valueOf(averageLi / 4.0), 5);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 求出声速；公式：v=f*λ
     * 带有评分前置条件：345.0
     *
     * @param para
     * @param DataSourse 数据
     * @return 声速
     */
    public static PhyParas SoundSpeed(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //频率学生值；单位：khz
            double f = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            //波长学生计算的数值；单位：mm
            double wavelength = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            //单位：hz
            f = f * 1000;
            //单位：m
            wavelength = wavelength * 0.001;
            para._StandardValue = DataConversion.CutDataEffective(String.valueOf(f * wavelength), 5) + ";345.00 ";
        } catch (Exception ex) {
            para._StandardValue = "" + ";345.00 ";
        }
        return para;
    }

    /**
     * △L的A类不确定度uA；公式：√(([(L(i+8)-Li)-△L]^2)/(8-1))
     *
     * @param para
     * @param DataSourse 数据
     * @return △L的A类不确定度uA
     */
    public static PhyParas UncertaintyL_A(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            //获取根据学生数据 计算的相差8个λ/2的△Li的平均值
            double averageli = Double.parseDouble(DataSourse.get(1)._StandardValue.toString().split(";")[0]);
            //表格总列数
            int rownum = 9;
            //表的列头占用列数
            int rowhead = 1;
            //要用到的数据所在行数
            int dsnum = 1;
            if (str.length > 1) {
                double sum = 0.0;
                double xi = 0.0;
                int num = rownum - 1;
                for (int i = rowhead; i < rownum; i++) {
                    try {
                        xi = Double.parseDouble(str[dsnum][i]) - averageli;
                        xi = xi * xi;
                    } catch (Exception ex) {
                        xi = 0.0;
                        num = num - 1;
                    }
                    sum = sum + xi;
                }

                if (num > 1) {
                    sum = Math.sqrt(sum / (num - 1));
                    para._StandardValue = DataConversion.ToUncertainty(String.valueOf(sum), 2);
                } else if (num == 1) {
                    para._StandardValue = "0.0";
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

    /**
     * △L的B类不确定度uB；公式：∆_仪/√3
     *
     * @param para
     * @param DataSourse 数据
     * @return △L的B类不确定度uB
     */
    public static PhyParas UncertaintyL_B(PhyParas para, List<PhyParas> DataSourse) {
        double num = 1.732;
        para._StandardValue = DataConversion.ToUncertainty(String.valueOf(0.004 / num), 2);
        return para;
    }

    /**
     * △L的不确定度u(△L)；公式：u(∆L)=√((uA)^2+(uB)^2)
     *
     * @param para
     * @param DataSourse 数据
     * @return △L的不确定度u(△L)
     */
    public static PhyParas UncertaintyL(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double ua = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double ub = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            double result = Math.sqrt(ua * ua + ub * ub);
            para._StandardValue = DataConversion.ToUncertainty(String.valueOf(result), 2);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 求出声速υ的不确定度u(υ)(m/s)；公式：u(υ)=u(∆L)/4*f
     *
     * @param para
     * @param DataSourse 数据
     * @return 声速υ的不确定度u(υ)(m / s)
     */
    public static PhyParas UncertaintySoundSpeed(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double ul = Double.parseDouble((DataSourse.get(0)._StandardValue.toString())) * 0.001;
            double f = Double.parseDouble((DataSourse.get(1)._StandardValue.toString())) * 1000.0;
            double result = ul / 4.0 * f;
            para._StandardValue = DataConversion.ToUncertainty(String.valueOf(result), 2);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 求出声速υ的相对不确定度Ur(υ)；公式：Ur=u(v)/v×100%
     *
     * @param para
     * @param DataSourse 数据
     * @return 求出声速υ的相对不确定度Ur(υ)
     */
    public static PhyParas UncertaintyRelative(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double uv = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double average_v = Double.parseDouble(DataSourse.get(1)._StandardValue.toString().split(";")[0]);
            if (average_v != 0) {
                double result = uv / average_v * 100.0;
                para._StandardValue = DataConversion.ToUncertainty(String.valueOf(result), 2);
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    /**
     * 声速的理论值；公式v理论= v0*√(T0+t)/T0
     *
     * @param para
     * @param DataSourse t:室温
     * @return 声速的理论值
     */
    public static PhyParas TheorySoundSpeed(PhyParas para, List<PhyParas> DataSourse) {
        try {
            // 单位：m/s
            double v0 = 331.30;
            // 单位：K
            double T0 = 273.15;
            // 单位：℃
            double t = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            // 单位：m/s
            double result = v0 * Math.sqrt((T0 + t) / T0);
            para._StandardValue = DataConversion.CutDataEffective(String.valueOf(result), 3);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 计算声速的相对误差Er(%)
     *
     * @param para
     * @param DataSourse 数据
     * @return 声速的相对误差Er(%)
     */
    public static PhyParas RelativeError(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //实际值
            double v0 = Double.parseDouble(DataSourse.get(0)._StandardValue.toString().split(";")[0]);
            //理论值
            double v1 = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            if (v1 != 0) {
                double result = Math.abs(v0 - v1) / v1 * 100;
                para._StandardValue = DataConversion.CutDataEffective(String.valueOf(result), 2);
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    // region  最终表达式

    /**
     * 标准值 保留2位小数  ；不确定度 保留1位或者2位小数
     *
     * @param para
     * @param DataSourse 数据
     * @return
     */
    public static PhyParas GetFinalExpression(PhyParas para, List<PhyParas> DataSourse) {
        try {
            if (!DataConversion.isNullOrEmpty(DataSourse.get(0)._StudentValue.toString()) && !DataConversion.isNullOrEmpty(DataSourse.get(1)._StudentValue.toString())) {
                para._StandardValue = DataConversion.GetExpressionBKD(DataSourse.get(0)._StudentValue.toString(), DataSourse.get(1)._StudentValue.toString(), 1);

            } else if (!DataConversion.isNullOrEmpty(DataSourse.get(0)._StandardValue.toString()) && !DataConversion.isNullOrEmpty(DataSourse.get(1)._StandardValue.toString())) {

                String staValue1 = DataSourse.get(0)._StandardValue.toString();
                String[] strList = staValue1.split(";");

                if (strList.length == 2 && !DataConversion.isNullOrEmpty(strList[0])) {
                    para._StandardValue = DataConversion.GetExpressionBKD(strList[0], DataSourse.get(1)._StandardValue.toString(), 1);

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
}