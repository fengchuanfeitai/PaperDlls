import common.PhyError;
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
    // region 1.使用游标卡尺测量样品半径和厚度

    /**
     * 使用游标卡尺，多次测量铜盘和橡胶盘的直径和厚度，记录到表格1中
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas BLDT_List1(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] stu = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            String[][] result = XmlAnalysis.GetMatrixString(para._StandardValue);
            //5行
            for (int i = 1; i < 5; i++) {
                //4列
                for (int j = 1; j < 4; j++) {
                    try {
                        double a = Double.parseDouble(stu[i][j]);
                        //四舍五入规则保留到小数点后2位
                        result[i][j] = DataConversion.CutDataDecimal(String.valueOf(a), 2);
                    } catch (Exception ex) {
                        result[i][j] = "";
                    }
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(result, 5, 4);

        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 计算平均值和不确定度
     *
     * @param para
     * @param DataSourse 表格1
     * @return 计算平均值和不确定度
     */
    public static PhyParas BLDT_List1_2(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] stu = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            String[][] result = XmlAnalysis.GetMatrixString(para._StandardValue);
            //5列
            for (int i = 1; i < 5; i++) {
                //平均值
                //表1对应位置填空数
                int n = 0;
                //和
                double sum = 0;
                //平均值
                double ave = 0;
                for (int j = 1; j < 4; j++) {
                    try {
                        if (stu[i][j] != null && !"".equals(stu[i][j])) {
                            double a = Double.parseDouble(stu[i][j]);
                            sum = sum + a;
                            n++;
                        }
                    } catch (Exception ex) {
                    }

                }
                if (n > 0) {
                    ave = sum / n;
                    //四舍五入规则保留到小数点后2位
                    result[1][i] = DataConversion.CutDataDecimal(String.valueOf(ave), 2);
                } else {
                    result[1][i] = "";
                }

                //Ua
                //平方差和
                double usum = 0;
                //A类不确定度
                double uA = 0;
                //表1对应位置填空数
                int un = 0;
                for (int j = 1; j < 4; j++) {
                    try {
                        if (stu[i][j] != null && !"".equals(stu[i][j])) {
                            double a = Double.parseDouble(stu[i][j]);
                            double d = (a - ave) * (a - ave);
                            usum = usum + d;
                            un++;
                        }
                    } catch (Exception ignored) {
                    }
                }
                if (un > 1) {
                    uA = Math.sqrt(usum / (n - 1));
                    //余位进一规则保留1位有效数字
                    result[2][i] = DataConversion.ToUncertainty(String.valueOf(uA), 1);
                } else {
                    result[2][i] = "";
                }

                //U合
                try {
                    uA = Double.parseDouble(result[2][i]);
                    double uB = 0.02;
                    double U = Math.sqrt(uA * uA + uB * uB);
                    //余位进一规则保留1位有效数字
                    result[3][i] = DataConversion.ToUncertainty(String.valueOf(U), 1);
                } catch (Exception ex) {
                    result[3][i] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(result, 4, 5);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 2.记录平衡温度时A铜盘和C铜盘的温差电动势

    /**
     * 温差电动势的绝对值
     *
     * @param para
     * @param DataSourse 温差电动势的绝对值
     * @return 温差电动势的绝对值
     */
    public static PhyParas BLDT_DDS(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double a = Double.parseDouble(para._StudentValue.toString());
            para._StandardValue = a;
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 3.测量C铜盘降温过程中不同时刻温度对应的温差电动势

    /**
     * C盘降温过程中不同时刻温度对应的温差电动势
     *
     * @param para
     * @param DataSourse C盘降温过程中不同时刻温度对应的温差电动势
     * @return C盘降温过程中不同时刻温度对应的温差电动势
     */
    public static PhyParas BLDT_List3(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] stu = XmlAnalysis.GetMatrixString(para._StudentValue);
            String[][] result = XmlAnalysis.GetMatrixString(para._StandardValue);
            for (int i = 1; i < 16; i++) {
                try {
                    double a = Double.parseDouble(stu[1][i]);
                    result[1][i] = String.valueOf(a);
                } catch (Exception ex) {
                    result[1][i] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(result, 2, 16);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 散热盘散热速率
     *
     * @param para
     * @param DataSourse C盘降温过程中不同时刻温度对应的温差电动势
     * @return 散热盘散热速率
     */
    public static PhyParas BLDT_SRSL(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] stu = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            //用列表防止中间有数字未填，跳过该空格
            List<Double> x = new ArrayList<>();
            List<Double> y = new ArrayList<>();
            for (int i = 1; i < 16; i++) {
                try {
                    double a = Double.parseDouble(stu[1][i]);
                    y.add(a);
                    x.add(Double.parseDouble(String.valueOf(i * 30)));
                } catch (Exception ignored) {
                }
            }
            if (x.size() > 1) {
                //将值刷入数组
                double[] xi = new double[x.size()];
                double[] yi = new double[y.size()];
                for (int i = 0; i < x.size(); i++) {
                    xi[i] = x.get(i);
                    yi[i] = y.get(i);
                }
                double[] k = PhyError.ZuiXiaoErChengFa(xi, yi);

                try {
                    //根据学生值返回标准答案有效值
                    String a1 = String.valueOf(para._StudentValue);
                    double a2 = Double.parseDouble(para._StudentValue.toString());
                    int n1 = DataConversion.GetEffectiveNumericalDigits(a1);
                    double k1 = Math.abs(k[0]);
                    if (a2 >= 0) {
                        para._StandardValue = DataConversion.CutDataEffective(String.valueOf(k1), n1);
                    } else {
                        para._StandardValue = DataConversion.CutDataEffective(String.valueOf(-k1), n1);
                    }
                } catch (Exception ex) {
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(k[0]), 2);
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

    // region 4.计算橡胶盘的导热系数

    /**
     * 橡胶盘的导热系数
     *
     * @param para
     * @param DataSourse 计算平均值和不确定度,A铜盘加热到平衡温度时的温差电动势,C铜盘加热到平衡温度时的温差电动势,
     * @return 橡胶盘的导热系数
     */
    public static PhyParas BLDT_DRXS(PhyParas para, List<PhyParas> DataSourse) {
        String Test = "0";
        try {
            //J/(Kg*℃)
            double Cc = 370.8;
            //铜盘质量kg
            double mc = 0.8;
            //橡胶盘质量kg
            double mb = 0.2;
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            Test = "1";
            //橡胶盘厚度/m
            double hB = Double.parseDouble(str[1][4]) * 0.001;
            //铜盘厚度/m
            double hC = Double.parseDouble(str[1][2]) * 0.001;
            //橡胶盘半径/m
            double RB = Double.parseDouble(str[1][3]) * 0.001 * 0.5;
            //铜盘半径/m
            double RC = Double.parseDouble(str[1][1]) * 0.001 * 0.5;
            Test = "2";
            //测量A铜盘加热到平衡温度时的温差电动势的绝对值/mV
            double Va = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());
            Test = "3";
            //测量C铜盘加热到平衡温度时的温差电动势的绝对值/mV
            double Vb = Double.parseDouble(DataSourse.get(2)._StudentValue.toString());
            Test = "4";
            //散热盘散热速率/mv/s
            double k = Double.parseDouble(DataSourse.get(3)._StandardValue.toString());
            Test = "5";
            if ((2 * 3.141593 * RB * RB * (Va - Vb) * (RC + hC)) != 0) {
                double A = mc * Cc * hB * (RC + 2 * hC) * k / (2 * 3.141593 * RB * RB * (Va - Vb) * (RC + hC));
                A = Math.abs(A);
                try {
                    //根据学生值返回标准答案有效值
                    String a1 = String.valueOf(para._StudentValue);
                    int n1 = DataConversion.GetDecimalPointDigits(a1);
                    para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(A), n1);
                } catch (Exception ex) {
                    para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(A), 6);
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