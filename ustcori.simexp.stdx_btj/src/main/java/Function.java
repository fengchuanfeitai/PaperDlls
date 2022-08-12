import common.PhyParas;
import common.XmlAnalysis;

import java.util.List;

/**
 * @author Administrator
 */
public class Function {

    //实验中所用透镜的焦距的标准值
    public static PhyParas BTJlist2(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double B = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            para._StandardValue =String.valueOf(B);
        }
        catch (Exception e)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //（1）二次成像法测薄凸透镜焦距的数据表格
    public static PhyParas BTJlist3(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentValue = "";
        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            //表格总行数
            int rownum = 5;
            //表格总列数
            int culmnum = 4;
            for (int i = 1; i < 5; i++)
            {
                for (int j = 1; j < 4; j++)
                {
                    try
                    {
                        double a = Double.parseDouble(str1[i][j]);
                        str1[i][j] = DataConversion.CutDataDecimal(String.valueOf(a), 2);
                    }
                    catch (Exception e)
                    {
                        str1[i][j] = "";
                    }
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
    //(2)对表格1进行计算，将结果填入到下面表格中
    public static PhyParas BTJlist4(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentValue = "";
        try
        {
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            String[][] str2 = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StudentValue);
            //表格总行数
            int rownum = 4;
            //表格总列数
            int culmnum = 4;
            for (int i = 1; i < 4; i++)
            {
                for (int j = 1; j < 4; j++)
                {
                    if (i == 1) {
                        try
                        {
                            double a = Double.parseDouble(str1[1][j]);
                            double b = Double.parseDouble(str1[2][j]);
                            double c = Math.abs(a - b);
                            str2[1][j] =String.valueOf(c);
                        }
                        catch (Exception e)
                        {
                            str2[1][j] = "";
                        }
                    }
                    if (i == 2) {
                        try
                        {
                            double q = Double.parseDouble(str1[3][j]);
                            double w = Double.parseDouble(str1[4][j]);
                            double e = Math.abs(q - w);
                            str2[2][j] =String.valueOf(e);
                        }
                        catch (Exception e)
                        {
                            str2[2][j] = "";
                        }
                    }
                    if (i == 3) {
                        try
                        {
                            double z = Double.parseDouble(str2[1][j]);
                            double x = Double.parseDouble(str2[2][j]);
                            double v = (Math.pow(z, 2) - Math.pow(x, 2)) / (4 * z);
                            str2[3][j] = DataConversion.CutDataDecimal(String.valueOf(v), 2); ;
                        }
                        catch (Exception e)
                        {
                            str2[3][j] = "";
                        }
                    }
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str2, rownum, culmnum);
        }
        catch (Exception e)
        {
            para._StandardValue = "";
        }
        return para;

    }
    // 平均焦距
    public static PhyParas BTJlist5(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            String[][] str2 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            double C = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());
            double d = 0.0;
            double sum = 0.0;
            int n = 0;
            for (int j = 1; j < 4; j++)
            {
                if (str2[3][j] != "" && str2[3][j] != null)
                {
                    d = Double.parseDouble(str2[3][j]);
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
                para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(sum), 2);
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
    //百分比误差
    public static PhyParas BTJlist6(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double B = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            double C = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());
            double p = Double.parseDouble(DataSourse.get(2)._StudentValue.toString());
            double D = Math.abs((C - B) / B) * 100;
            para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(D), 2) + ";" +"0";
        }
        catch (Exception e)
        {
            double B = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            para._StandardValue = "" + ";" + "0";
        }
        return para;
    }
    // 实验中所用透镜的编号
    //public static PhyParas BTJlist7(PhyParas para, List<PhyParas> DataSourse)
    //{
    //    //para._StudentdValue = "";
    //    try
    //    {
    //        double A = Double.parseDouble(DataSourse.get(0)._StudentValue);
    //        para._StandardValue =String.valueOf(A);
    //    }
    //    catch (Exception e)
    //    {
    //        para._StandardValue = "";
    //    }
    //    return para;
    //}
    //实验中所用透镜的焦距的标准值
    public static PhyParas BTJlist8(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double B = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            para._StandardValue = String.valueOf(B);
        }
        catch (Exception e)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //（1）自准直法法测薄凸透镜焦距的数据表格
    public static PhyParas BTJlist9(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentValue = "";
        try
        {
            String[][] str3 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            //表格总行数
            int rownum = 4;
            //表格总列数
            int culmnum = 4;
            for (int i = 1; i < 4; i++)
            {
                for (int j = 1; j < 4; j++)
                {
                    if (i == 1) {
                        try
                        {
                            double a = Double.parseDouble(str3[1][j]);
                            str3[1][j] = DataConversion.CutDataDecimal(String.valueOf(a), 2);
                        }
                        catch (Exception e)
                        {
                            str3[1][j] = "";
                        }
                    }
                    if (i == 2) {
                        try
                        {
                            double b = Double.parseDouble(str3[2][j]);
                            str3[2][j] = DataConversion.CutDataDecimal(String.valueOf(b), 2);
                        }
                        catch (Exception e)
                        {
                            str3[2][j] = "";
                        }
                    }
                    if (i == 3) {
                        try
                        {
                            double a = Double.parseDouble(str3[1][j]);
                            double b = Double.parseDouble(str3[2][j]);
                            double c = Math.abs(a - b);

                            str3[3][j] = DataConversion.CutDataDecimal(String.valueOf(c), 2);
                        }
                        catch (Exception e)
                        {
                            str3[3][j] = "";
                        }
                    }
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str3, rownum, culmnum);
        }
        catch (Exception e)
        {
            para._StandardValue = "";
        }
        return para;

    }
    // 平均焦距
    public static PhyParas BTJlist10(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            String[][] str3 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue.toString());
            double C = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());
            double d = 0.0;
            double sum = 0.0;
            int n = 0;
            for (int j = 1; j < 4; j++)
            {
                if (str3[3][j] != "" && str3[3][j] != null)
                {
                    d = Double.parseDouble(str3[3][j]);
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
                para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(sum), 2);
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

    //百分比误差
    public static PhyParas BTJlist11(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double B = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            double C = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());
            double u = Double.parseDouble(DataSourse.get(2)._StudentValue.toString());
            double D =Math.abs( (C - B) / B)*100;
            para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(D), 2) + ";" + "0";
        }
        catch (Exception e)
        {
            double B = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            para._StandardValue = "" + ";" + "0";
        }
        return para;
    }

    // 实验中所用透镜的编号
    //public static PhyParas BTJlist12(PhyParas para, List<PhyParas> DataSourse)
    //{
    //    //para._StudentdValue = "";
    //    try
    //    {
    //        double A = Double.parseDouble(DataSourse.get(0)._StudentValue);
    //        para._StandardValue =String.valueOf(A);
    //    }
    //    catch (Exception e)
    //    {
    //        para._StandardValue = "";
    //    }
    //    return para;
    //}
    //实验中所用透镜的焦距的标准值
    public static PhyParas BTJlist13(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double B = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            para._StandardValue =String.valueOf(B);
        }
        catch (Exception e)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //（1）辅助透镜成像法测薄凹透镜焦距的数据表格
    public static PhyParas BTJlist14(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentValue = "";
        try
        {
            String[][] str4 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            //表格总行数
            int rownum = 4;
            //表格总列数
            int culmnum = 4;
            for (int i = 1; i < 4; i++)
            {
                for (int j = 1; j < 4; j++)
                {
                    try
                    {
                        double a = Double.parseDouble(str4[i][j]);
                        str4[i][j] = DataConversion.CutDataDecimal(String.valueOf(a), 2);
                    }
                    catch (Exception e)
                    {
                        str4[i][j] = "";
                    }
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str4, rownum, culmnum);
        }
        catch (Exception e)
        {
            para._StandardValue = "";
        }
        return para;

    }
    //(2)对表格1进行计算，将结果填入到下面表格中
    public static PhyParas BTJlist15(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentValue = "";
        try
        {
            String[][] str4 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue.toString());
            String[][] str5 = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StudentValue.toString());
            //表格总行数
            int rownum = 4;
            //表格总列数
            int culmnum = 4;
            for (int i = 1; i < 4; i++)
            {
                for (int j = 1; j < 4; j++)
                {
                    if (i == 1) {
                        try
                        {
                            double a = Double.parseDouble(str4[1][j]);
                            double b = Double.parseDouble(str4[2][j]);
                            double c = Math.abs(a - b);
                            str5[1][j] = DataConversion.CutDataDecimal(String.valueOf(c), 2);
                        }
                        catch (Exception e)
                        {
                            str5[1][j] = "";
                        }
                    }
                    if (i == 2) {
                        try
                        {
                            double a = Double.parseDouble(str4[1][j]);
                            double w = Double.parseDouble(str4[3][j]);
                            double e = Math.abs(a - w);
                            str5[2][j] = DataConversion.CutDataDecimal(String.valueOf(e), 2);
                        }
                        catch (Exception e)
                        {
                            str5[2][j] = "";
                        }
                    }
                    if (i == 3) {
                        try
                        {
                            double z = Double.parseDouble(str5[1][j]);
                            double x = Double.parseDouble(str5[2][j]);
                            double v = (z * x) / (z - x);
                            str5[3][j] = DataConversion.CutDataDecimal(String.valueOf(v), 2);
                        }
                        catch (Exception e)
                        {
                            str5[3][j] = "";
                        }
                    }
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str5, rownum, culmnum);
        }
        catch (Exception e)
        {
            para._StandardValue = "";
        }
        return para;

    }
    // 平均焦距
    public static PhyParas BTJlist16(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            String[][] str5 = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            double C = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());
            double d = 0.0;
            double sum = 0.0;
            int n = 0;
            for (int j = 1; j < 4; j++)
            {
                if (str5[3][j] != "" && str5[3][j] != null)
                {
                    d = Double.parseDouble(str5[3][j]);
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
                para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(sum), 2);
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

    //百分比误差
    public static PhyParas BTJlist17(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double B = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            double C = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());
            double k = Double.parseDouble(DataSourse.get(2)._StudentValue.toString());
            double D = Math.abs((C - B) / B)*100;
            para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(D), 2) + ";" + "0";
        }
        catch (Exception e)
        {
            double B = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            para._StandardValue = "" + ";" + "0";
        }
        return para;
    }

}
