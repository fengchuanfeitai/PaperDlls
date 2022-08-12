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
            if (C >= 50.0 && C <= 60.0)
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(C), 1);
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

    //2.(1)
    public static PhyParas M2list1(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double C = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            if (C >= 1.400 && C <= 1.700)
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(C), 3);
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

    //2.(2)
    public static PhyParas M3list1(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double C = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            if (C >= 0.000 && C <= 0.300)
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(C), 3);
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

    //2.(3)
    public static PhyParas M4list1(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double C = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            double h = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());//x1
            double b = Double.parseDouble(DataSourse.get(2)._StudentValue.toString());//x2
            double x=Math.abs(h-b);
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(x), 3);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    //2.(4)
    public static PhyParas M5list1(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double C = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            double h = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());//x差值绝对值
            double x = 4*h;
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(x), 3);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    //2.(5)
    public static PhyParas M6list1(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double C = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            double h = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());//λ
            double b = Double.parseDouble(DataSourse.get(2)._StudentValue.toString());//f
            double x = h * b * 0.01;
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(x), 3);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    //3.(1)
    public static PhyParas M7list1(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double C = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            if (C >= 1.300 && C <= 1.600)
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(C), 3);
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

    //3.(2)
    public static PhyParas M8list1(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double C = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());//X2
            double h = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());//X1
            if (C >= 1.400 && C <= 1.700 && C > h)
            {
                para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(C), 3);
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

    //3.(3)
    public static PhyParas M9list1(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double C = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            double h = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());//x1
            double b = Double.parseDouble(DataSourse.get(2)._StudentValue.toString());//x2
            double x = Math.abs(h - b);
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(x), 3);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    //3.(4)
    public static PhyParas M10list1(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double C = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());//n
            double h = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());//X的差值绝对值
            double x = 1+2 * h;
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(x), 3);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }

    //3.(5)
    public static PhyParas M11list1(PhyParas para, List<PhyParas> DataSourse)
    {
        //para._StudentdValue = "";
        try
        {
            double C = Double.parseDouble(DataSourse.get(0)._StudentValue.toString());
            double h = Double.parseDouble(DataSourse.get(1)._StudentValue.toString());//n
            double b = Double.parseDouble(DataSourse.get(2)._StudentValue.toString());//V
            double x = b / h;
            para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(x), 3);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }


}
