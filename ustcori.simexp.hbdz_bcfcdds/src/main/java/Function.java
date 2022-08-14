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
    //  region 实验数据记录表

    /**
     * 实验数据记录表
     *
     * @param para
     * @param DataSourse 实验数据
     * @return
     */
    public static PhyParas GS_BCFCDDS_SJBG(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //电阻丝长度数据表格
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue.toString());
            //取7.5～8.5之间的有效数值
            List<Double> validlist = new ArrayList<>();
            double result = 0.0;
            if (str.length > 1) {
                double a = 0.0;
                for (int i = 1; i < str[0].length; i++) {
                    try {
                        a = Double.parseDouble(str[1][i]);
                        if (Math.abs(a - 8.0) <= 0.5) {
                            validlist.add(a);
                        } else {
                            str[1][i] = "8.0000";
                        }
                    } catch (Exception ex) {
                        str[1][i] = "";
                    }
                }
                // region 取分布概率最大的数值作为当前标准答案
                if (validlist.size() > 0) {
                    int[] maxValues = new int[validlist.size()];
                    for (int l = 0; l < validlist.size(); l++) {
                        for (Double aDouble : validlist) {
                            if (Math.abs(validlist.get(l) - aDouble) <= 0.1) {
                                maxValues[l]++;
                            }
                        }
                    }
                    int index = -1;
                    int max = 0;
                    for (int m = 0; m < validlist.size(); m++) {
                        if (maxValues[m] > max) {
                            max = maxValues[m];
                            index = m;
                        }
                    }
                    //标准答案

                    if (index >= 0) {
                        result = validlist.get(index);
                    } else {
                        //没有找到有效最大数值
                        result = 8.0000;
                    }
                }
                // endregion

                for (int i = 1; i < str[0].length; i++) {
                    if (!"8.0000".equals(str[1][i]) && result != 0 && !"".equals(str[1][i])) {
                        str[1][i] = DataConversion.CutDataDecimal(String.valueOf(result), 4);
                    }
                }
                para._StandardValue = XmlAnalysis.SetMatrixString(str, str.length, str[0].length);
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        ///Log("result:" + para._StandardValue);
        return para;
    }
    // endregion

    // region

    /**
     * 计算电阻丝长度L'CD六次测量的平均值
     *
     * @param para
     * @param DataSourse 数据
     * @return
     */
    public static PhyParas GS_BCFCDDS_Lcdpj(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //电阻丝长度数据表格
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue.toString());
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
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(sum), 6);
                } else {
                    para._StandardValue = "";
                }
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        ///Log("result:" + para._StandardValue);
        return para;
    }
    // endregion

    //  region 记录数据值L

    /**
     * 记录数据值L
     *
     * @param para
     * @param DataSourse 数据
     * @return 数据值L
     */
    public static PhyParas GS_BCFCDDS_L(PhyParas para, List<PhyParas> DataSourse) {
        //////Log("GetlengthOfJSS", DataSourse);
        try {
            double a = Double.parseDouble(para._StudentValue.toString());
            if (a >= 7.5 && a <= 8.5) {
                para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(para._StudentValue.toString()), 4);
            } else {
                para._StandardValue = "8.0000";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        ///Log("result:" + para._StandardValue);
        return para;
    }
    // endregion

    //   region 记录数据值L'

    /**
     * 记录数据值L'
     *
     * @param para
     * @param DataSourse 数据
     * @return 数据值L'
     */
    public static PhyParas GS_BCFCDDS_L2(PhyParas para, List<PhyParas> DataSourse) {
        //////Log("GetlengthOfJSS", DataSourse);
        try {
            double a = Double.parseDouble(para._StudentValue.toString());
            if (a >= 7.5 && a <= 8.5) {
                para._StandardValue = DataConversion.CutDataDecimal(para._StudentValue.toString(), 4);
            } else {
                para._StandardValue = "8.0000";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        ///Log("result:" + para._StandardValue);
        return para;
    }
    // endregion

    //   region

    /**
     * 根据公式计算出L'CD的最大误差
     *
     * @param para
     * @param DataSourse 数据
     * @return L'CD的最大误差
     */
    public static PhyParas GS_BCFCDDS_Lwc(PhyParas para, List<PhyParas> DataSourse) {
        //////Log("GetlengthOfJSS", DataSourse);
        try {
            double L1 = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            double L2 = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());
            double Lwc = Math.abs(L1 - L2) / 2.0;
            para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(Lwc), 5);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        ///Log("result:" + para._StandardValue);
        return para;
    }
    // endregion

    //  region 求出待测电池的电动势

    /**
     * 求出待测电池的电动势
     *
     * @param para
     * @param DataSourse 数据
     * @return 待测电池的电动势
     */
    public static PhyParas GS_BCFCDDS_Expj(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //电阻丝单位长度上的电压降A
            double a = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            //计算电阻丝长度L'CD六次测量的平均值
            double Lcdpingjun = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            double Ex = a * Lcdpingjun;
            para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(Ex), 5);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region L'CD的A类不确定度

    /**
     * L'CD的A类不确定度
     *
     * @param para
     * @param DataSourse 数据
     * @return L'CD的A类不确定度
     */
    public static PhyParas GS_BCFCDDS_UALcd(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //电阻丝长度数据表格
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue.toString());
            //平均值
            double Lpingjun = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            double ai = 0.0;
            int num = 0;
            double sum = 0.0;
            if (str.length > 1) {
                for (int i = 1; i < str[0].length; i++) {
                    try {
                        ai = Double.parseDouble(str[1][i]);
                        num = num + 1;
                        sum = sum + Math.pow(ai - Lpingjun, 2);
                    } catch (Exception ex) {
                        ai = 0.0;
                    }
                }
                if (num - 1 > 0) {
                    sum = Math.sqrt(sum / (num - 1));
                    para._StandardValue = DataConversion.ToUncertainty(String.valueOf(sum), 2);
                } else if (num - 1 == 0) {
                    sum = 0.0;
                    para._StandardValue = DataConversion.ToUncertainty(String.valueOf(sum), 2);
                } else {
                    para._StandardValue = "";
                }
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    // region L'CD的B类不确定度

    /**
     * L'CD的B类不确定度
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas GS_BCFCDDS_UBLcd(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double L1 = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            double L2 = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());
            double Ub = Math.abs(L1 - L2) / (2 * Math.sqrt(3));
            para._StandardValue = DataConversion.ToUncertainty(String.valueOf(Ub), 2);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    //  region LcD的合成不确定度

    /**
     * LcD的合成不确定度
     *
     * @param para
     * @param DataSourse 数据
     * @return LcD的合成不确定度
     */
    public static PhyParas GS_BCFCDDS_ULcd(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double ua = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double ub = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            double U = Math.sqrt(ua * ua + ub * ub);
            para._StandardValue = DataConversion.ToUncertainty(String.valueOf(U), 2);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    //  region 考虑A的B类不确定度则

    /**
     * 考虑A的B类不确定度则
     *
     * @param para
     * @param DataSourse 数据
     * @return A的B类不确定度则
     */
    public static PhyParas GS_BCFCDDS_UABLcd(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //Ub(L'cd)类不确定度
            double a = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            //L'cd平均
            double b = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            if (b != 0) {
                double u = a / b;
                para._StandardValue = DataConversion.ToUncertainty(String.valueOf(u), 2);
            } else {
                para._StandardValue = "";
            }

        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    //   region Ex的相对不确定度（%）

    /**
     * Ex的相对不确定度（%）
     *
     * @param para
     * @param DataSourse 数据
     * @return Ex的相对不确定度（%）
     */
    public static PhyParas GS_BCFCDDS_UrEx(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double a = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double b = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            double c = Double.parseDouble(DataSourse.get(2)._StandardValue.toString());
            if (c != 0.0) {
                double ure = Math.sqrt(a * a + b * b / (c * c)) * 100.0;
                para._StandardValue = DataConversion.ToUncertainty(String.valueOf(ure), 2);
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    //  region Ex的不确定度

    /**
     * Ex的不确定度
     *
     * @param para
     * @param DataSourse 数据
     * @return Ex的不确定度
     */
    public static PhyParas GS_BCFCDDS_UEx(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double a = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double b = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            double c = a * b / 100.0;
            para._StandardValue = DataConversion.ToUncertainty(String.valueOf(c), 2);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
    // endregion

    //   region 待测电池的电动势的结果表达式

    /**
     * 待测电池的电动势的结果表达式
     *
     * @param para
     * @param DataSourse 数据
     * @return 待测电池的电动势
     */
    public static PhyParas GS_BCFCDDS_BDS(PhyParas para, List<PhyParas> DataSourse) {
        try {
            if (!DataConversion.isNullOrEmpty(DataSourse.get(0)._StudentValue.toString()) && !DataConversion.isNullOrEmpty(DataSourse.get(1)._StudentValue.toString())) {
                String s1 = DataConversion.GetExpressionBKD(DataSourse.get(0)._StudentValue.toString(), DataSourse.get(1)._StudentValue.toString(), 1);
                String s2 = DataConversion.GetExpressionBKD(DataSourse.get(0)._StudentValue.toString(), DataSourse.get(1)._StudentValue.toString(), 2);
                if (s1.equals(s2)) {
                    para._StandardValue = s1;
                } else {
                    para._StandardValue = s1 + "或" + s2;
                }
            } else if (!DataConversion.isNullOrEmpty(DataSourse.get(0)._StandardValue.toString()) && !DataConversion.isNullOrEmpty(DataSourse.get(1)._StandardValue.toString())) {
                String s1 = DataConversion.GetExpressionBKD(DataSourse.get(0)._StandardValue.toString(), DataSourse.get(1)._StandardValue.toString(), 1);
                String s2 = DataConversion.GetExpressionBKD(DataSourse.get(0)._StandardValue.toString(), DataSourse.get(1)._StandardValue.toString(), 2);
                if (s1.equals(s2)) {
                    para._StandardValue = s1;
                } else {
                    para._StandardValue = s1 + "或" + s2;
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

    //  region 日志记录
    //public static void Log(String method, List<PhyParas> list)
    //{
    //    Log("方法" + method);
    //    for (int i = 0; i < list.size(); i++)
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
