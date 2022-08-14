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
    public static PhyParas ReMin1(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double sturesult = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            if (sturesult == 0.001 || sturesult == 0.01 || sturesult == 0.1 || sturesult == 1 || sturesult == 10 || sturesult == 100 || sturesult == 1000) {
                para._StandardValue = sturesult;
            } else {
                para._StandardValue = 1;
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
     * @param DataSourse
     * @return
     */
    public static PhyParas ReMin2(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 4; i++) {
                try {
                    str[4][i] = String.format("%.2f", Double.parseDouble(str[3][i]) / (Double.parseDouble(str[2][i]) /
                            Double.parseDouble(str[1][i])));
                } catch (Exception ex) {
                    str[4][i] = " ";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str, 5, 4);
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
    public static PhyParas ReMin3(PhyParas para, List<PhyParas> DataSourse) {
        double d = 0;
        double d1 = 0;
        int step = 0;
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 4; i++) {
                try {
                    d = Double.parseDouble(str[4][i]);
                    if (d > 0) {
                        step++;
                    }
                    d1 += d;
                } catch (Exception ex) {
                    para._StandardValue = "";
                }
            }
            if (d1 > 0) {
                //奇进偶退取值方式保留4位有效值
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf((d1 / step)), 2);
            } else {
                para._StandardValue = "";
            }
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
    public static PhyParas ReMin4(PhyParas para, List<PhyParas> DataSourse) {
        double d;
        double d1;
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 15; i++) {
                try {
                    d = Double.parseDouble(str[1][i]);
                    d1 = Double.parseDouble(str[2][i]);
                    if (d1 > 0 && d > 0) {
                        str[3][i] = String.format("%.2f", (d + d1) / 2);
                    }
                } catch (Exception ex) {
                    str[3][i] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str, 4, 15);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    private static double[] rezult;

    /**
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas ReMin6(PhyParas para, List<PhyParas> DataSourse) {
        double d;
        try {
            double[] Xi = new double[]{-1 / 293.15, -1 / 298.15, -1 / 303.15, -1 / 308.15, -1 / 313.15, -1 / 318.15, -1 / 323.15, -1 / 328.15, -1 / 333.15, -1 / 338.15, -1 / 343.15, -1 / 348.15, -1 / 353.15, -1 / 358.15};

            List<Double> list = new ArrayList<>();
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 15; i++) {
                try {
                    d = Math.log(Double.parseDouble(str[3][i]));
                    list.add(d);
                } catch (Exception ex) {

                }
            }
            double[] Yi = list.stream().mapToDouble(Double::doubleValue).toArray();
            rezult = ZuiXiaoErChengFa(Xi, Yi);
            if (Math.abs(rezult[0] - 10000) < 0.00001) {
                para._StandardValue = "";
            } else {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(Math.pow(Math.E, rezult[1])), 5);
            }
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
    public static PhyParas ReMin7(PhyParas para, List<PhyParas> DataSourse) {
        try {
            if (Math.abs(rezult[0] - 10000) < 0.00001) {
                para._StandardValue = "";
            } else {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf((-rezult[0])), 2);
            }
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
    public static PhyParas ReMin8(PhyParas para, List<PhyParas> DataSourse) {
        try {
            if (Math.abs(rezult[0] - 10000) < 0.00001) {
                para._StandardValue = "";
            } else {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf((rezult[0] / (323.15 * 323.15))), 5);
            }
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
    public static PhyParas ReMin9(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double sturesult = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            if (sturesult == 0.001 || sturesult == 0.01 || sturesult == 0.1 || sturesult == 1 || sturesult == 10 || sturesult == 100 || sturesult == 1000) {
                para._StandardValue = sturesult;
            } else {
                para._StandardValue = 1;
            }
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
    public static PhyParas ReMin10(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 4; i++) {
                try {
                    str[4][i] = String.format("%.2f", Double.parseDouble(str[3][i]) / (Double.parseDouble(str[2][i]) /
                            Double.parseDouble(str[1][i])));
                } catch (Exception ex) {
                    str[4][i] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str, 5, 4);
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
    public static PhyParas ReMin11(PhyParas para, List<PhyParas> DataSourse) {
        double d = 0;
        double d1 = 0;
        int step = 0;
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 4; i++) {
                try {
                    d = Double.parseDouble(str[4][i]);
                    if (d > 0) {
                        step++;
                    }
                    d1 += d;
                } catch (Exception ex) {
                    para._StandardValue = "";
                }
            }
            if (d1 > 0) {
                //奇进偶退取值方式保留4位有效值
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf((d1 / step)), 2);
            } else {
                para._StandardValue = "";
            }
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
    public static PhyParas ReMin12(PhyParas para, List<PhyParas> DataSourse) {
        double d;
        double d1;
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 15; i++) {
                try {
                    d = Double.parseDouble(str[1][i]);
                    d1 = Double.parseDouble(str[2][i]);
                    if (d1 > 0 && d > 0) {
                        str[3][i] = String.format("%.2f", (d + d1) / 2);
                    }
                } catch (Exception ex) {
                    str[3][i] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str, 4, 15);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    private static double[] rezult2;

    /**
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas ReMin14(PhyParas para, List<PhyParas> DataSourse) {
        double d;
        try {
            double[] Xi = new double[]{-1 / 293.15, -1 / 298.15, -1 / 303.15, -1 / 308.15, -1 / 313.15, -1 / 318.15, -1 / 323.15, -1 / 328.15, -1 / 333.15, -1 / 338.15, -1 / 343.15, -1 / 348.15, -1 / 353.15, -1 / 358.15};

            List<Double> list = new ArrayList<>();
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 15; i++) {
                try {
                    d = Math.log(Double.parseDouble(str[3][i]));
                    list.add(d);
                } catch (Exception ex) {

                }
            }
            double[] Yi = list.stream().mapToDouble(Double::doubleValue).toArray();
            rezult2 = ZuiXiaoErChengFa(Xi, Yi);
            if (Math.abs(rezult2[0] - 10000) < 0.00001) {
                para._StandardValue = "";
            } else {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(Math.pow(Math.E, rezult2[1])), 5);
            }
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
    public static PhyParas ReMin15(PhyParas para, List<PhyParas> DataSourse) {
        try {
            if (Math.abs(rezult2[0] - 10000) < 0.00001) {
                para._StandardValue = "";
            } else {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(-rezult2[0]), 2);
            }
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
    public static PhyParas ReMin16(PhyParas para, List<PhyParas> DataSourse) {
        try {
            if (Math.abs(rezult2[0] - 10000) < 0.00001) {
                para._StandardValue = "";
            } else {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(rezult2[0] / (323.15 * 323.15)), 5);
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 最小二乘法应用于线性回归:Y=mX+b
     *
     * @param Xi X数据组
     * @param Yi Y数据组
     * @return 返回结果：ZuiXiaoErChengFa[0]是一次系数；ZuiXiaoErChengFa[1]是常数项；ZuiXiaoErChengFa[2]是相关系数
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
            zxecf[0] = m;
            zxecf[1] = b;
            zxecf[2] = r;
        } else {
            zxecf[0] = 10000;
            zxecf[1] = b;
            zxecf[2] = r;
        }
        return zxecf;
    }

    /**
     * 求数组平均值
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