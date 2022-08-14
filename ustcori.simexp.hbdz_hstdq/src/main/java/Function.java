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
     * 表格一计算公式
     *
     * @param para
     * @param DataSourse 数据
     * @return 表格一数据
     */
    public static PhyParas TlistOneFunc(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            String[][] stdStr = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            if (str.length > 1) {
                for (int i = 1; i < str[0].length; i++) {
                    try {
                        double tempR1 = Double.parseDouble(str[0][i]);
                        double tempR2 = Double.parseDouble(str[2][i]);
                        double tempResult = Math.sqrt(tempR1 * tempR2);
                        if (tempResult >= 97 && tempResult <= 103) {
                            stdStr[3][i] = DataConversion.CutDataDecimal(String.valueOf(tempResult), 3);
                        } else {
                            stdStr[3][i] = "100.000";
                        }
                    } catch (Exception ex) {
                        stdStr[3][i] = "";
                    }
                }
                para._StandardValue = XmlAnalysis.SetMatrixString(stdStr, stdStr.length, stdStr[0].length);
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * Rx平均值计算
     *
     * @param para
     * @param DataSourse 数据
     * @return Rx平均值
     */
    public static PhyParas AvgRxFunc(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            String result = StudentAvgValue(str);
            para._StandardValue = DataConversion.CutDataDecimal(result, 3) + ";100.000";
        } catch (Exception ex) {
            para._StandardValue = "" + ";100.000";
        }
        return para;
    }

    /**
     * 返回计算灵敏度的△R
     *
     * @param para
     * @param DataSourse 数据
     * @return 返回计算灵敏度的△R
     */
    public static PhyParas DtRFunc(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double dtR = Double.parseDouble(para._StudentValue.toString());
            para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(dtR), 2);
        } catch (Exception ex) {
            para._StandardValue = "";
        }

        return para;
    }

    /**
     * 电桥灵敏度S
     *
     * @param para
     * @param DataSourse 数据
     * @return 电桥灵敏度S
     */
    public static PhyParas SValueFounc(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double dtR = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            if (dtR == 0.0) {
                para._StandardValue = "";
                return para;
            } else {
                para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(1 / dtR), 3);
            }
        } catch (Exception ex) {

            para._StandardValue = "";
        }

        return para;
    }

    /**
     * A 类不确定度
     *
     * @param para
     * @param DataSourse 数据
     * @return A 类不确定度
     */
    public static PhyParas UaFunc(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);

            double Xp = 0.0;
            try {
                //转换成功，则利用计算的标准值；
                Xp = Double.parseDouble(StudentAvgValue(str));
            } catch (Exception ex) {
                //无法计算出平均值，则平均值不确定度直接返回空字符串
                para._StandardValue = ";0.775";
                return para;
            }
            //表格总列数
            int rownum = str[0].length;
            //表的列头占用列数
            int rowhead = 1;
            //要用到的数据所在行数
            int dsnum = 3;

            if (str.length > 1) {
                double sum = 0.0;
                double xi = 0.0;
                int num = 5;
                for (int i = rowhead; i < rownum; i++) {
                    try {
                        xi = (Double.parseDouble(str[dsnum][i]) - Xp);
                    } catch (Exception ex) {
                        xi = 0.0;
                        num = num - 1;
                    }
                    sum += xi * xi;
                }
                if (num > 1) {
                    double ua = Math.sqrt(sum / (num - 1));
                    para._StandardValue = DataConversion.ToDecimal(String.valueOf(ua), 2) + ";0.775";
                } else {
                    para._StandardValue = ";0.775";
                }
            }
        } catch (Exception ex) {
            para._StandardValue = ";0.775";
        }
        return para;
    }

    /**
     * B 类不确定度
     *
     * @param para
     * @param DataSourse 数据
     * @return B 类不确定度
     */
    public static PhyParas UbFunc(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double avgRx;
            String[][] tempstr = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            try {
                //转换成功，则利用计算的标准值；
                avgRx = Double.parseDouble(StudentAvgValue(tempstr));
            } catch (Exception ex) {
                para._StandardValue = ";0.80";
                return para;
            }
            double svalue = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            double a = 0.1;
            double b = 0.2;
            double m = 6.0;
            double dtn = 0.2;
            double uB1 = dtn / svalue;
            if (avgRx != 0 && svalue != 0) {
                double dtIns = avgRx * (a + b * m / avgRx) * 0.01;
                double uB2 = dtIns / Math.sqrt(3);
                double result = Math.sqrt(uB1 * uB1 + uB2 * uB2);
                para._StandardValue = DataConversion.ToDecimal(String.valueOf(result), 2) + ";0.80";
            } else {
                para._StandardValue = ";0.80";
                return para;
            }
        } catch (Exception ex) {
            para._StandardValue = ";0.80";
        }
        return para;
    }

    /**
     * Rx合成不确定度
     *
     * @param para
     * @param DataSourse 数据
     * @return Rx合成不确定度
     */
    public static PhyParas URxFunc(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[] strua = DataSourse.get(0)._StandardValue.toString().trim().split(";");
            String[] strub = DataSourse.get(1)._StandardValue.toString().trim().split(";");
            try {
                double ua = Double.parseDouble(strua[0]);
                double ub = Double.parseDouble(strub[0]);
                double result = Math.sqrt(ua * ua + ub * ub);
                para._StandardValue = DataConversion.ToDecimal(String.valueOf(result), 2) + ";0.90";
            } catch (Exception ex) {
                para._StandardValue = ";0.90";
                return para;
            }

        } catch (Exception ex) {
            para._StandardValue = ";0.90";
        }
        return para;
    }

    /**
     * Rx相对不确定度
     *
     * @param para
     * @param DataSourse 数据
     * @return Rx相对不确定度
     */
    public static PhyParas UrRxFunc(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[] strURx = DataSourse.get(0)._StandardValue.toString().trim().split(";");
            String[][] tempstr = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StudentValue);
            try {
                double URx = Double.parseDouble(strURx[0]);
                double avgRx = Double.parseDouble(StudentAvgValue(tempstr));
                if (avgRx != 0.0) {
                    double result = URx / avgRx * 100;
                    para._StandardValue = DataConversion.ToDecimal(String.valueOf(result), 2) + ";1.05";
                } else {
                    para._StandardValue = ";1.05";
                }
            } catch (Exception ex) {
                para._StandardValue = ";1.05";
                return para;
            }
        } catch (Exception ex) {
            para._StandardValue = ";1.05";
        }
        return para;
    }

    /**
     * Rx完整表达式
     *
     * @param para
     * @param DataSourse 数据
     * @return Rx完整表达式
     */
    public static PhyParas RxExpressionFunc(PhyParas para, List<PhyParas> DataSourse) {
        try {
            if (!DataConversion.isNullOrEmpty(DataSourse.get(2)._StudentValue.toString()) && !DataConversion.isNullOrEmpty(DataSourse.get(1)._StudentValue.toString())) {
                para._StandardValue = DataConversion.GetExpressionBKD(DataSourse.get(2)._StudentValue.toString(), DataSourse.get(1)._StudentValue.toString(), 2);
            } else if (!DataConversion.isNullOrEmpty(DataSourse.get(0)._StandardValue.toString()) && !DataConversion.isNullOrEmpty(DataSourse.get(1)._StandardValue.toString())) {
                try {
                    String[][] strRx = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
                    String rx = StudentAvgValue(strRx);
                    String[] strURx = DataSourse.get(1)._StandardValue.toString().trim().split(";");
                    if (!DataConversion.isNullOrEmpty(rx) && !DataConversion.isNullOrEmpty(strURx[0])) {
                        para._StandardValue = DataConversion.GetExpressionBKD(rx, strURx[0], 1) + ";" + DataConversion.GetExpressionBKD(rx, strURx[0], 2);
                    } else {
                        para._StandardValue = "";
                    }
                } catch (Exception ex) {
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

    // region 最小二乘法计算斜率

    /**
     * 求一组数据的算术平均值
     *
     * @param NumList 输入的一组数据
     * @return 返回数组的算术平均值
     */
    public static double PingJunZhi(double[] NumList) {
        double Sum = 0.0;
        for (double v : NumList) {
            Sum = Sum + v;
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


    /**
     * 平均值
     *
     * @param str 数据
     * @return 平均值
     */
    private static String StudentAvgValue(String[][] str) {
        if (str.length > 1) {
            for (int i = 1; i < str[0].length; i++) {
                try {
                    double tempR1 = Double.parseDouble(str[0][i]);
                    double tempR2 = Double.parseDouble(str[2][i]);
                    //根据学生值计算学生数据计算的标准答案
                    double tempResult = Math.sqrt(tempR1 * tempR2);
                    str[3][i] = DataConversion.CutDataDecimal(String.valueOf(tempResult), 3);
                } catch (Exception ex) {
                    str[3][i] = "";
                }
            }

            double xi = 0.0;
            double sum = 0.0;
            double num = 5;
            for (int j = 1; j < 6; j++) {
                try {
                    xi = Double.parseDouble(str[3][j]);
                } catch (Exception ex) {
                    xi = 0.0;
                    num -= 1;
                }
                sum = sum + xi;
            }
            if (num > 0) {
                return DataConversion.CutDataDecimal(String.valueOf(sum / num), 3);
            } else {
                return "";
            }
        } else {
            return "";
        }
    }
    // endregion

}