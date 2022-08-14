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
    public static PhyParas JieDian1(PhyParas para, List<PhyParas> DataSourse) {
        try {
            para._StandardValue = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
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
    public static PhyParas JieDian2(PhyParas para, List<PhyParas> DataSourse) {
        try {
            para._StandardValue = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
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
    public static PhyParas JieDian3(PhyParas para, List<PhyParas> DataSourse) {
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
    public static PhyParas JieDian4(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double sturesult = 0;
            try {
                sturesult = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            } catch (Exception ex) {

            }
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StudentValue.toString());
            for (int i = 1; i < 21; i++) {
                try {
                    str[3][i] = String.format("%.3f", Double.parseDouble(str[2][i]) / sturesult);
                } catch (Exception ex) {
                    str[3][i] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str, 4, 21);
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
    public static PhyParas JieDian6(PhyParas para, List<PhyParas> DataSourse) {
        try {
            para._StandardValue = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 直接返回值
     *
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas JieDian7(PhyParas para, List<PhyParas> DataSourse) {
        try {
            para._StandardValue = 20;
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 直接返回学生答案
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas JieDian8(PhyParas para, List<PhyParas> DataSourse) {
        try {
            para._StandardValue = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 直接返回学生答案
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas JieDian9(PhyParas para, List<PhyParas> DataSourse) {
        try {
            para._StandardValue = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 直接返回学生答案
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas JieDian10(PhyParas para, List<PhyParas> DataSourse) {
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
    public static PhyParas JieDian11(PhyParas para, List<PhyParas> DataSourse) {
        try {
            double sturesult = 0;
            try {
                sturesult = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            } catch (Exception ex) {

            }
            //学生值
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StudentValue);
            for (int i = 1; i < 21; i++) {
                try {
                    str[3][i] = String.format("%.3f", Double.parseDouble(str[2][i]) / sturesult);
                } catch (Exception ex) {
                    str[3][i] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str, 4, 21);
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 直接返回值
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas JieDian13(PhyParas para, List<PhyParas> DataSourse) {
        try {
            para._StandardValue = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }

    /**
     * 直接返回值
     * @param para
     * @param DataSourse
     * @return
     */
    public static PhyParas JieDian14(PhyParas para, List<PhyParas> DataSourse) {
        try {
            para._StandardValue = 20;
        } catch (Exception ex) {
            para._StandardValue = "";
        }
        return para;
    }
}