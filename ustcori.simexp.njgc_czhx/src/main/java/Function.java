import common.PhyParas;
import common.XmlAnalysis;

import java.util.List;

/**
 * @author Administrator
 */
public class Function {
    //1.(1)
    public static PhyParas M1list1(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double C = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            if (C >= 0.58 && C <= 0.68)
            {
                try
                {
                    int n = DataConversion.GetDecimalPointDigits(para._StudentValue.toString());
                    if (n == 2 || n == 3)
                    {
                        para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(C), n);
                    }

                    else
                    {
                        para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(C), 2);
                    }
                }
                catch (Exception exception)
                {
                    para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(C), 2);
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
    //1.(2)
    public static PhyParas M2list1(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(para._StudentValue);
            //表格总行数
            int rownum = 9;
            //表格总列数
            int culmnum = 2;

            try
            {
                //double a = Double.parseDouble(str1[i, 1]);//循环的意义在哪，你这里的意思是把每行的值刷新赋给第一行的值，最终第一行的学生值等于最后一行，做如下调整
                double a = Double.parseDouble(str1[1][1]);
                if (a >= 10.0 && a <= 15.0)
                {
                    str1[1][1] = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 1);
                }
                else
                {
                    str1[1][1] = "";
                }
            }
            catch (Exception exception)
            {
                str1[1][1] = "";
            }
            try
            {
                double a = Double.parseDouble(str1[2][1]);//如上问题一致
                if (a >= 10.0 && a <= 15.0)
                {
                    str1[2][1] = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 1);
                }
                else
                {
                    str1[2][1] = "";
                }
            }
            catch (Exception exception)
            {
                str1[2][1] = "";
            }
            try
            {

                double a = Double.parseDouble(str1[3][1]);//如上问题一致
                double b = Double.parseDouble(str1[1][1]);
                if (0 < a && a < b)
                {
                    str1[3][1] = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 1);
                }
                else
                {
                    str1[3][1] = "";
                }
            }
            catch (Exception exception)
            {
                str1[3][1] = "";
            }
            try
            {
                double a = Double.parseDouble(str1[4][1]);//如上问题一致
                double b = Double.parseDouble(str1[2][1]);
                if (0< a && a< b)
                {
                    str1[4][1] = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 1);
                }
                else
                {
                    str1[4][1] = "";
                }
            }
            catch (Exception exception)
            {
                str1[4][1] = "";
            }
            try
            {
                double a = Double.parseDouble(str1[5][1]);//如上问题一致
                double b = Double.parseDouble(str1[7][1]);
                if (0 < a && a < b)
                {
                    str1[5][1] = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 1);
                }
                else
                {
                    str1[5][1] = "";
                }
            }
            catch (Exception exception)
            {
                str1[5][1] = "";
            }
            try
            {
                double a = Double.parseDouble(str1[6][1]);//如上问题一致
                double b = Double.parseDouble(str1[8][1]);
                if (0 < a && a < b)
                {
                    str1[6][1] = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 1);
                }
                else
                {
                    str1[6][1] = "";
                }
            }
            catch (Exception exception)
            {
                str1[6][1] = "";
            }
            try
            {
                double a = Double.parseDouble(str1[7][1]);//如上问题一致
                if (a >= 10.0 && a <= 15.0)
                {
                    str1[7][1] = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 1);
                }
                else
                {
                    str1[7][1] = "";
                }
            }
            catch (Exception exception)
            {
                str1[7][1] = "";
            }
            try
            {
                double a = Double.parseDouble(str1[8][1]);//如上问题一致
                if (a >= 10.0 && a <= 15.0)
                {
                    str1[8][1] = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 1);
                }
                else
                {
                    str1[8][1] = "";
                }
            }
            catch (Exception exception)
            {
                str1[8][1] = "";
            }


            para._StandardValue = XmlAnalysis.SetMatrixString(str1, rownum, culmnum);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //2.(1)
    public static PhyParas M3list1(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            String[][] result = XmlAnalysis.GetMatrixString(para._StandardValue);

            double h = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());
            double b = Double.parseDouble(DataSourse.get(2)._StudentValue.toString());
            //表格总行数
            int rownum = 6;
            //表格总列数
            int culmnum = 11;

            try
            {
                double a = Double.parseDouble(str1[1][1]);
                if (a >0.0 && a <= 25.0)
                {
                    result[1][1] = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 1);
                }
                else
                {
                    result[1][1] = "";
                }
            }
            catch (Exception exception)
            {
                result[1][1] = "";
            }
            try
            {
                double a = Double.parseDouble(str1[2][1]);//同上问题
                if (a > 0.0 && a  <= 25.0)
                {
                    result[2][1] = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 1);
                }
                else
                {
                    result[2][1] = "";
                }
            }
            catch (Exception exception)
            {
                result[2][1] = "";
            }



            for (int j = 1; j < 10; j++)
            {
                   // X(小格)
                //X小格
                try
                {
                    double a1 = Double.parseDouble(str1[1][j]);
                    double b1 = Double.parseDouble(str1[1][j + 1]);//局部变量和全局变量冲突，发我的时候请看下左下角的×，或者试试生成，看看能否顺利生成
                    if (b1 > 0.0 && b1 <= 25.0 && b1 > a1)
                    {
                        //str1[1][j + 1] = DataConversion.SingleDoubleOfDecimal(j + 1.toString(), 1);  //j+1需要括号括起来，防止出现把1转换成字符
                        result[1][j + 1] = DataConversion.SingleDoubleOfDecimal(String.valueOf(b1), 1);

                    }
                    else
                    {
                        result[1][j + 1] = "";
                    }
                }
                catch (Exception exception)
                {
                    result[1][j + 1] = "";
                }
                  

                //Y小格

                try
                {
                    double a2 = Double.parseDouble(str1[2][j]);
                    double b2 = Double.parseDouble(str1[2][j + 1]);//同上问题
                    if (b2 > 0.0 && b2 <= 25.0 && b2 > a2)
                    {
                        result[2][j + 1] = DataConversion.SingleDoubleOfDecimal(String.valueOf(b2), 1);
                    }
                    else
                    {
                        result[2][j + 1] = "";
                    }
                }
                catch (Exception exception)
                {
                    result[2][j + 1] = "";
                }


            }
            for (int j = 1; j < 11; j++)
            {
                //Hm
                try
                {
                    double a3 = Double.parseDouble(str1[1][j]);
                    double hm = a3 * h;
                    //int n = DataConversion.GetEffectiveNumericalDigits(DataSourse.get(0)._StudentValue.toString());//写的不对，你获取有效位数，计算的却是小数位数
                    int n = DataConversion.GetEffectiveNumericalDigits((str1[3][j]).toString());//写的不对，你获取有效位数，计算的却是小数位数
                    if (n == 2 || n == 3 || n == 4)
                    {
                        result[3][j] = DataConversion.SingleDoubleOfEffective(String.valueOf(hm), n);
                    }

                    else
                    {
                        result[3][j] = DataConversion.SingleDoubleOfEffective(String.valueOf(hm), 3);
                    }

                }
                catch (Exception exception)
                {
                    result[3][j] = "";
                }
                //Bm
                try
                {
                    double a4 = Double.parseDouble(str1[2][j]);
                    double bm = a4 * b;
                    int n = DataConversion.GetEffectiveNumericalDigits((str1[4][ j]).toString());
                    if (n == 2 || n == 3 || n == 4)
                    {
                        result[4][ j] = DataConversion.SingleDoubleOfEffective(String.valueOf(bm), n);
                    }

                    else
                    {
                        result[4][ j] = DataConversion.SingleDoubleOfEffective(String.valueOf(bm), 3);
                    }
                }
                catch (Exception exception)
                {
                    result[4][ j] = "";
                }
                //μ
                try
                {
                    double a5 = Double.parseDouble(str1[3][j]);
                    double c5 = Double.parseDouble(str1[4][ j]);
                    if (a5!=0)
                    {
                        double µ = c5 / a5 / 4 / Math.PI * 10000000;
                        int n = DataConversion.GetEffectiveNumericalDigits((str1[5][j]).toString());//按上面的表述，这个是表格的数据源，下面调用的数据源错误还是调某行某列？
                        if (n == 3 || n == 4 || n == 5)
                        {
                            result[5][j] = DataConversion.SingleDoubleOfEffective(String.valueOf(µ), n);
                        }

                        else
                        {
                            result[5][j] = DataConversion.SingleDoubleOfEffective(String.valueOf(µ), 3);
                        }
                    }
                    else
                    {
                        result[5][j] = "";
                    }

                }
                catch (Exception exception)
                {
                    result[5][j] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(result, rownum, culmnum);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //2.(2)

    //2.(3)

    //2.(3).1
    public static PhyParas M6list1(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StandardValue);
            {
                //μ0
                try
                {

                    String u = String.valueOf(para._StudentValue);
                    if (u != "" && u != null)
                    {
                        double U = Double.parseDouble(u);
                        if (U <= Double.parseDouble(str1[5][1]))
                        {
                            try
                            {
                                int n = DataConversion.GetEffectiveNumericalDigits(u);
                                if (n == 3 || n == 4 || n == 5)
                                {
                                    para._StandardValue = (DataConversion.SingleDoubleOfEffective(u, n) + ";" + DataConversion.SingleDoubleOfEffective(str1[5][1], n));
                                }
                                else
                                {
                                    para._StandardValue = (DataConversion.SingleDoubleOfEffective(u, 3) + ";" + DataConversion.SingleDoubleOfEffective(str1[5][1], 3));
                                }
                            }
                            catch (Exception exception)
                            {
                                para._StandardValue = (DataConversion.SingleDoubleOfEffective(u, 3) + ";" + DataConversion.SingleDoubleOfEffective(str1[5][1], 3));
                            }
                        }
                            else
                        {
                            try
                            {
                                int n = DataConversion.GetEffectiveNumericalDigits(str1[5][1]);
                                if (n == 3 || n == 4 || n == 5)
                                {
                                    para._StandardValue = (DataConversion.SingleDoubleOfEffective(str1[5][1], n) + ";" + DataConversion.SingleDoubleOfEffective(str1[5][1], n));
                                }
                                else
                                {
                                    para._StandardValue = (DataConversion.SingleDoubleOfEffective(str1[5][1], 3) + ";" + DataConversion.SingleDoubleOfEffective(str1[5][1], 3));
                                }
                            }
                            catch (Exception exception)
                            {
                                para._StandardValue = (DataConversion.SingleDoubleOfEffective(str1[5][1], 3) + ";" + DataConversion.SingleDoubleOfEffective(str1[5][1], 3));
                            }
                            para._StandardValue = (DataConversion.SingleDoubleOfEffective(str1[5][1], 3) + ";" + DataConversion.SingleDoubleOfEffective(str1[5][1], 3)) ;//自己根据学生值调整保留位数，固定值返回的是10V下的μ
                        }
                    }
                }
                catch (Exception exception)
                {
                    para._StandardValue = "" + "";
                }

            }

        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //2.(3).2
    public static PhyParas M7list1(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StandardValue);
            {
                //μ0

                try
                {
                    double a = Double.parseDouble(str1[5][1]);
                    double b = Double.parseDouble(str1[5][2]);
                    double k = 0.0;
                    if (a > b)
                    {
                        k = a;

                    }
                    else
                    {
                        k = b;
                    }
                    for (int j = 3; j < 11; j++)
                    {

                        double c = Double.parseDouble(str1[5][j]);

                        if (c - k > 0)
                        {
                            k = c;
                        }
                    }
                    String u = String.valueOf(para._StudentValue);
                    if (u != "" && u != null)
                    {
                        double U = Double.parseDouble(u);
                        String K = String.valueOf(k);
                        if (U > k)
                        {
                            try
                            {
                                int n = DataConversion.GetDecimalPointDigits(para._StudentValue.toString());
                                //int q = DataConversion.GetDecimalPointDigits(String.valueOf(k));
                                if (n == 0 || n == 1 || n == 2)
                                {
                                    para._StandardValue = (DataConversion.SingleDoubleOfDecimal(u, n) + ";" + DataConversion.SingleDoubleOfDecimal(K, n));
                                }
                                else
                                {
                                    para._StandardValue = (DataConversion.SingleDoubleOfDecimal(u, 1) + ";" + DataConversion.SingleDoubleOfDecimal(K, 1));
                                }
                            }
                            catch(Exception exception)
                            {
                                para._StandardValue = (DataConversion.SingleDoubleOfDecimal(u, 1) + ";" + DataConversion.SingleDoubleOfDecimal(K, 1));
                            }
                        }
                        else
                        {
                            try
                            {
                                int q = DataConversion.GetDecimalPointDigits(String.valueOf(k));
                                if (q == 0 || q == 1 || q == 2)
                                {
                                    para._StandardValue = (DataConversion.SingleDoubleOfDecimal(K, q) + ";" + DataConversion.SingleDoubleOfDecimal(K, q));
                                }
                                else
                                {
                                    para._StandardValue = (DataConversion.SingleDoubleOfDecimal(K, 1) + ";" + DataConversion.SingleDoubleOfDecimal(K, 1));
                                }
                            }
                            catch(Exception exception)
                            {
                                para._StandardValue = (DataConversion.SingleDoubleOfDecimal(K, 1) + ";" + DataConversion.SingleDoubleOfDecimal(K, 1));
                            }
                        }
                    }

                }
                catch (Exception exception)
                {
                    para._StandardValue = "";
                }
            }
        }

        catch (Exception exception)
        {
            para._StandardValue = "";
        }


        return para;
    }
    //3.(1)
    public static PhyParas M8list1(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            //表格总行数
            int rownum = 3;
            //表格总列数
            int culmnum = 7;
            //Mx
            try
            {
                double a = Double.parseDouble(str1[1][1]);//可拖至循环外，减少计算
                if (a >= 5.0 && a <= 10.0)
                {
                    str1[1][1] = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 1);
                }
                else
                {
                    str1[1][1] = "";
                }
            }
            catch (Exception exception)
            {
                str1[1][1] = "";
            }
            for (int j = 2; j < 6; j++)
            {
                try
                {
                    double a = Double.parseDouble(str1[1][1]);
                    double b = a * j;
                    str1[1][j] = DataConversion.SingleDoubleOfDecimal(String.valueOf(b), 1);

                }
                catch (Exception exception)
                {
                    str1[1][j] = "";

                }
            }
            try
            {
                double a = Double.parseDouble(str1[1][1]);
                double b = a * 6.0;
                if(b <= 50.0)
                {
                    str1[1][6] = DataConversion.SingleDoubleOfDecimal(String.valueOf(b), 1);
                }
                else
                {
                    str1[1][6] = "50.0";
                }
            }
            catch (Exception exception)
            {
                str1[1][6] = "";

            }
            //H0
            for (int j = 1; j < 7; j++)
            {
                try
                {
                    double a = Double.parseDouble(str1[0][j]);
                    double b = Double.parseDouble(str1[1][j]);
                    double c = 2 * Math.sqrt(2) * 600 * a * 100 / 47.123 / b;
                    int n = DataConversion.GetEffectiveNumericalDigits(DataSourse.get(0)._StudentValue.toString());
                    if (n == 2 || n == 3)
                    {
                        str1[2][j] = DataConversion.SingleDoubleOfEffective(String.valueOf(c), n);
                    }

                    else
                    {
                        str1[2][j] = DataConversion.SingleDoubleOfEffective(String.valueOf(c), 3);
                    }
                }
                catch (Exception exception)
                {
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
    //3.(2)
    public static PhyParas M9list1(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            String[][] str2 = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StudentValue);

            double d = 0.0;
            double sum = 0.0;
            int n = 0;
            for (int j = 1; j < 7; j++)
            {
                if (str2[2][j] != "" && str2[2][j] != null)
                {
                    d = Double.parseDouble(str2[2][j]);
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
                int m = DataConversion.GetEffectiveNumericalDigits(DataSourse.get(0)._StudentValue.toString());
                if (m == 2 || m == 3)
                {
                    para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(sum), m);
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
    //4.(1)
    public static PhyParas M10list1(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            //表格总行数
            int rownum = 3;
            //表格总列数
            int culmnum = 7;
            //My
            try
            {
                double a = Double.parseDouble(str1[1][1]);
                if (a >= 5.0 && a <=10.0)
                {
                    str1[1][1] = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 1);
                }
                else
                {
                    str1[1][1] = "";
                }
            }
            catch (Exception exception)
            {
                str1[1][1] = "";
            }
            for (int j = 2; j < 6; j++)
            {


                try
                {
                    double a = Double.parseDouble(str1[1][1]);
                    double b = a * j;
                    str1[1][j] = DataConversion.SingleDoubleOfDecimal(String.valueOf(b), 1);

                }
                catch (Exception exception)
                {
                    str1[1][j] = "";

                }
            }
            try
            {
                double a = Double.parseDouble(str1[1][1]);
                double b = a * 6.0;
                if (b <= 50.0)
                {
                    str1[1][6] = DataConversion.SingleDoubleOfDecimal(String.valueOf(b), 1);
                }
                else
                {
                    str1[1][6] = "50.0";
                }
            }
            catch (Exception exception)
            {
                str1[1][6] = "";

            }
            //B0
            for (int j = 1; j < 7; j++)
            {
                try
                {
                    double a = Double.parseDouble(str1[0][j]);
                    double b = Double.parseDouble(str1[1][j]);
                    double c = 2 * Math.sqrt(2) * 0.01 * a * 10000 / 75 / 1.3273 / b;
                    int n = DataConversion.GetEffectiveNumericalDigits(DataSourse.get(0)._StudentValue.toString());
                    if (n == 2 || n == 3)
                    {
                        str1[2][j] = DataConversion.SingleDoubleOfEffective(String.valueOf(c), n);
                    }

                    else
                    {
                        str1[2][j] = DataConversion.SingleDoubleOfEffective(String.valueOf(c), 3);
                    }
                }
                catch (Exception exception)
                {
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
    //4.(2)
    public static PhyParas M11list1(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            String[][] str2 = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StudentValue);

            double d = 0.0;
            double sum = 0.0;
            int n = 0;
            for (int j = 1; j < 7; j++)
            {
                if (str2[2][j] != "" && str2[2][j] != null)
                {
                    d = Double.parseDouble(str2[2][j]);
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
                int m = DataConversion.GetEffectiveNumericalDigits(DataSourse.get(0)._StudentValue.toString());
                if (m == 2 || m == 3)
                {
                    para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(sum), m);
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


}
