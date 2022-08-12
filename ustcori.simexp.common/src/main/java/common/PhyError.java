package common;

/**
 * @author Administrator
 */
public class PhyError {
    /**
     * PhyError建立一个公开的算法接口
     * 1. 输入inputDataList
     * 2. 做任务的分发
     *   1） 根据algorithmID 并根据算法ID，将inputDataList转换为具体算法 输入参数顺序和格式
     *   2） 调用具体处理算法，得到返回结果
     * 3. 输出结果的封装
     *   将得到的具体算法的结果按照   outResultList 形式封装 输出
     */

    /**
     * 求一组数据的算术平均值
     *
     * @param NumList 输入的一组数据
     * @return 返回数组的算术平均值
     */
    public static double PingJunZhi(double[] NumList) {
        double Sum = 0.0;
        for (int i = 0; i < NumList.length; i++) {
            Sum = Sum + NumList[i];
        }
        Sum = Sum / NumList.length;
        return Sum;
    }

    /**
     * 求一组数据的标准误差
     *
     * @param NumList 输入的一组数据
     * @return 返回数组的标准误差
     */
    public static double BiaoZhunWuCha(double[] NumList) {
        double Sum = 0.0;
        for (int i = 0; i < NumList.length; i++) {
            Sum = Sum + NumList[i];
        }
        Sum = Sum / NumList.length;

        double SqureSum = 0.0;
        for (int i = 0; i < NumList.length; i++) {
            SqureSum = SqureSum + Math.pow((NumList[i] - Sum), 2);
        }
        SqureSum = Math.sqrt(SqureSum / (NumList.length - 1));

        return SqureSum;
    }

    /**
     * 求一组数据的算术平均值的标准误差
     *
     * @param NumList 输入的一组数据
     * @return 返回数组的算术平均值的标准误差
     */
    public static double SuanShuPingJunZhiBiaoZhunWuCha(double[] NumList) {
        double Sum = 0.0;
        for (int i = 0; i < NumList.length; i++) {
            Sum = Sum + NumList[i];
        }
        Sum = Sum / NumList.length;

        double SqureSum = 0.0;
        for (int i = 0; i < NumList.length; i++) {
            SqureSum = SqureSum + Math.pow((NumList[i] - Sum), 2);
        }
        SqureSum = Math.sqrt(SqureSum / ((NumList.length - 1) * (NumList.length)));

        return SqureSum;
    }

    /**
     * 求一组数据算术平均误差
     *
     * @param NumList 输入的一组数据
     * @return 返回数组的算术平均误差
     */
    public static double SuanShuPingJunWuCha(double[] NumList) {
        double Sum = 0.0;
        for (int i = 0; i < NumList.length; i++) {
            Sum = Sum + NumList[i];
        }
        Sum = Sum / NumList.length;

        double SqureSum = 0.0;
        for (int i = 0; i < NumList.length; i++) {
            SqureSum = (NumList[i] - Sum);
        }
        SqureSum = Math.sqrt(SqureSum / NumList.length);

        return SqureSum;
    }

    /**
     * 最小二乘法应用于线性回归:Y=mX+b
     *
     * @param Xi X数据组
     * @param Yi Y数据组
     * @return 返回结果：ZuiXiaoErChengFa[0]是一次系数；ZuiXiaoErChengFa[1]是常数项；ZuiXiaoErChengFa[2]是相关系数
     */
    public static double[] ZuiXiaoErChengFa(double[] Xi, double[] Yi) {
        //Y=mX+b
        double[] zxecf = new double[]{0.0, 0.0, 0.0};
        double m = 0.0;
        double b = 0.0;
        double r = 0.0;

        if (Xi.length == Yi.length) {
            double xp = PingJunZhi(Xi);
            double yp = PingJunZhi(Yi);
            double[] xynew = new double[Xi.length];
            double[] DXi = new double[Xi.length];
            double[] DYi = new double[Yi.length];

            for (int i = 0; i < Xi.length; i++) {
                xynew[i] = Xi[i] * Yi[i];
                DXi[i] = Xi[i] * Xi[i];
                DYi[i] = Yi[i] * Yi[i];
            }
            double xyp = PingJunZhi(xynew);

            double lxy = Xi.length * (xyp - xp * yp);

            double xdp = PingJunZhi(DXi);
            double ydp = PingJunZhi(DYi);

            double lxx = Xi.length * (xdp - xp * xp);
            m = lxy / lxx;
            b = yp - m * xp;
            r = (xyp - xp * yp) / Math.sqrt((xdp - xp * xp) * (ydp - yp * yp));
        }
        zxecf[0] = m;
        zxecf[1] = b;
        zxecf[2] = r;

        return zxecf;
    }

