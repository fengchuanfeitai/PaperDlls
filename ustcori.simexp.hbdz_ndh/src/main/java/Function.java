import common.PhyParas;
import common.XmlAnalysis;

import java.util.List;

/**
 * 函数
 *
 * @author cp
 */
public class Function {
    // region 实验数据记录及相关数据处理

    /**
     * 实验数据记录表格，各行标准值返回
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas DataListOne(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            String[][] result = XmlAnalysis.GetMatrixString(para._StandardValue);
            /* 需要计算标准答案的行：
             * 第3行的数据源：第1行和第2行
             * 第6行的数据源：第4行和第5行
             * 第7行的数据源：第3行和第6行
             * 第8行的数据源：第7行
             * 第9行的数据源：第0行和第8行
             * 第10行的数据源：第0行
             * 第11行的数据源：第8行
             */

            //表格的总列数
            int numcolumn = 11;
            // region 处理过程
            for (int i = 1; i < numcolumn; i++) {
                result[1][i] = str[1][i];
                result[2][i] = str[2][i];

                result[4][i] = str[4][i];
                result[5][i] = str[5][i];

                // region  x1的平均值/mm  第3行

                //0：x11转化失败，1：x12转化失败，2：x11、x12转化成功
                int isOkTry3 = 2;
                try {
                    isOkTry3 = 0;
                    double x11 = Double.parseDouble(str[1][i]);
                    isOkTry3 = 1;
                    double x12 = Double.parseDouble(str[2][i]);
                    isOkTry3 = 2;
                    result[3][i] = DataConversion.CutDataDecimal(String.valueOf((x11 + x12) / 2.0), 3);
                } catch (Exception ex) {
                    if (isOkTry3 == 0) {
                        try {
                            double x12 = Double.parseDouble(str[2][i]);
                            result[3][i] = DataConversion.CutDataDecimal(String.valueOf(x12), 3);
                        } catch (Exception exception) {
                            result[3][i] = "";
                        }
                    } else if (isOkTry3 == 1) {
                        result[3][i] = DataConversion.CutDataDecimal(String.valueOf(str[1][i]), 3);
                    } else {
                        result[3][i] = "";
                    }
                }
                // endregion

                // region x2的平均值/mm  第6行
                //0：x21转化失败，1：x22转化失败，2：x22、x21转化成功
                int isOkTry6 = 2;
                try {
                    isOkTry6 = 0;
                    double x21 = Double.parseDouble(str[4][i]);
                    isOkTry6 = 1;
                    double x22 = Double.parseDouble(str[5][i]);
                    isOkTry6 = 2;
                    result[6][i] = DataConversion.CutDataDecimal(String.valueOf((x21 + x22) / 2.0), 3);
                } catch (Exception ex) {
                    if (isOkTry6 == 0) {
                        try {
                            double x21 = Double.parseDouble(str[5][i]);
                            result[6][i] = DataConversion.CutDataDecimal(String.valueOf(x21), 3);
                        } catch (Exception exception) {
                            result[6][i] = "";
                        }
                    } else if (isOkTry6 == 1) {
                        result[6][i] = DataConversion.CutDataDecimal(String.valueOf(str[4][i]), 3);
                    } else {
                        result[6][i] = "";
                    }
                }
                // endregion
            }

