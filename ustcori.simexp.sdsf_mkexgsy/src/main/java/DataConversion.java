import common.Arith;
import common.ConstValue;
import common.DataValidity;

/**
 * @author Administrator
 */
public class DataConversion {
    /**
     * 获取表达式结果
     * @param Xp 准确值
     * @param Ux 不确定度
     * @param Unum 不确定度保留最多可保留位数，只能是1或2
     * @return
     */
    public static String GetExpression(String Xp, String Ux, int Unum)
    {
        String temp = "";

        String Sx = "-352.1";
        String Su = "11.01";

        try
        {
            Sx = Xp;
            Su = Ux;

            int xhigh = High(Sx);
            int xlow = Low(Sx);

            int yhigh = High(Su);
            int ylow = Low(Su);


            if (xlow < 0)//准确值的最低位为小数位
            {
                if (yhigh < xlow)
                {
                    temp = Sx + "±" + ToUncertainty(String.valueOf(Math.pow(10, Low(Sx))), 1);//[12.02---0.0012] = 12.02±0.01
                }
                else if (yhigh == xlow)
                {
                    temp = Sx + "±" + ToUncertainty(Su, 1);//[12.02---0.031] = 12.02±0.04
                }
                else
                {
                    yhigh = High(ToUncertainty(Su, Unum));
                    ylow = Low(ToUncertainty(Su, Unum));

                    if (ylow >= 0)
                    {
                        int k = yhigh - Unum + 1;
                        temp = String.format("%.0f", Arith.mul(Double.parseDouble(String.format("%.0f",Arith.div(Double.parseDouble(Sx),Math.pow(10, k)))),Math.pow(10, k))) + "±" + ToUncertainty(Su, Unum);
                    }
                    else//ylow <0
                    {
                        if (ylow >= xlow)
                        {
                            temp =String.format("%."+Math.abs(ylow)+"f", Double.parseDouble(Sx)) + "±" + ToUncertainty(Su, Unum);
                        }
                        else
                        {
                            //[102.10---0.321] = 102.10±0.04
                            temp = Sx + "±" + ToUncertainty(Su, yhigh - Low(Sx) + 1);
                        }
                    }

                }

            }
            else//准确值的最低位为整数位
            {

                if (yhigh == 0)
                {
                    temp = Sx + "±" + ToUncertainty(Su, 1);//[12---1.251] = 12±2
                }
                else if (yhigh < 0)
                {
                    temp = Sx + "±" + "1";//[120---0.251] = 120±1
                }
                else
                {

                    yhigh = High(ToUncertainty(Su, Unum));
                    ylow = Low(ToUncertainty(Su, Unum));

                    if (ylow < 0)
                    {
                        temp = Sx + "±" + ToUncertainty(Su, yhigh + 1); //[120---13.34] = 120±14
                    }
                    else
                    {
                        int k = yhigh - Unum + 1;
                        temp = String.format("%.0f",Arith.mul(Double.parseDouble(String.format("%.0f",Arith.div(Double.parseDouble(Sx),Math.pow(10, k)))),Math.pow(10, k))) + "±" + ToUncertainty(Su, Unum);
                    }

                }
            }
        }
        catch (Exception exception)
        {
            temp = "";
        }

        return temp;
    }

    /**
     * 获取数值的有效值最低位数
     * @param str
     * @return
     */
    public static int Low(String str)
    {
        int pos = 0;
        try
        {
            if ("+".equals(str.substring(0, 1)) || "-".equals(str.substring(0, 1)))
            {
                //去除符号位
                str = str.substring(1, str.length() - 1);
            }
            String[] HL = str.split(".");
            int high = Integer.parseInt(HL[0]);
            if (HL.length > 1)
            {
                pos = -HL[1].length();
            }
            else
            {
                pos = 0;
            }
        }
        catch (Exception exception)
        {
            pos = 0;
        }
        return pos;

    }

    /**
     * 获取数值的有效值起始最高位数
     * @param str
     * @return
     */
    public static int High(String str)
    {
        int pos = 0;
        try
        {
            if ("+".equals(str.substring(0, 1)) || "-".equals(str.substring(0, 1)))
            {
                str = str.substring(1, str.length() - 1);//去除符号位
            }
            String[] HL = str.split(".");
            Integer high = Integer.parseInt(HL[0]);
            if (HL.length > 1)
            {
                if (high > 0)
                {
                    pos = (high.toString()).length() - 1;
                }
                else
                {
                    while (pos < HL[1].length())
                    {
                        String ss = HL[1].substring(pos, 1);
                        if (!"0".equals(ss))
                        {
                            pos = -(pos + 1);
                            break;
                        }
                        else
                        {
                            pos++;
                            if (pos == HL[1].length())
                            {
                                pos = -pos;
                                break;
                            }
                        }
                    }
                }
            }
            else
            {
                if (high > 0)
                {
                    pos = (high.toString()).length() - 1;
                }
                else
                {
                    pos = 0;
                }
            }
        }
        catch (Exception exception)
        {
            pos = 0;
        }
        return pos;
    }

