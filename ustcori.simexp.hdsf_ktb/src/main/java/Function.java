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
     * （1）选定凯特摆两刀口间的距离为（750.0±5.0）mm，调节并测量凯特摆两刀口之间的距离，两刀口之间的距离变为等效摆场。测量三次，并将数据记录到表1
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas B1_FH(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            int columnum = 4;
            double x = 750.0;
            if (str.length > 1) {
                for (int j = 1; j < columnum; j++) {
                    try {
                        double xi = Double.parseDouble(str[1][j]);
                        if (745.0 <= xi && xi <= 755.0) {
                            str[1][j] = DataConversion.CutDataDecimal(String.valueOf(xi), 1);
                        } else {
                            str[1][j] = DataConversion.CutDataDecimal(String.valueOf(x), 1);
                        }
                    } catch (Exception ex) {
                        str[1][j] = "";
                    }
                }
                para._StandardValue = XmlAnalysis.SetMatrixString(str, str.length, str[0].length);
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * （2）等效摆场的平均值l(mm)
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas LL_FH(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            int columnum = 4;
            int num = 3;
            double sum = 0.0;
            double xi = 0.0;
            double eaveary = 0.0;
            if (str.length > 1) {
                for (int j = 1; j < columnum; j++) {
                    try {
                        xi = Double.parseDouble(str[1][j]);
                    } catch (Exception ex) {
                        xi = 0.0;
                        num = num - 1;
                    }
                    sum = sum + xi;
                }
                if (num >= 1) {
                    eaveary = sum / num;
                    para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(eaveary), 1);
                } else {
                    para._StandardValue = "";
                }
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * （3）粗略估算T(s)
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas TT_FH(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double l = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            double g = 9.8;
            if (l >= 0) {
                double T = 2 * Math.PI * Math.sqrt(l / (1000.0 * g));
                para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(T), 3);
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * （1）记录10T1的5次测量值，记录到数据表格2
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas B2_FH(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            int columnum = 6;
            double xi = 0.0;
            if (str.length > 1) {
                for (int j = 1; j < columnum; j++) {
                    try {
                        xi = Double.parseDouble(str[1][j]);
                        str[1][j] = String.valueOf(xi);
                    } catch (Exception ex) {
                        str[1][j] = "";
                    }
                }
                para._StandardValue = XmlAnalysis.SetMatrixString(str, str.length, str[0].length);
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * （2）10个T1的平均值T(s)
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas T1_FH(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            double xi = 0.0;
            int columnum = 6;
            int num = 5;
            double sum = 0.0;
            double average = 0.0;
            for (int j = 1; j < columnum; j++) {
                try {
                    xi = Double.parseDouble(str[1][j]);
                } catch (Exception ex) {
                    xi = 0.0;
                    num = num - 1;
                }
                sum = sum + xi;
            }
            if (num >= 1) {
                average = sum / num;
                para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(average), 3);
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * （1）记录10T2的5次测量值，记录到数据表格3
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas B3_FH(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            int columnum = 6;
            double xi = 0.0;
            if (str.length > 1) {
                for (int j = 1; j < columnum; j++) {
                    try {
                        xi = Double.parseDouble(str[1][j]);
                        str[1][j] = String.valueOf(xi);
                    } catch (Exception ex) {
                        str[1][j] = "";
                    }
                }
                para._StandardValue = XmlAnalysis.SetMatrixString(str, str.length, str[0].length);
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * （2）10个T2的平均值T(s)
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas T2_FH(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            double xi = 0.0;
            int columnum = 6;
            int num = 5;
            double sum = 0.0;
            double average = 0.0;
            for (int j = 1; j < columnum; j++) {
                try {
                    xi = Double.parseDouble(str[1][j]);
                } catch (Exception ex) {
                    xi = 0.0;
                    num = num - 1;
                }
                sum = sum + xi;
            }
            if (num >= 1) {
                average = sum / num;
                para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(average), 3);
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * （1）左刀口到重心的距离h(mm)
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas HH_FH(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double h = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(h), 1);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * （1）计算重力加速度大小g(m/s^2)
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas GG_FH(PhyParas para, List<PhyParas> DataSourse) {
        double g = 9.80;
        try {
            double G = 0.0;
            double LL = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            double T1 = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());
            double T2 = Double.parseDouble(DataSourse.get(2)._StudentValue.toString());
            double HH = Double.parseDouble(DataSourse.get(3)._StudentValue.toString());
            if (LL != 0.0 && (2 * HH - LL != 0.0) && ((T1 * T1 + T2 * T2) / (2 * LL * 0.1) + (T1 * T1 - T2 * T2) / (2 * (2 * HH - LL) * 0.1) != 0.0)) {
                G = 4 * Math.PI * Math.PI / ((T1 * T1 + T2 * T2) / (2 * LL * 0.1) + (T1 * T1 - T2 * T2) / (2 * (2 * HH - LL) * 0.1));
                para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(G), 2) + ";" + DataConversion.CutDataDecimal(String.valueOf(g), 2);
            } else {
                para._StandardValue = "" + ";" + DataConversion.CutDataDecimal(String.valueOf(g), 2);
            }
        } catch (Exception ex) {
            para._StandardValue = "" + ";" + DataConversion.CutDataDecimal(String.valueOf(g), 2);
        }
        return para;
    }
    // endregion

}