            for (int j = 1; j < numcolumn; j++) {
                // region 各环直径Dk=|x2-x1|/mm  第7行
                try {
                    double dk = Double.parseDouble(result[3][j]) - Double.parseDouble(result[6][j]);
                    result[7][j] = DataConversion.CutDataDecimal(String.valueOf(Math.abs(dk)), 3);
                } catch (Exception ex) {
                    result[7][j] = "";
                }
                // endregion

                // region 各环直径的平方：Dk^2 /mm^2  第8行
                try {
                    double dk = Double.parseDouble(result[7][j]);
                    result[8][j] = DataConversion.CutDataDecimal(String.valueOf(dk * dk), 3);
                } catch (Exception ex) {
                    result[8][j] = "";
                }
                // endregion

                // region  k*(Dk^2) /mm^2  第9行
                try {
                    double dk2 = Double.parseDouble(result[8][j]);
                    double k = 16 - j;
                    result[9][j] = DataConversion.CutDataDecimal(String.valueOf(dk2 * k), 3);
                } catch (Exception ex) {
                    result[9][j] = "";
                }

                // endregion

                // region  环级k的平方：k^2  第10行
                double k22 = 16 - j;
                result[10][j] = DataConversion.CutDataDecimal(String.valueOf(k22 * k22), 0);
                // endregion

                // region  各环直径的平方值的平方：(Dk^2)^2 /mm^4  第11行
                try {
                    double dk4 = Double.parseDouble(result[8][j]);
                    result[11][j] = DataConversion.CutDataDecimal(String.valueOf(dk4 * dk4), 3);
                } catch (Exception ex) {
                    result[11][j] = "";
                }
                // endregion
            }
            // endregion