    /**
     * 截取有效数值，截取规则为四舍五入
     * @param result 待截取的数值转换的字符串
     * @param num 有效数值位数：要求该值为大于等于1的整数
     * @return 截取后的结果
     */
    public static String CutDataEffective(String result, int num)
    {
        if (num < 1)
        {
            return "";
        }
        String temp = "";
        double data = 0.0;
        //默认为正
        int FuHao = 1;
        if (DataValidity.IsNumeric(result).isValid)
        {
            data = Double.parseDouble(result);
            if (data < 0)
            {
                FuHao = -1;
            }
            data = Math.abs(data);
            if (data == 0)
            {
                if (num >= 1)
                {
                    temp = String.format("%."+(num - 1)+"f",ConstValue.minimumValue);
                }
                else
                {
                    temp =  String.format("%.0f", ConstValue.minimumValue);
                }
                return temp;
            }
            //数值太小，不予考虑
            else if (data <= 0.00000000000000000001 && data > 0)
            {
                data = 0.00000000000000000001;
            }
            int N = 0;
            while (Math.abs(data) < 1 || Math.abs(data) > 10)
            {
                if (Math.abs(data) < 1)
                {
                    data = data * 10.0;
                    N--;
                }
                else if (Math.abs(data) > 10)
                {
                    data = Arith.mul(data,0.1);
                    N++;
                }
                else
                {
                    break;
                }
            }
            String STR =String.format("%."+(num - 1)+"f",data);
            data = Arith.mul(Double.parseDouble(STR),Math.pow(10, N));
            if (N != 0)
            {
                if (num - N - 1 > 0)
                {
                    if (Integer.parseInt(STR) == 10)
                    {
                        temp = String.format("%."+(num - N - 1 - 1)+"f",data);
                    }
                    else
                    {
                        temp = String.format("%."+(num - N - 1)+"f",data);
                    }
                }
                else
                {
                    temp = String.valueOf(data);
                }
            }
            else
            {
                temp = STR;
                if (Integer.parseInt(STR) == 10)
                {
                    if (num <= 2)
                    {
                        temp = "10";
                    }
                    else
                    {
                        temp = "10.000000000000000000000000000000000000".substring(0, num + 1);
                    }
                }
            }
            if (FuHao == -1)
            {
                temp = "-" + temp;
            }
        }
        else
        {
            temp = "";
        }
        return temp;
    }

    /**
     * 保留数值小数点位数，截取规则为四舍五入
     * @param result 待截取的数值转换的字符串
     * @param num 小数点位数：要求该值为大于等于0的整数
     * @return 截取后的结果
     */
    public static String CutDataDecimal(String result, int num)
    {
        if (num < 0)
        {
            return "";
        }
        String temp = "";
        if (DataValidity.IsNumeric(result).isValid)
        {
            temp = String.format("%."+num+"f",(DataValidity.IsNumeric(result).result));
        }
        else
        {
            temp = "";
        }
        return temp;
    }

    /**
     * 取有效数值，截取规则为余位进一，末尾不为零则进一
     * @param result 待截取的数值转换的字符串
     * @param num 有效数值位数：要求该值为大于等于1的整数
     * @return 截取后的结果
     */
    public static String ToUncertainty(String result, int num)
    {
        String temp = CutDataEffective(result, num);

        try
        {
            //默认为正
            int FuHao = 1;

            double data = Double.parseDouble(temp);

            if (data < 0)
            {
                //记录负号
                FuHao = -1;
            }

            data = Math.abs(data);

            if (data < Math.abs(Double.parseDouble(result)))
            {

                double dsa = 0.0;

                if (Low(temp) < 0)
                {
                    dsa = data + Math.pow(10, Low(temp));
                }
                else
                {
                    dsa = data + Math.pow(10, High(temp) - num + 1);
                }
                //该行代码的目的是结果的格式规范化
                temp = CutDataEffective(String.valueOf(dsa), num);

                if (FuHao == -1)
                {
                    temp = "-" + temp;
                }
            }
        }
        catch (Exception exception)
        {
            temp = "";
        }

        return temp;
    }

