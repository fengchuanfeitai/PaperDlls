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
    // region 遏制电压方法

    /**
     * 排序
     *
     * @param sourse
     * @return
     */
    private static List<UIStruct> Sort(List<UIStruct> sourse) {
        List<UIStruct> temp = new ArrayList<>();

        while (sourse.size() > 0) {
            UIStruct ui = new UIStruct();
            int n = 0;
            for (int i = 0; i < sourse.size(); i++) {
                if (i == 0) {
                    ui.U = sourse.get(i).U;
                    ui.I = sourse.get(i).I;
                } else if (sourse.get(i).U <= ui.U) {
                    ui.U = sourse.get(i).U;
                    ui.I = sourse.get(i).I;
                    n = i;
                }
            }

            temp.add(ui);
            sourse.remove(sourse.get(n));
        }

        return temp;
    }


    /**
     * 数组转集合
     *
     * @param sourse
     * @return
     */
    private static List<UIStruct> StrMatrixToUIList(String[][] sourse) {
        List<UIStruct> temp = new ArrayList<>();
        //获得列数
        int row = sourse[0].length;

        for (int i = 1; i < row; i++) {
            try {
                UIStruct iterm = new UIStruct();
                iterm.U = Double.parseDouble(sourse[1][i]);
                iterm.I = Double.parseDouble(sourse[2][i]);
                temp.add(iterm);

            } catch (Exception ignored) {
            }
        }
        return temp;
    }

    /**
     * 获取ua
     *
     * @param STR
     * @return
     */
    private static String GetUA(String[][] STR) {
        List<UIStruct> L1 = StrMatrixToUIList(STR);
        List<UIStruct> L1p = Sort(L1);
        int num = L1p.size();
        double U = 0.0;
        boolean HasValue = false;
        for (int i = 0; i < num; i++) {
            if (i < num - 1) {
                if (L1p.get(i).I == 0 && L1p.get(i + 1).I > 0) {
                    U = Math.abs(L1p.get(i).U);
                    HasValue = true;
                }

                if (L1p.get(i).I < 0 && L1p.get(i + 1).I > 0) {
                    U = Math.abs(0.5 * (L1p.get(i).U + L1p.get(i + 1).U));
                    HasValue = true;
                }
            }
        }
        if (HasValue) {
            return String.format("%.1f", U);
        } else {
            return "";
        }
    }

    // endregion

    // region 调用计算方法

    /**
     * uv关系表格
     *
     * @param para
     * @param DataSourse 参数
     * @return
     */
    public static PhyParas GDXYCalcUa(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] GDXY365List = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            String[][] GDXY405List = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StandardValue);
            String[][] GDXY436List = XmlAnalysis.GetMatrixString(DataSourse.get(2)._StandardValue);
            String[][] GDXY546List = XmlAnalysis.GetMatrixString(DataSourse.get(3)._StandardValue);
            String[][] GDXY577List = XmlAnalysis.GetMatrixString(DataSourse.get(4)._StandardValue);

            String[][] GDXYPLKList = XmlAnalysis.GetMatrixString(DataSourse.get(5)._StandardValue);


            GDXYPLKList[2][1] = GetUA(GDXY365List);
            GDXYPLKList[2][2] = GetUA(GDXY405List);
            GDXYPLKList[2][3] = GetUA(GDXY436List);
            GDXYPLKList[2][4] = GetUA(GDXY546List);
            GDXYPLKList[2][5] = GetUA(GDXY577List);
            double a = 519.8;
            double b = 549.2;
            double c = 688.2;
            double d = 741.0;
            double e = 821.6;

            GDXYPLKList[1][1] = String.valueOf(e);
            GDXYPLKList[1][2] = String.valueOf(d);
            GDXYPLKList[1][3] = String.valueOf(c);
            GDXYPLKList[1][4] = String.valueOf(b);
            GDXYPLKList[1][5] = String.valueOf(a);


            para._StandardValue = XmlAnalysis.SetMatrixString(GDXYPLKList, GDXYPLKList.length, GDXYPLKList[0].length);

        } catch (Exception ex) {
            para._StandardValue = "";
        }

        return para;
    }

    // region 最小二乘法

    /**
     * 求一组数据的算术平均值
     *
     * @param numList 输入的一组数据
     * @return 返回数组的算术平均值
     */
    public static double PingJunZhi(double[] numList) {
        double Sum = 0.0;
        for (double v : numList) {
            Sum = Sum + v;
        }
        Sum = Sum / numList.length;
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
     * 普朗克常量
     *
     * @param para
     * @param DataSourse 参数
     * @return
     */
    public static PhyParas GetPLKValue(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] GDXYPLKList = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);

            int row = GDXYPLKList[0].length;
            List<UIStruct> ValidUI = new ArrayList<>();
            for (int i = 1; i < row; i++) {
                try {
                    UIStruct ui = new UIStruct();
                    ui.U = Double.parseDouble(GDXYPLKList[1][i]);
                    ui.I = Double.parseDouble(GDXYPLKList[2][i]);

                    ValidUI.add(ui);
                } catch (Exception ignored) {
                }
            }
            if (ValidUI.size() >= 2) {
                double[] xi = new double[ValidUI.size()];
                double[] yi = new double[ValidUI.size()];

                for (int i = 0; i < ValidUI.size(); i++) {
                    yi[i] = ValidUI.get(i).I;
                    xi[i] = ValidUI.get(i).U;
                }

                double k = (ZuiXiaoErChengFa(xi, yi))[0] * (1.602 * Math.pow(10, -19)) * Math.pow(10, 34) * Math.pow(10, -12);


                para._StandardValue = String.format("%.2f", k);
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }

        return para;
    }

    /**
     * 逐差法处理数据:Yi=X[i+6]-X[i]
     *
     * @param para
     * @param DataSourse X[1~12]
     * @return 得到Y[1~6]
     */
    public static PhyParas GetPLKEr(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double k = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            para._StandardValue = String.format("%.3f", (Math.abs((6.67 - k) / 6.67 * 100.0)));

        } catch (Exception ex) {
            para._StandardValue = "";
        }

        return para;
    }

    /**
     * 逐差法处理数据,计算平均值：Xp=(X1'+X2'+X3'+...+X6')/6
     *
     * @param para
     * @param DataSourse X[1~6]
     * @return 得到平均值Xp
     */
    public static PhyParas CalHXPinLv(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] GDXYPLKList = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);

            int row = GDXYPLKList[0].length;
            List<UIStruct> ValidUI = new ArrayList<>();
            for (int i = 1; i < row; i++) {
                try {
                    UIStruct ui = new UIStruct();
                    ui.U = Double.parseDouble(GDXYPLKList[1][i]);
                    ui.I = Double.parseDouble(GDXYPLKList[2][i]);

                    ValidUI.add(ui);
                } catch (Exception ex) {
                }
            }
            if (ValidUI.size() >= 2) {
                double[] xi = new double[ValidUI.size()];
                double[] yi = new double[ValidUI.size()];

                for (int i = 0; i < ValidUI.size(); i++) {
                    xi[i] = ValidUI.get(i).U;
                    yi[i] = ValidUI.get(i).I;

                }

                double[] result = ZuiXiaoErChengFa(xi, yi);

                double k = result[0] * (1.602 * Math.pow(10, -19)) * Math.pow(10, 34) * Math.pow(10, -12);

                double A = -result[1] * (1.602 * Math.pow(10, -19));
                if (k != 0) {
                    double hx = A / (k * Math.pow(10, -34)) * Math.pow(10, -14);
                    para._StandardValue = String.format("%.2f", hx);
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
     * 逐差法处理数据:u=math.sqrt(tp*ua*tp*ua+tk*ub*tk*ub)
     *
     * @param para
     * @param DataSourse X[1~12]，Xp
     * @return 得到平均值的不确定度
     */
    public static PhyParas CalXieLvK(PhyParas para, List<PhyParas> DataSourse) {
        //para._StandardValue = "";
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);

            int row = str[0].length;
            List<UIStruct> ValidUI = new ArrayList<>();
            for (int i = 1; i < row; i++) {
                try {
                    UIStruct ui = new UIStruct();
                    ui.U = (i - 1) * 0.25;
                    ui.I = Double.parseDouble(str[1][i]);

                    ValidUI.add(ui);
                } catch (Exception ignored) {
                }
            }
            if (ValidUI.size() >= 2) {
                double[] xi = new double[ValidUI.size()];
                double[] yi = new double[ValidUI.size()];

                for (int i = 0; i < ValidUI.size(); i++) {
                    xi[i] = ValidUI.get(i).U;
                    yi[i] = ValidUI.get(i).I;

                }

                para._StandardValue = String.format("%.2f", ((ZuiXiaoErChengFa(xi, yi))[0]));
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