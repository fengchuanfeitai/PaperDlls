import common.PhyParas;
import common.XmlAnalysis;

import java.util.List;

/**
 * @author Administrator
 */
public class Function {

   // 用游标卡尺测量空心圆柱体的体积，并计算密度
    /// <summary>
    ///（1）游标卡尺零点读数
    /// </summary>
    public static PhyParas YB(PhyParas para, List<PhyParas> DataSourse)
    {
        //Log("LXCWJ_FDZ_id", DataSourse);

        try
        {
            double ds = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            if (ds >=0 && ds <= 0.30)
            {
                para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(DataSourse.get(0)._StudentValue), 2);
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        //Log("result:"+para._StandardValue);
        return para;
    }

    /// <summary>
    ///填入所确定状态下的相应数据
    /// </summary>
    public static PhyParas SJBG1(PhyParas para, List<PhyParas> DataSourse)
    {
        //Log("LXCWJ_LC_id", DataSourse);
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            for (int j = 1; j < 7; j++)
            {



                if (str[1][j] != "" && str[1][j] != null)
                {
                    double h = Double.parseDouble(str[1][j]);
                    if (h >= 81.50 && h <= 82.50)
                    {
                        str[1][j] = DataConversion.SingleDoubleOfEffective(str[1][j], 4);
                    }
                    else
                    {
                        str[1][j] = "";
                    }
                }
                    else
                {
                    str[1][j] = "";
                }

                if (str[2][j] != "" && str[2][j] != null)
                {
                    double d = Double.parseDouble(str[2][j]);
                    if (d >= 12.50 && d <= 13.10)
                    {
                        str[2][j] = DataConversion.SingleDoubleOfEffective(str[2][j], 4);
                    }
                    else
                    {
                        str[2][j] = "";
                    }
                }
                    else
                {
                    str[2][j] = "";
                }

                if (str[3][j] != "" && str[3][j] != null)
                {
                    double D = Double.parseDouble(str[3][j]);
                    if (D >= 14.70 && D <= 15.30)
                    {
                        str[3][j] = DataConversion.SingleDoubleOfEffective(str[3][j], 4);
                    }
                    else
                    {
                        str[3][j] = "";
                    }
                }
                    else
                {
                    str[3][j] = "";
                }


            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str, 4, 7);


        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        // Log("result:" + para._StandardValue);
        return para;
    }

    /// <summary>
    ///平均值表格
    /// </summary>
    public static PhyParas SJBG2(PhyParas para, List<PhyParas> DataSourse)
    {
        // Log("LXCWJ_JB_id", DataSourse);

        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            String[][] str2 = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StandardValue);

            double resulth;
            double[] h = { 0, 0, 0, 0, 0, 0 };
            int nh= 0;
            double resultd;
            double[] d = { 0, 0, 0, 0, 0, 0 };
            int nd = 0;
            double resultD;
            double[] D = { 0, 0, 0, 0, 0, 0 };
            int nD = 0;
            for (int j = 1; j < 7; j++)
            {
                try
                {
                    if (str2[1][j] != null && str2[1][j] != "")
                    {
                        h[j - 1] = Double.parseDouble(str2[1][j]);
                        nh++;
                    }
                        else
                    {
                        h[j - 1] = 0;
                    }
                }
                catch (Exception exception)
                {

                    h[j - 1] = 0;
                }
                try
                {
                    if (str2[2][j] != null && str2[2][j] != "")
                    {
                        d[j - 1] = Double.parseDouble(str2[2][j]);
                        nd++;
                    }
                        else
                    {
                        d[j - 1] = 0;
                    }
                }
                catch (Exception exception)
                {

                    d[j - 1] = 0;
                }
                try
                {
                    if (str2[3][j] != null && str2[3][j] != "")
                    {
                        D[j - 1] = Double.parseDouble(str2[3][j]);
                        nD++;
                    }
                        else
                    {
                        D[j - 1] = 0;
                    }
                }
                catch (Exception exception)
                {

                    D[j - 1] = 0;
                }

            }

            if (nh != 0)
            {
                resulth = (h[0] + h[1] + h[2] + h[3] + h[4] + h[5]) / nh;
                str1[1][1] = DataConversion.SingleDoubleOfEffective(String.valueOf(resulth),4);
            }
            else
            {
                str1[1][1] = "";
            }
            if (nd != 0)
            {
                resultd = (d[0] + d[1] + d[2] + d[3] + d[4] + d[5]) / nd;
                 str1[2][1] = DataConversion.SingleDoubleOfEffective(String.valueOf(resultd), 4);
            }
            else
            {
                 str1[2][1] = "";
            }
            if (nD != 0)
            {
                resultD = (D[0] + D[1] + D[2] + D[3] + D[4] + D[5]) / nD;
               str1[3][1] = DataConversion.SingleDoubleOfEffective(String.valueOf(resultD), 4);
            }
            else
            {
               str1[3][1] = "";
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str1, 4, 2);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        // Log("result:" + para._StandardValue);
        return para;
    }

