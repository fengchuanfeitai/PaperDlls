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

    /**
     * 直接返回学生答案
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas BanDaoTi1(PhyParas para, List<PhyParas> DataSourse) {
        try {
            para._StandardValue = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 直接返回学生答案
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas BanDaoTi2(PhyParas para, List<PhyParas> DataSourse) {
        try {
            para._StandardValue = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas BanDaoTi3(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double sturesult1 = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            double sturesult2 = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());
            double sturesult3 = 2 / 0.0001 * (0.5 - sturesult2 / (sturesult2 + sturesult1)) - 2 * (1200 + sturesult2 * sturesult1 / (sturesult2 + sturesult1));
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(sturesult3), 1);

        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 返回学生值
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas BanDaoTi5(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 12; i++) {
                try {
                    str[1][i] = String.valueOf(Double.parseDouble(str[1][i]));
                } catch (Exception ex) {
                    str[1][i] = "";
                }
            }
            for (int i = 1; i < 12; i++) {
                try {
                    str[2][i] = String.valueOf(Double.parseDouble(str[2][i]));
                } catch (Exception ex) {
                    str[2][i] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str, 3, 12);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 返回19.99999~70.000001  数据
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas BanDaoTi6(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double sturesult = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            if (sturesult < 70.000001 && sturesult > 19.99999) {
                para._StandardValue = sturesult;
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 直接返回学生答案
     *
     * @param para
     * @param DataSourse 数据
     * @return 直接返回学生答案
     */
    public static PhyParas BanDaoTi7(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double sturesult = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            para._StandardValue = sturesult;
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas BanDaoTi8(PhyParas para, List<PhyParas> DataSourse) {
        double d;
        try {
            List<Double> list = new ArrayList<>();
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            double sturesult = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());
            double[] wendu = new double[]{20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70};
            for (int i = 1; i < 12; i++) {
                d = Double.parseDouble(str[2][i]);
                list.add(d);
            }
            double[] dianliu = list.stream().mapToDouble(Double::doubleValue).toArray();
            double[] jieguo = ZuiXiaoErChengFa(wendu, dianliu);
            double dianl = jieguo[0] * sturesult + jieguo[1];
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(dianl), 2);

        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
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

    /**
     * 求平均值
     *
     * @param NumList 数组
     * @return 平均值
     */
    public static double PingJunZhi(double[] NumList) {
        double Sum = 0.0;
        for (double v : NumList) {
            Sum = Sum + v;
        }
        Sum = Sum / NumList.length;
        return Sum;
    }
}