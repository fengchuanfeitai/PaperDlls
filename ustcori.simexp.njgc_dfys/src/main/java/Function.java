import common.PhyParas;
import common.XmlAnalysis;

import java.util.List;

/**
 * @author Administrator
 */
public class Function {

    //L1
    public static PhyParas D1list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double a = Double.parseDouble(para._StudentValue.toString());
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 2);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //D
    public static PhyParas D2list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double a = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 3);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //L2
    public static PhyParas D3list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double a = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 2);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //f
    public static PhyParas D4list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double b = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());//L1
            double d = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());//L2
            double e = Math.abs(d - b) + 6;
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(e), 2);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //表1
    public static PhyParas D5list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentValue = "";
        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            //表格总行数
            int rownum = 2;
            //表格总列数
            int culmnum = 82;
            for (int j = 1; j < 82; j++)
            {
                try
                {
                    double c = Double.parseDouble(str1[1][ j]);
                    if (c <= 2)
                    {
                        str1[1][ j] = DataConversion.SingleDoubleOfDecimal(String.valueOf(c), 3);
                    }
                    else
                    {
                        str1[1][ j] = DataConversion.SingleDoubleOfDecimal(String.valueOf(2.000), 3);
                    }

                }
                catch (Exception exception)
                {
                    str1[1][ j] = DataConversion.SingleDoubleOfDecimal(String.valueOf(2.000), 3);
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
    //x1
    public static PhyParas D7list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double a = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            int n = DataConversion.GetDecimalPointDigits(para._StudentValue.toString());
            if (!String.valueOf(n).equals(""))
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), n);
            }
            else
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 3);
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //y1
    public static PhyParas D8list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double a = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            int n = DataConversion.GetDecimalPointDigits(para._StudentValue.toString());
            if (!String.valueOf(n).equals(""))
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), n);
            }
            else
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 3);
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //x2
    public static PhyParas D9list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double a = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            int n = DataConversion.GetDecimalPointDigits(para._StudentValue.toString());
            if (!String.valueOf(n).equals(""))
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), n);
            }
            else
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 3);
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //y2
    public static PhyParas D10list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double a = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            int n = DataConversion.GetDecimalPointDigits(para._StudentValue.toString());
            if (!String.valueOf(n).equals(""))
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), n);
            }
            else
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 3);
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //dx1
    public static PhyParas D11list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double a = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());//x1
            double b = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());//y1
            double c = Double.parseDouble(DataSourse.get(2)._StudentValue.toString());//x2
            double d = Double.parseDouble(DataSourse.get(3)._StudentValue.toString());//y2
            double e = Math.sqrt(((c - a) * (c - a)) + ((d - b) * (d - b)));
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(e), 3);

        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //x3
    public static PhyParas D12list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double a = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            int n = DataConversion.GetDecimalPointDigits(para._StudentValue.toString());
            if (!String.valueOf(n).equals(""))
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), n);
            }
            else
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 3);
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //y3
    public static PhyParas D13list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double a = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            int n = DataConversion.GetDecimalPointDigits(para._StudentValue.toString());
            if (!String.valueOf(n).equals(""))
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), n);
            }
            else
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 3);
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //x4
    public static PhyParas D14list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double a = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            int n = DataConversion.GetDecimalPointDigits(para._StudentValue.toString());
            if (!String.valueOf(n).equals(""))
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), n);
            }
            else
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 3);
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //y4
    public static PhyParas D15list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double a = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            int n = DataConversion.GetDecimalPointDigits(para._StudentValue.toString());
            if (!String.valueOf(n).equals(""))
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), n);
            }
            else
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 3);
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //dx2
    public static PhyParas D16list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double a = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());//x3
            double b = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());//y3
            double c = Double.parseDouble(DataSourse.get(2)._StudentValue.toString());//x4
            double d = Double.parseDouble(DataSourse.get(3)._StudentValue.toString());//y4
            double e = Math.sqrt(((c - a) * (c - a)) + ((d - b) * (d - b)));
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(e), 3);

        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //x5
    public static PhyParas D17list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double a = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            int n = DataConversion.GetDecimalPointDigits(para._StudentValue.toString());
            if (!String.valueOf(n).equals(""))
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), n);
            }
            else
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 3);
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //y5
    public static PhyParas D18list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double a = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            int n = DataConversion.GetDecimalPointDigits(para._StudentValue.toString());
            if (!String.valueOf(n).equals(""))
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), n);
            }
            else
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 3);
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //x6
    public static PhyParas D19list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double a = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            int n = DataConversion.GetDecimalPointDigits(para._StudentValue.toString());
            if (!String.valueOf(n).equals(""))
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), n);
            }
            else
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 3);
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //y6
    public static PhyParas D20list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double a = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            int n = DataConversion.GetDecimalPointDigits(para._StudentValue.toString());
            if (!String.valueOf(n).equals(""))
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), n);
            }
            else
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 3);
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //dx3
    public static PhyParas D21list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double a = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());//x5
            double b = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());//y5
            double c = Double.parseDouble(DataSourse.get(2)._StudentValue.toString());//x6
            double d = Double.parseDouble(DataSourse.get(3)._StudentValue.toString());//y6
            double e = Math.sqrt(((c - a) * (c - a)) + ((d - b) * (d - b)));
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(e), 3);

        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //表2
    public static PhyParas D22list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentValue = "";
        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(4)._StudentValue);
            String[][] result = XmlAnalysis.GetMatrixString(DataSourse.get(4)._StandardValue);
            // double a = Double.parseDouble(DataSourse.get(0)._StudentValue);//dx1
            // string  b = String.valueOf(DataSourse.get(1)._StudentValue);//dx2
            // double c = Double.parseDouble(DataSourse.get(2)._StudentValue);//dx3
            //double f=  Double.parseDouble(DataSourse.get(3)._StudentValue);//f
            //表格总行数
            int rownum = 3;
            //表格总列数
            int culmnum = 4;

            try
            {
                result[1][1] = String.valueOf(DataSourse.get(0)._StudentValue);
            }
            catch (Exception exception)
            {
                result[1][1] = "";
            }
            try
            {

                result[1][2] = String.valueOf(DataSourse.get(1)._StudentValue);
            }
            catch (Exception exception)
            {
                result[1][2] = "";
            }
            try
            {
                result[1][3] = String.valueOf(DataSourse.get(2)._StudentValue);
            }
            catch (Exception exception)
            {
                result[1][3] = "";
            }
            try
            {
                double x = Double.parseDouble(str1[1][1]);
                double f = Double.parseDouble(DataSourse.get(3)._StudentValue.toString());//f
                double q = (2 * f * 10 * 1 * 0.6328 * 0.001) / x;
                try
                {
                    double u = Double.parseDouble(str1[2][1]);
                    int n = DataConversion.GetEffectiveNumericalDigits(String.valueOf(u));
                    if (n == 3 || n == 4)
                    {
                        result[2][1] = DataConversion.SingleDoubleOfEffective(String.valueOf(q), n);
                    }
                    else
                    {
                        result[2][1] = DataConversion.SingleDoubleOfEffective(String.valueOf(q), 3);
                    }
                }
                catch (Exception exception)
                {
                    result[2][1] = DataConversion.SingleDoubleOfEffective(String.valueOf(q), 3);
                }

            }
            catch (Exception exception)
            {
                result[2][1] = "";
            }
            try
            {
                double x = Double.parseDouble(str1[1][2]);
                double f = Double.parseDouble(DataSourse.get(3)._StudentValue.toString());//f
                double q = (2 * f * 10 * 2 * 0.6328 * 0.001) / x;
                try
                {
                    double u = Double.parseDouble(str1[2][2]);
                    int n = DataConversion.GetEffectiveNumericalDigits(String.valueOf(u));
                    if (n == 3 || n == 4)
                    {
                        result[2][2] = DataConversion.SingleDoubleOfEffective(String.valueOf(q), n);
                    }
                    else
                    {
                        result[2][2] = DataConversion.SingleDoubleOfEffective(String.valueOf(q), 3);
                    }
                }
                catch (Exception exception)
                {
                    result[2][2] = DataConversion.SingleDoubleOfEffective(String.valueOf(q), 3);
                }
            }
            catch (Exception exception)
            {
                result[2][2] = "";
            }
            try
            {
                double x = Double.parseDouble(str1[1][3]);
                double f = Double.parseDouble(DataSourse.get(3)._StudentValue.toString());//f
                double q = (2 * f * 10 * 3 * 0.6328 * 0.001) / x;
                try
                {
                    double u = Double.parseDouble(str1[2][3]);
                    int n = DataConversion.GetEffectiveNumericalDigits(String.valueOf(u));
                    if (n == 3 || n == 4)
                    {
                        result[2][3] = DataConversion.SingleDoubleOfEffective(String.valueOf(q), n);
                    }
                    else
                    {
                        result[2][3] = DataConversion.SingleDoubleOfEffective(String.valueOf(q), 3);
                    }
                }
                catch (Exception exception)
                {
                    result[2][3] = DataConversion.SingleDoubleOfEffective(String.valueOf(q), 3);
                }
            }
            catch (Exception exception)
            {
                result[2][3] = "";
            }

            para._StandardValue = XmlAnalysis.SetMatrixString(result, rownum, culmnum);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //平均值
    public static PhyParas D23list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            double d = 0.0;
            double sum = 0.0;
            int n = 0;
            for (int j = 1; j < 4; j++)
            {
                if (str1[2][j] != "" && str1[2][j] != null)
                {
                    d = Double.parseDouble(str1[2][j]);
                    n = n + 1;
                }
                    else
                {
                    d = 0.0;
                }
                sum = sum + d;
            }
            if (n > 0)
            {
                sum = sum / n;
                int ws = DataConversion.GetEffectiveNumericalDigits(para._StudentValue.toString());
                if (ws == 3 || ws == 4)
                {
                    para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(sum), ws);
                }
                else
                {
                    para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(sum), 3);
                }
            }
            else
            {
                para._StandardValue = "";
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //相对误差
    public static PhyParas D24list(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double a = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());//D
            double b = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());//d
            double D = Math.abs((b - a) / a);
            int ws = DataConversion.GetEffectiveNumericalDigits(para._StudentValue.toString());
            if (ws == 2 || ws == 3)
            {
                para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(D), ws) + ";" + "0.2"; ;
            }
            else
            {
                para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(D), 3) + ";" + "0.2"; ;
            }
        }
        catch (Exception exception)
        {
            // double B = Double.parseDouble(DataSourse.get(0)._StudentValue);
            para._StandardValue = "" + ";" + "0.2";
        }
        return para;
    }



}
