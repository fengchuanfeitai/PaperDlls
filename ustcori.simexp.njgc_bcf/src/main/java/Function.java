import common.PhyParas;
import common.XmlAnalysis;

import java.util.List;

/**
 * @author Administrator
 */
public class Function {

    /// <summary>
    ///记录实验中的Eₛ(V)
    /// </summary>
    /// <param name="DataSourse">/param>
    /// <returns>t</returns>
    public static PhyParas ES(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String a = String.valueOf(DataSourse.get(0)._StandardValue);


            if (a != null)
            {
                para._StandardValue = DataConversion.SingleDoubleOfEffective(a.toString(), 6);
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


    /// <summary>
    ///记录实验中的E₀(V)
    /// </summary>
    /// <param name="DataSourse">E0/param>
    /// <returns>Es</returns>
    public static PhyParas E0(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String a = String.valueOf(DataSourse.get(0)._StudentValue);
            if (a != null)
            {
                para._StandardValue = DataConversion.SingleDoubleOfEffective(a.toString(), 1);
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


    /// <summary>
    ///LS和LX各测量6次，将实验数据记录下列表格中
    /// </summary>
    /// <param name="DataSourse">SJBG1/param>
    /// <returns>Es</returns>
    public static PhyParas SJBG1(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            //String[,] result = XmlAnalysis.GetMatrixString(para._StandardValue);
            for (int i = 1; i < 7; i++)
            {
                if (str[1][i] != null && str[1][i] != "")
                {
                    double a=Double.parseDouble(str[1][i]);
                    if (a >= 3.7500 && a <= 3.7600)
                    {
                        str[1][i] = DataConversion.SingleDoubleOfDecimal(str[1][i].toString(), 4);
                    }
                    else
                    {

                        str[1][i] = "3.7550";
                    }
                }
                    else
                {

                    str[1][i] = "3.7550";
                }
                if (str[2][i] != null && str[2][i] != "")
                {
                    double a = Double.parseDouble(str[2][i]);
                    if (a >= 5.5400 && a <= 5.5500)
                    {
                        str[2][i] = DataConversion.SingleDoubleOfDecimal(str[2][i].toString(), 4);
                    }
                    else
                    {

                        str[2][i] = "5.5450";
                    }
                }
                    else
                {

                    str[2][i] = "5.5450";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str, 3, 7);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }


    /// <summary>
    /// LS的平均值(m)
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas LSPJZ(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StandardValue);
            double sum=0;
            double a;
            for(int j = 1; j < 7; j++)
            {
                a = Double.parseDouble(str[1][j]);
                sum += a;
            }
            sum = sum / 6;
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(sum), 4);

        }
        catch(Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }


    /// <summary>
    /// LX的平均值(m)
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas LXPJZ(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StandardValue);
            double sum = 0;
            double a;
            for (int j = 1; j < 7; j++)
            {
                a = Double.parseDouble(str[2][j]);
                sum += a;
            }
            sum = sum / 6;
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(sum),4);

        }
        catch(Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }


    /// <summary>
    /// LS的A类不确定度计算
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas LSA(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StandardValue);
            double ave = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double result;
            double[] a = { 0, 0, 0, 0, 0, 0 };
            int n = 0;
            for (int j = 1;j<7;j++)
            {
                try
                {
                    if (str1[1][ j] != null && str1[1][ j] != "")
                    {
                        a[j - 1] = Double.parseDouble(str1[1][ j]) - ave;
                        n++;
                    }
                        else
                    {
                        a[j - 1] = 0;
                    }
                }
                catch (Exception exception)
                {
                    a[j - 1] = 0;
                }
                if (n > 1)
                {
                    result =  Math.sqrt((a[0] * a[0] + a[1] * a[1] + a[2] * a[2] + a[3] * a[3] + a[4] * a[4] + a[5] * a[5]) / (n * (n - 1)));
                    if (result != 0)
                    {
                        para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(result), 2);
                    }
                    else
                    {
                        para._StandardValue=0.00;
                    }
                }
            }
        }
        catch(Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }


    /// <summary>
    /// LX的A类不确定度计算
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas LXA(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StandardValue);
            double ave = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double result;
            double[] a = { 0, 0, 0, 0, 0, 0 };
            int n = 0;
            for (int j = 1; j < 7; j++)
            {
                try
                {
                    if (str1[2][j] != null && str1[2][j] != "")
                    {
                        a[j - 1] = Double.parseDouble(str1[2][j]) - ave;
                        n++;
                    }
                        else
                    {
                        a[j - 1] = 0;
                    }
                }
                catch (Exception exception)
                {
                    a[j - 1] = 0;
                }
                if (n > 1)
                {
                    result =  Math.sqrt((a[0] * a[0] + a[1] * a[1] + a[2] * a[2] + a[3] * a[3] + a[4] * a[4] + a[5] * a[5]) / (n * (n - 1)));
                    if (result != 0)
                    {
                        para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(result), 2);
                    }
                    else
                    {
                        para._StandardValue = 0.00;
                    }
                }
            }
        }
        catch(Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }


    /// <summary>
    /// LS的B类不确定度计算
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas LSB(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            para._StandardValue = 0.00029;
        }
        catch(Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }


    /// <summary>
    /// LX的B类不确定度计算
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas LXB(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            para._StandardValue = 0.00029;
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }


    /// <summary>
    /// LS的合成不确定度计算
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas LSHC(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double lsa = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            double lsb = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double result =  Math.sqrt(lsa * lsa + lsb * lsb);
            para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(result), 2);


        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }


    /// <summary>
    ///  LX的合成不确定度计算
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas LXHC(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double lsa = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            double lsb = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double result =  Math.sqrt(lsa * lsa + lsb * lsb);
            para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(result), 2);


        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }


   /** 计算电池的电动势及不确定度*/

    /// <summary>
    ///电池电动势平均值
    /// </summary>
    /// <param name="DataSourse">电池电动势和路端电压的数据表/param>
    /// <returns>平均值</returns>
    public static PhyParas DDSPJZ(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double ave = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            if (ave >= 1.45000 && ave <= 1.55000)
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(ave), 5);
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


    /// <summary>
    /// 算出待测电池电动势的不确定度
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas DDSBQDD(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double ex ;
            String str = String.valueOf(DataSourse.get(1)._StudentValue);
            if (str != null&&str!="")
            {
                double ave = Double.parseDouble(str);
                if (ave >= 1.45000 && ave <= 1.55000)
                {
                    ex = ave;
                }
                else
                {
                    ex = 1.50000;
                }
            }
            else
            {
                ex = 1.50000;
            }


            double lshc = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double lspjz = Double.parseDouble(DataSourse.get(3)._StandardValue.toString());
            double lxhc = Double.parseDouble(DataSourse.get(4)._StandardValue.toString());
            double lxpjz = Double.parseDouble(DataSourse.get(5)._StandardValue.toString());
            double result = ex *  Math.sqrt((lshc*lshc)/(lspjz*lspjz)+(lxhc*lxhc)/(lxpjz*lxpjz));
            String b = String.valueOf(result);
            int num = DataConversion.High(b);
            int c = 0;
            if (num < 0)
            {
                c = Integer.parseInt(b.substring(-num + 1, 1));
            }
            else
            {
                c = Integer.parseInt(b.substring(0, 1));
            }
            if (c == 1 || c == 2)
            {
                para._StandardValue = DataConversion.CutDataEffective(String.valueOf(result), 2);
                String b1 = String.valueOf(para._StandardValue);
                int num1 = DataConversion.High(b1);
                int c1 = 0;
                if (num1 < 0)
                {
                    c1 = Integer.parseInt(b1.substring(-num1 + 1, 1));
                }
                else
                {
                    c1 = Integer.parseInt(b1.substring(0, 1));
                }
                if (c1 >= 3)
                {
                    para._StandardValue = DataConversion.CutDataEffective(b1.toString(), 1);
                }

            }
            else if (c >= 3)
            {
                para._StandardValue = DataConversion.CutDataEffective(String.valueOf(result), 1);
                String b1 = String.valueOf(para._StandardValue);
                int num1 = DataConversion.High(b1);
                int c1 = 0;
                if (num1 < 0)
                {
                    c1 = Integer.parseInt(b1.substring(-num1 + 1, 1));
                }
                else
                {
                    c1 = Integer.parseInt(b1.substring(0, 1));
                }
                if (c1 == 1 || c == 2)
                {
                    para._StandardValue = DataConversion.CutDataEffective(b1.toString(), 2);
                }
            }
            else if (c == 0)
            {
                para._StandardValue = "0";
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


    /// <summary>
    /// 待测电池电动势的最终结果表达式
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas BDS(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            if (!DataSourse.get(0)._StudentValue.toString().isEmpty() &&  !DataSourse.get(1)._StudentValue.toString().isEmpty())
            {
                String result = String.valueOf(DataSourse.get(1)._StudentValue);
                int num = DataConversion.GetEffectiveNumericalDigits(result);
                para._StandardValue =DataConversion.GetExpression_SSWR(DataSourse.get(0)._StudentValue.toString(), DataSourse.get(1)._StudentValue.toString(), num);
            }
            else if (!DataSourse.get(0)._StandardValue.toString().isEmpty() && !DataSourse.get(1)._StandardValue.toString().toString().isEmpty())
            {
                String result = String.valueOf(DataSourse.get(1)._StandardValue.toString());
                int num = DataConversion.GetEffectiveNumericalDigits(result);
                para._StandardValue = DataConversion.GetExpression_SSWR(DataSourse.get(0)._StandardValue.toString(), DataSourse.get(1)._StandardValue.toString().toString(), num);

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
