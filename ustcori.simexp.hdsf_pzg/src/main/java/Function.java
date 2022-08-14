import common.PhyParas;
import common.XmlAnalysis;

import java.util.ArrayList;
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

    // region 第一部分  测量激光的偏振度

    /**
     * 表1：偏振度的测量  【保留3位小数点】
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas List_PZD_FH(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            if (str.length > 1) {
                //列
                for (int j = 1; j < str[0].length; j++) {
                    try {
                        double x1 = 92.78;
                        str[1][j] = DataConversion.CutDataDecimal(String.valueOf(x1), 2);
                    } catch (Exception ex) {
                        str[1][j] = "";
                    }
                    try {
                        double x2 = 60.66;
                        str[2][j] = DataConversion.CutDataDecimal(String.valueOf(x2), 2);
                    } catch (Exception ex) {
                        str[2][j] = "";
                    }
                }
                para._StandardValue = XmlAnalysis.SetMatrixString(str, str.length, str[0].length);
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 计算Imax和Imin的平均值  【保留3位小数点】
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas List_PZDPJ_FH(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StudentValue);
            double xi = 0.0;
            int num = 3;
            int num1 = 3;
            double sum = 0.0;
            double sum1 = 0.0;
            double average = 0.0;
            double average1 = 0.0;
            if (str.length > 1) {
                // region Imax平均值
                for (int j = 1; j < str[0].length; j++) {
                    try {
                        xi = Double.parseDouble(str[1][j]);
                    } catch (Exception ex) {
                        xi = 0.0;
                        num = num - 1;
                    }
                    sum = sum + xi;
                }
                if (num > 0) {
                    average = sum / num;
                    str1[1][1] = String.valueOf(DataConversion.CutDataDecimal(String.valueOf(average), 2));
                } else {
                    str1[1][1] = "";
                }
                // endregion

                // region Imin平均值
                for (int j = 1; j < str[0].length; j++) {
                    try {
                        xi = Double.parseDouble(str[2][j]);
                    } catch (Exception ex) {
                        xi = 0.0;
                        num1 = num1 - 1;
                    }
                    sum1 = sum1 + xi;
                }
                if (num1 > 0) {
                    average1 = sum1 / num1;
                    str1[1][2] = String.valueOf(DataConversion.CutDataDecimal(String.valueOf(average1), 2));
                } else {
                    str1[1][2] = "";
                }
                // endregion
                para._StandardValue = XmlAnalysis.SetMatrixString(str1, str1.length, str1[0].length);
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }

        return para;
    }

    /**
     * 计算V=(Imax-Imin) ÷ (Imax+Imin )  【保留3位小数点】
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas V_FH(PhyParas para, List<PhyParas> DataSourse) {
        String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
        //double e = 1.000;
        try {
            double Imax = Double.parseDouble(str[1][1]);
            double Imin = Double.parseDouble(str[1][2]);
            double V = (Imax - Imin) / (Imax + Imin);
            para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(V), 2);

        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    // endregion

    // region 第二部分 验证马吕斯定律

    /**
     * Imax
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas Imax_FH(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double Imax = 100;
            para._StandardValue = Imax;
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 80
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas JD0_FH(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double JD0 = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            int num = DataConversion.GetDecimalPointDigits(String.valueOf(DataSourse.get(0)._StudentValue));
            para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(JD0), num);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 表2马吕斯定律
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas List_MLSDL_FH(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            if (str.length > 1) {
                //列
                for (int j = 1; j < str[0].length; j++) {
                    try {
                        double Imax = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());
                        double I = Double.parseDouble(str[2][j]);
                        if (Imax != 0) {
                            double x = I / Imax;
                            str[3][j] = DataConversion.CutDataDecimal(String.valueOf(x), 3);
                        } else {
                            str[3][j] = "";
                        }
                    } catch (Exception ex) {
                        str[3][j] = "";
                    }
                    try {
                        double Cos2 = Double.parseDouble(str[1][j]);
                        double Imax = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());
                        double I = Cos2 * Imax;
                        str[2][j] = DataConversion.CutDataDecimal(String.valueOf(I), 1);
                    } catch (Exception exception) {
                        str[2][j] = "";
                    }
                }
                para._StandardValue = XmlAnalysis.SetMatrixString(str, str.length, str[0].length);
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 斜率
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas XLK_FH(PhyParas para, List<PhyParas> DataSourse) {
        double e = 1.00;
        try {
            String[][] GDXYPLKList = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            int row = GDXYPLKList[0].length;
            List<UIStruct> ValidUI = new ArrayList<>();
            for (int i = 1; i < row; i++) {
                try {
                    UIStruct ui = new UIStruct();
                    ui.U = Double.parseDouble(GDXYPLKList[3][i]);
                    ui.I = Double.parseDouble(GDXYPLKList[1][i]);

                    ValidUI.add(ui);
                } catch (Exception ex) {
                }
            }
            if (ValidUI.size() >= 2) {
                double[] xi = new double[ValidUI.size()];
                double[] yi = new double[ValidUI.size()];

                for (int i = 0; i < ValidUI.size(); i++) {
                    yi[i] = ValidUI.get(i).U;
                    xi[i] = ValidUI.get(i).I;
                }
                double k = (ZuiXiaoErChengFa(xi, yi))[0];
                para._StandardValue = DataConversion.CutDataEffective(String.valueOf(k), 3) + ";" + DataConversion.CutDataEffective(String.valueOf(e), 3);
            } else {
                para._StandardValue = "" + ";" + DataConversion.CutDataEffective(String.valueOf(e), 3);
            }
        } catch (Exception ex) {
            para._StandardValue = "" + ";" + DataConversion.CutDataEffective(String.valueOf(e), 3);
        }
        return para;
    }

    // endregion

    // region 最小二乘法

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
        double[] zxecf = new double[]{
                0.0, 0.0, 0.0
        };
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

    // endregion

}
