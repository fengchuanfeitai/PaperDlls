import common.PhyParas;
import common.XmlAnalysis;

import java.util.List;

/**
 * 实验公式
 *
 * @author cp
 */
public class Function {
    //region 调用计算方法

    /**
     * 计算单摆摆长平均值:X=(X1+X2+...+X5)/5
     *
     * @param para
     * @param DataSourse 实际测量结果数据表格
     * @return 摆长平均值Lp
     */
    public static PhyParas GetGSHBJList(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);

            if (str.length > 1) {
                for (int i = 1; i < str[0].length; i++) {
                    //D1
                    try {
                        str[3][i] = String.format("%.3f", Math.abs(Double.parseDouble(str[2][i]) - Double.parseDouble(str[1][i])));
                    } catch (Exception ex) {
                        str[3][i] = "";
                    }

                    //D2
                    try {
                        str[6][i] = String.format("%.3f", Math.abs(Double.parseDouble(str[5][i]) - Double.parseDouble(str[4][i])));
                    } catch (Exception ex) {
                        str[6][i] = "";
                    }

                    //D3
                    try {
                        str[9][i] = String.format("%.3f", Math.abs(Double.parseDouble(str[8][i]) - Double.parseDouble(str[7][i])));
                    } catch (Exception ex) {
                        str[9][i] = "";
                    }
                }
                para._StandardValue = XmlAnalysis.SetMatrixString(str, str.length, str[0].length);
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }

