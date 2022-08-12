import common.PhyParas;
import common.XmlAnalysis;

import java.util.List;

/**
 * @author Administrator
 */
public class Function {

    public static PhyParas T11(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentValue = "";
        try
        {

            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            //表格总行数
            int rownum = 3;
            //表格总列数
            int culmnum = 9;
            for (int j = 1; j < 9; j++)
            {
                try
                {
                    double a = Double.parseDouble(str1[1][j]);
                    double b = Double.parseDouble(str1[2][j]);
                    str1[1][j] = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 2);
                    str1[2][j] = DataConversion.SingleDoubleOfDecimal(String.valueOf(b), 2);
                }
                catch (Exception exception)
                {
                    str1[1][j] = "";
                    str1[2][j] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str1, rownum, culmnum);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;

    }

    public static PhyParas T21(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {

            // double C = Double.parseDouble(DataSourse.get(0)._StudentValue);
            double a = -0.20;
            para._StandardValue = String.valueOf(a);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    public static PhyParas T31(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double a = -0.35;
            para._StandardValue = String.valueOf(a);

        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    public static PhyParas T61(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {

            // double C = Double.parseDouble(DataSourse.get(0)._StudentValue);
            double a = -0.95;
            para._StandardValue = String.valueOf(a);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    public static PhyParas T71(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {

            // double C = Double.parseDouble(DataSourse.get(0)._StudentValue);
            double a = -1.18;
            para._StandardValue = String.valueOf(a);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    public static PhyParas T81(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {

            // double C = Double.parseDouble(DataSourse.get(0)._StudentValue);
            double a = 4.14;
            para._StandardValue = String.valueOf(a);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    public static PhyParas T91(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {

            // double C = Double.parseDouble(DataSourse.get(0)._StudentValue);
            double a = 6.63;
            para._StandardValue = String.valueOf(a);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    public static PhyParas T111(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentValue = "";
        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            //表格总行数
            int rownum = 3;
            //表格总列数
            int culmnum = 9;
            for (int j = 1; j < 9; j++)
            {
                try
                {
                    double a = Double.parseDouble(str1[1][j]);
                    double b = Double.parseDouble(str1[2][j]);
                    str1[1][j] = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 2);
                    str1[2][j] = DataConversion.SingleDoubleOfDecimal(String.valueOf(b), 2);
                }
                catch (Exception exception)
                {
                    str1[1][j] = "";
                    str1[2][j] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str1, rownum, culmnum);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;

    }
    public static PhyParas T121(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {

            // double C = Double.parseDouble(DataSourse.get(0)._StudentValue);
            double a = 1.62;
            para._StandardValue = String.valueOf(a);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    public static PhyParas T141(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {

            // double C = Double.parseDouble(DataSourse.get(0)._StudentValue);
            //double a = 0.81;
            para._StandardValue ="0.81";
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    public static PhyParas T161(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {

            // double C = Double.parseDouble(DataSourse.get(0)._StudentValue);
            double a = 0.41;
            para._StandardValue = String.valueOf(a);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    public static PhyParas T181(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {

            // double C = Double.parseDouble(DataSourse.get(0)._StudentValue);
            double a = 0.16;
            para._StandardValue = String.valueOf(a);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
}
