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

                    case "PHTJ1":
                    {
                        ParaList.set(num,Function.PHTJ1(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }

                    case "PHTJ2":
                    {
                        ParaList.set(num,Function.PHTJ2(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }

                    case "M1":
                    {
                        ParaList.set(num,Function.M1(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }

                    case "M2":
                    {
                        ParaList.set(num,Function.M2(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }

                    case "SJBG1":
                    {
                        ParaList.set(num,Function.SJBG1(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }


                    case "tM1":
                    {
                        ParaList.set(num,Function.tM1(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }
                    case "tM2":
                    {
                        ParaList.set(num,Function.tM2(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }

                    case "SJBG2":
                    {
                        ParaList.set(num,Function.SJBG2(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

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
