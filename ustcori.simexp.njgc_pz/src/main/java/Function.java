import common.PhyParas;
import common.XmlAnalysis;

import java.util.List;

/**
 * @author Administrator
 */
public class Function {


    public static PhyParas PHTJ1(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {


        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    public static PhyParas PHTJ2(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {

        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    public static PhyParas M1(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String m1 = String.valueOf(para._StudentValue);
            if (m1 != "" && m1 != null)
            {
                double M1 = Double.parseDouble(m1);
                if (M1 >= 198.00 && M1 <= 200.00)
                {
                    para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(M1),2);

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
    public static PhyParas M2(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String m1 = String.valueOf(DataSourse.get(0)._StandardValue);
            String m2 = String.valueOf(para._StudentValue);
            if (m1 != "" && m1 != null)
            {
                if (m2 != "" && m2 != null)
                {
                    double M1 = Double.parseDouble(m1);
                    double M2 = Double.parseDouble(m2);
                    if (M1 > M2)
                    {
                        para._StandardValue = DataConversion.SingleDoubleOfDecimal(String.valueOf(M2), 2);
                    }
                }
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }

        return para;
    }
    public static PhyParas SJBG1(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(para._StudentValue);
            String[][] str2 = XmlAnalysis.GetMatrixString(para._StandardValue);
            String m1 = String.valueOf(DataSourse.get(0)._StandardValue);
            String m2 = String.valueOf(DataSourse.get(1)._StandardValue);
              // 第一行，第二行
            for (int i = 1; i < 3; i++)
            {
                for (int j = 1; j < 4; j++)
                {
                    try
                    {
                        double a = Double.parseDouble(str[i][j]);
                        if (a > 0.0)
                        {
                            str2[i][j] = DataConversion.SingleDoubleOfDecimal(String.valueOf(a), 2);
                        }
                        else
                        {
                            str2[i][j] = "";
                        }
                    }
                    catch (Exception exception)
                    {
                        str2[i][j] = "";
                    }
                    //str2[i][j] = DataConversion.SingleDoubleOfDecimal(str[i][j], 2);
                    //int n = DataConversion.GetDecimalPointDigits(str[i][j]);
                    //if (n == 2 && Double.parseDouble(str[i][j]) > 0.0)
                    //{
                    //    str2[i][j] = str[i][j];
                    //}
                    //else
                    //{
                    //    str2[i][j] = DataConversion.SingleDoubleOfDecimal(str[i][j], 2);
                    //}
                }
            }
               //第三行，第四行
            //for (int j = 1; j < 4; j++)
            //{
            //    try
            //    {
            //        double t10 = Double.parseDouble(str2[1][j]);
            //        double t2 = Double.parseDouble(str2[2][j]);
            //        double v10 = 100 / t10;
            //        double v2 = 100 / t2;
            //        str2[3][j] = DataConversion.SingleDoubleOfDecimal(v10.ToString(), 2);
            //        str2[4][j] = DataConversion.SingleDoubleOfDecimal(v2.ToString(), 2);
            //    }
            //    catch (Exception exception)
            //    {
            //        str2[3][j] = "";
            //        str2[4][j] = "";
            //    }
            //}
            for (int i = 1; i < 3; i++)
            {
                for (int j = 1; j < 4; j++)
                {
                    try
                    {
                        if (str[i][j] != "" && str[i][j] != null && Double.parseDouble(str[i][j]) > 0.0)
                        {
                            str2[i + 2][j] = DataConversion.CutDataDecimal(String.valueOf(100.0 / Double.parseDouble(str[i][j])), 2);
                        }
                            else
                        {
                            str2[i + 2][j] = "";
                        }
                    }
                    catch (Exception exception)
                    {
                        str2[i + 2][j] = "";
                    }
                }
            }
               //第五行，第六行
            for (int i = 5; i < 7; i++)
            {
                for (int j = 1; j < 3; j++)
                {
                    try
                    {
                        if (i == 5)
                        {
                            if (m1 != "" && m1 != null)
                            {
                                double M1 = Double.parseDouble(m1);
                                if (str2[i - 2][j] != "" && str2[i - 2][j] != null)
                                {
                                    double P1 = Double.parseDouble(str2[i - 2][j]) * M1 * 0.01;
                                    if (P1 > 0.0)
                                    {
                                        str2[i][j] = DataConversion.CutDataDecimal(String.valueOf(P1), 2);
                                    }
                                    else
                                    {
                                        str2[i][j] = "";
                                    }
                                }
                                    else
                                {
                                    str2[i][j] = "";
                                }

                            }
                            else
                            {
                                str2[i][j] = "";
                            }
                        }
                        if (i == 6)
                        {
                            if (m2 != "" && m2 != null)
                            {
                                double M2 = Double.parseDouble(m2);
                                if (str2[i - 2][j] != "" && str2[i - 2][j] != null)
                                {
                                    double P2 = Double.parseDouble(str2[i - 2][j]) * M2 * 0.01;
                                    if (P2 > 0.0)
                                    {
                                        str2[i][j] = DataConversion.CutDataDecimal(String.valueOf(P2), 2);
                                    }
                                }
                                    else
                                {
                                    str2[i][j] = "";
                                }

                            }
                            else
                            {
                                str2[i][j] = "";
                            }
                        }
                    }
                    catch (Exception exception)
                    {
                        str2[i][j] = "";
                    }
                }

            }
               // 第七行
            for (int j = 1; j < 4; j++)
            {
                try
                {
                    if (str2[3][j] != "" && str2[3][j] != null)
                    {
                        if (str2[4][j] != "" && str2[4][j] != null)
                        {
                            double e1 = Double.parseDouble(str2[4][j]) / Double.parseDouble(str2[3][j]);
                            if (e1 < 1.0 && e1>0.0&&Double.parseDouble(str[7][j]) < 1.0)
                            {
                                int n = DataConversion.GetDecimalPointDigits(str[7][j]);
                                if (n == 3)
                                {
                                    str2[7][j] = DataConversion.CutDataDecimal(String.valueOf(e1), 3);
                                }
                                else
                                {
                                    str2[7][j] = DataConversion.CutDataDecimal(String.valueOf(e1), 4);
                                }
                            }
                                else
                            {
                                str2[7][j] = "";
                            }
                        }
                            else
                        {
                            str2[7][j] = "";
                        }

                    }
                        else
                    {
                        str2[7][j] = "";
                    }
                }
                catch (Exception exception)
                {
                    str2[7][j] = "";
                }


            }
               //第八行
            for (int j = 1; j < 4; j++)
            {
                try
                {
                    if (m1 != "" && m1 != null)
                    {
                        if (m2 != "" && m2 != null)
                        {
                            if (str2[7][j] != "" && str2[7][j] != null)
                            {
                                double M1 = Double.parseDouble(m1);
                                double M2 = Double.parseDouble(m2);
                                if (M1 != 0.0 & M2 != 0.0)
                                {
                                    double e = Double.parseDouble(str2[7][j]);
                                    double R = (M1 + M2 * e * e) / (M1 + M2);
                                    if (R < 1.0 && R>0.0&&Double.parseDouble(str[8][j]) < 1.0)
                                    {
                                        int n = DataConversion.GetDecimalPointDigits(str[8][j]);
                                        if (n == 3)
                                        {
                                            str2[8][j] = DataConversion.CutDataDecimal(String.valueOf(R), 3);
                                        }
                                        else
                                        {
                                            str2[8][j] = DataConversion.CutDataDecimal(String.valueOf(R), 4);
                                        }

                                    }
                                        else
                                    {
                                        str2[8][j] = "";
                                    }
                                }
                                else
                                {
                                    str2[8][j] = "";
                                }
                            }
                                else
                            {
                                str2[8][j] = "";
                            }
                        }
                        else
                        {
                            str2[8][j] = "";
                        }

                    }
                    else
                    {
                        str2[8][j] = "";
                    }
                }
                catch (Exception exception)
                {
                    str2[8][j] = "";
                }

            }

              // 第九行
            for (int j = 1; j < 4; j++)
            {
                try
                {
                    if (str2[5][j] != "" && str2[5][j] != null)
                    {
                        if (str2[6][j] != "" && str2[6][j] != null)
                        {
                            double P1 = Double.parseDouble(str2[5][j]);
                            double P2 = Double.parseDouble(str2[6][j]);
                            if (P1 != 0.0)
                            {
                                double E = Math.abs(P1 - P2) / P1 * 100.0;
                                if (E > 0.0 && Double.parseDouble(str[9][j]) > 0.0)
                                {
                                    str2[9][j] = DataConversion.CutDataDecimal(String.valueOf(E), 2);
                                }
                                    else
                                {
                                    str2[9][j] = "";
                                }
                            }
                            else
                            {
                                str2[9][j] = "";
                            }
                        }
                            else
                        {
                            str2[9][j] = "";
                        }
                    }
                        else
                    {
                        str2[9][j] = "";
                    }
                }
                catch (Exception exception)
                {
                    str2[9][j] = "";
                }

            }

            para._StandardValue = XmlAnalysis.SetMatrixString(str2, str[0].length, str[1].length);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    public static PhyParas tM1(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String m1 = String.valueOf(para._StudentValue);

            if (m1 != "" && m1 != null)
            {
                double M1 = Double.parseDouble(m1);
                if (M1 > 0.0)
                {
                    para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(m1), 2);
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
    public static PhyParas tM2(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String m2 = String.valueOf(para._StudentValue);
            if (m2 != "" && m2 != null)
            {
                double M2 = Double.parseDouble(m2);
                if (M2 > 0.0)
                {
                    para._StandardValue = DataConversion.CutDataDecimal(String.valueOf(m2), 2);
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
    public static PhyParas SJBG2(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            String[][] str = XmlAnalysis.GetMatrixString(para._StudentValue);
            String[][] str3 = XmlAnalysis.GetMatrixString(para._StandardValue);
            String m1 = String.valueOf(DataSourse.get(0)._StudentValue);
            String m2 = String.valueOf(DataSourse.get(1)._StudentValue);
               //第一行，第二行，第三行
            for (int i = 1; i < 4; i++)
            {
                for (int j = 1; j < 4; j++)
                {
                    try
                    {
                        if (Double.parseDouble(str[i][j]) > 0.0)
                        {
                            str3[i][j] = DataConversion.SingleDoubleOfDecimal(str[i][j], 2);
                        }
                            else
                        {
                            str3[i][j] = "";
                        }
                        //int n = DataConversion.GetDecimalPointDigits(str[i][j]);
                        //if (n == 2&&Double.parseDouble(str[i,j])>0.0)
                        //{
                        //    str2[i][j] = str[i][j];
                        //}
                        //else
                        //{
                        //    str2[i][j] = "";
                        //}
                    }
                    catch (Exception exception)
                    {
                        str3[i][j] = "";
                    }
                }
            }

              //第四行，第五行，第六行
            for (int i = 1; i < 4; i++)
            {
                for (int j = 1; j < 4; j++)
                {
                    try
                    {
                        if (str[i][j] != "" && str[i][j] != null && Double.parseDouble(str[i][j]) > 0.0)
                        {
                            str3[i + 3][j] = DataConversion.CutDataDecimal(String.valueOf(100.0 / Double.parseDouble(str[i][j])), 2);
                        }
                            else
                        {
                            str3[i + 3][j] = "";
                        }
                    }
                    catch (Exception exception)
                    {
                        str3[i + 3][j] = "";
                    }
                }
            }


               //第七行
            for (int j = 1; j < 4; j++)
            {
                try
                {
                    if (m1 != "" && m1 != null)
                    {

                        if (str3[4][j] != "" && str3[4][j] != null)
                        {
                            double M1 = Double.parseDouble(m1);

                            double e = Double.parseDouble(str3[4][j]);
                            double P1 = M1 * e * 0.01;
                            if (P1 > 0.0)
                            {
                                str3[7][j] = DataConversion.CutDataDecimal(String.valueOf(P1), 2);
                            }
                            else
                            {
                                str3[7][j] = "";
                            }

                        }
                            else
                        {
                            str3[7][j] = "";
                        }
                    }
                    else
                    {
                        str3[7][j] = "";
                    }
                }
                catch(Exception exception)
                {
                    str3[7][j] = "";
                }

            }

                //第八行
            for (int j = 1; j < 4; j++)
            {
                try
                {
                    if (m1 != "" && m1 != null)
                    {
                        if (m2 != "" && m2 != null)
                        {
                            if (str3[5][j] != "" && str3[5][j] != null)
                            {
                                if (str3[6][j] != "" && str3[6][j] != null)
                                {
                                    double M1 = Double.parseDouble(m1);
                                    double M2 = Double.parseDouble(m2);
                                    double P2 = (M1 * Double.parseDouble(str3[6][j]) + M2 * Double.parseDouble(str3[5][j])) * 0.01;
                                    if (P2 > 0.0)
                                    {
                                        str3[8][j] = DataConversion.CutDataDecimal(String.valueOf(P2), 2);
                                    }
                                    else
                                    {
                                        str3[8][j] = "";
                                    }

                                }
                                    else
                                {
                                    str3[8][j] = "";
                                }
                            }
                                else
                            {
                                str3[8][j] = "";
                            }
                        }
                        else
                        {
                            str3[8][j] = "";
                        }

                    }
                    else
                    {
                        str3[8][j] = "";
                    }
                }
                catch (Exception exception)
                {
                    str3[8][j] = "";
                }

            }

                //第九行
            for (int j = 1; j < 4; j++)
            {
                try
                {
                    if (str3[4][j] != "" && str3[4][j] != null)
                    {
                        if (str3[5][j] != "" && str3[5][j] != null)
                        {
                            if (str3[6][j] != "" && str3[6][j] != null)
                            {
                                double v10 = Double.parseDouble(str3[4][j]);
                                if (v10 != 0.0)
                                {
                                    double e = (Double.parseDouble(str3[5][j]) - Double.parseDouble(str3[6][j])) / Double.parseDouble(str3[4][j]);

                                    if (e < 1.0 && e>0.0&&Double.parseDouble(str[9][j]) < 1.0)
                                    {
                                        int n = DataConversion.GetDecimalPointDigits(str[9][j]);
                                        if (n == 3)
                                        {
                                            str3[9][j] = DataConversion.CutDataDecimal(String.valueOf(e), 3);
                                        }
                                        else
                                        {
                                            str3[9][j] = DataConversion.CutDataDecimal(String.valueOf(e), 4);
                                        }

                                    }
                                        else
                                    {
                                        str3[9][j] = "";
                                    }
                                }
                                else
                                {
                                    str3[9][j] = "";
                                }
                            }
                                else
                            {
                                str3[9][j] = "";
                            }
                        }
                            else
                        {
                            str3[9][j] = "";
                        }

                    }
                        else
                    {
                        str3[9][j] = "";
                    }
                }
                catch (Exception exception)
                {
                    str3[9][j] = "";
                }


            }

                // 第十行
            for (int j = 1; j < 4; j++)
            {
                try
                {
                    if (m1 != "" && m1 != null)
                    {
                        if (m2 != "" && m2 != null)
                        {
                            if (str3[9][j] != "" && str3[9][j] != null)
                            {
                                double M1 = Double.parseDouble(m1);
                                double M2 = Double.parseDouble(m2);
                                if (M1 != 0.0 & M2 != 0.0)
                                {
                                    double e = Double.parseDouble(str3[9][j]);
                                    double R = (M1 + M2 * e * e) / (M1 + M2);
                                    if (R < 1.0 && R>0.0&&Double.parseDouble(str[10][j]) < 1.0)
                                    {
                                        int n = DataConversion.GetDecimalPointDigits(str[10][j]);
                                        if (n == 3)
                                        {
                                            str3[10][j] = DataConversion.CutDataDecimal(String.valueOf(R), 3);
                                        }
                                        else
                                        {
                                            str3[10][j] = DataConversion.CutDataDecimal(String.valueOf(R), 4);
                                        }

                                    }
                                        else
                                    {
                                        str3[10][j] = "";
                                    }
                                }
                                else
                                {
                                    str3[10][j] = "";
                                }
                            }
                                else
                            {
                                str3[10][j] = "";
                            }
                        }
                        else
                        {
                            str3[10][j] = "";
                        }

                    }
                    else
                    {
                        str3[10][j] = "";
                    }
                }
                catch (Exception exception)
                {
                    str3[10][j] = "";
                }

            }
               // 第十一行
            for (int j = 1; j < 4; j++)
            {
                try
                {
                    if (str3[7][j] != "" && str3[7][j] != null)
                    {
                        if (str3[8][j] != "" && str3[8][j] != null)
                        {
                            double P1 = Double.parseDouble(str3[7][j]);
                            double P2 = Double.parseDouble(str3[8][j]);
                            if (P1 != 0.0)
                            {
                                double E = Math.abs(P1 - P2) / P1 * 100.0;
                                if (E > 0.0 && Double.parseDouble(str[11][j]) > 0.0)
                                {
                                    str3[11][j] = DataConversion.CutDataDecimal(String.valueOf(E), 2);
                                }
                                    else
                                {
                                    str3[11][j] = "";
                                }
                            }
                            else
                            {
                                str3[11][j] = "";
                            }
                        }
                            else
                        {
                            str3[11][j] = "";
                        }
                    }
                        else
                    {
                        str3[11][j] = "";
                    }
                }
                catch (Exception exception)
                {
                    str3[11][j] = "";
                }

            }

            para._StandardValue = XmlAnalysis.SetMatrixString(str3, str[0].length, str[1].length);
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }

        return para;
    }



}
