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
     * 直接返回学生答案
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas DianQiao1(PhyParas para, List<PhyParas> DataSourse) {
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
    public static PhyParas DianQiao2(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 4; i++) {
                try {
                    str[6][i] = String.format("%.5f", Double.parseDouble(str[2][i]) * Double.parseDouble(str[3][i]) *
                            Double.parseDouble(str[5][i]) * 0.000001);
                } catch (Exception ex) {
                    str[6][i] = "";
                }
                try {
                    str[7][i] = String.format("%.2f", Double.parseDouble(str[2][i]) * Double.parseDouble(str[3][i]) /
                            Double.parseDouble(str[4][i]) - Double.parseDouble(str[1][i]));
                } catch (Exception ex) {
                    str[7][i] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str, 8, 4);
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
    public static PhyParas DianQiao3(PhyParas para, List<PhyParas> DataSourse) {
        double d = 0;
        double d1 = 0;
        int step = 0;
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 4; i++) {
                try {
                    d = Double.parseDouble(str[6][i]);
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
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(d1 / step), 5);

            } else {
                para._StandardValue = "";
            }
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
    public static PhyParas DianQiao4(PhyParas para, List<PhyParas> DataSourse) {
        double d = 0;
        double d1 = 0;
        int step = 0;
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 4; i++) {
                try {
                    d = Double.parseDouble(str[7][i]);
                    if (d > 0) {
                        step++;
                    }
                    d1 += d;
                } catch (Exception ex) {
                    str[1][i] = "";
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

    public static PhyParas DianQiao5(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double sturesult = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double sturesult1 = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            double sturesult2 = Double.parseDouble(DataSourse.get(2)._StandardValue.toString());
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf((2 * Math.PI * sturesult * sturesult1 / sturesult2)), 2);
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
    public static PhyParas DianQiao6(PhyParas para, List<PhyParas> DataSourse) {
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
    public static PhyParas DianQiao7(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 4; i++) {
                try {
                    str[6][i] = String.format("%.4f", Double.parseDouble(str[2][i]) / Double.parseDouble(str[1][i]) *
                            Double.parseDouble(str[5][i]));
                } catch (Exception ex) {
                    str[6][i] = "";
                }
                try {
                    str[7][i] = String.format("%.2f", Double.parseDouble(str[1][i]) * Double.parseDouble(str[4][i]) /
                            Double.parseDouble(str[2][i]) - Double.parseDouble(str[3][i]));
                } catch (Exception ex) {
                    str[7][i] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str, 8, 4);
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
    public static PhyParas DianQiao8(PhyParas para, List<PhyParas> DataSourse) {
        double d = 0;
        double d1 = 0;
        int step = 0;
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 4; i++) {
                try {
                    d = Double.parseDouble(str[6][i]);
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
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf((d1 / step)), 4);

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
    public static PhyParas DianQiao9(PhyParas para, List<PhyParas> DataSourse) {
        double d = 0;
        double d1 = 0;
        int step = 0;
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 4; i++) {
                try {
                    d = Double.parseDouble(str[7][i]);
                    if (d > 0) {
                        step++;
                    }
                    d1 += d;
                } catch (Exception ex) {
                    str[1][i] = "";
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
}