    /// <summary>
    ///不确定度表格
    /// </summary>
    public static PhyParas SJBG3(PhyParas para, List<PhyParas> DataSourse)
    {
        // Log("LXCWJ_YQBQDD_id", DataSourse);
        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            String[][] str2 = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StandardValue);
            String[][] str3 = XmlAnalysis.GetMatrixString(DataSourse.get(2)._StandardValue);

            double resultha;
            double resulthb;
            double resulth;
            double[] h = { 0, 0, 0, 0, 0, 0 };
            int nh = 0;

            double resultda;
            double resultdb;
            double resultd;
            double[] d = { 0, 0, 0, 0, 0, 0 };
            int nd = 0;

            double resultDa;
            double resultDb;
            double resultD;
            double[] D = { 0, 0, 0, 0, 0, 0 };
            int nD = 0;
            for (int j = 1; j < 7; j++)
            {
                try
                {
                    if ((str2[1][j] != null && str2[1][j] != "")&& (str3[1][1] != null && str3[1][1] != ""))
                    {
                        h[j - 1] = Double.parseDouble(str2[1][j]) - Double.parseDouble(str3[1][1]);
                        nh++;
                    }
                        else
                    {
                        h[j - 1] = 0;
                    }
                }
                catch (Exception exception)
                {
                    h[j - 1] = 0;
                }
                try
                {
                    if ((str2[2][j] != null && str2[2][j] != "")&& (str3[2][1] != null && str3[2][1] != ""))
                    {
                        d[j - 1] = Double.parseDouble(str2[2][j]) - Double.parseDouble(str3[2][1]);
                        nd++;
                    }
                        else
                    {
                        d[j - 1] = 0;
                    }
                }
                catch (Exception exception)
                {
                    d[j - 1] = 0;
                }
                try
                {
                    if ((str2[3][j] != null && str2[3][j] != "")&& (str3[3][1] != null && str3[3][1] != ""))
                    {
                        D[j - 1] = Double.parseDouble(str2[3][j]) - Double.parseDouble(str3[3][1]);
                        nD++;
                    }
                        else
                    {
                        D[j - 1] = 0;
                    }
                }
                catch (Exception exception)
                {
                    D[j - 1] = 0;
                }
            }
            if (nh > 1)
            {
                resultha = Math.sqrt((h[0] * h[0] + h[1] * h[1] + h[2] * h[2] + h[3] * h[3] + h[4] * h[4] + h[5] * h[5]) / (nh * (nh - 1)));
                resulthb = 0.02 / (Math.sqrt(3));
                resulth = Math.sqrt(resultha * resultha + resulthb * resulthb);
                String b = String.valueOf(resulth);
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
                    str1[1][1] = DataConversion.CutDataEffective(String.valueOf(resulth), 2);
                    String b1 = String.valueOf(str1[1][1]);
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
                        str1[1][1] = DataConversion.CutDataEffective(b1.toString(), 1);
                    }

                }
                else if (c >= 3)
                {
                    str1[1][1] = DataConversion.CutDataEffective(String.valueOf(resulth), 1);
                    String b1 = String.valueOf(str1[1][1]);
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
                        str1[1][1] = DataConversion.CutDataEffective(b1.toString(), 2);
                    }
                }
                else if (c == 0)//增加相对误差为0时，返回标准答案为空的问题
                {
                    str1[1][1] = "0";
                }
                else
                {
                    str1[1][1] = "";
                }
            }
            else
            {
                str1[1][1] = "";
            }
            if (nd > 1)
            {
                resultda = Math.sqrt((d[0] * d[0] + d[1] * d[1] + d[2] * d[2] + d[3] * d[3] + d[4] * d[4] + d[5] * d[5]) / (nd * (nd - 1)));
                resultdb = 0.02 / (Math.sqrt(3));
                resultd = Math.sqrt(resultda * resultda + resultdb * resultdb);
                String b = String.valueOf(resultd);
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
                     str1[2][1] = DataConversion.CutDataEffective(String.valueOf(resultd), 2);
                    String b1 = String.valueOf( str1[2][1]);
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
                         str1[2][1] = DataConversion.CutDataEffective(b1.toString(), 1);
                    }

                }
                else if (c >= 3)
                {
                     str1[2][1] = DataConversion.CutDataEffective(String.valueOf(resultd), 1);
                    String b1 = String.valueOf( str1[2][1]);
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
                         str1[2][1] = DataConversion.CutDataEffective(b1.toString(), 2);
                    }
                }
                else if (c == 0)//增加相对误差为0时，返回标准答案为空的问题
                {
                    str1[2][1] = "0";
                }
                else
                {
                     str1[2][1] = "";
                }
            }
            else
            {
                 str1[2][1] = "";
            }
            if (nD > 1)
            {
                resultDa = Math.sqrt((D[0] * D[0] + D[1] * D[1] + D[2] * D[2] + D[3] * D[3] + D[4] * D[4] + D[5] * D[5]) / (nD * (nD - 1)));
                resultDb = 0.02 / (Math.sqrt(3));
                resultD = Math.sqrt(resultDa * resultDa + resultDb * resultDb);
                String b = String.valueOf(resultD);
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
                    str1[3][1] = DataConversion.CutDataEffective(String.valueOf(resultD), 2);
                    String b1 = String.valueOf(str1[3][1]);
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
                       str1[3][1] = DataConversion.CutDataEffective(b1.toString(), 1);
                    }

                }
                else if (c >= 3)
                {
                   str1[3][1] = DataConversion.CutDataEffective(String.valueOf(resultD), 1);
                    String b1 = String.valueOf(str1[3][1]);
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
                       str1[3][1] = DataConversion.CutDataEffective(b1.toString(), 2);
                    }
                }
                else if (c == 0)//增加相对误差为0时，返回标准答案为空的问题
                {
                   str1[3][1] = "0";
                }
                else
                {
                   str1[3][1] = "";
                }
            }
            else
            {
               str1[3][1] = "";
            }

            para._StandardValue = XmlAnalysis.SetMatrixString(str1, 4, 2);

        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        // Log("result:" + para._StandardValue);
        return para;
    }

    /// <summary>
    ///计算空心圆柱体的体积
    /// </summary>
    public static PhyParas YZTTJ(PhyParas para, List<PhyParas> DataSourse)
    {
        // Log("LXCWJ_LDPC_id", DataSourse);
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StandardValue);
            if((str[1][1]!=""&&str[1][1]!=null)&& (str[2][1] != "" && str[2][1] != null)&& (str[3][1] != "" && str[3][1] != null))
            {
                double h = Double.parseDouble(str[1][1]);
                double d = Double.parseDouble(str[2][1]);
                double D = Double.parseDouble(str[3][1]);
                double v = 0.25 * (D * D - d * d) * h * (Math.PI);
                para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(v), 5);

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
        // Log("result:" + para._StandardValue);
        return para;
    }


    /// <summary>
    ///计算体积的相对不确定度
    /// </summary>
    public static PhyParas TJXDBQDD(PhyParas para, List<PhyParas> DataSourse)
    {
        // Log("LXCWJ_tp_id", DataSourse);
        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StandardValue);
            String[][] str2 = XmlAnalysis.GetMatrixString(DataSourse.get(2)._StandardValue);
            if ((str1[1][1] != "" && str1[1][1] != null) && ( str1[2][1] != "" &&  str1[2][1] != null) && (str1[3][1] != "" &&str1[3][1] != null)&& (str2[1][1] != "" && str2[1][1] != null) && (str2[2][1] != "" && str2[2][1] != null) && (str2[3][1] != "" && str2[3][1] != null))
            {
                double h = Double.parseDouble(str1[1][1]);
                double d = Double.parseDouble( str1[2][1]);
                double D = Double.parseDouble(str1[3][1]);
                double Uh = Double.parseDouble(str2[1][1]);
                double Ud = Double.parseDouble(str2[2][1]);
                double UD = Double.parseDouble(str2[3][1]);
                double pd1 =  2*D / (D * D - d * d);
                double pd11 = pd1 * pd1;
                double pd2=   2*d/  (D * D - d * d);
                double pd22 = pd2 * pd2;
                double pd3 = 1 / h;
                double er = 100* Math.sqrt((pd11 * UD * UD) + (pd22 * Ud * Ud) + (pd3 * pd3 * Uh * Uh));
                String b = String.valueOf(er);
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
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(er), 3);
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
                        para._StandardValue = DataConversion.CutDataEffective(b1.toString(), 2);
                    }

                }
                else if (c >= 3)
                {
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(er), 2);
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
                        para._StandardValue = DataConversion.CutDataEffective(b1.toString(), 3);
                    }
                }
                else if (c == 0)//增加相对误差为0时，返回标准答案为空的问题
                {
                    para._StandardValue = "0";
                }
                else
                {
                    para._StandardValue = "";
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
        //   Log("result:" + para._StandardValue);
        return para;
    }

    /// <summary>
    ///计算体积的不确定度
    /// </summary>
    public static PhyParas TJBQDD(PhyParas para, List<PhyParas> DataSourse)
    {
        // Log("SJDW", DataSourse);
        try
        {
            String v = String.valueOf(DataSourse.get(1)._StandardValue);
            String er = String.valueOf(DataSourse.get(2)._StandardValue);
            if((v!=""&&v!=null)&& (er != "" && er != null))
            {
                double uv = Double.parseDouble(v) * Double.parseDouble(er)/100;
                String b = String.valueOf(uv);
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
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(uv), 2);
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
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(uv), 1);
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
                else if (c == 0)//增加相对误差为0时，返回标准答案为空的问题
                {
                    para._StandardValue = "0";
                }
                else
                {
                    para._StandardValue = "";
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
        // Log("result:" + para._StandardValue);
        return para;
    }


    /// <summary>
    ///圆柱体体积表达式
    /// </summary>
    public static PhyParas BDS1(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            if (!DataSourse.get(0)._StudentValue.toString().isEmpty() &&  !DataSourse.get(1)._StudentValue.toString().isEmpty())
            {
                String result = String.valueOf(DataSourse.get(1)._StudentValue);
                int num = DataConversion.GetEffectiveNumericalDigits(result);
                para._StandardValue = DataConversion.GetExpression_SSWR(DataSourse.get(0)._StudentValue.toString(), DataSourse.get(1)._StudentValue.toString(), num);
            }
          else   if (!DataSourse.get(0)._StandardValue.toString().isEmpty() &&  !DataSourse.get(1)._StandardValue.toString().isEmpty())
            {
                String result = String.valueOf(DataSourse.get(1)._StandardValue);
                int num = DataConversion.GetEffectiveNumericalDigits(result);
                para._StandardValue = DataConversion.GetExpression_SSWR(DataSourse.get(0)._StandardValue.toString(), DataSourse.get(1)._StandardValue.toString(), num);

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
    //用螺旋测微器测量铁丝直径，计算测量平均值和不确定度

    /// <summary>
    ///螺旋测微器零点读数
    /// </summary>
    public static PhyParas LXDS(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double ds = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            if (ds >= -0.030 & ds <= 0.030)
            {
                para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(ds), 3);
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
    ///选择题1
    /// </summary>
    public static PhyParas XZT1(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            para._StandardValue = "B";
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    /// <summary>
    ///选择题2
    /// </summary>
    public static PhyParas XZT2(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            para._StandardValue = "C";
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    /// <summary>
    ///数据表格4
    /// </summary>
    public static PhyParas SJBG4(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);

            for(int j = 1; j < 7; j++)
            {
                if (str[1][j] != "" && str[1][j] != null)
                {
                    double d = Double.parseDouble(str[1][j]);
                    if (d >= 4.170 && d <= 4.230)
                    {
                        str[1][j] = DataConversion.SingleDoubleOfEffective(str[1][j], 4);
                    }
                    else
                    {
                        str[1][j] = "";
                    }
                }
                    else
                {
                    str[1][j] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str, 2, 7);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    /// <summary>
    ///铁丝直径平均值
    /// </summary>
    public static PhyParas TSZJPJZ(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {

            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StandardValue);
            double resultd;
            double[] d = { 0, 0, 0, 0, 0, 0 };
            int nd = 0;
            for(int j = 1; j < 7; j++)
            {
                try
                {
                    if (str[1][j] != null && str[1][j] != "")
                    {
                        d[j - 1] = Double.parseDouble(str[1][j]);
                        nd++;
                    }
                        else
                    {
                        d[j - 1] = 0;
                    }
                }
                catch (Exception exception)
                {

                    d[j - 1] = 0;
                }
            }
            if (nd != 0)
            {
                resultd = (d[0] + d[1] + d[2] + d[3] + d[4] + d[5]) / nd;
                para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(resultd), 4);
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
    ///铁丝直径的不确定度
    /// </summary>
    public static PhyParas TSZJBQDD(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StandardValue);
            String aved1=String.valueOf(DataSourse.get(2)._StandardValue);
            double resultda1;
            double resultdb1;
            double resultd1;
            double[] d = { 0, 0, 0, 0, 0, 0 };
            int nd = 0;
            for(int j = 1; j < 7; j++)
            {
                try
                {
                    if ((str[1][j] != null && str[1][j] != "")&&(aved1!=null&&aved1!=""))
                    {
                        d[j - 1] = Double.parseDouble(str[1][j]) - Double.parseDouble(aved1);
                        nd++;
                    }
                        else
                    {
                        d[j - 1] = 0;
                    }
                }
                catch (Exception exception)
                {
                    d[j - 1] = 0;
                }
            }
            if (nd > 1)
            {
                resultda1 = Math.sqrt((d[0] * d[0] + d[1] * d[1] + d[2] * d[2] + d[3] * d[3] + d[4] * d[4] + d[5] * d[5]) / (nd * (nd - 1)));
                resultdb1 = 0.004 / (Math.sqrt(3));
                resultd1 = Math.sqrt(resultda1 * resultda1 + resultdb1 * resultdb1);
                String b = String.valueOf(resultd1);
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
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(resultd1), 2);
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
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(resultd1), 1);
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
                else if (c == 0)//增加相对误差为0时，返回标准答案为空的问题
                {
                    para._StandardValue = "0";
                }
                else
                {
                    para._StandardValue = "";
                }
            }
            else
            {
                para._StandardValue = "0.0023";
            }


        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    /// <summary>
    ///铁丝直径表达式
    /// </summary>
    public static PhyParas BDS2(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            if (!DataSourse.get(0)._StudentValue.toString().isEmpty() &&  !DataSourse.get(1)._StudentValue.toString().isEmpty())
            {
                String result = String.valueOf(DataSourse.get(1)._StudentValue);
                int num = DataConversion.GetEffectiveNumericalDigits(result);
                para._StandardValue = DataConversion.GetExpression_SSWR(DataSourse.get(0)._StudentValue.toString(), DataSourse.get(1)._StudentValue.toString(), num);
            }
            else  if (!DataSourse.get(0)._StandardValue.toString().isEmpty() &&  !DataSourse.get(1)._StandardValue.toString().isEmpty())
            {
                String result = String.valueOf(DataSourse.get(1)._StandardValue);
                int num = DataConversion.GetEffectiveNumericalDigits(result);
                para._StandardValue = DataConversion.GetExpression_SSWR(DataSourse.get(0)._StandardValue.toString(), DataSourse.get(1)._StandardValue.toString(), num);

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
    ///铁丝直径的相对不确定度
    /// </summary>
    public static PhyParas TSZJXDBQDD(PhyParas para, List<PhyParas> DataSourse)
    {
        // Log("BQDD_A", DataSourse);
        try
        {
            String ud = String.valueOf(DataSourse.get(1)._StandardValue);
            String d = String.valueOf(DataSourse.get(2)._StandardValue);
            double ud1 = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            double d1 = Double.parseDouble(DataSourse.get(2)._StandardValue.toString());
            if ((ud != "" && ud != null) && (d != "" && d != null))
            {
                double er = (ud1 / d1) * 100;
                String b = String.valueOf(er);
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
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(er), 3);
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
                        para._StandardValue = DataConversion.CutDataEffective(b1.toString(), 2);
                    }

                }
                else if (c >= 3)
                {
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(er), 2);
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
                        para._StandardValue = DataConversion.CutDataEffective(b1.toString(), 3);
                    }
                }
                else if (c == 0)//增加相对误差为0时，返回标准答案为空的问题
                {
                    para._StandardValue = "0";
                }
                else
                {
                    para._StandardValue = "";
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
        //Log("result:" + para._StandardValue);
        return para;
    }

   // 用读数显微镜测量金属丝的直径，计算平均直径和不确定度

    /// <summary>
    ///数据表格5
    /// </summary>
    public static PhyParas SJBG5(PhyParas para, List<PhyParas> DataSourse)
    {
        // Log("BQDD_A", DataSourse);
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);

            for(int j = 1; j < 7; j++)
            {
                if ((str[1][j] != "" && str[1][j] != null))
                {
                    str[1][j] = DataConversion.SingleDoubleOfEffective(str[1][j], 5);
                }
                    else
                {
                    str[1][j] = "";
                }

                if ((str[2][j] != "" && str[2][j] != null))
                {
                    str[2][j] = DataConversion.SingleDoubleOfEffective(str[2][j], 5);
                }
                    else
                {
                    str[2][j] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str, 3, 7);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        //Log("result:" + para._StandardValue);
        return para;
    }

    /// <summary>
    ///钢丝直径计算
    /// </summary>
    public static PhyParas SJBG6(PhyParas para, List<PhyParas> DataSourse)
    {
        // Log("LXCWJ_BQDD_B", DataSourse);
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            for (int j = 1; j < 7; j++)
            {
                if ((str[1][j] != "" && str[1][j] != null))
                {
                    double d = Double.parseDouble(str[1][j]);
                    if (d >= 0.650 && d <= 0.750)
                    {
                        str[1][j] = DataConversion.SingleDoubleOfEffective(str[1][j], 3);
                    }
                    else
                    {
                        str[1][j] = "";
                    }
                }
                    else
                {
                    str[1][j] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str, 2, 7);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        // Log("result:" + para._StandardValue);
        return para;
    }


    /// <summary>
    ///金属丝直径平均值
    /// </summary>

    public static PhyParas JSSZJPJZ(PhyParas para, List<PhyParas> DataSourse)
    {

        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StandardValue);
            double resultd;
            double[] d = { 0, 0, 0, 0, 0, 0 };
            int nd = 0;
            for (int j = 1; j < 7; j++)
            {
                try
                {
                    if (str[1][j] != null && str[1][j] != "")
                    {
                        d[j - 1] = Double.parseDouble(str[1][j]);
                        nd++;
                    }
                        else
                    {
                        d[j - 1] = 0;
                    }
                }
                catch (Exception exception)
                {

                    d[j - 1] = 0;
                }
            }
            if (nd != 0)
            {
                resultd = (d[0] + d[1] + d[2] + d[3] + d[4] + d[5]) / nd;
                para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(resultd), 3);
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
    /// 金属丝直径的A类不确定度
    /// </summary>
    public static PhyParas JSSABQDD(PhyParas para, List<PhyParas> DataSourse)
    {
        // Log("BDS", DataSourse);
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StandardValue);
            String aved = String.valueOf(DataSourse.get(2)._StandardValue);

            double resultda2;
            double[] d = { 0, 0, 0, 0, 0, 0 };
            int nd = 0;
            for(int j = 1; j < 7; j++)
            {
                try
                {
                    if ((str[1][j] != null && str[1][j] != "") && (aved != null && aved != ""))
                    {
                        d[j - 1] = Double.parseDouble(str[1][j]) - Double.parseDouble(aved);
                        nd++;
                    }
                        else
                    {
                        d[j - 1] = 0;
                    }
                }
                catch (Exception exception)
                {
                    d[j - 1] = 0;
                }
            }
            if (nd > 1)
            {
                resultda2 = Math.sqrt((d[0] * d[0] + d[1] * d[1] + d[2] * d[2] + d[3] * d[3] + d[4] * d[4] + d[5] * d[5]) / (nd * (nd - 1)));
                String b = String.valueOf(resultda2);
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
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(resultda2), 2);
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
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(resultda2), 1);
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
                else if (c == 0)//增加相对误差为0时，返回标准答案为空的问题
                {
                    para._StandardValue = "0";
                }
                else
                {
                    para._StandardValue = "";
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
        // Log("result:" + para._StandardValue);
        return para;
    }


    /// <summary>
    ///金属丝直径的不确定度
    /// </summary>
    public static PhyParas JSSZJBQDD(PhyParas para, List<PhyParas> DataSourse)
    {
        //Log("YBKC_PJZ1", DataSourse);
        try
        {
            double resultda = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            String resultda1 = String.valueOf(DataSourse.get(1)._StandardValue);
            double resultdb= 0.004 / (Math.sqrt(3));
            if (resultda1 != "" && resultda1 != null)
            {
                double resultd = Math.sqrt(resultda * resultda + resultdb * resultdb);
                String b = String.valueOf(resultd);
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
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(resultd), 2);
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
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(resultd), 1);
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
                else if (c == 0)//增加相对误差为0时，返回标准答案为空的问题
                {
                    para._StandardValue = "0";
                }
                else
                {
                    para._StandardValue = "";
                }
            }
            else
            {
                para._StandardValue = 0.0023;
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        //Log("result:" + para._StandardValue);
        return para;
    }

    /// <summary>
    ///金属丝直径的表达式
    /// </summary>
    public static PhyParas BDS3(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            if (!DataSourse.get(0)._StudentValue.toString().isEmpty() &&  !DataSourse.get(1)._StudentValue.toString().isEmpty())
            {
                String result = String.valueOf(DataSourse.get(1)._StudentValue);
                int num = DataConversion.GetEffectiveNumericalDigits(result);
                para._StandardValue = DataConversion.GetExpression_SSWR(DataSourse.get(0)._StudentValue.toString(), DataSourse.get(1)._StudentValue.toString(), num);
            }
            else if (!DataSourse.get(0)._StandardValue.toString().isEmpty() && !DataSourse.get(1)._StandardValue.toString().isEmpty())
            {
                String result = String.valueOf(DataSourse.get(1)._StandardValue);
                int num = DataConversion.GetEffectiveNumericalDigits(result);
                para._StandardValue = DataConversion.GetExpression_SSWR(DataSourse.get(0)._StandardValue.toString(), DataSourse.get(1)._StandardValue.toString(), num);

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
    ///金属丝直径的相对不确定度
    /// </summary>
    public static PhyParas JSSZJXDBQDD(PhyParas para, List<PhyParas> DataSourse)
    {
        //Log("YBKC_PJZ3", DataSourse);
        try
        {
            double ud = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            String ud1 = String.valueOf(DataSourse.get(1)._StandardValue.toString());
            double d = Double.parseDouble(DataSourse.get(2)._StandardValue.toString());
            String d1 = String.valueOf(DataSourse.get(2)._StandardValue.toString());
            if ((ud1 != "" && ud1 != null) && (d1 != "" && d1 != null))
            {
                double er = 100*ud / d;
                String b = String.valueOf(er);
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
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(er), 3);
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
                        para._StandardValue = DataConversion.CutDataEffective(b1.toString(), 2);
                    }

                }
                else if (c >= 3)
                {
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(er), 2);
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
                        para._StandardValue = DataConversion.CutDataEffective(b1.toString(), 3);
                    }
                }
                else if (c == 0)//增加相对误差为0时，返回标准答案为空的问题
                {
                    para._StandardValue = "0";
                }
                else
                {
                    para._StandardValue = "";
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
        //Log("result:" + para._StandardValue);
        return para;
    }
}