    /**
     * 逐差法
     *
     * @param Xi 输入的一组数据
     * @return 返回逐差平均值结果
     */
    public static double ZhuChaFa(double[] Xi) {
        double Sum = 0.0;

        int lxi = Xi.length;

        if (lxi % 2 == 0) {
            double[] MidNum = new double[Xi.length / 2];
            for (int i = 0; i < (lxi / 2); i++) {
                MidNum[i] = Xi[lxi / 2 + i] - Xi[i];

                Sum = Sum + MidNum[i];
            }
            Sum = Sum / (lxi / 2);
        } else {
            double[] MidNum = new double[(lxi - 1) / 2];
            for (int i = 0; i < ((lxi - 1) / 2); i++) {
                MidNum[i] = Xi[(lxi - 1) / 2 + 1 + i] - Xi[i];

                Sum = Sum + MidNum[i];
            }
            Sum = Sum / ((lxi - 1) / 2);
        }

        return Sum;
    }

    /**
     * e指数拟合法：y=A*Math.Exp(B*x)+C；
     *
     * @param Xi X数据组
     * @param Yi Y数据组
     * @return 返回拟合结果：A=ZhiShuNiHeFa[0];B=ZhiShuNiHeFa[1];C=ZhiShuNiHeFa[2];当(B*x)大于30.0以后，A和B正确，但是C值精度不够或者错误
     */
    public static double[] ZhiShuNiHeFa(double[] Xi, double[] Yi) {
        //y=A*Math.Exp(B*x)+C
        double[] zxecf = new double[]{0.0, 0.0, 0.0};
        double A = 0.0;
        double B = 0.0;
        double C = 0.0;

        double[] Temp = new double[Yi.length];
        Temp[0] = 0.0;
        Temp[Yi.length - 1] = 0.0;

        for (int i = 0; i < (Yi.length - 2); i++) {
            double g1 = (Yi[i + 1] - Yi[i]) / (Xi[i + 1] - Xi[i]);//得到i与i+1之间的斜率
            double g2 = (Yi[i + 2] - Yi[i + 1]) / (Xi[i + 2] - Xi[i + 1]);//得到i+1与i+2之间的斜率

            double g = (g1 + g2) / 2.0;//把g1和g2的平均值作为i+1位置处的斜率值
            Temp[i + 1] = g;
        }

        double[] TempB = new double[(Temp.length - 1) * (Temp.length - 1) - (Temp.length - 2)];
        double SumB = 0.0;
        int k = 0;
        for (int i = 1; i < Temp.length - 1; i++) {
            for (int j = 1; j < Temp.length - 1; j++) {
                if (i != j) {
                    TempB[k] = Math.log(Math.pow((Temp[i] / Temp[j]), (1.0 / (Xi[i] - Xi[j]))));
                } else {
                    TempB[k] = 0.0;
                }
                SumB = SumB + TempB[k];
                k = k + 1;
            }
        }
        B = SumB / ((Temp.length - 2) * (Temp.length - 3));
        double[] EBX = new double[Xi.length];
        for (int i = 0; i < EBX.length; i++) {
            EBX[i] = Math.exp(B * Xi[i]);
        }

        A = ZuiXiaoErChengFa(EBX, Yi)[0];
        C = ZuiXiaoErChengFa(EBX, Yi)[1];
        double mm = ZuiXiaoErChengFa(EBX, Yi)[2];

        zxecf[0] = A;
        zxecf[1] = B;
        zxecf[2] = C;
        return zxecf;
    }

