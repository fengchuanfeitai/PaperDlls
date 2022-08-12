import common.PhyParas;
import common.XmlAnalysis;

import java.util.List;

/**
 * @author Administrator
 */
public class Function {


// 调用计算方法

    /// <summary>
    ///  计算正弦信号、方波信号、三角波信号的峰—峰电压值
    ///  （Upp=Hpp*K） 单位：V
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas ChengJiValue1(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double hpp = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());
            double k = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());
            double number = hpp * k;
            para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(number), 2);//有效数据个数不为0;取二位有效数字
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

       // 各个信号波周期的计算
    /// <summary>
    /// 500Hz正弦波周期（T=L*t0）
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas ChengJiValue2(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double l = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());//单位：格
            double t0 = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());//单位：ms/格
            double number = l * t0;//单位：ms

            para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(number), 2);//有效数据个数不为0;取二位有效数字
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    /// <summary>
    /// 5KHz方波周期 （T=L*t0）
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas ChengJiValue3(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double l = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());//单位：格
            double t0 = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());//单位：us/格
            double number = l * t0 * 0.001;//单位：ms
            para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(number), 2);//有效数据个数不为0;取二位有效数字
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }

        return para;
    }

    /// <summary>
    /// 500KHz三角波周期 （T=L*S1）
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas ChengJiValue4(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double l = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());//单位：格
            double t0 = Double.parseDouble(DataSourse.get(1)._StandardValue.toString());//单位：ns/格
            double number = l * t0 * 0.001;//单位：us
            para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(number), 2);//有效数据个数不为0;取二位有效数字
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
        

       // 各个信号波频率的计算
    /// <summary>
    /// 计算500Hz正弦信号的频率 (单位：kHz)
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas ChuFa(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double number = 0.0;
            double t = 1.0;
            t = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());//单位：ms
            if (t == 0)
            {
                para._StandardValue = "";
                return para;
            }
            else
            {
                t = t * 0.001;//单位：s
                number = 1 / t;//单位：Hz
            }
            para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf((number * 0.001)), 2);//单位：kHz
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    /// <summary>
    /// 计算5KHz方波信号的频率 (单位：KHz)
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas ChuFa1(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double number = 0.0;
            double t = 1.0;
            t = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());//单位：ms
            if (t == 0)
            {
                para._StandardValue = "";
                return para;
            }
            else
            {
                t = t * 0.001;//单位：s
                number = 1 / t; //单位：Hz
            }
            number = number * 0.001;
            para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(number), 2);//单位：kHz
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    // 计算三角波信号的频率 (单位：MHz)
    public static PhyParas ChuFa2(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double number = 0.0;
            double t0 = 1.0;
            t0 = Double.parseDouble(DataSourse.get(0)._StandardValue.toString());//单位：us
            if (t0 == 0)
            {
                para._StandardValue = "";
                return para;
            }
            else
            {
                t0 = t0 * Math.pow(10, -6);//单位：s
                number = 1 / t0; //单位：Hz
            }
            number = number * Math.pow(10, -6);//单位：MHz
            para._StandardValue = DataConversion.SingleDoubleOfEffective(String.valueOf(number), 2);//单位：kHz
        }
        catch (Exception exception)
        { para._StandardValue = ""; }
        return para;
    }
        

       // 使用李萨如图形测频率
    /// <summary>
    /// 数据表格标准答案
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas LiSaRuList(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(para._StandardValue);

            str[1][1] = "2";
            str[1][2] = "2";
            str[1][3] = "4";
            str[2][1] = "4";
            str[2][2] = "2";
            str[2][3] = "2";

            for (int i = 1; i <= 3; i++)
            {
                str[3][i] = String.valueOf(Double.parseDouble(str[2][i]) / Double.parseDouble(str[1][i]));
            }

            str[4][1] = "250.0";
            str[4][2] = "500.0";
            str[4][3] = "1000.0";
            for (int i = 1; i <= 3; i++)
            {
                str[5][i] = String.valueOf(Double.parseDouble(str[4][i]) * Double.parseDouble(str[3][i]));
            }
            //str[5, 1] = "500.0";
            //str[5, 2] = "500.0";
            //str[5, 3] = "500.0";
            para._StandardValue = XmlAnalysis.SetMatrixString(str, str[0].length, str[1].length);
        }
        catch (Exception e)
        {
            para._StandardValue = "";
        }
        return para;
    }

    /// <summary>
    /// (2)	fx的平均值 Hz
    /// </summary>
    /// <param name="para"></param>
    /// <param name="DataSourse"></param>
    /// <returns></returns>
    public static PhyParas Average_f(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][]str = XmlAnalysis.GetMatrixString(DataSourse.get(0)._StandardValue);
            double number = 0.0;
            int num = 3;
            int rownum = 3;
            double sum = 0.0;
            double xi = 0.0;
            for (int i = 1; i <= rownum; i++)
            {
                try
                {
                    xi = Double.parseDouble(str[5][i]);
                }
                catch (Exception e)
                {
                    xi = 0.0;
                    num = num - 1;
                }
                sum = sum + xi;
            }

            if (num > 0)
            {
                number = sum / num;
                para._StandardValue = DataConversion.CutDataEffective(String.valueOf(number), 5); ;
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

}
