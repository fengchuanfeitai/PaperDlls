import common.PhyParas;
import common.XmlAnalysis;

import java.util.List;

/**
 * 实验公式
 *
 * @author cp
 */
public class Function {
    // region 方式一测量数据及结果

    /**
     * 表1,返回表格数据
     *
     * @param para
     * @param DataSourse 表1
     * @return 表1
     */
    public static PhyParas LTF_list1(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            String[][] result = XmlAnalysis.GetMatrixString(para._StandardValue);
            for (int i = 1; i < 5; i++) {
                //ZA (cm)
                try {
                    double d = Double.parseDouble(str[1][i]);
                    if (d >= 0 && d <= 5) {
                        //奇进偶退方式保留2位小数
                        result[1][i] = DataConversion.SingleDoubleOfDecimal(String.valueOf(d), 2);
                    } else {
                        result[1][i] = "";
                    }
                } catch (Exception ex) {
                    result[1][i] = "";
                }
                //ZB (cm)
                try {
                    double a = Double.parseDouble(str[1][i]);
                    double d = Double.parseDouble(str[2][i]);
                    if (d >= 0 && d - a > 0) {
                        //奇进偶退方式保留2位小数
                        result[2][i] = DataConversion.SingleDoubleOfDecimal(String.valueOf(d), 2);
                    } else {
                        result[2][i] = "";
                    }
                } catch (Exception ex) {
                    result[2][i] = "";
                }
                try//h (cm)
                {
                    double a = Double.parseDouble(result[1][i]);
                    double d = Double.parseDouble(result[2][i]);
                    double h = d - a;
                    //奇进偶退方式保留4位有效值
                    result[3][i] = DataConversion.SingleDoubleOfEffective(String.valueOf(h), 4);
                } catch (Exception ex) {
                    result[3][i] = "";
                }
                try//t(ms)
                {
                    double t = Double.parseDouble(str[4][i]);
                    int pos = DataConversion.High(String.valueOf(t));
                    if (t > 0 && pos == 2) {
                        //奇进偶退方式保留2位小数
                        result[4][i] = DataConversion.SingleDoubleOfDecimal(String.valueOf(t), 2);
                    } else {
                        result[4][i] = "";
                    }
                } catch (Exception ex) {
                    result[4][i] = "";
                }
                try//g(m/s2)
                {
                    //cm
                    double h = Double.parseDouble(result[3][i]);
                    h = h / 100.0;
                    //ms
                    double t = Double.parseDouble(result[4][i]);
                    t = t / 1000.0;
                    double g = 2 * h / (t * t);
                    //奇进偶退方式保留4位有效值
                    result[5][i] = DataConversion.SingleDoubleOfEffective(String.valueOf(g), 4);
                } catch (Exception ex) {
                    result[5][i] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(result, 6, 5);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 计算表格中，重力加速度的平均值
     *
     * @param para
     * @param DataSourse 方式一重力加速度
     * @return 方式一重力加速度平均值
     */
    public static PhyParas LTF_GPJ1(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //标准值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            double sum = 0;
            int n = 0;
            for (int i = 1; i < 5; i++) {
                try {
                    double a = Double.parseDouble(str[5][i]);
                    sum += a;
                    n++;
                } catch (Exception ex) {
                }
            }
            if (n >= 1) {
                double g = sum / n;
                //奇进偶退方式保留4位有效值
                para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(g), 4);
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 百分误差
     *
     * @param para
     * @param DataSourse 方式一重力加速度g
     * @return 百分误差
     */
    public static PhyParas LTF_BFWC1(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double g = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            //参考值
            double g0 = 9.79144;
            double radio = Math.abs((g - g0) / g0 * 100);
            //奇进偶退取值方式保留2位有效值
            para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(radio), 2);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    //  endregion

    // region 方式二测量数据及结果

    /**
     * 表2，返回表格数据
     *
     * @param para
     * @param DataSourse 表2
     * @return 表2
     */
    public static PhyParas LTF_list2(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            String[][] result = XmlAnalysis.GetMatrixString(para._StandardValue);
            for (int i = 1; i < 7; i++) {
                try//ZA (cm)
                {
                    double d = Double.parseDouble(str[1][i]);
                    if (d >= 0) {
                        //奇进偶退方式保留2位小数
                        result[1][i] = DataConversion.SingleDoubleOfDecimal(String.valueOf(d), 2);
                    } else {
                        result[1][i] = "";
                    }
                } catch (Exception ex) {
                    result[1][i] = "";
                }
                //ZB1 (cm)
                try {
                    double a = Double.parseDouble(str[1][i]);
                    double d = Double.parseDouble(str[2][i]);
                    if (d >= 0 && d - a > 0) {
                        //奇进偶退方式保留2位小数
                        result[2][i] = DataConversion.SingleDoubleOfDecimal(String.valueOf(d), 2);
                    } else {
                        result[2][i] = "";
                    }
                } catch (Exception ex) {
                    result[2][i] = "";
                }
                try//h1 (cm)
                {
                    double a = Double.parseDouble(result[1][i]);
                    double d = Double.parseDouble(result[2][i]);
                    double h = d - a;
                    //奇进偶退方式保留4位有效值
                    result[3][i] = DataConversion.SingleDoubleOfEffective(String.valueOf(h), 4);
                } catch (Exception ex) {
                    result[3][i] = "";
                }
                try//t1(ms)
                {
                    double t = Double.parseDouble(str[4][i]);
                    int pos = DataConversion.High(String.valueOf(t));
                    if (t > 0 && pos == 2) {
                        //奇进偶退方式保留2位小数
                        result[4][i] = DataConversion.SingleDoubleOfDecimal(String.valueOf(t), 2);
                    } else {
                        result[4][i] = "";
                    }
                } catch (Exception ex) {
                    result[4][i] = "";
                }
                try//h1/t1 (m/s)
                {
                    //cm
                    double h = Double.parseDouble(result[3][i]);
                    h = h / 100.0;
                    //ms
                    double t = Double.parseDouble(result[4][i]);
                    t = t / 1000.0;
                    if (t != 0) {
                        double g = h / t;
                        //奇进偶退方式保留4位有效值
                        result[5][i] = DataConversion.SingleDoubleOfEffective(String.valueOf(g), 4);
                    } else {
                        result[5][i] = "";
                    }
                } catch (Exception ex) {
                    result[5][i] = "";
                }
                try//ZB2 (cm)
                {
                    double a = Double.parseDouble(str[1][i]);
                    double d = Double.parseDouble(str[6][i]);
                    if (d >= 5 && d <= 145 && d - a > 0) {
                        //奇进偶退方式保留2位小数
                        result[6][i] = DataConversion.SingleDoubleOfDecimal(String.valueOf(d), 2);
                    } else {
                        result[6][i] = "";
                    }
                } catch (Exception ex) {
                    result[6][i] = "";
                }
                try//h2 (cm)
                {
                    double a = Double.parseDouble(result[1][i]);
                    double d = Double.parseDouble(result[6][i]);
                    double h = d - a;
                    //奇进偶退方式保留4位有效值
                    result[7][i] = DataConversion.SingleDoubleOfEffective(String.valueOf(h), 4);
                } catch (Exception ex) {
                    result[7][i] = "";
                }
                try//t2(ms)
                {
                    double t = Double.parseDouble(str[8][i]);
                    int pos = DataConversion.High(String.valueOf(t));
                    if (t > 0 && pos == 2) {
                        //奇进偶退方式保留2位小数
                        result[8][i] = DataConversion.SingleDoubleOfDecimal(String.valueOf(t), 2);
                    } else {
                        result[8][i] = "";
                    }
                } catch (Exception ex) {
                    result[8][i] = "";
                }
                try//h2/t2 (m/s)
                {
                    //cm
                    double h2 = Double.parseDouble(result[7][i]);
                    h2 = h2 / 100.0;
                    //ms
                    double t2 = Double.parseDouble(result[8][i]);
                    t2 = t2 / 1000.0;
                    if (t2 != 0) {
                        double g = h2 / t2;
                        //奇进偶退方式保留4位有效值
                        result[9][i] = DataConversion.SingleDoubleOfEffective(String.valueOf(g), 4);
                    } else {
                        result[9][i] = "";
                    }
                } catch (Exception ex) {
                    result[9][i] = "";
                }
                try//g(m/s2)
                {
                    //ms
                    double t1 = Double.parseDouble(result[4][i]);
                    t1 = t1 / 1000.0;
                    double th1 = Double.parseDouble(result[5][i]);
                    //ms
                    double t2 = Double.parseDouble(result[8][i]);
                    t2 = t2 / 1000.0;
                    double th2 = Double.parseDouble(result[9][i]);
                    if ((t2 - t1) != 0) {
                        double g = 2 * (th2 - th1) / (t2 - t1);
                        //奇进偶退方式保留4位有效值
                        result[10][i] = DataConversion.SingleDoubleOfEffective(String.valueOf(g), 4);
                    } else {
                        result[10][i] = "";
                    }
                } catch (Exception ex) {
                    result[10][i] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(result, 11, 7);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 计算方式二重力加速度平均值
     *
     * @param para
     * @param DataSourse 方式二重力加速度
     * @return 方式二重力加速度平均值
     */
    public static PhyParas LTF_GPJ2(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //标准值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            double sum = 0;
            int n = 0;
            for (int i = 1; i < 7; i++) {
                try {
                    double a = Double.parseDouble(str[10][i]);
                    sum += a;
                    n++;
                } catch (Exception ex) {
                }
            }
            if (n >= 1) {
                double g = sum / n;
                //奇进偶退方式保留4位有效值
                para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(g), 4);
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 百分误差
     *
     * @param para
     * @param DataSourse 方式二重力加速度g
     * @return 百分误差
     */
    public static PhyParas LTF_BFWC2(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double g = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            //重力加速度，参考值
            double g0 = 9.79144;
            double radio = Math.abs((g - g0) / g0 * 100);
            //奇进偶退取值方式保留2位有效值
            para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(radio), 2);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    // endregion

    // region 画图法

    /**
     * 返回：坐标x轴, x1值
     *
     * @param para
     * @param DataSourse x1
     * @return x1
     */
    public static PhyParas LTF_X1(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double x = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            para._StandardValue = String.valueOf(x);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 返回：坐标y轴, y1值
     *
     * @param para
     * @param DataSourse y1
     * @return y1
     */
    public static PhyParas LTF_Y1(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double y1 = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            para._StandardValue = String.valueOf(y1);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 返回：坐标x轴 ,x2值
     *
     * @param para
     * @param DataSourse x2
     * @return x2
     */
    public static PhyParas LTF_X2(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double x2 = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            para._StandardValue = String.valueOf(x2);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 返回：坐标y轴 , y2值
     *
     * @param para
     * @param DataSourse y2
     * @return y2
     */
    public static PhyParas LTF_Y2(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double y2 = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            para._StandardValue = String.valueOf(y2);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 通过斜率计算g
     *
     * @param para
     * @param DataSourse x1,y1,x2,y2
     * @return 通过斜率计算g
     */
    public static PhyParas LTF_XL(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double x1 = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double y1 = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            double x2 = Double.parseDouble(DataSourse.get(2)._StandardValue.toString());
            double y2 = Double.parseDouble(DataSourse.get(3)._StandardValue.toString());
            if ((x2 - x1) != 0) {
                double result = 2 * (y2 - y1) / (x2 - x1);
                //奇进偶退方式保留4位有效值
                para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(result), 4);
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 百分误差
     *
     * @param para
     * @param DataSourse 通过斜率计算重力加速度g
     * @return 百分误差
     */
    public static PhyParas LTF_BFWC3(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double g = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            //参考值
            double g0 = 9.79144;
            double radio = Math.abs((g - g0) / g0 * 100);
            //奇进偶退取值方式保留2位有效值
            para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(radio), 2);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion
}