    /**
     * 最小二乘拟合多项式：y=a0+a1*x^1+a2*x^2+a3*x^3+...+an*x^N
     *
     * @param Xi X数据组
     * @param Yi Y数据组
     * @param N  多项式最高幂指数为X
     * @return
     */
    public static double[] DuoXiangShiNiHeFa(double[] Xi, double[] Yi, int N) {
        int n = N + 1;
        double[][] Temp = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Temp[i][j] = QiuHe(Xi, i + j);
            }
        }

        Temp[0][0] = Xi.length;

        double[][] TempNiv = new double[n][n];
        TempNiv = MatrixNiv(Temp);

        double[][] TempXNY = new double[n][n];

        for (int i = 0; i < n; i++) {
            double xy = 0.0;
            for (int j = 0; j < Xi.length; j++) {
                xy = xy + Math.pow(Xi[j], i) * Yi[j];
            }
            TempXNY[i][0] = xy;
        }

        double[][] Result = new double[n][n];
        Result = MatrixMulti(TempNiv, TempXNY);

        double[] dxsnh = new double[n];
        for (int i = 0; i < n; i++) {
            dxsnh[i] = Result[i][0];
        }

        return dxsnh;
    }

    /**
     * 先求幂指数和求和累加
     *
     * @param Xi
     * @param n
     * @return
     */
    public static double QiuHe(double[] Xi, int n) {
        double qh = 0.0;

        for (int i = 0; i < Xi.length; i++) {
            qh = qh + Math.pow(Xi[i], n);
        }

        return qh;
    }

    /**
     * 矩阵相乘
     *
     * @param X 第一个矩阵
     * @param Y 第二个矩阵
     * @return
     */
    public static double[][] MatrixMulti(double[][] X, double[][] Y) {
        double[][] A = X;
        double[][] B = Y;
        double[][] C = new double[A[0].length][B[1].length];
        for (int i = 0; i < A[0].length; i++) {
            for (int j = 0; j < B[1].length; j++) {
                C[i][j] = 0;
                for (int k = 0; k < A[1].length; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    /**
     * 矩阵求逆（初等行变换）
     *
     * @param X
     * @return
     */
    public static double[][] MatrixNiv(double[][] X) {
        double[][] u = X;
        int t = u[0].length;
        double[][] q = new double[t][t];

        // 构造单位矩阵
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < t; j++) {
                if (i == j) {
                    q[i][j] = 1;
                } else {
                    q[i][j] = 0;
                }
            }
        }

        // 求左下
        for (int i = 0; i < t; i++) {
            double c = u[i][i];

            for (int k = 0; k < t; k++)  // 换成 for (int k = i; k < t; k++) ？
            {
                u[i][k] = u[i][k] / c;
                q[i][k] = q[i][k] / c;
            }

            for (int j = i + 1; j < t; j++) {
                double l = u[j][i];
                for (int k = 0; k < t; k++) {
                    u[j][k] = u[j][k] - l * u[i][k];

                    q[j][k] = q[j][k] - l * q[i][k];
                }
            }
        }


        // 求右上
        for (int i = t - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                double l = u[j][i];
                for (int k = 0; k < t; k++) {
                    u[j][k] = u[j][k] - l * u[i][k];

                    q[j][k] = q[j][k] - l * q[i][k];
                }
            }
        }

        return q;

    }

    /**
     * 正态分布拟合法：y=(1.0 / (Math.Sqrt(2 * Math.PI) * XiGeMa)) * Math.Exp(-Math.Pow(Xi[i] - MiYou, 2) / (2.0 * XiGeMa * XiGeMa))
     *
     * @param Xi X数据组
     * @param Yi Y数据组
     * @return
     */
    public static double[] ZhengTaiFenBuNiHeFa(double[] Xi, double[] Yi) {
        double[] ztfb = new double[2];
        double XiGeMa = 1.0;
        double MiYou = 0.0;

        double Sum = 0.0;

        for (int i = 0; i < Xi.length; i++) {
            Sum = Sum + Xi[i];
        }
        MiYou = Sum / Xi.length;

        double Temp = Yi[0];
        for (int i = 0; i < Yi.length; i++) {
            if (Temp <= Yi[i]) {
                Temp = Yi[i];
            }
        }
        XiGeMa = 1.0 / (Math.sqrt(2 * Math.PI) * Temp);

        Sum = 0.0;
        while (Sum >= 0.0) {
            double s1 = 0.0;
            double s2 = 0.0;
            double s3 = 0.0;

            for (double i = MiYou; true; ) {
                for (double j = XiGeMa; true; ) {
                    s1 = ZhengTaiFuZhu(Xi, Yi, XiGeMa - XiGeMa * 0.1, MiYou);
                    s2 = ZhengTaiFuZhu(Xi, Yi, XiGeMa, MiYou);
                    s3 = ZhengTaiFuZhu(Xi, Yi, XiGeMa + XiGeMa * 0.1, MiYou);

                    if (s1 < s2 && s3 > s2) {
                        XiGeMa = XiGeMa - XiGeMa * 0.1;
                    } else if (s1 > s2 && s3 < s2) {
                        XiGeMa = XiGeMa + XiGeMa * 0.1;
                    } else if (s1 < s2 && s3 < s2) {
                        if (s1 < s3) {
                            XiGeMa = XiGeMa - XiGeMa * 0.1;
                        } else {
                            XiGeMa = XiGeMa + XiGeMa * 0.1;
                        }
                    } else {
                        break;
                    }
                }

                s1 = ZhengTaiFuZhu(Xi, Yi, XiGeMa, MiYou - MiYou * 0.1);
                s2 = ZhengTaiFuZhu(Xi, Yi, XiGeMa, MiYou);
                s3 = ZhengTaiFuZhu(Xi, Yi, XiGeMa, MiYou + MiYou * 0.1);

                if (s1 < s2 && s3 > s2) {
                    MiYou = MiYou - MiYou * 0.1;
                } else if (s1 > s2 && s3 < s2) {
                    MiYou = MiYou + MiYou * 0.1;
                } else if (s1 < s2 && s3 < s2) {
                    if (s1 < s3) {
                        MiYou = MiYou - MiYou * 0.1;
                    } else {
                        MiYou = MiYou + MiYou * 0.1;
                    }
                } else {
                    break;
                }
            }
            break;
        }

        ztfb[0] = XiGeMa;
        ztfb[1] = MiYou;
        return ztfb;
    }

    /**
     * 辅助进行正态分布计算
     *
     * @param Xi
     * @param Yi
     * @param XGM
     * @param MY
     * @return
     */
    private static double ZhengTaiFuZhu(double[] Xi, double[] Yi, double XGM, double MY) {
        double ztfz = 0.0;
        double Fx = 0.0;
        for (int i = 0; i < Xi.length; i++) {
            Fx = (1.0 / (Math.sqrt(2 * Math.PI) * XGM)) * Math.exp(-Math.pow(Xi[i] - MY, 2) / (2.0 * XGM * XGM));
            ztfz = ztfz + (Fx - Yi[i]) * (Fx - Yi[i]);
        }

        return ztfz;
    }


}