    /**
     * 保留小数点位数，保留规则为余位进一，末尾不为零则进一
     * @param result 待截取的数值转换的字符串
     * @param num 小数点位数：要求该值为大于等于0的整数
     * @return 截取后的结果
     */
    public static String ToDecimal(String result, int num)
    {
        String temp = CutDataDecimal(result, num);

        try
        {
            int FuHao = 1;//默认为正

            double data = Double.parseDouble(temp);

            if (data < 0)
            {
                FuHao = -1;//记录负号
            }

            data = Math.abs(data);

            if (data < Math.abs(Double.parseDouble(result)))
            {

                double dsa = 0.0;

                if (Low(temp) < 0)
                {
                    dsa = data + Math.pow(10, Low(temp));
                }
                else
                {
                    dsa = data + 1;
                }
                //该行代码的目的是结果的格式规范化
                temp = CutDataDecimal(String.valueOf(dsa), num);

                if (FuHao == -1)
                {
                    temp = "-" + temp;
                }
            }
        }
        catch (Exception exception)
        {
            temp = "";
        }

        return temp;
    }


    /**
     * 截取小数点奇进偶退，保留规则为奇进偶退，"4舍6入5看齐,5后有数进上去,尾数为零向左看,左数奇进偶舍弃"
     * @param result 待截取的数值转换的字符串
     * @param num 小数点位数：要求该值为大于等于0的整数,小于18
     * @return 截取后的结果
     */
    public static String SingleDoubleOfDecimal(String result, int num)
    {
        if (num < 1)
        {
            return "";
        }
        String temp = "";
        if (DataValidity.IsNumeric(result).isValid)
        {
            temp = String.format("%.18f",Double.parseDouble(result));
            //默认为正
            int FuHao = 1;
            double data = Double.parseDouble(temp);
            if (data < 0)
            {
                //记录负号
                FuHao = -1;
            }
            data = Math.abs(data);
            temp = String.format("%.18f",data);
            int PointPos = temp.indexOf(".");
            //取舍之前要保留到的末位值
            String lastStr = temp.substring(PointPos + num, 1);
            //取舍之前要保留到的末位的后一位值
            String nextStr = temp.substring(PointPos + num + 1, 1);
            int nextPos = PointPos + num + 1;
            if (lastStr.equals("."))
            {
                //加1去除小数点
                lastStr = temp.substring(PointPos + num - 1, 1);
            }
            //末尾为偶数
            if (Integer.parseInt(lastStr) % 2 == 0)
            {
                if (Integer.parseInt(nextStr) != 5)
                {
                    temp = CutDataDecimal(temp, num);
                }
                else
                {
                    String pl = temp.substring(nextPos + 1, temp.length() - (nextPos + 1));
                    if (Integer.parseInt(pl) > 0)
                    {
                        temp = CutDataDecimal(temp, num);
                    }
                    else
                    {
                        temp = temp.substring(0, nextPos);
                    }
                    temp = CutDataDecimal(temp, num);
                }
            }
            else//末尾为奇数
            {
                temp = CutDataDecimal(temp, num);
            }
            if (FuHao == -1)
            {
                temp = "-" + temp;
            }
        }
        else
        {
            temp = "";
        }
        return temp;
    }

    /**
     * 截取有效值奇进偶退，保留规则为奇进偶退，"4舍6入5看齐,5后有数进上去,尾数为零向左看,左数奇进偶舍弃"
     * @param result 待截取的数值转换的字符串
     * @param num 小数点位数：要求该值为大于等于1的整数,小于18
     * @return 截取后的结果
     */
    public static String SingleDoubleOfEffective(String result, int num)
    {
        if (num < 1)
        {
            return "";
        }
        String temp = "";
        if (DataValidity.IsNumeric(result).isValid)
        {
            double data = 0.0;
            //默认为正
            int FuHao = 1;
            data = Double.parseDouble(result);
            if (data < 0)
            {
                //记录负号
                FuHao = -1;
            }
            data = Math.abs(data);
            if (data == 0)
            {
                temp = String.format("%."+num+"f",0.00000000000000000001);
                return temp;
            }
            else if (data <= 0.00000000000000000001 && data > 0)
            {
                data = 0.00000000000000000001;
            }
            int N = 0;
            while (Math.abs(data) < 1 || Math.abs(data) > 10)
            {
                if (Math.abs(data) < 1)
                {
                    data = data * 10.0;
                    N--;
                }
                else if (Math.abs(data) > 10)
                {
                    data = data * 0.1;
                    N++;
                }
                else
                {
                    break;
                }
            }
            String STR = SingleDoubleOfDecimal(String.valueOf(data), num - 1);
            data = Arith.mul(Double.parseDouble(STR),Math.pow(10, N));
            if (N != 0)
            {
                if (num - N - 1 > 0)
                {
                    if ((int)Double.parseDouble(STR) == 10)
                    {
                        temp = SingleDoubleOfDecimal(String.valueOf(data), num - N - 1 - 1);
                    }
                    else
                    {
                        temp = SingleDoubleOfDecimal(String.valueOf(data), num - N - 1);
                    }
                }
                else
                {
                    temp = String.valueOf(data);
                }
            }
            else
            {
                temp = STR;
                if (Double.parseDouble(STR) == 10)
                {
                    if (num <= 2)
                    {
                        temp = "10";
                    }
                    else
                    {
                        temp = "10.000000000000000000000000000000000000".substring(0, num + 1);
                    }
                }
            }
            if (FuHao == -1)
            {
                temp = "-" + temp;
            }
        }
        else
        {
            temp = "";
        }
        return temp;
    }

