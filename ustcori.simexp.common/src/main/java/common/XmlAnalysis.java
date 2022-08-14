package common;

/**
 * @author Administrator
 */
public class XmlAnalysis {
    /**
     * 将_Type为BOOL的参数，返回为bool类型
     *
     * @param obj 参数
     * @return 返回bool类型
     */
    public static boolean GetBool(Object obj) {
        if (obj.toString().equalsIgnoreCase("True")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 将_Type为STRING的参数，返回为string类型
     *
     * @param obj 参数
     * @return 返回string类型
     */
    public static String GetString(Object obj) {
        return obj.toString();
    }

    /**
     * 将_Type为DOUBLE的参数，返回为double类型
     *
     * @param obj
     * @return
     */
    public static double GetDouble(Object obj) {
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception exception) {
            return ConstValue.ErrorNum;
        }
    }

    /**
     * 将_Type为INT的参数，返回为INT类型
     *
     * @param obj
     * @return
     */
    public static int GetInt(Object obj) {
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception exception) {
            return ConstValue.ErrorNum;
        }
    }

    /**
     * 例如将_Type为TList的参数，返回为double[行,列]类型
     *
     * @param obj
     * @return 去掉二维表的第一列标签和第一行标签后，返回double[行,列]类型
     */
    public static double[][] GetMatrix(Object obj) {
        try {
            String strValue = obj.toString();

            String[] RowValue = strValue.split(";");
            //行数
            int RowNum = RowValue.length - 1;
            //根据二维表视图中的第2列得到行数
            String[] FirstValue = RowValue[1].replace("(", "").replace(")", "").split(",");
            //列数
            int ColumnNum = FirstValue.length - 1;
            //先得到行数和列数，才可以定义二维数组大小
            double[][] mat = new double[ColumnNum][RowNum];
            //去掉第一列标签
            String[] RealRowValue = new String[RowValue.length - 1];
            for (int i = 0; i < RowValue.length - 1; i++) {
                //得到无第一列标签的字符串，RealRowValue[i]表示二维表视图中的第i+1列
                RealRowValue[i] = RowValue[i + 1];

                String[] ColumnValue = RealRowValue[i].replace("(", "").replace(")", "").split(",");
                for (int j = 0; j < ColumnValue.length - 1; j++) {
                    try {
                        mat[j][i] = Double.parseDouble(ColumnValue[j + 1]);
                    } catch (Exception exception) {
                        mat[j][i] = ConstValue.ErrorNum;
                    }

                }
            }

            return mat;
        } catch (Exception exception) {
            return new double[0][0];
        }
    }

    /**
     * 将数据表格返回为string[行,列]格式的二维数组
     *
     * @param obj
     * @return
     */
    public static String[][] GetMatrixString(Object obj) {
        try {
            //将字符串分割为不同的列
            String[] RowValue = obj.toString().split(";");
            //列数
            int RowNum = RowValue.length;
            //根据二维表视图中的第2列得到行数
            String[] FirstValue = RowValue[0].substring(1, RowValue[0].length() - 1).split(",");
            //行数
            int ColumnNum = FirstValue.length;
            //先得到行数和列数，才可以定义二维数组大小
            String[][] mat = new String[ColumnNum][RowNum];

            for (int i = 0; i < RowValue.length; i++) {
                RowValue[i] = RowValue[i].substring(1, RowValue[i].length() - 1);
                String[] ColumnValue = RowValue[i].split(",", -1);
                for (int j = 0; j < ColumnValue.length; j++) {
                    try {
                        if (ColumnValue[j] != null) {
                            mat[j][i] = ColumnValue[j];
                        } else {
                            mat[j][i] = "";
                        }
                    } catch (Exception ex) {
                        mat[j][i] = "";
                    }
                }
            }

            return mat;
        } catch (Exception ex) {
            return new String[0][0];
        }
    }

    /**
     * 将_Type为BOOL的参数，返回为bool类型
     *
     * @param obj
     * @return
     */
    public static Object SetBool(Object obj) {
        return obj.toString();
    }

    /**
     * 将_Type为STRING的参数，返回为string类型
     *
     * @param obj
     * @return
     */
    public static Object SetString(Object obj) {
        return obj.toString();
    }

    /**
     * 将_Type为DOUBLE的参数，返回为double类型
     *
     * @param obj
     * @return
     */
    public static Object SetDouble(Object obj) {
        return obj.toString();
    }

    /**
     * 例如将_Type为INT的参数，返回为INT类型
     *
     * @param obj
     * @return
     */
    public static Object SetInt(Object obj) {
        return obj.toString();
    }

    /**
     * 例如将_Type为TList的参数，返回为double[行,列]类型
     *
     * @param obj
     * @return 去掉二维表的第一列标签和第一行标签后，返回double[行,列]类型
     */
    public static Object SetMatrix(Object obj, Object ori) {
        try {
            String strValue = ori.toString();
            String result = "";

            String[] RowValue = strValue.split(";");

            int RowNum = RowValue.length - 1;

            String[] FirstValue = RowValue[1].replace("(", "").replace(")", "").split(",");
            int ColumnNum = FirstValue.length - 1;
            //先把二维表的第一列行标签写入result
            result = RowValue[0];
            //得到的标准值数组
            double[][] mat = (double[][]) obj;
            //去掉第一列标签
            String[] RealRowValue = new String[RowValue.length - 1];
            for (int i = 0; i < RowValue.length - 1; i++) {
                if (i < RowValue.length - 1) {
                    result = result + ";";
                }
                //得到无第一列标签的字符串，RealRowValue[i]表示二维表视图中的第i+1列
                RealRowValue[i] = RowValue[i + 1];

                String[] ColumnValue = RealRowValue[i].replace("(", "").replace(")", "").split(",");
                result = result + "(";

                result = result + ColumnValue[0] + ",";

                for (int j = 0; j < ColumnValue.length - 1; j++) {
                    if (Math.abs(mat[j][i] - ConstValue.ErrorNum) >= 0.000001) {
                        result = result + String.valueOf(mat[j][i]);
                    } else {
                        result = result + "";
                    }


                    if (j < ColumnValue.length - 2) {
                        result = result + ",";
                    }

                }

                result = result + ")";

            }

            return result;
        } catch (Exception exception) {
            return ori;
        }
    }

    /**
     * 将string[行,列]格式的二维数组重新写成XML中数据表格格式
     *
     * @param Column
     * @param Row
     * @return
     */
    public static String SetMatrixString(String[][] str, int Column, int Row) {
        String stemp = "";

        for (int i = 0; i < Row; i++) {
            stemp = stemp + "(";
            for (int j = 0; j < Column; j++) {
                stemp = stemp + str[j][i];
                if (j < Column - 1) {
                    stemp = stemp + ",";
                }
            }

            stemp = stemp + ")";
            if (i < Row - 1) {
                stemp = stemp + ";";
            }
        }

        return stemp;
    }
}
