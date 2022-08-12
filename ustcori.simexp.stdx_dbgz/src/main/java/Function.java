import common.PhyParas;
import common.XmlAnalysis;

import java.util.List;

/**
 * @author Administrator
 */
public class Function {

    // 调用计算方法


    /// <summary>
    /// 微安表参数记录表
    /// </summary>
    /// <param name="DataSourse">Xp</param>
    /// <returns> 微安表参数记录表</returns>
    public static PhyParas WAB_CS(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StandardValue = "";
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);

            if (str.length > 1)
            {
                str[1][1] = "100";
                str[2][1] = "10";
                str[3][1] = "1000";
                double a = 10.10;
                double b = 0.0;
                int n = 0;
                try
                {
                    n = DataConversion.GetDecimalPointDigits(str[4][1]);

                    if (n == 1 || n == 2)
                    {
                        str[4][1] = DataConversion.CutDataDecimal(String.valueOf(a), n);
                    }
                    else
                    {
                        str[4][1] = DataConversion.CutDataDecimal(String.valueOf(a), 2);
                    }
                }
                catch (Exception e)
                {
                    str[4][1] = "";
                }
                try
                {
                    b = Double.parseDouble(str[5][1]);

                    if (b >= 9.5 && b <= 11.6)
                    {
                        str[5][1] = DataConversion.CutDataDecimal(String.valueOf(b), 1);
                    }
                    else
                    {
                        str[5][1] = "";
                    }
                }
                catch (Exception e)
                {
                    str[5][1] = "";
                }

                para._StandardValue = XmlAnalysis.SetMatrixString(str, str[0].length, str[1].length);
            }
            else
            {
                para._StandardValue = "";
            }
        }
        catch (Exception e)
        {
            para._StandardValue = "";
        }

        return para;
    }


    /// <summary>
    /// 改装后电流表内阻
    /// </summary>
    /// <param name="DataSourse">Xp</param>
    /// <returns> 改装后电流表内阻</returns>
    public static PhyParas DLBNZ_R(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            double b = 0.0;
            double c = 0.0;

            b = Double.parseDouble(str[5][1]);
            c = b - 0.1;
            para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(c), 1);
        }
        catch (Exception e)
        {
            para._StandardValue = "";
        }
        return para;
    }

    /// <summary>
    /// 电流表校准数据表
    /// </summary>
    /// <param name="DataSourse">Xp</param>
    /// <returns> 电流表校准数据表</returns>
    public static PhyParas DLB_JZ(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            //表格总行数
            int rownum = 5;
            //表格总列数
            int culmnum = 7;
            for (int j = 1; j < 7; j++)
            {
                try
                {
                    double a = Double.parseDouble(str1[1][j]);
                    str1[1][j] = DataConversion.CutDataDecimal(String.valueOf(a), 2);
                }
                catch (Exception e)
                {
                    str1[1][j] = "";
                }
                try
                {
                    double b = Double.parseDouble(str1[2][j]);
                    str1[2][j] = DataConversion.CutDataDecimal(String.valueOf(b), 2);
                }
                catch (Exception e)
                {
                    str1[2][j] = "";
                }
                try
                {
                    double a = Double.parseDouble(str1[1][j]);
                    double b = Double.parseDouble(str1[2][j]);
                    double c = (a + b) / 2.0;
                }
                catch (Exception e)
                {
                    str1[3][j] = "";
                }
                try
                {
                    double b = Double.parseDouble(str1[4][j]);
                    if (b <= 0.25 && b >= -0.25)
                    {
                        int n = DataConversion.GetDecimalPointDigits(str1[4][j]);
                        if (n == 2 || n == 3)
                        {
                            str1[4][j] = DataConversion.CutDataDecimal(String.valueOf(b), n);
                        }
                        else
                        {
                            str1[4][j] = DataConversion.CutDataDecimal(String.valueOf(b), 3);
                        }
                    }
                    else
                    {
                        str1[4][j] = "";
                    }
                }
                catch (Exception e)
                {
                    str1[4][j] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str1, rownum, culmnum);
        }
        catch (Exception e)
        {
            para._StandardValue = "";
        }

        return para;
    }

    /// <summary>
    /// 微安表头参数表
    /// </summary>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas WABT_CS(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StandardValue = "";
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);

            if (str.length > 1)
            {
                str[1][1] = "100";
                str[2][1] = "1.5";
                str[3][1] = "1000";
                str[4][1] = "14000";
                double b = 0.0;
                try
                {
                    b = Double.parseDouble(str[5][1]);

                    if (b >= 13000 && b <= 14500)
                    {
                        str[5][1] = DataConversion.CutDataEffective(String.valueOf(b), 5);
                    }
                    else
                    {
                        str[5][1] = "";
                    }
                }
                catch (Exception e)
                {
                    str[5][1] = "";
                }

                para._StandardValue = XmlAnalysis.SetMatrixString(str, str[0].length, str[1].length);
            }
            else
            {
                para._StandardValue = "";
            }
        }
        catch (Exception e)
        {
            para._StandardValue = "";
        }

        return para;
    }

    /// <summary>
    /// 改装后电压表得内阻r(Ω)
    /// </summary>
    /// <param name="DataSourse">Xp</param>
    /// <returns>改装后电压表得内阻r(Ω)</returns>
    public static PhyParas DYB_NZ(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StandardValue = "";
        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            double a = Double.parseDouble(str1[5][1]);
            double b = a + 1000;
            int n = DataConversion.GetEffectiveNumericalDigits(para._StudentValue.toString());
            if (n == 5 || n == 6)
            {
                para._StandardValue = DataConversion.CutDataEffective(String.valueOf(b), n);
            }
            else
            {
                para._StandardValue = DataConversion.CutDataEffective(String.valueOf(b), 5);
            }


        }
        catch (Exception e)
        {
            para._StandardValue = "";
        }

        return para;
    }

    /// <summary>
    /// 表(4)电压表校准数据
    /// </summary>
    /// <param name="DataSourse"></param>
    /// <returns>表(4)电压表校准数据</returns>
    public static PhyParas DYB_JZ(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StandardValue = "";
        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            //表格总行数
            int rownum = 5;
            //表格总列数
            int culmnum = 7;
            for (int j = 1; j < 7; j++)
            {
                try
                {
                    double a = Double.parseDouble(str1[1][j]);
                    str1[1][j] = DataConversion.CutDataDecimal(String.valueOf(a), 2);
                }
                catch (Exception e)
                {
                    str1[1][j] = "";
                }
                try
                {
                    double b = Double.parseDouble(str1[2][j]);
                    str1[2][j] = DataConversion.CutDataDecimal(String.valueOf(b), 2);
                }
                catch (Exception e)
                {
                    str1[2][j] = "";
                }
                try
                {
                    double a = Double.parseDouble(str1[1][j]);
                    double b = Double.parseDouble(str1[2][j]);
                    double c = (a + b) / 2.0;
                }
                catch (Exception e)
                {
                    str1[3][j] = "";
                }
                try
                {
                    double b = Double.parseDouble(str1[4][j]);
                    if (b <= 0.037 && b >= -0.037)
                    {
                        int n = DataConversion.GetDecimalPointDigits(str1[4][j]);
                        if (n == 3 || n == 4)
                        {
                            str1[4][j] = DataConversion.CutDataDecimal(String.valueOf(b), n);
                        }
                        else
                        {
                            str1[4][j] = DataConversion.CutDataDecimal(String.valueOf(b), 4);
                        }
                    }
                    else
                    {
                        str1[4][j] = "";
                    }
                }
                catch (Exception e)
                {
                    str1[4][j] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str1, rownum, culmnum);
        }
        catch (Exception e)
        {
            para._StandardValue = "";
        }

        return para;
    }
    /// <summary>
    /// 测量电阻伏安特性
    /// </summary>
    /// <param name="DataSourse">X[1~12]</param>
    /// <returns>表(5)测量结果</returns>
    public static PhyParas DZ_FATX(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            //表格总行数
            int rownum = 3;
            //表格总列数
            int culmnum = 9;
            for (int j = 1; j < 9; j++)
            {
                try
                {
                    double b = Double.parseDouble(str1[1][j]);
                    if (b <= 1.50 && b >= 0.00)
                    {
                        str1[1][j] = DataConversion.CutDataDecimal(String.valueOf(b), 2);
                    }
                    else
                    {
                        str1[1][j] = "";
                    }
                }
                catch (Exception e)
                {
                    str1[1][j] = "";
                }
                try
                {
                    double b = Double.parseDouble(str1[2][j]);
                    if (b <= 10.0 && b >= 0.0)
                    {
                        str1[2][j] = DataConversion.CutDataDecimal(String.valueOf(b), 1);
                    }
                    else
                    {
                        str1[2][j] = "";
                    }
                }
                catch (Exception e)
                {
                    str1[2][j] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str1, rownum, culmnum);
        }
        catch (Exception e)
        {
            para._StandardValue = "";
        }

        return para;
    }

    /// <summary>
    /// 毫安表，伏特表的级别
    /// </summary>
    /// <param name="DataSourse"></param>
    /// <returns>毫安表、伏特表级别表格</returns>
    public static PhyParas DBJB_BG(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StandardValue = "";
        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            //表格总行数
            int rownum = 2;
            //表格总列数
            int culmnum = 3;
            for (int j = 1; j < 3; j++)
            {
                try
                {
                    double b = Double.parseDouble(str1[1][j]);
                    if (b == 0.5 || b == 1.0 || b == 1.5 || b == 2.5)
                    {
                        str1[1][j] = DataConversion.CutDataDecimal(String.valueOf(b), 1);
                    }
                    else
                    {
                        str1[1][j] = "";
                    }
                }
                catch (Exception e)
                {
                    str1[1][j] = "";
                }

            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str1, rownum, culmnum);
        }
        catch (Exception e)
        {
            para._StandardValue = "";
        }

        return para;
    }

    /// <summary>
    /// 表(7)电阻的伏安特性
    /// </summary>
    /// <param name="DataSourse">X[1~12]</param>
    /// <returns>表(5)修正</returns>
    public static PhyParas DZXZ_FATX(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            //表格总行数
            int rownum = 3;
            //表格总列数
            int culmnum = 9;
            for (int j = 1; j < 9; j++)
            {
                try
                {
                    double a = Double.parseDouble(str1[1][j]);
                    if (a <= 1.50 && a >= 0.00)
                    {
                        str1[1][j] = DataConversion.CutDataDecimal(String.valueOf(a), 2);
                    }
                    else
                    {
                        str1[1][j] = "";
                    }
                }
                catch (Exception e)
                {
                    str1[1][j] = "";
                }
                try
                {
                    double b = Double.parseDouble(str1[2][j]);
                    if (b <= 10.0 && b >= 0.0)
                    {
                        str1[2][j] = DataConversion.CutDataDecimal(String.valueOf(b), 1);
                    }
                    else
                    {
                        str1[2][j] = "";
                    }
                }
                catch (Exception e)
                {
                    str1[2][j] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str1, rownum, culmnum);
        }
        catch (Exception e)
        {
            para._StandardValue = "";
        }

        return para;
    }
    public static PhyParas _R(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StandardValue = "";
        try
        {
            double w = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            if (w >= 1.2 && w <= 1.7)
            {
                para._StandardValue = DataConversion.CutDataEffective(String.valueOf(w), 2);
            }
            else
            {
                para._StandardValue = "";
            }
        }
        catch (Exception e)
        {
            para._StandardValue = "";
        }

        return para;
    }
}
