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
    public static PhyParas SanXian1(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            para._StandardValue = XmlAnalysis.SetMatrixString(str, 4, 2);
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
    public static PhyParas SanXian2(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 4; i++) {
                try {
                    str[1][i] = String.valueOf(Double.parseDouble(str[1][i]));
                } catch (Exception ex) {
                    str[1][i] = "";
                }
                try {
                    str[2][i] = String.valueOf(Double.parseDouble(str[2][i]));
                } catch (Exception ex) {
                    str[2][i] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str, 3, 4);
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
    public static PhyParas SanXian3(PhyParas para, List<PhyParas> DataSourse) {
        double d = 0;
        double d1 = 0;
        int step = 0;
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 4; i++) {
                try {
                    d = Double.parseDouble(str[1][i]);
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
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf((d1 / step / Math.sqrt(3))), 2);
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
    public static PhyParas SanXian4(PhyParas para, List<PhyParas> DataSourse) {
        double d = 0;
        double d1 = 0;
        int step = 0;
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 4; i++) {
                try {
                    d = Double.parseDouble(str[2][i]);
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
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf((d1 / step / Math.sqrt(3))), 2);
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
    public static PhyParas SanXian5(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 4; i++) {
                try {
                    str[1][i] = String.valueOf(Double.parseDouble(str[1][i]));
                } catch (Exception ex) {
                    str[1][i] = " ";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str, 2, 4);
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
    public static PhyParas SanXian6(PhyParas para, List<PhyParas> DataSourse) {
        double d = 0;
        double d1 = 0;
        int step = 0;
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 4; i++) {
                try {
                    d = Double.parseDouble(str[1][i]);
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
    public static PhyParas SanXian7(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 4; i++) {
                try {
                    str[1][i] = String.format("%.2f", Double.parseDouble(str[1][i]));
                } catch (Exception ex) {
                    str[1][i] = " ";
                }
                try {
                    str[2][i] = String.format("%.2f", Double.parseDouble(str[2][i]));
                } catch (Exception ex) {
                    str[2][i] = " ";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str, 3, 4);
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
    public static PhyParas SanXian8(PhyParas para, List<PhyParas> DataSourse) {
        double d = 0;
        double d1 = 0;
        int step = 0;
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 4; i++) {
                try {
                    d = Double.parseDouble(str[1][i]);
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
    public static PhyParas SanXian9(PhyParas para, List<PhyParas> DataSourse) {
        double d = 0;
        double d1 = 0;
        int step = 0;
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 4; i++) {
                try {
                    d = Double.parseDouble(str[2][i]);
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
    public static PhyParas SanXian10(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            para._StandardValue = XmlAnalysis.SetMatrixString(str, 3, 5);
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
    public static PhyParas SanXian11(PhyParas para, List<PhyParas> DataSourse) {
        double d = 0;
        double d1 = 0;
        int step = 0;
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 5; i++) {
                try {
                    d = Double.parseDouble(str[1][i]);
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
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf((d1 / step / 20.0)), 2);
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
    public static PhyParas SanXian12(PhyParas para, List<PhyParas> DataSourse) {
        double d = 0;
        double d1 = 0;
        int step = 0;
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 5; i++) {
                try {
                    d = Double.parseDouble(str[2][i]);
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
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf((d1 / step / 20.0)), 2);
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
    public static PhyParas SanXian13(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);//学生值
            double sturesult = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());
            double sturesult2 = Double.parseDouble(DataSourse.get(2)._StudentValue.toString());
            double sturesult3 = Double.parseDouble(DataSourse.get(3)._StudentValue.toString());
            double sturesult4 = Double.parseDouble(DataSourse.get(4)._StudentValue.toString());
            double result = Double.parseDouble(str[1][1]) *
                    0.001 * 9.8 * sturesult * sturesult2 * 0.001 * sturesult4 * sturesult4 / (4 * Math.PI * Math.PI * sturesult3);
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(result), 4);
        } catch (Exception ex) {
            para._StandardValue = " ";
        }
        return para;
    }

    /**
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas SanXian14(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);//学生值
            double sturesult = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());
            double sturesult2 = Double.parseDouble(DataSourse.get(2)._StudentValue.toString());
            double sturesult3 = Double.parseDouble(DataSourse.get(3)._StudentValue.toString());
            double sturesult4 = Double.parseDouble(DataSourse.get(4)._StudentValue.toString());
            double result = (Double.parseDouble(str[1][1]) * 0.001 + Double.parseDouble(str[2][1]) * 0.001) *
                    9.8 * sturesult * sturesult2 * 0.001 * sturesult4 * sturesult4 / (4 * Math.PI * Math.PI * sturesult3);
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(result), 4);
        } catch (Exception ex) {
            para._StandardValue = " ";
        }
        return para;
    }

    /**
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas SanXian15(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double sturesult = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            double sturesult2 = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf((sturesult2 - sturesult)), 4);
        } catch (Exception ex) {
            para._StandardValue = " ";
        }
        return para;
    }

    /**
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas SanXian16(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double sturesult = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(sturesult), 2);
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
    public static PhyParas SanXian17(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);//学生值

            for (int i = 1; i < 4; i++) {
                try {
                    str[1][i] = String.format("%.2f", Double.parseDouble(str[1][i]));
                } catch (Exception ex) {
                    str[1][i] = " ";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str, 2, 4);
        } catch (Exception ex) {
            para._StandardValue = " ";
        }
        return para;
    }

    /**
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas SanXian18(PhyParas para, List<PhyParas> DataSourse) {
        double d = 0;
        double d1 = 0;
        int step = 0;
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 4; i++) {
                try {
                    d = Double.parseDouble(str[1][i]);
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
     * 返回学生值
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas SanXian19(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            para._StandardValue = XmlAnalysis.SetMatrixString(str, 2, 4);
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
    public static PhyParas SanXian20(PhyParas para, List<PhyParas> DataSourse) {
        double d = 0;
        double d1 = 0;
        int step = 0;
        try {
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            for (int i = 1; i < 4; i++) {
                try {
                    d = Double.parseDouble(str[1][i]);
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
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf((d1 / step / 20.0)), 2);
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
    public static PhyParas SanXian21(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);//学生值
            double sturesult = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());
            double sturesult2 = Double.parseDouble(DataSourse.get(2)._StudentValue.toString());
            double sturesult3 = Double.parseDouble(DataSourse.get(3)._StudentValue.toString());
            double sturesult4 = Double.parseDouble(DataSourse.get(4)._StudentValue.toString());
            double result = (Double.parseDouble(str[1][1]) * 0.001 + 2 * Double.parseDouble(str[3][1]) * 0.001) *
                    9.8 * sturesult * sturesult2 * 0.001 * sturesult4 * sturesult4 / (4 * Math.PI * Math.PI * sturesult3);
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(result), 4);
        } catch (Exception ex) {
            para._StandardValue = " ";
        }
        return para;
    }

    /**
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas SanXian22(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double sturesult = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            double sturesult2 = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf((0.5 * (sturesult2 - sturesult))), 4);
        } catch (Exception ex) {
            para._StandardValue = " ";
        }
        return para;
    }

    /**
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas SanXian23(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);//学生值
            double sturesult = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());
            double sturesult2 = Double.parseDouble(DataSourse.get(2)._StudentValue.toString());
            double result = Double.parseDouble(str[3][1]) *
                    0.001 * sturesult * sturesult * 0.001 * 0.001 + 0.5 * Double.parseDouble(str[3][1]) *
                    0.001 * 0.001 * 0.001 * sturesult2 * sturesult2;
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(result), 4);
        } catch (Exception ex) {
            para._StandardValue = " ";
        }
        return para;
    }

}