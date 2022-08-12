import common.PhyParas;
import common.XmlAnalysis;

import java.util.List;

/**
 * @author Administrator
 */
public class Function {
 // 调用计算方法

    /// <summary>
    /// 平衡电压的平均值
    /// </summary>
    public static PhyParas DianQiao1(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);
            int rownum = 4;//表格总列数
            int culmnum = 6;//表格总行数

            if (str.length > 1)
            {
                for (int i = 1; i < rownum; i++)
                {
                    try
                    {
                        if (str[1][i]!= "" && ( Double.parseDouble(str[1][i]) >= 0.1 &&  Double.parseDouble(str[1][i]) <= 99999.9))
                        {
                            int xiaosd = IsLingOrYi(str[1][i]);
                            str[1][i] = DataConversion.SingleDoubleOfDecimal(str[1][i], xiaosd);
                        }
                            else
                        {
                            str[1][i] = "";
                        }
                        if (str[2][i] != "" && ( Double.parseDouble(str[2][i]) >= 0.1 &&  Double.parseDouble(str[2][i]) <= 99999.9))
                        {
                            int xiaosd = IsLingOrYi(str[2][i]);
                            str[2][i] = DataConversion.SingleDoubleOfDecimal(str[2][i], xiaosd);
                        }
                            else
                        {
                            str[2][i] = "";
                        }
                        if (str[3][i] != "" && ( Double.parseDouble(str[3][i]) >= 100 &&  Double.parseDouble(str[3][i]) <= 999.9))
                        {
                            int xiaosd = IsLingOrYi(str[3][i]);
                            str[3][i] = DataConversion.SingleDoubleOfDecimal(str[3][i], xiaosd);
                        }
                            else
                        {
                            str[3][i] = "";
                        }
                        if (str[4][i] != "" && ( Double.parseDouble(str[4][i]) >= 100 &&  Double.parseDouble(str[4][i]) <= 999.9))
                        {
                            int xiaosd = IsLingOrYi(str[4][i]);
                            str[4][i] = DataConversion.SingleDoubleOfDecimal(str[4][i], xiaosd);
                        }
                            else
                        {
                            str[4][i] = "";
                        }
                        if (str[3][i] != "" && str[4][i] != "")
                        {
                            str[5][i] = DataConversion.SingleDoubleOfDecimal(String.valueOf(Math.sqrt( Double.parseDouble(str[3][i]) *  Double.parseDouble(str[4][i]))), 1);
                        }
                            else
                        {
                            str[5][i] = "";
                        }
                    }
                    catch (Exception exception)
                    {
                    }
                }
                para._StandardValue = XmlAnalysis.SetMatrixString(str, culmnum, rownum);

            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    /// <summary>
    /// 平衡电压不确定度
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas DianQiao2(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);

            int rownum = 4;//表格总列数
            int rowhead = 1;//表的列头占用列数
            int dsnum = 5;//要用到的数据所在行数

            if (str.length > 1)
            {
                double sum = 0.0;
                double xi = 0.0;
                int num = rownum - rowhead;
                for (int i = rowhead; i < rownum; i++)
                {
                    try
                    {
                        xi =  Double.parseDouble(str[dsnum][i]);
                    }
                    catch (Exception exception)
                    {
                        xi = 0.0;
                        num = num - 1;
                    }

                    sum = sum + xi;
                }

                if (num > 0)
                {
                    sum = sum / num;
                    para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(sum), 1);
                }
                else
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

    /// <summary>
    /// 自由落体下落时间
    /// </summary>
    public static PhyParas DianQiao3(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);

            int rownum = 4;//表格总列数
            int culmnum = 4;//表格总行数

            if (str.length > 1)
            {
                for (int i = 1; i < rownum; i++)
                {
                    try
                    {
                        if (str[1][i] != "" && ( Double.parseDouble(str[1][i]) >= 100 &&  Double.parseDouble(str[1][i]) <= 999.9))
                        {
                            int xiaosd = IsLingOrYi(str[1][i]);
                            str[1][i] = DataConversion.SingleDoubleOfDecimal(str[1][i], xiaosd);
                        }
                            else
                        {
                            str[1][i] = "";
                        }
                        if (str[2][i] != "" && ( Double.parseDouble(str[2][i]) >= 0.1 &&  Double.parseDouble(str[2][i]) <= 100))
                        {
                            int xiaosd = IsLingOrYi(str[2][i]);
                            str[2][i] = DataConversion.SingleDoubleOfDecimal(str[2][i], xiaosd);
                        }
                            else
                        {
                            str[2][i] = "";
                        }
                        if (str[3][i] != "" && ( Double.parseDouble(str[3][i]) >= 0 &&  Double.parseDouble(str[3][i]) <= 15))
                        {
                            str[3][i] = DataConversion.SingleDoubleOfDecimal(str[3][i], 0);
                        }
                            else
                        {
                            str[3][i] = "";
                        }
                    }
                    catch (Exception exception)
                    {
                    }
                }
                para._StandardValue = XmlAnalysis.SetMatrixString(str, culmnum, rownum);

            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    /// <summary>
    /// 时间不确定度
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas DianQiao4(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            int rownum = 4;//表格总列数
            int rowhead = 1;//表的列头占用列数
            if (str.length > 1)
            {
                double sum = 0.0;
                double xi = 0.0;
                double xi2 = 0.0;
                double xi3 = 0.0;
                int num = rownum - rowhead;
                for (int i = rowhead; i < rownum; i++)
                {
                    try
                    {
                        xi =  Double.parseDouble(str[1][i]);
                        xi2 =  Double.parseDouble(str[2][i]);
                        xi3 =  Double.parseDouble(str[3][i]);
                    }
                    catch (Exception exception)
                    {
                        num = num - 1;
                        continue;
                    }
                    if (xi2 != 0)
                    {
                        sum = sum + xi3 * xi / xi2;
                    }
                }
                if (num > 0)
                {
                    sum = sum / num;
                    para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(sum), 1);
                }
                else
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

    /// <summary>
    /// 第一个油滴的半径
    /// </summary>
    public static PhyParas DianQiao5(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StudentValue);

            int rownum = 7;//表格总列数
            int culmnum = 5;//表格总行数
            int rowhead = 1;//表的列头占用列数
            if (str.length > 1)
            {
                double sum = 0.0;
                double xi = 0.0;
                double xi2 = 0.0;
                double xi3 = 0.0;
                int num = rownum - rowhead;
                for (int i = 1; i < rownum; i++)
                {
                    try
                    {
                        if (str[1][i] != "" && ( Double.parseDouble(str[1][i]) >= 100 &&  Double.parseDouble(str[1][i]) <= 999.9))
                        {
                            int xiaosd = IsLingOrYi(str[1][i]);
                            str[1][i] = DataConversion.SingleDoubleOfDecimal(str[1][i], xiaosd);
                        }
                            else
                        {
                            str[1][i] = "";
                        }
                        if (str[2][i] != "" && ( Double.parseDouble(str[2][i]) >= 0.1 &&  Double.parseDouble(str[2][i]) <= 100))
                        {
                            int xiaosd = IsLingOrYi(str[2][i]);
                            str[2][i] = DataConversion.SingleDoubleOfDecimal(str[2][i], xiaosd);
                        }
                            else
                        {
                            str[2][i] = "";
                        }
                        if (str[3][i] != "" && ( Double.parseDouble(str[3][i]) >= 0 &&  Double.parseDouble(str[3][i]) <= 15))
                        {
                            str[3][i] = DataConversion.SingleDoubleOfDecimal(str[3][i], 0);
                        }
                            else
                        {
                            str[3][i] = "";
                        }
                    }
                    catch (Exception exception)
                    {
                    }
                    try
                    {
                        xi =  Double.parseDouble(str[1][i]);
                        xi2 =  Double.parseDouble(str[2][i]);
                        xi3 =  Double.parseDouble(str[3][i]);
                    }
                    catch (Exception exception)
                    {
                        str[4][i] = "";
                        continue;
                    }
                    if (xi2 != 0)
                    {
                        str[4][i] = DataConversion.SingleDoubleOfDecimal(String.valueOf((xi3 * xi / xi2)),1);
                    }
                }
                para._StandardValue = XmlAnalysis.SetMatrixString(str, culmnum, rownum);

            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

        
    static int IsLingOrYi(String shuju)
    {
        int xiaoshudian = 0;
        String[] str = shuju.split(".");
        if (str[1].length() > 0)
        {
            xiaoshudian = 1;
        }
        else
        {
            xiaoshudian = 0;
        }
        return xiaoshudian;
    }
}