            para._StandardValue = XmlAnalysis.SetMatrixString(result, result.length, result[0].length);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * (2)根据表格中的数据,计算出环级k的平均值
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas Average_k(PhyParas para, List<PhyParas> DataSourse) {
        try {
            para._StandardValue = "10.5";
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * (3)根据表格中的数据，计算出各环直径的平方Dk^2的平均值
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas Average_DK2(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            //表格的总列数
            int numcolumn = 11;
            //数据源：表格第8行
            if (str.length > 1) {
                double xi = 0.0;
                double sum = 0.0;
                int num = numcolumn - 1;
                for (int i = 1; i < numcolumn; i++) {
                    try {
                        xi = Double.parseDouble(str[8][i]);
                    } catch (Exception ex) {
                        xi = 0.0;
                        num = num - 1;
                    }
                    sum = sum + xi;
                }

                if (num > 0) {
                    para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(sum / num), 3);
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
     * (4)根据表格中的数据，计算出k*Dk^2的平均值
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas Average_kDK2(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            int numcolumn = 11;//表格的总列数
            //数据源：表格第9行
            if (str.length > 1) {
                double xi = 0.0;
                double sum = 0.0;
                int num = numcolumn - 1;
                for (int i = 1; i < numcolumn; i++) {
                    try {
                        xi = Double.parseDouble(str[9][i]);
                    } catch (Exception ex) {
                        xi = 0.0;
                        num = num - 1;
                    }
                    sum = sum + xi;
                }

                if (num > 0) {
                    para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(sum / num), 3);
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
     * (5)根据表格中的数据，计算出k^2的平均值
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas Average_k2(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            //表格的总列数
            int numcolumn = 11;
            //数据源：表格第0行
            if (str.length > 1) {
                double xi = 0.0;
                double sum = 0.0;
                int num = numcolumn - 1;
                for (int i = 1; i < numcolumn; i++) {
                    try {
                        xi = Double.parseDouble(str[10][i]);
                    } catch (Exception ex) {
                        xi = 0.0;
                        num = num - 1;
                    }
                    sum = sum + xi;
                }

                if (num > 0) {
                    para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(sum / num), 1);
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
     * (6)根据表格中的数据，计算出(Dk^2)^2的平均值
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas Average_Dk22(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            //表格的总列数
            int numcolumn = 11;
            //数据源：表格第8行
            if (str.length > 1) {
                double xi = 0.0;
                double sum = 0.0;
                int num = numcolumn - 1;
                for (int i = 1; i < numcolumn; i++) {
                    try {
                        xi = Double.parseDouble(str[11][i]);
                    } catch (Exception ex) {
                        xi = 0.0;
                        num = num - 1;
                    }
                    sum = sum + xi;
                }

                if (num > 0) {
                    para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(sum / num), 3);
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

    // endregion

    /**
     * 根据最小二乘法拟合原理，计算出各环直径的平方Dk^2与环级k的线性比例系数a
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas Ratio_a(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(4)._StandardValue);
            //表格的总列数
            int numcolumn = 11;
            int num = 10;
            /// 确保学生把所有的数据，全都填写完成，否则不进行拟合k的值
            for (int i = 1; i < numcolumn; i++) {
                try {
                    double xi1 = Double.parseDouble(str[3][i]);
                    double xi2 = Double.parseDouble(str[6][i]);
                } catch (Exception ex) {
                    num--;
                }
            }

            /// 学生把所有的数据，全都填写完成
            if (num == 10) {
                double ave_k = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
                double ave_dk2 = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
                double ave_kdk2 = Double.parseDouble(DataSourse.get(2)._StandardValue.toString());
                double ave_k2 = Double.parseDouble(DataSourse.get(3)._StandardValue.toString());
                if (ave_k * ave_k - ave_k2 != 0) {
                    double result = (ave_k * ave_dk2 - ave_kdk2) / (ave_k * ave_k - ave_k2);
                    para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(result), 3);
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
     * 根据最小二乘法拟合原理，计算出截距b
     * 加评分前置条件：0.00
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas Intercept_b(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(3)._StandardValue);
            //表格的总列数
            int numcolumn = 11;
            int num = 10;
            /// 确保学生把所有的数据，全都填写完成，否则不进行拟合k的值
            for (int i = 1; i < numcolumn; i++) {
                try {
                    double xi1 = Double.parseDouble(str[3][i]);
                    double xi2 = Double.parseDouble(str[6][i]);
                } catch (Exception ex) {
                    num--;
                }
            }

            /// 学生把所有的数据，全都填写完成
            if (num == 10) {
                double ave_dk2 = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
                double a = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
                double ave_k = Double.parseDouble(DataSourse.get(2)._StandardValue.toString());

                double result = ave_dk2 - a * ave_k;
                para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(result), 3) + ";0.000";
            } else {
                para._StandardValue = "" + ";0.000";
            }
        } catch (Exception ex) {
            para._StandardValue = "" + ";0.000";
        }
        return para;
    }

    /**
     * 将a代入相关计算公式中，求出透镜的曲率半径R
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas CurvatureRadius(PhyParas para, List<PhyParas> DataSourse) {
        try {
            //单位：mm
            double a = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            //单位：mm
            double wave_Na = 589.3 * Math.pow(10, -6);
            double result = a / (4 * wave_Na);
            para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(result), 3);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 计算相关系数r
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas CorrelationCoefficient(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(5)._StandardValue);
            //表格的总列数
            int numcolumn = 11;
            int num = 10;
            /// 确保学生把所有的数据，全都填写完成，否则不进行拟合k的值
            for (int i = 1; i < numcolumn; i++) {
                try {
                    double xi1 = Double.parseDouble(str[3][i]);
                    double xi2 = Double.parseDouble(str[6][i]);
                } catch (Exception ex) {
                    num--;
                }
            }

            /// 学生把所有的数据，全都填写完成
            if (num == 10) {
                double ave_kdk2 = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
                double ave_k = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
                double ave_dk2 = Double.parseDouble(DataSourse.get(2)._StandardValue.toString());
                double ave_k2 = Double.parseDouble(DataSourse.get(3)._StandardValue.toString());
                double ave_dk22 = Double.parseDouble(DataSourse.get(4)._StandardValue.toString());

                double difference_k = ave_k2 - ave_k * ave_k;
                double difference_dk = ave_dk22 - ave_dk2 * ave_dk2;
                double product = difference_k * difference_dk;
                //1.根号下，不可以小于0
                //2.分母，不可以等于0
                if (product > 0) {
                    double result = (ave_kdk2 - ave_k * ave_dk2) / Math.sqrt(product);
                    para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(result), 3);
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

    // region 求不确定度u(R)和相对不确定度Ur(R)

    /**
     * 根据表格中的各环直径的平方值Dk^2及相关实验数据，计算出各环直径的平方的标准差值S(dk^2)
     * 加评分前置条件：0.00
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas StandardDeviation_SDk2(PhyParas para, List<PhyParas> DataSourse) {
        try {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            double a = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            double b = Double.parseDouble(DataSourse.get(2)._StandardValue.toString().split(";")[0]);
            //表格的总列数
            int numcolumn = 11;
            if (str.length > 1) {
                double xi = 0.0;
                double sum = 0.0;
                double num = numcolumn - 1;
                for (int i = 1; i < numcolumn; i++) {
                    try {
                        double k = 16 - i;
                        double dk2 = Double.parseDouble(str[8][i]);
                        xi = dk2 - a * k - b;
                    } catch (Exception ex) {
                        xi = 0.0;
                        num = num - 1;
                    }
                    sum = sum + xi * xi;
                }

                if (num > 1) {
                    double result = Math.sqrt(sum / (num - 1));
                    para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(result), 3) + ";0.000";
                } else if (num == 1) {
                    para._StandardValue = "0.000" + ";0.000";
                } else {
                    para._StandardValue = "" + ";0.000";
                }
            } else {
                para._StandardValue = "" + ";0.000";
            }
        } catch (Exception ex) {
            para._StandardValue = "" + ";0.000";
        }
        return para;
    }

    /**
     * (2)忽略B类不确定度。斜率a的不确定度等于a的标准偏差为
     * 加评分前置条件：0.00
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas Uncertainty_ua(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double sdk2 = Double.parseDouble(DataSourse.get(0)._StandardValue.toString().split(";")[0]);
            double ave_k2 = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            double ave_k = Double.parseDouble(DataSourse.get(2)._StandardValue.toString());
            double difference_k = ave_k2 - ave_k * ave_k;
            if (difference_k > 0) {
                double result = sdk2 / Math.sqrt(difference_k);
                para._StandardValue = DataConversion.ToDecimal(String.valueOf(result), 3) + ";0.000";
            } else {
                para._StandardValue = "" + ";0.000";
            }
        } catch (Exception ex) {
            para._StandardValue = "" + ";0.000";
        }
        return para;
    }

    /**
     * (3)因为λ为常量，所以透镜的曲率半径的不确定度u(R)
     * 加评分前置条件：0.00
     *
     * @param para       参数
     * @param DataSourse 参数
     * @return
     */
    public static PhyParas Uncertainty_uR(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double ua = Double.parseDouble(DataSourse.get(0)._StandardValue.toString().split(";")[0]);
            //单位：mm
            double wave_Na = 589.3 * Math.pow(10, -6);
            double result = ua / 4.0 / wave_Na;
            para._StandardValue = DataConversion.ToDecimal(String.valueOf(result), 3) + ";0.000";
        } catch (Exception ex) {
            para._StandardValue = "" + ";0.000";
        }
        return para;
    }

    /**
     * (4)计算出透镜的曲率半径的相对不确定度Ur(R)
     * 加评分前置条件：0.00
     *
     * @param para
     * @param DataSourse 数据
     * @return 结果
     */
    public static PhyParas Uncertainty_UrR(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double ur = Double.parseDouble(DataSourse.get(0)._StandardValue.toString().split(";")[0]);
            double average_v = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            if (average_v != 0) {
                double result = ur / average_v * 100.0;
                para._StandardValue = DataConversion.ToDecimal(String.valueOf(result), 3) + ";0.000";
            } else {
                para._StandardValue = "" + ";0.000";
            }
        } catch (Exception ex) {
            para._StandardValue = "" + ";0.000";
        }
        return para;
    }
    // endregion

    // region  最终表达式

    /**
     * 标准值 保留2位小数  ；不确定度 保留1位或者2位小数
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas Expression_R(PhyParas para, List<PhyParas> DataSourse) {
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
                String staValue1 = DataSourse.get(1)._StandardValue.toString();
                String[] strList = staValue1.split(";");

                if (strList.length == 2 && !DataConversion.isNullOrEmpty(strList[0])) {
                    String s1 = DataConversion.GetExpressionBKD(DataSourse.get(0)._StandardValue.toString(), strList[0], 1);
                    String s2 = DataConversion.GetExpressionBKD(DataSourse.get(0)._StandardValue.toString(), strList[0], 2);
                    if (s1.equals(s2)) {
                        para._StandardValue = s1;
                    } else {
                        para._StandardValue = s1 + "或" + s2;
                    }
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

    // endregion
}