        return para;
    }

    /**
     * 计算干涉环的直径
     *
     * @param para
     * @param DataSourse 实际测量结果数据表格
     * @return 平均值
     */
    public static PhyParas GetGSZHJList(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            String[][] result = XmlAnalysis.GetMatrixString(para._StandardValue);
            if (str.length > 1) {
                //20200420重写
                for (int i = 1; i < result[0].length; i++) {
                    //D平均值
                    try {
                        result[1][i] = DataConversion.CutDataDecimal(String.valueOf((Double.parseDouble(str[3][i])
                                + Double.parseDouble(str[6][i]) + Double.parseDouble(str[9][i])) / 3.0), 3);
                    } catch (Exception ex) {
                        result[1][i] = "";
                    }
                    //不确定度
                    try {
                        double d1 = Double.parseDouble(str[3][i]);
                        double d2 = Double.parseDouble(str[6][i]);
                        double d3 = Double.parseDouble(str[9][i]);
                        double ave = Double.parseDouble(result[1][i]);
                        double biaoZhuenCha = Math.sqrt(((d1 - ave) * (d1 - ave) + (d2 - ave) * (d2 - ave) + (d3 - ave) * (d3 - ave)) / (3 - 1));
                        result[2][i] =
                                DataConversion.ToUncertainty(String.valueOf(Math.sqrt(Math.pow(4.30 * biaoZhuenCha / Math.sqrt(3), 2) + Math.pow(1.96 * 0.005 / 3.0, 2))), 2);
                    } catch (Exception ex) {
                        result[2][i] = "";
                    }
                }
                para._StandardValue = XmlAnalysis.SetMatrixString(result, result.length, result[0].length);
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }

        return para;
    }

    /**
     * 计算摆长标准差:u=math.sqrt(((L1-Lp)*(L1-Lp)+(L2-Lp)*(L2-Lp)+...+(L5-Lp)*(L5-Lp))/(5-1))
     *
     * @param para
     * @param DataSourse L[1~5]，Lp
     * @return 得到摆长标准差
     */
    public static PhyParas GetQLBJDm(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            String[][] result = XmlAnalysis.GetMatrixString(para._StandardValue);

            for (int i = 1; i < result[0].length; i++) {
                try {
                    result[1][i] = DataConversion.CutDataEffective(String.valueOf(Math.pow(Double.parseDouble(str[1][7 - 3 - i]), 2)
                            - Math.pow(Double.parseDouble(str[1][7 - i]), 2)), 4);
                } catch (Exception ex) {
                    result[1][i] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(result, result.length, result[0].length);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 计算摆长不确定度：u=math.sqrt(tp*ua*tp*ua+tk*ub*tk*ub)
     *
     * @param para
     * @param DataSourse L[1~5]，Lp
     * @return 得到摆长不确定度
     */
    public static PhyParas GetQUBJDpj(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);

            para._StandardValue = DataConversion.CutDataEffective(String.valueOf((Double.parseDouble(str[1][1])
                    + Double.parseDouble(str[1][2]) + Double.parseDouble(str[1][3])) / 3.0), 4);

        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 计算Ud ,20200420重写
     *
     * @param para
     * @param DataSourse 实际测量结果数据表格
     * @return 摆长平均值Tp
     */
    public static PhyParas GetQLBJUd(PhyParas para, List<PhyParas> DataSourse)//
    {
        try {
            String[][] strD = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            double sum = 0.0;
            for (int i = 1; i < strD[0].length; i++) {
                sum += Math.pow(2.0 * Double.parseDouble(strD[1][i]) * Double.parseDouble(strD[2][i]), 2);
            }
            para._StandardValue = DataConversion.ToUncertainty(String.valueOf(Math.sqrt(sum / 3.0)), 2);
        } catch (Exception ex) {
            para._StandardValue = "";
        }

        return para;
    }

    /**
     * 计算周期标准差:u=math.sqrt(((T1-Tp)*(T1-Tp)+(T2-Tp)*(T2-Tp)+...+(T5-Tp)*(T5-Tp))/(5-1))
     *
     * @param para
     * @param DataSourse T[1~5]，Tp
     * @return 得到周期标准差
     */
    public static PhyParas GetQuLvR(PhyParas para, List<PhyParas> DataSourse) {
        try {
            para._StandardValue = DataConversion.CutDataEffective(String.valueOf(Double.parseDouble(DataSourse.get(0)._StandardValue.toString()) * Math.pow(10, -6) / (4.0 * 15.0 * 589.3 * Math.pow(10, -9))), 4);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 计算周期标准差:u=math.sqrt(((T1-Tp)*(T1-Tp)+(T2-Tp)*(T2-Tp)+...+(T5-Tp)*(T5-Tp))/(5-1))
     *
     * @param para
     * @param DataSourse T[1~5]，Tp
     * @return 得到周期标准差
     */
    public static PhyParas GetQuLvU(PhyParas para, List<PhyParas> DataSourse) {
        try {
            para._StandardValue = DataConversion.ToUncertainty(String.valueOf(Double.parseDouble(DataSourse.get(0)._StandardValue.toString()) * Math.pow(10, -6) / (4.0 * 15.0 * 589.3 * Math.pow(10, -9))), 2);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 测量周期T而言,其B类不确定度Ub(T):Ub=(Uyq*Uyq+Uren*Uren)/50;
     *
     * @param para
     * @param DataSourse 仪器误差,人为误差
     * @return 得到B类不确定度
     */
    public static PhyParas GetXSZJ_AWList(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);

            for (int i = 1; i < str[0].length; i++) {
                try {
                    str[3][i] = String.format("%.3f", Math.abs(Double.parseDouble(str[2][i]) - Double.parseDouble(str[1][i])));
                } catch (Exception ex) {
                    str[3][i] = "";
                }
            }

            para._StandardValue = XmlAnalysis.SetMatrixString(str, str.length, str[0].length);

        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 计算周期的不确定度：u=math.sqrt(tp*ua*tp*ua+tk*ub*tk*ub)
     *
     * @param para
     * @param DataSourse 周期标准差,周期B类不确定
     * @return 得到单摆周期的不确定度
     */
    public static PhyParas GetXSZJ_Lpj(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            double sum = 0.0;
            int n = 0;
            for (int i = 1; i < str[0].length; i++) {
                try {
                    sum += Double.parseDouble(str[3][i]);
                    n++;
                } catch (Exception ignored) {
                }
            }
            if (n > 0) {
                para._StandardValue = DataConversion.CutDataEffective(String.valueOf(sum / (double) n), 4);
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 计算重力加速度G准确值
     *
     * @param para
     * @param DataSourse Lp,Tp
     * @return 得到重力加速度G准确值
     */
    public static PhyParas GetXSZJ_Ul(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            double Lp = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            double sum = 0.0;
            int n = 0;
            for (int i = 0; i < str[0].length; i++) {
                try {
                    sum += Math.pow(Double.parseDouble(str[3][i]) - Lp, 2);
                    n++;
                } catch (Exception ex) {
                }
            }
            if (n > 1) {
                double xita = Math.sqrt(sum / (double) (n - 1));
                double tp = 4.3;
                double kp = 1.96;
                xita = xita / Math.sqrt(n);
                double ub = 0.005 / 3.0;
                double ul = Math.sqrt(tp * xita * tp * xita + kp * ub * kp * ub);
                para._StandardValue = DataConversion.ToUncertainty(String.valueOf(ul), 2);
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * p=0.68时，g的不确定度
     *
     * @param para
     * @param DataSourse Lp,u(l),Tp,u(t),G
     * @return 得到g的不确定度
     */
    public static PhyParas GetXSZJ_D(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double L = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double lp = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            double bc = 589.3 * Math.pow(10, -6);

            if (lp > 0) {
                double d = 20.0 * L / lp * bc / 2.0;

                para._StandardValue = DataConversion.CutDataEffective(String.valueOf(d), 4);
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * p=0.68时，g的不确定度
     *
     * @param para
     * @param DataSourse Lp,u(l),Tp,u(t),G
     * @return 得到g的不确定度
     */
    public static PhyParas GetXSZJ_Ud(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double ul = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double lp = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            double dp = Double.parseDouble(DataSourse.get(2)._StandardValue.toString());

            if (lp > 0) {
                double ud = ul / lp * dp;
                para._StandardValue = DataConversion.ToUncertainty(String.valueOf(ud), 2);
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 计算数据源数据的3倍：X=3.0*Xp
     *
     * @param para
     * @param DataSourse Xp
     * @return 得到3*数据源数据值
     */
    public static PhyParas Triple(PhyParas para, List<PhyParas> DataSourse) {
        //para._StandardValue = "";
        try {
            double Xp = 0.0;
            try {
                Xp = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
                para._StandardValue = String.format("%.2f", (3.0 * Xp));
            } catch (Exception ex) {
                //无法计算出平均值，则波长直接返回空字符串
                para._StandardValue = "";
                return para;
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }

        return para;
    }

    /**
     * 计算最终结果：Y=x±u(x)
     *
     * @param para
     * @param DataSourse x,u(x)
     * @return 得到最终结果表达式
     */
    public static PhyParas FinalResult(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //准确结果部分的有效值数字位数
            if (!DataConversion.isNullOrEmpty(DataSourse.get(0)._StudentValue.toString()) && !DataConversion.isNullOrEmpty(DataSourse.get(1)._StudentValue.toString())) {
                int Nkeep = 4;
                para._StandardValue = DataConversion.GetExpression(DataConversion.CutDataEffective(DataSourse.get(0)._StudentValue.toString(), Nkeep), DataSourse.get(1)._StudentValue.toString(), 2);
            } else if (!DataConversion.isNullOrEmpty(DataSourse.get(0)._StandardValue.toString()) && !DataConversion.isNullOrEmpty(DataSourse.get(1)._StandardValue.toString())) {
                //准确结果部分的有效值数字位数
                int Nkeep = 4;
                para._StandardValue = DataConversion.GetExpression(DataConversion.CutDataEffective(DataSourse.get(0)._StandardValue.toString(), Nkeep), DataSourse.get(1)._StandardValue.toString(), 2);

            } else {
                para._StandardValue = "";
            }

        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 多个数据源数据连续相乘：X=X1*X2*...*Xn
     *
     * @param para
     * @param DataSourse X1,X2,...,Xn
     * @return 得到相乘结果
     */
    public static PhyParas Multiplication(PhyParas para, List<PhyParas> DataSourse) {
        //para._StandardValue = "";
        try {
            double mul = 1.0;
            double xi = 0.0;

            for (PhyParas phyParas : DataSourse) {
                try {
                    xi = Double.parseDouble(phyParas._StandardValue.toString());
                } catch (Exception ex) {
                    xi = 1.0;
                }
                mul = mul * xi;
            }

            para._StandardValue = String.format("%.0f", mul);

        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 计算两个数据源相除结果：Y=D0/D1
     *
     * @param para
     * @param DataSourse D0,D1
     * @return 两个数据源相除结果
     */
    public static PhyParas Division(PhyParas para, List<PhyParas> DataSourse) {
        try {
            if (Double.parseDouble(DataSourse.get(1)._StandardValue.toString()) != 0) {
                para._StandardValue = String.format("%.4f", Double.parseDouble(DataSourse.get(0)._StandardValue.toString()) / Double.parseDouble(DataSourse.get(1)._StandardValue.toString()));
            } else {
                para._StandardValue = "";
            }
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 数据源是否小于指定数值：数据源小于指定值返回true，数据源大于指定值返回false
     *
     * @param para
     * @param DataSourse 数据源
     * @return 得到比较结果：数据源小于指定值返回true，数据源大于指定值返回false
     */
    public static PhyParas CompareBool(PhyParas para, List<PhyParas> DataSourse) {
        para._StandardValue = true;
        try {
            double xi = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            if (xi < 0.01) {
                para._StudentValue = true;
            } else {
                para._StudentValue = false;
            }

        } catch (Exception ex) {
            para._StudentValue = false;
        }
        return para;
    }

    //endregion


}