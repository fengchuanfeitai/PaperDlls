import common.PhyParas;
import common.XmlAnalysis;

import java.util.List;

/**
 * @author Administrator
 */
public class Function {


// 调用计算方法

       // 平均室温
    /// <summary>
    /// 计算室温平均值:Y=(T1+T2)/2.0
    /// </summary>
    /// <param name="DataSourse">室温数据源</param>
    /// <returns>得到室温求解结果</returns>
    public static PhyParas AverageValue(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StandardValue = "";
        try
        {
            long num = DataSourse.stream().count();
            double sum = 0.0;
            double xi = 0.0;

            for (int i = 0; i < DataSourse.stream().count(); i++)
            {

                try
                {
                    xi = Double.parseDouble(DataSourse.get(i)._StandardValue.toString());
                }
                catch (Exception  exception)
                {
                    xi = 0.0;
                    num = num - 1;
                }

                sum = sum + xi;


            }


            if (num > 0)
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(sum / num), 1);//有效数据个数不为0;取三位有效数字
            }
            else
            {
                para._StandardValue = "";
            }


        }
        catch (Exception  exception)
        {
            para._StandardValue = "";
        }

        return para;
    }
        

       // 声速理论值
    /// <summary>
    /// 计算声速理论值:Y=331.45 * Math.sqrt(1.0 + Tpingjun / 273.15);
    /// </summary>
    /// <param name="DataSourse">室温数据源</param>
    /// <returns>得到声速理论值求解结果</returns>
    public static PhyParas SSCLSY_LLVoiceSpeed(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StandardValue = "";
        try
        {
            double t = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());

            String temp = String.valueOf(331.45 * Math.sqrt(1.0 + (Double.parseDouble(DataSourse.get(0)._StandardValue.toString()) / 273.15)));
            para._StandardValue = DataConversion.SingleDoubleOfEffective(temp, 4);
        }
        catch (Exception  exception)
        {
            para._StandardValue = "";
        }

        return para;
    }
        

       // 处理表格
    /// <summary>
    /// 逐差法处理数据:Yi=X[i+6]-X[i]
    /// </summary>
    /// <param name="DataSourse">X[1~12]</param>
    /// <returns>得到Y[1~6]</returns>
    public static PhyParas ZC_XW_List(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StandardValue = "";
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            String[][] str1 = XmlAnalysis.GetMatrixString(DataSourse.get(1)._StandardValue);
            int rownum = 13;//表格总列数

            int rowhead = 1;//表的列头占用列数

            int resultnum = 1;//要计算的行数

            if (str.length > 1)
            {
                for (int i = rowhead; i < (rownum - rowhead) / 2 + rowhead; i++)
                {

                    try
                    {
                        double a = Math.abs(Double.parseDouble(str[1][i + 6]) - Double.parseDouble(str[1][i]));
                        str1[resultnum][i] = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 2);
                    }
                    catch (Exception  exception)
                    {
                        str1[resultnum][i] = "";
                    }

                }

                para._StandardValue = XmlAnalysis.SetMatrixString(str1, str1[0].length, str1[1].length);
            }
        }
        catch (Exception  exception)
        {
            para._StandardValue = "";
        }

        return para;
    }
        

       // 平均间距
    /// <summary>
    /// 逐差法处理数据,计算平均值
    /// </summary>
    /// <param name="DataSourse">X[1~6]'</param>
    /// <returns>得到平均值Xp</returns>
    public static PhyParas HalfWavelength(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StandardValue = "";
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            int rownum = 13;//表格总列数
            int rowhead = 1;//表的列头占用列数


            if (str.length > 1)
            {
                double sum = 0.0;
                double xi = 0.0;
                int num = (rownum - rowhead) / 2;
                for (int i = rowhead; i < (rownum - rowhead) / 2 + rowhead; i++)
                {
                    try
                    {
                        xi = Math.abs(Double.parseDouble(str[1][i + (rownum - rowhead) / 2]) - Double.parseDouble(str[1][i]));
                    }
                    catch (Exception  exception)
                    {
                        xi = 0.0;
                        num = num - 1;
                    }

                    sum = sum + xi;
                }

                if (num > 0)
                {
                    sum = sum / (num*6) ;

                    para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(sum), 4);
                }
                else
                {
                    para._StandardValue = "";
                }
            }
        }
        catch (Exception  exception)
        {
            para._StandardValue = "";
        }

        return para;
    }
        

       // 间距不确定度
    /// <summary>
    /// 逐差法处理数据:u=math.sqrt(tp*ua*tp*ua+ub*ub)
    /// </summary>p=0.68  n=6
    /// <param name="DataSourse">X[1~12]，Xp</param>
    /// <returns>得到平均值的不确定度</returns>
    public static PhyParas HWLUncertainty(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StandardValue = "";
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);

            double Xp =0.0;
            try
            {
                Xp = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            }
            catch (Exception  exception)
            {
                //无法计算出平均值，则平均值不确定度直接返回空字符串
                para._StandardValue = "";
                return para;
            }

            int rownum = 13;//表格总列数
            //int culmnum = 2;//表格总行数
            int rowhead = 1;//表的列头占用列数


            if (str.length > 1)
            {
                double sum = 0.0;
                double xi = 0.0;
                int num = (rownum - rowhead) / 2;
                for (int i = rowhead; i < (rownum - rowhead) / 2 + rowhead; i++)
                {
                    try
                    {
                        xi = (Double.parseDouble(str[1][i + (rownum - rowhead) / 2]) - Double.parseDouble(str[1][i]))/6 - Xp;
                    }
                    catch (Exception  exception )
                    {
                        xi = 0.0;
                        num = num - 1;
                    }

                    sum = sum + xi * xi;
                }

                if (num == 6)
                {
                    double ua = Math.sqrt(sum / (num * (num - 1)));
                    double ub = 0.02 / Math.sqrt(3);
                    double tp = 1.11;
                    para._StandardValue = DataConversion.ToUncertainty(String.valueOf(Math.sqrt((tp * ua * tp * ua + ub * ub))), 2);
                }
                else
                {
                    para._StandardValue = "";
                }
            }
        }
        catch (Exception  exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
        

       // 计算波长
    /// <summary>
    /// 计算波长值
    /// </summary>
    /// <param name="DataSourse">Xp</param>
    /// <returns>得到波长值</returns>
    public static PhyParas WHDoubleValue(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StandardValue = "";
        try
        {
            double Xp = 0.0;
            try
            {
                Xp = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
                para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(2 * Xp), 4);
            }
            catch (Exception  exception)
            {
                //无法计算出平均值，则波长直接返回空字符串
                para._StandardValue = "";
                return para;
            }
        }
        catch (Exception  exception)
        {
            para._StandardValue = "";
        }

        return para;
    }
        

       // 波长不确定度
    /// <summary>
    /// 计算波长值不确定度：u(wl)=2u(Xp)
    /// </summary>
    /// <param name="DataSourse">Xp</param>
    /// <returns>得到波长值不确定度</returns>
    public static PhyParas UHDoubleValue(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StandardValue = "";
        try
        {
            double Xp = 0.0;
            try
            {
                Xp = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
                para._StandardValue = DataConversion.ToUncertainty(String.valueOf(2 * Xp), 2);
            }
            catch (Exception  exception)
            {
                //无法计算出平均值，则波长直接返回空字符串
                para._StandardValue = "";
                return para;
            }
        }
        catch (Exception  exception)
        {
            para._StandardValue = "";
        }

        return para;
    }
        

       // 波长表达式
    /// <summary>
    /// 计算波长值：Y=x±u(x)
    /// </summary>
    /// <param name="DataSourse">x,u(x)</param>
    /// <returns>得到波长值</returns>
    public static PhyParas FinalResult(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            if (!DataSourse.get(0)._StudentValue.toString().isEmpty() && !DataSourse.get(1)._StudentValue.toString().isEmpty())
            {
                //int Nkeep = 4;//准确结果部分的有效值数字位数

                para._StandardValue = DataConversion.GetExpression(DataSourse.get(0)._StudentValue.toString(), DataSourse.get(1)._StudentValue.toString(), 1);
            }
            else if (!DataSourse.get(0)._StandardValue.toString().isEmpty() && !DataSourse.get(1)._StandardValue.toString().isEmpty())
            {
                //int Nkeep = 4;//准确结果部分的有效值数字位数

                para._StandardValue = DataConversion.GetExpression(DataSourse.get(0)._StandardValue.toString(), DataSourse.get(1)._StandardValue.toString(), 1);

            }
            else
            {
                para._StandardValue = "";
            }

        }
        catch (Exception  exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
        

       // 声速计算值
    /// <summary>
    /// 计算声速值：V=f*Wavelength
    /// </summary>
    /// <param name="DataSourse">f,Wavelength</param>
    /// <returns>得到声速值</returns>
    public static PhyParas Multiplication(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StandardValue = "";
        try
        {
            double f=Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double v=Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(f*v), 4);
        }
        //*0.001目的是波长单位mm换算为m
        catch (Exception  exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
        

       // 声速不确定度
    /// <summary>
    /// 计算声速不确定度：u(V)=V*math.sqrt((u(f)/f)~2+(u(wl)/wl)~2);  wl表示波长
    /// </summary>
    /// <param name="DataSourse">V,u(f),f,u(wl),wl</param>
    /// <returns>得到声速不确定度</returns>
    public static PhyParas VoiceSpeedUncertainty(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StandardValue = "";
        try
        {
            double v=Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double u=Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            double x = Double.parseDouble(DataSourse.get(2)._StandardValue.toString());

            if (x != 0)
            {
                String temp = String.valueOf(Math.sqrt(u * u / (x * x)) * v);
                para._StandardValue = DataConversion.ToUncertainty(temp, 2);
            }
            else
            {
                para._StandardValue = "";
            }

        }
        catch (Exception  exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
        

       // 声速误差
    /// <summary>
    /// 计算相对误差：Er=|(v-vt)/vt |*100%
    /// </summary>
    /// <param name="DataSourse">V,f,u(f),wl,u(wl)</param>
    /// <returns>得到声速不确定度</returns>
    public static PhyParas RelativeError(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StandardValue = "";
        try
        {
            double ShiJiValue = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double BiaoZhunValue = Double.parseDouble(DataSourse.get(1)._StandardValue.toString().toString());


            if (BiaoZhunValue != 0)
            {
                double result = Math.abs((ShiJiValue - BiaoZhunValue) / BiaoZhunValue) * 100.0;

                para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(result), 2);

            }
            else
            {
                para._StandardValue = "";
            }

        }
        catch (Exception  exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

}
