import common.ParaModel;
import common.PhyParas;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class Answer {

    /**
     * 存放原始参数，该列表值直接参数计算
     */
    List<PhyParas> ParaList;

    /**
     * 存放缓存的各个参数计算结果的List,相当于原始数据的数值备份： List_PhyParas
     */
    List<PhyParas> TempParas;

    /**
     * 通用算法接口，供实验大厅调用
     * @param paras 由大厅传入的XML数据参数(不包含标准值)：List_PhyParas
     * @return 返回给实验大厅的参数完整数据结果
     */
    public List<PhyParas> InitMethod(List<PhyParas> paras)
    {
        ParaList = new ArrayList<PhyParas>();
        TempParas = new ArrayList<PhyParas>();
        ParaModel paraModel = CopyData.CopyParas(paras);
        ParaList=paraModel.getPhyParasList();
        //使用缓存数据进行数据计算
        CalParasMethod();
        //对完成计算的缓存数据进行转换,转换为大厅传入的标准格式，并存放到ParaList中
        TempParas = CopyData.ReCopyParas(ParaList, paraModel.getTempPhyParasList());
        //将参数返回到实验大厅
        return TempParas;
    }

    public void SetParam(String name, PhyParas value)
    {
        for (int i = 0; i < ParaList.stream().count(); i++)
        {
            if (name.equals(ParaList.get(i)._Name))
            {
                ParaList.set(i,value);
            }
        }
    }

    public PhyParas ReadParam(String name)
    {
        PhyParas para = new PhyParas();

        for (PhyParas item : ParaList)
        {
            if (name.equals(item._Name))
            {
                para = item;
            }
        }

        if (!"".equals(para._Name))
        {
            return para;
        }
        else
        {
            return null;
        }
    }

    public void CalParasMethod()
    {
        try
        {
            for (int num = 0; num < ParaList.stream().count(); num++)
            {

                switch (ParaList.get(num)._Formula)
                {

                // 第一部分
                    //第一次测量
                    case "T11":
                    {
                        ParaList.set(num,Function.T11(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    //第二次测量
                    case "T21":
                    {
                        ParaList.set(num,Function.T21(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    //第三次测量
                    case "T31":
                    {
                        ParaList.set(num,Function.T31(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }



                    //数据表格 干涉环直径平均值，不确定度
                    case "T61":
                    {
                        ParaList.set(num,Function.T61(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    //数据表格 干涉环直径平方差、不确定度
                    case "T71":
                    {
                        ParaList.set(num,Function.T71(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    //干涉环直径平方差-平均值
                    case "T81":
                    {
                        ParaList.set(num,Function.T81(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    //干涉环直径平方差-不确定度
                    case "T91":
                    {
                        ParaList.set(num,Function.T91(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    //平凸透镜的曲率半径
                    case "T111":
                    {
                        ParaList.set(num,Function.T111(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    //平凸透镜的曲率半径的不确定度
                    case "T121":
                    {
                        ParaList.set(num,Function.T121(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    //平凸透镜的曲率半径的完整表达形式：R ± UR
                    case "T141":
                    {
                        ParaList.set(num,Function.T141(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

              //第二部分
                    //劈尖长度的不确定度UL
                    case "T161":
                    {
                        ParaList.set(num,Function.T161(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    //20条暗纹长度：数据表格 【方法同第一部分： 干涉环半径表格,第1,2,3次测量】
                    case "T181":
                    {
                        ParaList.set(num,Function.T181(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }


                    default:
                        break;
                }

            }

        }
        catch (Exception exception)
        { }
    }


    public List<PhyParas> DStoPP(List<String> paraDataSourse)
    {
        List<PhyParas> lp = new ArrayList<PhyParas>();

        for (int i = 0; i < paraDataSourse.stream().count(); i++)
        {
            PhyParas p = ReadParam(paraDataSourse.get(i));
            lp.add(p);
        }

        return lp;
    }

}
