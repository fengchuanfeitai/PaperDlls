import common.PhyParas;
import common.XmlAnalysis;

import java.util.List;

/**
 * @author Administrator
 */
public class Function {

    /**
     * 计算室温平均值:Y=(T1+T2)/2.0
     * @param para
     * @param DataSourse 室温数据源
     * @return
     */
    public static PhyParas AverageValue(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StandardValue = "";
        try
        {
            long num = DataSourse.stream().count();
            double sum = 0.0;
            double xi = 0.0;
            //是否存在不良数据
            boolean BadData = false;

            for (int i = 0; i < DataSourse.stream().count(); i++)
            {
                if (IsValidData(DataSourse.get(i)))
                {
                    try
                    {
                        xi = Double.parseDouble(DataSourse.get(i)._StandardValue.toString());
                    }
                    catch (Exception exception)
                    {
                        xi = 0.0;
                        num = num - 1;
                    }

                    sum = sum + xi;
                }
                else
                {
                    //存在不良数据时，退出循环，计算结果为无效数据
                    BadData = true;
                    break;
                }

            }
            //无不良数据
            if (BadData == false)
            {
                if (num > 0)
                {
                    para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(sum / num), 3);
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

    /**
     * 计算声速理论值:Y=331.45 * Math.Sqrt(1.0 + Tpingjun / 273.15)
     * @param para
     * @param DataSourse 室温数据源
     * @return
     */
    public static PhyParas SSCLSY_LLVoiceSpeed(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            if (IsValidData(DataSourse.get(0)))
            {
                String temp = String.valueOf(331.45 * Math.sqrt(1.0 + (Double.parseDouble(DataSourse.get(0)._StandardValue.toString()) / 273.15)));
                para._StandardValue = DataConversion.CutDataEffective(temp, 4);
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

    /**
     * 逐差法处理数据:Yi=X[i+6]-X[i]
     * @param para
     * @param DataSourse X[1~12]
     * @return 得到Y[1~6]
     */
    public static PhyParas SSCLSY_ZCFList(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            int rownum = 13;
            int culmnum = 3;
            int rowhead = 1;
            int dsnum = 1;
            int resultnum = 2;

            if (str.length > 1)
            {
                for (int i = rowhead; i < rownum; i++)
                {
                    if (i < (rownum - rowhead) / 2 + rowhead)
                    {
                        try
                        {
                            str[resultnum][i] =String.format("%.2f",Double.parseDouble(str[dsnum][i + ((rownum - rowhead) / 2)]) - Double.parseDouble(str[dsnum][i]));
                        }
                        catch (Exception exception)
                        {
                            str[resultnum][i] = "";
                        }
                    }
                    else
                    {
                        str[resultnum][i] = "";
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

    /**
     * 逐差法处理数据,计算平均值：Xp=(X1'+X2'+X3'+...+X6')/6
     * @param para
     * @param DataSourse X[1~6]'
     * @return 得到平均值Xp
     */
    public static PhyParas HalfWaveLength(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            //表格总列数
            int rownum = 13;
            //表的列头占用列数
            int rowhead = 1;
            //要用到的数据所在行数
            int dsnum = 2;

            if (str.length > 1)
            {
                double sum = 0.0;
                double xi = 0.0;
                int num = (rownum - rowhead) / 2;
                for (int i = rowhead; i < (rownum - rowhead) / 2 + rowhead; i++)
                {
                    try
                    {
                        xi =Double.parseDouble(str[dsnum][i]);
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
                    sum = sum / num / ((rownum - rowhead) / 2);

                    para._StandardValue = DataConversion.CutDataEffective(String.valueOf(sum), 4);

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

    /**
     * 逐差法处理数据:u=math.sqrt(tp*ua*tp*ua+tk*ub*tk*ub)
     * @param para
     * @param DataSourse X[1~12]，Xp
     * @return 得到平均值的不确定度
     */
    public static PhyParas HWLUncertainty(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);

            double Xp = 0.0;
            try
            {
                Xp = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            }
            catch (Exception exception)
            {
                //无法计算出平均值，则平均值不确定度直接返回空字符串
                para._StandardValue = "";
                return para;
            }
            //表格总列数
            int rownum = 13;
            //表的列头占用列数
            int rowhead = 1;
            //要用到的数据所在行数
            int dsnum = 2;

            if (str.length > 1)
            {
                double sum = 0.0;
                double xi = 0.0;
                int num = (rownum - rowhead) / 2;
                for (int i = rowhead; i < (rownum - rowhead) / 2 + rowhead; i++)
                {
                    try
                    {
                        xi = Double.parseDouble(str[dsnum][i]) / ((rownum - rowhead) / 2) - Xp;
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
                    sum = Math.sqrt(sum / (num - 1));

                    double ua = sum / Math.sqrt(num);
                    double ub = 0.02 / Math.sqrt(3.0);
                    double tp = 2.57;
                    double tk = 1.96;

                    para._StandardValue = DataConversion.ToUncertainty(String.valueOf(Math.sqrt(tp * ua * tp * ua + tk * ub * tk * ub)), 2);
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

    /**
     * 计算波长值WaveLength=2.0*Xp;
     * @param para
     * @param DataSourse Xp
     * @return 得到波长值
     */
    public static PhyParas WHDoubleValue(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double Xp = 0.0;
            try
            {
                Xp = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
                para._StandardValue = DataConversion.CutDataEffective(String.valueOf(2.0 * Xp), 4);
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

    /**
     * 计算波长值不确定度：u(wl)=2.0*u(Xp)
     * @param para
     * @param DataSourse Xp
     * @return 得到波长值
     */
    public static PhyParas UHDoubleValue(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double Xp = 0.0;
            try
            {
                Xp = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
                para._StandardValue = DataConversion.ToUncertainty(String.valueOf(2.0 * Xp), 2);
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

    /**
     * 计算波长值：Y=x±u(x)
     * @param para
     * @param DataSourse x,u(x)
     * @return 得到波长值
     */
    public static PhyParas FinalResult(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            if (!DataSourse.get(0)._StudentValue.toString().isEmpty() && !DataSourse.get(1)._StudentValue.toString().isEmpty())
            {
                int Nkeep = 4;

                para._StandardValue = DataConversion.GetExpression(DataConversion.CutDataEffective(DataSourse.get(0)._StudentValue.toString(), Nkeep), DataSourse.get(1)._StudentValue.toString(), 2);
            }
            else if (!DataSourse.get(0)._StandardValue.toString().isEmpty() && !DataSourse.get(1)._StandardValue.toString().isEmpty())
            {
                int Nkeep = 4;

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

    /**
     * 计算声速值：V=f*WaveLength
     * @param para
     * @param DataSourse f,WaveLength
     * @return 得到声速值
     */
    public static PhyParas Multiplication(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double mul = 1.0;
            double xi = 0.0;
            boolean BadData = false;//是否存在不良数据

            for (int i = 0; i < DataSourse.stream().count(); i++)
            {
                if (IsValidData(DataSourse.get(i)) == true)
                {
                    try
                    {
                        xi = Double.parseDouble(DataSourse.get(i)._StandardValue.toString());
                    }
                    catch (Exception exception)
                    {
                        xi = 1.0;
                    }

                    mul = mul * xi;
                }
                else
                {
                    BadData = true;//存在不良数据时，退出循环，计算结果为无效数据
                    break;
                }
            }

            if (BadData == false)//无不良数据
            {
                para._StandardValue = DataConversion.CutDataEffective(String.valueOf(mul * 0.001), 4);//*0.001目的是波长单位mm换算为m
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

    /**
     * 计算声速不确定度：u(V)=V*math.sqrt((u(f)/f)~2+(u(wl)/wl)~2);  wl表示波长
     * @param para
     * @param DataSourse V,u(f),f,u(wl),wl
     * @return 得到声速不确定度
     */
    public static PhyParas VoiceSpeedUncertainty(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            if (IsValidData(DataSourse.get(0)) == true &&
                    IsValidData(DataSourse.get(1)) == true &&
                    IsValidData(DataSourse.get(2)) == true &&
                    IsValidData(DataSourse.get(3)) == true &&
                    IsValidData(DataSourse.get(4)) == true)
            {

                String temp = String.valueOf(Double.parseDouble(DataSourse.get(0)._StandardValue.toString()) *
                        Math.sqrt(Math.pow((Double.parseDouble(DataSourse.get(1)._StandardValue.toString()) / Double.parseDouble(DataSourse.get(2)._StandardValue.toString())), 2) +
                                Math.pow((Double.parseDouble(DataSourse.get(3)._StandardValue.toString()) / Double.parseDouble(DataSourse.get(4)._StandardValue.toString())), 2)));
                para._StandardValue = DataConversion.ToUncertainty(temp, 2);
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

    /**
     * 计算相对误差：Er=|(v-vt)/vt |*100%
     * @param para
     * @param DataSourse V,f,u(f),wl,u(wl)
     * @return 得到声速不确定度
     */
    public static PhyParas RelativeError(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            if (IsValidData(DataSourse.get(0)) && IsValidData(DataSourse.get(1)))
            {
                if (Double.parseDouble(DataSourse.get(1)._StandardValue.toString()) != 0)
                {
                    para._StandardValue = String.format("%.4f",Math.abs((Double.parseDouble(DataSourse.get(0)._StandardValue.toString()) - Double.parseDouble(DataSourse.get(1)._StandardValue.toString())) / Double.parseDouble(DataSourse.get(1)._StandardValue.toString())));
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

    /**
     * 判断数据有效性：如果数据参数有效，则返回true；否则返回false。
     * @param php 待判断的参数
     * @return 如果数据参数有效，则返回true；否则返回false
     */
    public static boolean IsValidData(PhyParas php)
    {
        if (php._StudentValue != null && !php._StudentValue.toString().isEmpty())
        {
            switch (php._Type.toUpperCase())
            {
                case "INT":
                {
                    try
                    {
                        Integer.parseInt(php._StudentValue.toString());
                        return true;
                    }
                    catch (Exception exception)
                    {
                        return false;
                    }

                }

                case "DOUBLE":
                {
                    try
                    {
                       Double.parseDouble(php._StudentValue.toString());
                        return true;
                    }
                    catch (Exception exception)
                    {
                        return false;
                    }
                }

                case "STRING":
                {
                    if (php._StudentValue != null)
                    {
                        if (!php._StudentValue.toString().isEmpty())
                        {
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }

                case "BOOL":
                {
                    try
                    {
                        Boolean.parseBoolean(php._StudentValue.toString());
                        return true;
                    }
                    catch (Exception exception)
                    {
                        return false;
                    }
                }

                case "TLIST":
                {
                    String[][] str = XmlAnalysis.GetMatrixString(php._StudentValue);
                    if (str.length > 1)
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }

                default:
                    break;
            }

            return false;

        }
        else
        {
            return false;
        }

    }

}