    /**
     * 获取result的有效数值位数
     * @param result
     * @return
     */
    public static int GetEffectiveNumericalDigits(String result)
    {
        return High(result) - Low(result) + 1;
    }

    /**
     * 获取result的保留小数点位数
     * @param result
     * @return
     */
    public static int GetDecimalPointDigits(String result)
    {
        return Math.abs(Low(result));
    }

    /// <summary>
    /// 获取表达式结果,进位规则为四舍五入
    /// </summary>
    /// <param name="Xp">准确值</param>
    /// <param name="Ux">不确定度</param>
    /// <param name="Unum">不确定度保留最多可保留位数，只能是1或2,</param>
    /// <returns></returns>
    public static String GetExpression_SSWR(String Xp, String Ux, int Unum)
    {
        String temp = "";

        String Sx = "-352.1";
        String Su = "11.01";

        try
        {
            Sx = Xp;
            Su = Ux;

            int xhigh = High(Sx);
            int xlow = Low(Sx);

            int yhigh = High(Su);
            int ylow = Low(Su);


            if (xlow < 0)//准确值的最低位为小数位
            {
                if (yhigh < xlow)
                {
                    temp = Sx + "±" + CutDataEffective(String.valueOf(Math.pow(10, Low(Sx))), 1);//[12.02---0.0012] = 12.02±0.00
                }
                else if (yhigh == xlow)
                {
                    temp = Sx + "±" + CutDataEffective(Su, 1);//[12.02---0.031] = 12.02±0.03
                }
                else
                {
                    yhigh = High(CutDataEffective(Su, Unum));
                    ylow = Low(CutDataEffective(Su, Unum));

                    if (ylow >= 0)
                    {
                        int k = yhigh - Unum + 1;
                        temp = String.format("%.0f", Arith.mul(Double.parseDouble(String.format("%.0f",Arith.div(Double.parseDouble(Sx),Math.pow(10, k)))),Math.pow(10, k))) + "±" + CutDataEffective(Su, Unum);

                    }
                    else//ylow <0
                    {
                        if (ylow >= xlow)
                        {
                            temp =String.format("%."+Math.abs(ylow)+"f", Double.parseDouble(Sx)) + "±" + CutDataEffective(Su, Unum);
                        }
                        else  //ylow <xlow
                        {
                            temp = Sx + "±" + CutDataEffective(Su, yhigh - Low(Sx) + 1);//[102.10---0.321] = 102.10±0.32
                        }
                    }

                }

            }
            else//准确值的最低位为整数位
            {

                if (yhigh == 0)
                {
                    temp = Sx + "±" + CutDataEffective(Su, 1);//[12---1.251] = 12±1
                }
                else if (yhigh < 0)
                {
                    temp = Sx + "±" + "1";//[120---0.251] = 120±1
                }
                else
                {

                    yhigh = High(CutDataEffective(Su, Unum));
                    ylow = Low(CutDataEffective(Su, Unum));

                    if (ylow < 0)
                    {
                        temp = Sx + "±" + CutDataEffective(Su, yhigh + 1); //[120---13.34] = 120±14
                    }
                    else
                    {
                        int k = yhigh - Unum + 1;
                        temp = String.format("%.0f",Arith.mul(Double.parseDouble(String.format("%.0f",Arith.div(Double.parseDouble(Sx),Math.pow(10, k)))),Math.pow(10, k))) + "±" + CutDataEffective(Su, Unum);
                    }


                }
            }
        }
        catch (Exception exception)
        {
            temp = "";
        }

        return temp;
    }
}
