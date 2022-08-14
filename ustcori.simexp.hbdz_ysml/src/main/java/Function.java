import common.PhyParas;
import common.XmlAnalysis;

import java.util.List;

/**
 * 实验公式
 *
 * @author cp
 */
public class Function {
    // region  实验数据记录表

    /**
     * 实验数据记录表
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas GS_YSLM_SJBG(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double a1 = 0.0;
            double a2 = 0.0;
            double a3 = 0.0;
            double a4 = 0.0;
            double a5 = 0.0;
            double a6 = 0.0;
            int num1 = 6, num2 = 6, num3 = 6, num4 = 6, num5 = 6, num6 = 6;
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            if (str.length > 1) {
                for (int i = 2; i < str.length - 1; i++) {
                    for (int j = 1; j < str[0].length; j++) {
                        str[i][j] = DataConversion.CutDataDecimal(str[i][j], 2);
                    }
                    try {
                        a1 = a1 + Double.parseDouble(str[i][1]);
                    } catch (Exception ex) {
                        num1 = num1 - 1;
                    }
                    try {
                        a2 = a2 + Double.parseDouble(str[i][2]);
                    } catch (Exception ex) {
                        num2 = num2 - 1;
                    }
                    try {
                        a3 = a3 + Double.parseDouble(str[i][3]);
                    } catch (Exception ex) {
                        num3 = num3 - 1;
                    }
                    try {
                        a4 = a4 + Double.parseDouble(str[i][4]);
                    } catch (Exception ex) {
                        num4 = num4 - 1;
                    }
                    try {
                        a5 = a5 + Double.parseDouble(str[i][5]);
                    } catch (Exception ex) {
                        num5 = num5 - 1;
                    }
                    try {
                        a6 = a6 + Double.parseDouble(str[i][6]);
                    } catch (Exception ex) {
                        num6 = num6 - 1;
                    }
                }
                if (num1 != 0) {
                    str[8][1] = DataConversion.CutDataDecimal(String.valueOf(a1 / num1), 2);
                } else {
                    str[8][1] = "";
                }
                if (num2 != 0) {
                    str[8][2] = DataConversion.CutDataDecimal(String.valueOf(a2 / num2), 2);
                } else {
                    str[8][2] = "";
                }
                if (num3 != 0) {
                    str[8][3] = DataConversion.CutDataDecimal(String.valueOf(a3 / num3), 2);
                } else {
                    str[8][3] = "";
                }
                if (num4 != 0) {
                    str[8][4] = DataConversion.CutDataDecimal(String.valueOf(a4 / num4), 2);
                } else {
                    str[8][4] = "";
                }
                if (num5 != 0) {
                    str[8][5] = DataConversion.CutDataDecimal(String.valueOf(a5 / num5), 2);
                } else {
                    str[8][5] = "";
                }
                if (num6 != 0) {
                    str[8][6] = DataConversion.CutDataDecimal(String.valueOf(a6 / num6), 2);
                } else {
                    str[8][6] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str, str.length, str[0].length);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 用逐差法处理数据，计算出每增减荷重3个砝码标尺读数△n

    /**
     * 用逐差法处理数据，计算出每增减荷重3个砝码标尺读数△n
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas GS_YSLM_ZCF(PhyParas para, List<PhyParas> DataSourse) {
        //////Log("GetLengthOfJSS", DataSourse);
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);//原始数据表格
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StandardValue);//逐差法数据表格
            if (str.length > 1) {
                try {
                    double a1 = Double.parseDouble(str[8][1]);
                    double a4 = Double.parseDouble(str[8][4]);
                    str1[1][1] = DataConversion.CutDataDecimal(String.valueOf(Math.abs(a4 - a1)), 2);
                } catch (Exception ex) {
                    str1[1][1] = "";
                }
                try {
                    double a2 = Double.parseDouble(str[8][2]);
                    double a5 = Double.parseDouble(str[8][5]);
                    str1[1][2] = DataConversion.CutDataDecimal(String.valueOf(Math.abs(a5 - a2)), 2);
                } catch (Exception ex) {
                    str1[1][2] = "";
                }
                try {
                    double a3 = Double.parseDouble(str[8][3]);
                    double a6 = Double.parseDouble(str[8][6]);
                    str1[1][3] = DataConversion.CutDataDecimal(String.valueOf(Math.abs(a6 - a3)), 2);
                } catch (Exception ex) {
                    str1[1][3] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str1, str1.length, str1[0].length);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 每增减荷重3个砝码标尺读数△n的平均值

    /**
     * 每增减荷重3个砝码标尺读数△n的平均值
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas GS_YSML_nPJ(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //逐差法数据表格
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            double a = 0.0;
            double sum = 0.0;
            int num = 0;
            if (str.length > 1) {
                for (int i = 1; i < str[0].length; i++) {
                    try {
                        a = Double.parseDouble(str[1][i]);
                        num = num + 1;
                        sum = sum + a;
                    } catch (Exception ex) {
                        a = 0.0;
                    }
                }
                if (num != 0) {
                    sum = sum / num;
                } else {
                    para._StandardValue = "";
                    return para;
                }
            }
            para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(sum), 2);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 出钢丝直径d的平均值

    /**
     * 出钢丝直径d的平均值
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas GS_YSML_dPJ(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //直径数据表格
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            double a = 0.0;
            double sum = 0.0;
            int num = 0;
            if (str.length > 1) {
                for (int i = 1; i < str[0].length; i++) {
                    try {
                        a = Double.parseDouble(str[1][i]);
                        sum = a + sum;
                        num = num + 1;
                    } catch (Exception ex) {
                        a = 0.0;
                    }
                }
                if (num != 0) {
                    sum = sum / num;
                    para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(sum), 3) + ";0.305";
                } else {
                    para._StandardValue = ";0.305";
                }
            }
        } catch (Exception ex) {
            para._StandardValue = ";0.305";
        }
        return para;
    }
    // endregion

    // region 记录金属丝原长的测量值L

    /**
     * 记录金属丝原长的测量值L
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas GS_YSML_L(PhyParas para, List<PhyParas> DataSourse) {
        try {
            try {
                double a = Double.parseDouble(para._StudentValue.toString());
                if (a >= 43.00 && a <= 52.00) {
                    para._StandardValue = para._StudentValue;
                } else {
                    para._StandardValue = "47.50";
                }
            } catch (Exception ex) {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 记录光杠杆平面镜到标尺的距离的测量值D

    /**
     * 记录光杠杆平面镜到标尺的距离的测量值D
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas GS_YSML_D(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double a = Double.parseDouble(para._StudentValue.toString());
            if (a >= 1.3000 && a <= 1.6500) {
                para._StandardValue = para._StudentValue;
            } else {
                para._StandardValue = "1.4750";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 记录光杠杆臂长的测量值b

    /**
     * 记录光杠杆臂长的测量值b
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas GS_YSML_b(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double a = Double.parseDouble(para._StudentValue.toString());
            if (a >= 66.30 && a <= 92.00) {
                para._StandardValue = para._StudentValue;
            } else {
                para._StandardValue = "79.15";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 计算出金属丝原长L的不确定度u(L)

    /**
     * 计算出金属丝原长L的不确定度u(L)
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas GS_YSML_Ul(PhyParas para, List<PhyParas> DataSourse) {
        try {
            para._StandardValue = "0.29";
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 计算出光杠杆平面镜到标尺的距离D的不确定度u(D)

    /**
     * 计算出光杠杆平面镜到标尺的距离D的不确定度u(D)
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas GS_YSML_UD(PhyParas para, List<PhyParas> DataSourse) {
        try {
            para._StandardValue = "0.58";
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 计算出光杠杆臂长b的不确定度u(b)

    /**
     * 计算出光杠杆臂长b的不确定度u(b)
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas GS_YSML_Ub(PhyParas para, List<PhyParas> DataSourse) {
        try {
            para._StandardValue = "0.012";
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 计算出金属丝直径d的A类不确定度

    /**
     * 计算出金属丝直径d的A类不确定度
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas GS_YSML_UAd(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String Sdpingjun = "0.0";
            double dpingjun = 0.0;
            try {
                String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
                double a1 = 0.0;
                double sum1 = 0.0;
                int num1 = 0;
                if (str.length > 1) {
                    for (int i = 1; i < str[0].length; i++) {
                        try {
                            a1 = Double.parseDouble(str[1][i]);
                            sum1 = a1 + sum1;
                            num1 = num1 + 1;
                        } catch (Exception ex) {
                            a1 = 0.0;
                        }
                    }
                    if (num1 != 0) {
                        sum1 = sum1 / num1;
                        Sdpingjun = DataConversion.CutDataDecimal(String.valueOf(sum1), 3);
                    } else {
                        Sdpingjun = "0.305";
                    }
                }
            } catch (Exception ex) {
                Sdpingjun = "0.305";
            }
            dpingjun = Double.parseDouble(Sdpingjun);
            try {
                double a = 0.0;
                int num = 0;
                double sum = 0.0;
                String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
                if (str.length > 1) {
                    for (int i = 1; i < str[0].length; i++) {
                        try {
                            a = Double.parseDouble(str[1][i]);
                            sum = sum + (a - dpingjun) * (a - dpingjun);
                            num = num + 1;
                        } catch (Exception ex) {
                            a = 0.0;
                        }
                    }
                    if ((num - 1) > 0) {
                        sum = Math.sqrt(sum / (num - 1));
                        para._StandardValue = DataConversion.ToUncertainty(String.valueOf(sum), 2);
                    } else if ((num - 1) == 0) {
                        sum = 0.0;
                        para._StandardValue = DataConversion.ToUncertainty(String.valueOf(sum), 2);
                    } else {
                        sum = 0.0;
                        para._StandardValue = "";
                    }
                }
            } catch (Exception ex) {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 计算出金属丝直径d的B类不确定度

    /**
     * 计算出金属丝直径d的B类不确定度
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas GS_YSML_UBd(PhyParas para, List<PhyParas> DataSourse) {
        try {
            para._StandardValue = "0.012";
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 计算出金属丝直径d的合成不确定度

    /**
     * 计算出金属丝直径d的合成不确定度
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas GS_YSML_UZJ(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //A类不确定度
            double a = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            //B类不确定度
            double b = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            double u = Math.sqrt(a * a + b * b);
            para._StandardValue = DataConversion.ToUncertainty(String.valueOf(u), 2);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 计算出△n平均值的A类不确定度

    /**
     * 计算出△n平均值的A类不确定度
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas GS_YSML_nPJA(PhyParas para, List<PhyParas> DataSourse) {
        try {
            try {
                double a = 0.0;
                int num = 0;
                double sum = 0.0;
                //逐差法△n表格
                String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
                //△n平均值
                double npingjun = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
                if (str.length > 1) {
                    for (int i = 1; i < str[0].length; i++) {
                        try {
                            a = Double.parseDouble(str[1][i]);
                            sum = sum + (a - Math.abs(npingjun)) * (a - Math.abs(npingjun));
                            num = num + 1;
                        } catch (Exception ex) {
                            a = 0.0;
                        }
                    }
                    if ((num - 1) > 0) {
                        sum = Math.sqrt(sum / (num - 1));
                        para._StandardValue = DataConversion.ToUncertainty(String.valueOf(sum), 2);
                    } else if ((num - 1) == 0) {
                        sum = 0.0;
                        para._StandardValue = DataConversion.ToUncertainty(String.valueOf(sum), 2);
                    } else {
                        sum = 0.0;
                        para._StandardValue = "";
                    }
                }
            } catch (Exception ex) {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 计算出△n平均值的B类不确定度

    /**
     * 计算出△n平均值的B类不确定度
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas GS_YSML_nPJB(PhyParas para, List<PhyParas> DataSourse) {
        try {
            para._StandardValue = "0.058";
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 计算出△n平均值的不确定度

    /**
     * 计算出△n平均值的不确定度
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas GS_YSML_nPJBQD(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //A类不确定度
            double a = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            //B类不确定度
            double b = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            double u = Math.sqrt(a * a + b * b);
            para._StandardValue = DataConversion.ToUncertainty(String.valueOf(u), 2);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 杨氏模量的结果表达式

    /**
     * 杨氏模量的结果表达式
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas GS_YSML_YSMLExpression(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String s1 = "";
            String s2 = "";
            try {
                double Epingjun = 0;
                double uE = 0;
                //金属丝原长L的不确定度u(L)
                double uL = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
                //金属丝原长的测量值L
                double L = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());
                //光杠杆平面镜到标尺的距离D的不确定度u(D)
                double uD = Double.parseDouble(DataSourse.get(2)._StandardValue.toString());
                //光杠杆平面镜到标尺的距离的测量值D
                double D = Double.parseDouble(DataSourse.get(3)._StudentValue.toString());
                //光杠杆臂长b的不确定度u(b)
                double ub = Double.parseDouble(DataSourse.get(4)._StandardValue.toString());
                //光杠杆臂长的测量值b
                double b = Double.parseDouble(DataSourse.get(5)._StudentValue.toString());
                //金属丝直径d的不确定度ud
                double uAd = Double.parseDouble(DataSourse.get(6)._StandardValue.toString());
                String Sdpingjun = "0.0";
                double dpingjun = 0.0;
                try {
                    //直径数据表格
                    String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(7)._StudentValue);
                    double a = 0.0;
                    double sum = 0.0;
                    int num = 0;
                    if (str.length > 1) {
                        for (int i = 1; i < str[0].length; i++) {
                            try {
                                a = Double.parseDouble(str[1][i]);
                                sum = a + sum;
                                num = num + 1;
                            } catch (Exception ex) {
                                a = 0.0;
                            }
                        }
                        if (num != 0) {
                            sum = sum / num;
                            Sdpingjun = DataConversion.CutDataDecimal(String.valueOf(sum), 3);
                        } else {
                            Sdpingjun = "0.305";
                        }
                    }
                } catch (Exception ex) {
                    Sdpingjun = "0.305";
                }
                dpingjun = Double.parseDouble(Sdpingjun);
                // un平均的不确定度
                double unpingjun = Double.parseDouble(DataSourse.get(8)._StandardValue.toString());
                // 每增减荷重3个砝码标尺读数△n的平均值
                double npingjun = Double.parseDouble(DataSourse.get(9)._StandardValue.toString());
                //一个砝码的质量
                double M = Double.parseDouble(DataSourse.get(10)._StudentValue.toString());
                //杨氏模量的相对不确定度UrE
                double UrE = Math.sqrt(Math.pow(uL / (L * 10), 2) + Math.pow(uD / (D * 1000), 2) + Math.pow(ub / b, 2) + Math.pow(2 * uAd / dpingjun, 2) + Math.pow(unpingjun / (npingjun * 10), 2));

                if (dpingjun != 0 && b != 0 && npingjun != 0) {
                    Epingjun = 8.0 * M * 3.0 * 9.8 * D * (L * 0.01) / (3.14 * Math.pow(dpingjun / 1000.0, 2) * (b / 1000.0) * (npingjun / 100.0));
                    Epingjun = Epingjun / 100000000000.0;
                    uE = Epingjun * UrE;
                    s1 = DataConversion.GetExpressionBKD(String.valueOf(Epingjun), String.valueOf(uE), 1);
                    s2 = DataConversion.GetExpressionBKD(String.valueOf(Epingjun), String.valueOf(uE), 2);
                } else {
                    s1 = "";
                    s2 = "";
                }

            } catch (Exception ex) {
                s1 = "";
                s2 = "";
            }

            if (!DataConversion.isNullOrEmpty(para._StudentValue.toString())) {
                try {
                    String a = para._StudentValue.toString();
                    String s1_XS = a.split("±")[0];
                    String s2_XS = a.split("±")[1];
                    try {
                        double XS1 = Double.parseDouble(s1_XS);
                        double XS2 = Double.parseDouble(s2_XS);
                        String s3 = DataConversion.GetExpressionBKD(String.valueOf(XS1), String.valueOf(XS2), 1);
                        String s4 = DataConversion.GetExpressionBKD(String.valueOf(XS1), String.valueOf(XS2), 2);
                        if (s3.equals(s4)) {
                            para._StandardValue = s3;
                        } else {
                            para._StandardValue = s3 + "或" + s4;
                        }
                    } catch (Exception ex) {
                        if (s1.equals(s2)) {
                            para._StandardValue = s1;
                        } else {
                            para._StandardValue = s1 + "或" + s2;
                        }
                    }
                } catch (Exception ex) {
                    if (s1.equals(s2)) {
                        para._StandardValue = s1;
                    } else {
                        para._StandardValue = s1 + "或" + s2;
                    }
                }
            } else {
                if (s1.equals(s2)) {
                    para._StandardValue = s1;
                } else {
                    para._StandardValue = s1 + "或" + s2;
                }
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region 日志记录
    //public static void Log(string method, List<PhyParas> list)
    //{
    //    Log("方法" + method);
    //    for (int i = 0; i < list.Count; i++)
    //    {
    //        Log("参数" + (i + 1) + ":" + list[i]._StandardValue);
    //    }
    //}

    //public static void Log(object str)
    //{
    //    StreamWriter log = new StreamWriter(@"D:\\1.txt", true);
    //    log.WriteLine(str + "\n");
    //    log.Close();
    //}
    // endregion

}