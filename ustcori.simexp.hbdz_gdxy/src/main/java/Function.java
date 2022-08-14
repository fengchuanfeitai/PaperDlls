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
    // region 调用计算方法

    /**
     * 表格一计算公式
     *
     * @param para
     * @param DataSourse 数据
     * @return 表格一数据
     */
    public static PhyParas TListOneFunc(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            String[][] stdStr = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            List<Double> vList = new ArrayList<>();
            List<Double> VList = new ArrayList<>();
            List<Double> vVLsit = new ArrayList<>();
            List<Double> vSquareList = new ArrayList<>();
            List<Double> VSquareList = new ArrayList<>();
            //去除首列和最后一列（平均值列）
            for (int i = 1; i < str[0].length - 1; i++) {
                // region 频率行
                try {
                    double temp = Double.parseDouble(str[1][i]);
                    //转换成功才添加至列表里边
                    vList.add(temp);
                    String tempElement = DataConversion.CutDataDecimal(String.valueOf(temp), 2);
                    stdStr[1][i] = tempElement;
                } catch (Exception ex) {
                    stdStr[1][i] = "";
                }
                // endregion

                // region 截止电压行 V
                try {
                    double temp = Double.parseDouble(str[2][i]);
                    VList.add(temp);
                    String tempElement = DataConversion.CutDataDecimal(String.valueOf(temp), 3);
                    stdStr[2][i] = tempElement;
                } catch (Exception ex) {
                    stdStr[2][i] = "";
                }
                // endregion

                // region 频率乘截止电压行
                try {
                    double v = Double.parseDouble(str[1][i]);
                    double tempV = Double.parseDouble(str[2][i]);
                    String tempResult = String.valueOf(v * tempV);
                    vVLsit.add(v * tempV);
                    String tempElement = DataConversion.CutDataDecimal(tempResult, 3);
                    stdStr[3][i] = tempElement;
                } catch (Exception ex) {
                    stdStr[3][i] = "";
                }
                // endregion

                // region 频率平方行
                try {
                    double temp = Double.parseDouble(str[1][i]);
                    double tempResult = temp * temp;
                    vSquareList.add(tempResult);
                    String tempElement = DataConversion.CutDataDecimal(String.valueOf(tempResult), 3);
                    stdStr[4][i] = tempElement;
                } catch (Exception ex) {
                    stdStr[4][i] = "";
                }
                // endregion

                // region 截止电压平方
                try {
                    double temp = Double.parseDouble(str[2][i]);
                    double tempResult = temp * temp;
                    VSquareList.add(tempResult);
                    String tempElement = DataConversion.CutDataDecimal(String.valueOf(tempResult), 3);
                    stdStr[5][i] = tempElement;
                } catch (Exception ex) {
                    stdStr[5][i] = "";
                }
                // endregion

            }
            // region 第一行平均值
            if (vList.size() != 0) {
                stdStr[1][6] = DataConversion.CutDataDecimal(String.valueOf(vList.stream().mapToDouble(val -> val).average().orElse(0.0)), 2);
            } else {
                stdStr[1][6] = "";
            }
            // endregion

            // region 第二行平均值
            if (VList.size() != 0) {
                stdStr[2][6] = DataConversion.CutDataDecimal(String.valueOf(VList.stream().mapToDouble(val -> val).average().orElse(0.0)), 3)
                ;
            } else {
                stdStr[2][6] = "";
            }
            // endregion

            // region 第三行平均值
            if (vVLsit.size() != 0) {
                stdStr[3][6] = DataConversion.CutDataDecimal(String.valueOf(vVLsit.stream().mapToDouble(val -> val).average().orElse(0.0)), 3)
                ;
            } else {
                stdStr[3][6] = "";
            }
            // endregion

            // region 第四行平均值
            if (vSquareList.size() != 0) {
                stdStr[4][6] = DataConversion.CutDataDecimal(String.valueOf(vSquareList.stream().mapToDouble(val -> val).average().orElse(0.0)), 3);
            } else {
                stdStr[4][6] = "";
            }
            // endregion

            // region 第四行平均值
            if (VSquareList.size() != 0) {
                stdStr[5][6] = DataConversion.CutDataDecimal(String.valueOf(VSquareList.stream().mapToDouble(val -> val).average().orElse(0.0)), 3);
            } else {
                stdStr[5][6] = "";
            }
            // endregion

            para._StandardValue = XmlAnalysis.SetMatrixString(stdStr, stdStr[0].length, stdStr[0].length);
        } catch (Exception ex) {
            para._StandardValue = "";
        }

        return para;
    }

    /**
     * △V计算公式
     *
     * @param para
     * @param DataSourse 数据
     * @return △V
     */
    public static PhyParas DtVFunc(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            //判断参与计算的数据是否为空
            if (!DataConversion.isNullOrEmpty(str[2][1]) && !DataConversion.isNullOrEmpty(str[2][5])) {
                try {
                    double V365 = Double.parseDouble(str[2][1]);
                    double V577 = Double.parseDouble(str[2][5]);
                    double result = V365 - V577;
                    para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(result), 3) + ";1.200";
                } catch (Exception ex) {
                    para._StandardValue = ";1.200";
                }
            } else {
                para._StandardValue = ";1.200";
            }
        } catch (Exception ex) {
            para._StandardValue = ";1.200";
        }

        return para;
    }

    /**
     * 拟合v和V的斜率
     *
     * @param para
     * @param DataSourse 数据
     * @return 拟合v和V的斜率
     */
    static Boolean dataFull = true;

    public static PhyParas SlopeFunc(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);

            // region 检查数据完整性
            for (int i = 1; i < str[0].length - 1; i++) {
                try {
                    Double.parseDouble(str[1][i]);
                    Double.parseDouble(str[2][i]);
                } catch (Exception ex) {

                    dataFull = false;
                    para._StandardValue = ";4.140";
                    return para;
                }
            }
            // endregion

            if (dataFull) {
                List<UIStruct> ValidUI = new ArrayList<>();
                //除去表头列和最后平均值一列
                for (int i = 1; i < str[0].length - 1; i++) {
                    try {
                        UIStruct ui = new UIStruct();
                        //获取数据Us做X轴
                        ui.I = Double.parseDouble(str[1][i]);
                        //获取V做Y轴
                        ui.U = Double.parseDouble(str[2][i]);

                        if (ui.I != 0.0 && ui.U != 0.0) {
                            ValidUI.add(ui);
                        }
                    } catch (Exception ignored) {
                    }
                }
                if (ValidUI.size() >= 2) {
                    double[] xi = new double[ValidUI.size()];
                    double[] yi = new double[ValidUI.size()];

                    for (int i = 0; i < ValidUI.size(); i++) {
                        xi[i] = ValidUI.get(i).I;
                        yi[i] = ValidUI.get(i).U;
                    }

                    double k = (ZuiXiaoErChengFa(xi, yi))[0] * 10.0;
                    if (k != 0.0) {
                        para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(k), 3) + ";4.140";
                    } else {
                        para._StandardValue = ";4.140";
                    }
                } else {
                    para._StandardValue = ";4.140";
                }
            }

        } catch (Exception ex) {
            para._StandardValue = ";4.140";
        }

        return para;
    }

    /**
     * 计算普朗克常数
     *
     * @param para
     * @param DataSourse 数据
     * @return 普朗克常数
     */
    public static PhyParas PlonkValueFunc(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //获取学生数据计算的斜率与标准斜率
            String[] str = DataSourse.get(0)._StandardValue.toString().trim().split(";");
            double k;
            //单位 10^-15
            double unit = 1e-15;
            double e = 1.602e-19;
            double reslut;
            try {
                //转换成功 用学生数据标准值计算的K标准值
                double tempK = Double.parseDouble(str[0]);
                k = tempK;
                //单位换算
                reslut = e * k * unit / (1e-34);
                para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(reslut), 3) + ";6.63";
            } catch (Exception ex) {
                para._StandardValue = ";6.63";
            }

        } catch (Exception ex) {
            para._StandardValue = ";6.63";
        }

        return para;
    }

    /**
     * 普朗克常数不确定度
     *
     * @param para
     * @param DataSourse 数据
     * @return 普朗克常数不确定度
     */
    public static PhyParas PlonkErrorFunc(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double result;
            double hs;
            //获取学生数据计算的斜率与标准斜率
            String[] str = DataSourse.get(0)._StandardValue.toString().trim().split(";");
            try {
                hs = Double.parseDouble(str[0]);
                result = Math.abs(hs - 6.63) / 6.63 * 100.0;
                para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(result), 1) + ";0.0";
            } catch (Exception ex) {
                para._StandardValue = "" + ";0.0";
            }
        } catch (Exception ex) {
            para._StandardValue = "" + ";0.0";
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
        for (double aDouble : NumList) {
            Sum = Sum + aDouble;
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
