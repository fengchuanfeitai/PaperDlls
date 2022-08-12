import common.PhyParas;
import common.XmlAnalysis;

import java.util.List;

/**
 * @author Administrator
 */
public class Function {
 // 调用计算方法

        // 氦氖激光波长  相关计算
    /// <summary>
    /// 逐差法处理数据，间隔为250环相减
    /// </summary>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas GetHNJGZCFList(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] result = XmlAnalysis.GetMatrixString(para._StandardValue);
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);//获取原始数据表格

            int rownum = 10;//表格总列数
            int dsnum = rownum / 2;//逐差间隔

            for (int i = 1; i <= dsnum; i++)
            {
                try
                {
                    double number = Math.abs( Double.parseDouble(str[1][i]) -  Double.parseDouble(str[1][i + dsnum]));
                    result[1][i] = DataConversion.CutDataEffective(String.valueOf(number), 4);
                }
                catch (Exception exception)
                {
                    result[1][i] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(result, result[0].length, result[1].length);
        }
        catch (Exception exception )
        {
            para._StandardValue = "";
        }
        return para;
    }

    /// <summary>
    /// 计算平均值:X=(X1+X2+...+X5)/5
    /// </summary>
    /// <param name="DataSourse">实际测量结果数据表格</param>
    /// <returns></returns>
    public static PhyParas GetXiPJ(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);//获取数据源
            int dsnum = 1;//测量值所在行数

            int num = str[1].length - 1;
            double sum = 0.0;
            double xi = 0.0;
            boolean BadData = false;//是否存在不良数据
            if (str.length > 1)
            {
                for (int i = 1; i <str[1].length; i++)
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
            }
            else
            {
                BadData = true;//存在不良数据时，计算结果为无效数据
                para._StandardValue = "";
            }

            if (BadData == false)//无不良数据
            {
                if (num > 0)
                {
                    double number = sum / num;
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(number), 4);//有效数据个数不为0
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

        return para;
    }

    /// <summary>
    /// 计算氦氖激光波长:X=(X1+X2+...+X5)/5
    /// </summary>
    /// <param name="DataSourse">实际测量结果数据表格</param>
    /// <returns>氦氖激光波长</returns>
    public static PhyParas GetHeNeJGBC(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double x =  Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double n = 5.0 * 50.0;
            double number = 2 * x / n;//单位：mm
            para._StandardValue = DataConversion.CutDataEffective(String.valueOf(number * Math.pow(10, 6)), 4);//单位：nm
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    /// <summary>
    /// 氦氖激光波长不确定度
    /// </summary>
    /// <param name="DataSourse"></param>
    /// <returns>氦氖激光波长不确定度</returns>
    public static PhyParas GetHeNeJGBEU(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double ux =  Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double n = 5.0 * 50.0;
            double number = 2 * ux / n;//单位：mm
            para._StandardValue = DataConversion.ToUncertainty(String.valueOf(number * Math.pow(10, 6)), 4);//单位：nm
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    /// <summary>
    /// 计算氦氖激光波长 的相对误差  Er
    /// </summary>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas GetHeNeBCEr(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double HeNe = 632.8;//波长标准值nm
            double Xp = 0.0;
            try
            {
                Xp =  Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
                double number = Math.abs((Xp - HeNe) / HeNe) * 100.0;
                para._StandardValue = DataConversion.CutDataEffective(String.valueOf(number), 2);
            }
            catch (Exception exception)
            {
                //无法计算出平均值，则平均值不确定度直接返回空字符串
                para._StandardValue = "";
                return para;
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    /// <summary>
    /// 氦氖激光 标准差计算
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas HeNeStandardDeviation(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);

            double Xp = 0.0;
            try
            {
                Xp =  Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            }
            catch (Exception exception)
            {
                //无法计算出平均值，则平均值不确定度直接返回空字符串
                para._StandardValue = "";
                return para;
            }

            int dsnum = 1;//要用到的数据所在行数

            if (str.length > 1)
            {
                double sum = 0.0;
                double xi = 0.0;
                int num = 5;
                for (int i = 1; i <= num; i++)
                {
                    try
                    {
                        xi =  Double.parseDouble(str[dsnum][i]) - Xp;
                    }
                    catch (Exception exception)
                    {
                        xi = 0.0;
                        num = num - 1;
                    }

                    sum = sum + xi * xi;
                }

                if (num > 1)
                {
                    double number = Math.sqrt(sum / (num - 1));
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(number), 2);
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
    /// 氦氖激光 A类不确定度计算
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas HeNeUncertainty_A(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double Xp = 0.0;
            //如果标准差为空，直接将A类不确定度返回空
            try
            {
                Xp =  Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            }
            catch (Exception exception)
            {
                para._StandardValue = "";
                return para;
            }

            double tp = 2.78;
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StandardValue);
            int dsnum = 1;//要用到的数据所在行数

            //为了确定学生填写了几个数据的异常处理
            if (str.length > 1)
            {
                double xi = 0.0;
                int num = 5;
                for (int i = 1; i <= num; i++)
                {
                    try
                    {
                        xi =  Double.parseDouble(str[dsnum][i]);
                    }
                    catch (Exception exception)
                    {
                        num = num - 1;
                    }
                }

                if (num > 1)
                {
                    double number = tp * Xp / Math.sqrt(num);
                    para._StandardValue = DataConversion.ToUncertainty(String.valueOf(number), 2);
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
        catch (Exception  e)
        {
            para._StandardValue = "";
        }
        return para;
    }



        // 钠黄光波长 相关计算
    /// <summary>
    /// 逐差法处理数据，取间隔为6相减得到150道条纹间隔
    /// </summary>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas GetNHGZCFList(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] result = XmlAnalysis.GetMatrixString(para._StandardValue);
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);

            int rownum = 12;//表格总列数
            int dsnum = rownum / 2;//逐差间隔

            for (int i = 1; i <= dsnum; i++)
            {
                try
                {
                    double num = Math.abs( Double.parseDouble(str[1][i + dsnum]) -  Double.parseDouble(str[1][i]));
                    result[1][i] = DataConversion.CutDataEffective(String.valueOf(num), 4);
                }
                catch (Exception exception)
                {
                    result[1][i] = "";
                }
            }

            para._StandardValue = XmlAnalysis.SetMatrixString(result, result[0].length, result[1].length);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    /// <summary>
    /// 150道条纹间隔平均值
    /// </summary>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas GetNHGHp(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);

            int dsnum = 1;//测量值所在行数

            int num = str[1].length - 1;
            double sum = 0.0;
            double xi = 0.0;
            boolean BadData = false;//是否存在不良数据
            if (str.length > 1)
            {
                for (int i = 1; i < str[1].length; i++)
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
            }
            else
            {
                BadData = true;//存在不良数据时，计算结果为无效数据
                para._StandardValue = "";
            }

            if (BadData == false)//无不良数据
            {
                if (num > 0)
                {
                    double number = sum / num;
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(number), 4);//有效数据个数不为0
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

        return para;
    }

    /// <summary>
    /// 钠黄光波长
    /// </summary>
    /// <param name="DataSourse"></param>
    /// <returns>钠黄光波长</returns>
    public static PhyParas GetNHGBC(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double x =  Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double n = 150.0;
            double number = 2 * x / n;//单位：mm
            para._StandardValue = DataConversion.CutDataEffective(String.valueOf(Math.pow(10, 6) * number), 4);//单位：nm
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    /// <summary>
    /// 钠黄光波长不确定度
    /// </summary>
    /// <param name="DataSourse"></param>
    /// <returns>钠黄光波长不确定度</returns>
    public static PhyParas GetNHGBCU(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double ux =  Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double n = 150.0;
            double number = 2 * ux / n;//单位：mm
            para._StandardValue = DataConversion.ToUncertainty(String.valueOf(Math.pow(10, 6) * number), 4);//单位：nm
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    /// <summary>
    /// 计算钠黄光波长的相对误差Er
    /// </summary>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas GetNHGBCEr(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double HeNe = 589.3;//波长标准值
            double Xp = 0.0;
            try
            {
                Xp =  Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
                double number = Math.abs((Xp - HeNe) / HeNe) * 100.0;
                para._StandardValue = DataConversion.CutDataEffective(String.valueOf(number), 2);
            }
            catch (Exception exception)
            {
                //无法计算出平均值，则平均值不确定度直接返回空字符串
                para._StandardValue = "";
                return para;
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    /// <summary>
    /// Na黄光波长 标准差计算
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas NaYellowStandardDeviation(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);

            double Xp = 0.0;
            try
            {
                Xp =  Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            }
            catch (Exception exception)
            {
                //无法计算出平均值，则平均值不确定度直接返回空字符串
                para._StandardValue = "";
                return para;
            }

            int dsnum = 1;//要用到的数据所在行数

            if (str.length > 1)
            {
                double sum = 0.0;
                double xi = 0.0;
                int num = 6;
                for (int i = 1; i <= num; i++)
                {
                    try
                    {
                        xi =  Double.parseDouble(str[dsnum][i]) - Xp;
                    }
                    catch (Exception exception)
                    {
                        xi = 0.0;
                        num = num - 1;
                    }

                    sum = sum + xi * xi;
                }

                if (num > 1)
                {
                    double number = Math.sqrt(sum / (num - 1));
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(number), 2);
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
    /// Na黄光波长 A类不确定度计算
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas NaYellowUncertainty_A(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double Xp = 0.0;
            //如果标准差为空，直接将A类不确定度返回空
            try
            {
                Xp =  Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            }
            catch (Exception exception)
            {
                para._StandardValue = "";
                return para;
            }

            double tp = 2.57;
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StandardValue);
            int dsnum = 1;//要用到的数据所在行数

            //为了确定学生填写了几个数据的异常处理
            if (str.length > 1)
            {
                double xi = 0.0;
                int num = 6;
                for (int i = 1; i <= num; i++)
                {
                    try
                    {
                        xi =  Double.parseDouble(str[dsnum][i]);
                    }
                    catch (Exception exception)
                    {
                        num = num - 1;
                    }
                }

                if (num > 1)
                {
                    double number = tp * Xp / Math.sqrt(num);
                    para._StandardValue = DataConversion.ToUncertainty(String.valueOf(number), 2);
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
        catch (Exception  e)
        {
            para._StandardValue = "";
        }
        return para;
    }



        // 钠光双线波长差 相关计算
    /// <summary>
    /// 钠光双线的波长差 表格逐差法处理
    /// </summary>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas GetNGSXdList(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] result = XmlAnalysis.GetMatrixString(para._StandardValue);
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);

            int rownum = 6;//表格总列数
            int dsnum = rownum / 2;//逐差间隔

            for (int i = 1; i <= dsnum; i++)
            {
                try
                {
                    result[1][i] = String.format("%.5f",Math.abs( Double.parseDouble(str[1][i + dsnum]) -  Double.parseDouble(str[1][i])));
                }
                catch (Exception exception)
                {
                    result[1][i] = "";
                }
            }

            para._StandardValue = XmlAnalysis.SetMatrixString(result, result[0].length, result[1].length);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    /// <summary>
    /// 光程差变化ΔL
    /// </summary>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas GetNGSXGCCL(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);

            int rownum = 3;//表格总列数
            int UseNum = rownum;
            double sum = 0.0;
            double xi = 0.0;
            for (int i = 1; i <= rownum; i++)
            {
                try
                {
                    xi =  Double.parseDouble(str[1][i]);
                }
                catch (Exception exception)
                {
                    UseNum = UseNum - 1;
                    xi = 0.0;
                }
                sum = sum + xi;
            }
            if (UseNum > 0)
            {
                double number = 2.0 / 3.0 * (sum / UseNum);
                para._StandardValue = DataConversion.CutDataEffective(String.valueOf(number), 4);
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
    /// 钠光双线的波长差△λ
    /// </summary>
    /// <param name="DataSourse">Xp</param>
    /// <returns>钠光双线的波长差△λ</returns>
    public static PhyParas GetNGSXBCClmd(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double bzbc = 589.3;//单位：nm
            double Xp = 0.0;
            try
            {
                Xp =  Double.parseDouble(DataSourse.get(0)._StandardValue.toString());//单位：mm
                if (Xp > 0)
                {
                    double number = bzbc * bzbc / (Xp * Math.pow(10, 6));
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(number), 4);
                }
                else
                {
                    para._StandardValue = "";
                }
            }
            catch (Exception exception)
            {
                //无法计算出平均值，则波长直接返回空字符串
                para._StandardValue = "";
                return para;
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }

        return para;
    }

    /// <summary>
    /// 根据不确定度的传递公式，那么有UΔλ=(Δλ*UΔL)/ΔL
    /// </summary>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas GetNGSXUbc(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double l =  Double.parseDouble(DataSourse.get(0)._StandardValue.toString());//光程差 △L
            double ul =  Double.parseDouble(DataSourse.get(1)._StandardValue.toString());//光程差不确定度 U△L
            double r =  Double.parseDouble(DataSourse.get(2)._StandardValue.toString());//钠光双线的波长差△λ

            if (l > 0)
            {
                double number = r * ul / l;
                para._StandardValue = DataConversion.ToUncertainty(String.valueOf(number), 2);
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
    /// 计算钠光双线的波长差 相对误差 Er
    /// </summary>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas GetNGSXbcEr(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double Na = 0.597;
            double Xp = 0.0;
            try
            {
                Xp =  Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
                double number = Math.abs((Xp - Na) / Na) * 100.0;
                para._StandardValue = DataConversion.CutDataEffective(String.valueOf(number), 2);
            }
            catch (Exception exception)
            {
                //无法计算出平均值，则平均值不确定度直接返回空字符串
                para._StandardValue = "";
                return para;
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

        // 光程差 不确定度计算
    /// <summary>
    /// 光程差 标准差计算
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas NaDoubleStandardDeviation(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);

            double averageD = 0.0;
            int dsnum = 1;//要用到的数据所在行数

            if (str.length > 1)
            {
                double sum = 0.0;
                double xi = 0.0;
                int num = 3;
                    // 求平均值
                for (int i = 1; i <= num; i++)
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
                    averageD = sum / num;
                }
                else
                {
                    //如果无法求出 △d 的平均值，标准差的标准答案直接返回空
                    para._StandardValue = "";
                    return para;
                }


                    xi = 0.0;
                num = 3;
                sum = 0.0;
                for (int i = 1; i <= num; i++)
                {
                    try
                    {
                        xi =  Double.parseDouble(str[dsnum][i]) - averageD;
                    }
                    catch (Exception exception)
                    {
                        xi = 0.0;
                        num = num - 1;
                    }
                    sum = sum + xi * xi;
                }

                if (num > 1)
                {
                    double number = Math.sqrt(sum / (num - 1));
                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(number), 2);
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
        return para;
    }

    /// <summary>
    /// 光程差 A类不确定度计算
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas NaDoubleUncertainty_A(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double Xp = 0.0;
            //如果标准差为空，直接将A类不确定度返回空
            try
            {
                Xp =  Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            }
            catch (Exception exception)
            {
                para._StandardValue = "";
                return para;
            }

            double tp = 4.30;
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StandardValue);
            int dsnum = 1;//要用到的数据所在行数

            //为了确定学生填写了几个数据的异常处理
            if (str.length > 1)
            {
                double xi = 0.0;
                int num = 3;
                for (int i = 1; i <= num; i++)
                {
                    try
                    {
                        xi =  Double.parseDouble(str[dsnum][i]);
                    }
                    catch (Exception exception)
                    {
                        num = num - 1;
                    }
                }

                if (num > 1)
                {
                    double number = tp * Xp / Math.sqrt(num);
                    para._StandardValue = DataConversion.ToUncertainty(String.valueOf(number), 2);
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
        catch (Exception  e)
        {
            para._StandardValue = "";
        }
        return para;
    }

    /// <summary>
    /// 光程差 不确定度 U△L  ：U∆L=2/3 U∆d
    /// </summary>
    /// <param name="DataSourse">U∆d：合成不确定度</param>
    /// <returns>不确定度</returns>
    public static PhyParas NaDoubleUncertainty_D(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double ud =  Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double number = 2.0 / 3.0 * ud;
            para._StandardValue = DataConversion.ToUncertainty(String.valueOf(number), 2);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }





        // 薄膜的折射率 相关计算
    /// <summary>
    /// 薄膜的折射率 数据表格处理
    /// </summary>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas GetBMZSLdList(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);

            for (int i = 1; i < str[1].length; i++)
            {
                try
                {
                    str[3][i] = String.format("%.5f",Math.abs( Double.parseDouble(str[1][i]) -  Double.parseDouble(str[2][i])));
                }
                catch (Exception exception)
                {
                    str[3][i] = "";
                }
            }
            para._StandardValue = XmlAnalysis.SetMatrixString(str, str[0].length, str[1].length);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    /// <summary>
    /// 放入薄膜后的光程差
    /// </summary>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas GetBMZSLdertaD(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);

            int rownum = 3;//表格总列数
            int UseNum = rownum;
            double sum = 0.0;
            double xi = 0.0;
            for (int i = 1; i <= rownum; i++)
            {
                try
                {
                    xi =  Double.parseDouble(str[3][i]);
                }
                catch (Exception exception)
                {
                    UseNum = UseNum - 1;
                    xi = 0.0;
                }
                sum = sum + xi;
            }
            if (UseNum > 0)
            {
                double number = sum / UseNum;
                para._StandardValue = DataConversion.CutDataEffective(String.valueOf(number), 4);
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
    /// 计算 薄膜后的光程差 不确定度U d
    /// </summary>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas GetBMZSLUd(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            double avgX =  Double.parseDouble(DataSourse.get(1)._StandardValue.toString());

            int rownum = 3;//表格总列数
            int UseNum = rownum;
            double sum = 0.0;
            double xi = 0.0;
            for (int i = 1; i <= rownum; i++)
            {
                try
                {
                    xi =  Double.parseDouble(str[3][i]) - avgX;
                }
                catch (Exception exception)
                {
                    UseNum = UseNum - 1;
                    xi = 0.0;
                }
                sum = sum + xi * xi ;
            }

            if (UseNum > 1)
            {
                double sgmd = Math.sqrt(sum) / Math.sqrt(UseNum - 1);//标准差
                double tp = 4.3;
                double ua = sgmd / Math.sqrt(UseNum);//A类不确定度
                double kp = 1.96;
                double ub = 0.0001 / 3.0;
                double number = Math.sqrt(tp * ua * tp * ua + kp * ub * kp * ub);
                para._StandardValue = DataConversion.ToUncertainty(String.valueOf(number), 2);
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
    /// 计算出薄膜的折射率n ： d/l+1
    /// </summary>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas GetBMZSLzslN(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double d = 0.0;
            double l = 0.0;
            d =  Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            l =  Double.parseDouble(DataSourse.get(1)._StandardValue.toString());

            if (l > 0)
            {
                double nnumber = d / l + 1.0;
                para._StandardValue = DataConversion.CutDataEffective(String.valueOf(nnumber), 4);
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
    /// 薄膜折射率的不确定度Un
    /// </summary>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas GetBMZSLUn(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double n =  Double.parseDouble(DataSourse.get(0)._StandardValue.toString());//薄膜的折射率n
            double ud =  Double.parseDouble(DataSourse.get(1)._StandardValue.toString());//放入薄膜后的光程差不确定度U d
            double d =  Double.parseDouble(DataSourse.get(2)._StandardValue.toString());//放入薄膜后的光程差
            double l =  Double.parseDouble(DataSourse.get(3)._StandardValue.toString());//实验中的薄膜厚度l
            double ul = 1.96 * 0.004 / 3.0;

            if (d > 0 && l > 0)
            {
                double number = n * Math.sqrt(ud * ud / d / d + ul * ul / l / l);
                para._StandardValue = DataConversion.ToUncertainty(String.valueOf(number), 4);
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
    /// 计算最终结果：Y=x±u(x)
    /// </summary>
    /// <param name="DataSourse">x,u(x)</param>
    /// <returns>得到最终结果表达式</returns>
    public static PhyParas FinalResult(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            if (!DataSourse.get(0)._StudentValue.toString().isEmpty() && !DataSourse.get(1)._StudentValue.toString().isEmpty())
            {
                int Nkeep = 4;//准确结果部分的有效值数字位数
                para._StandardValue = DataConversion.GetExpression(DataConversion.CutDataEffective(DataSourse.get(0)._StudentValue.toString(), Nkeep), DataSourse.get(1)._StudentValue.toString(), 2);
            }
            else if (!DataSourse.get(0)._StandardValue.toString().isEmpty() && !DataSourse.get(1)._StandardValue.toString().isEmpty())
            {
                int Nkeep = 4;//准确结果部分的有效值数字位数
                para._StandardValue = DataConversion.GetExpression(DataConversion.CutDataEffective(DataSourse.get(0)._StandardValue.toString(), Nkeep), DataSourse.get(1)._StandardValue.toString(), 2);
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
    /// B类不确定度
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas Uncertainty_B(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double x = 0.0001;
            double c=3.0;
            double number = x / c;
            para._StandardValue = DataConversion.ToUncertainty(String.valueOf(number), 2);
        }
        catch (Exception  e)
        {
            para._StandardValue = "";
        }
        return para;
    }

    /// <summary>
    /// 合成不确定度
    /// u=math.sqrt(ua*ua+tk*ub*tk*ub)
    /// </summary>
    /// <param name="DataSourse"></param>
    /// <returns>不确定度</returns>
    public static PhyParas Uncertainty_C(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double ua =  Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double ub =  Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            //double tp = 2.78;
            double tk = 1.96;
            double number = Math.sqrt(ua * ua + tk * ub * tk * ub);
            para._StandardValue = DataConversion.ToUncertainty(String.valueOf(number), 2);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

}
