import common.ParaModel;
import common.PhyParas;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class CopyData {

    /**
     * 复制原始数据，同时可以保证，即使Dll中没有进行任何处理的情况下，也能够使大厅的得到的数据结果不为空。
     * @param paras 由大厅传入的XML数据参数(不包含标准值)：List_PhyParas
     * @return
     */
    public static ParaModel CopyParas(List<PhyParas> paras)
    {
        ParaModel paraModel=new ParaModel();
        paraModel.setTempPhyParasList(paras);
        List<PhyParas> ParaList=new ArrayList<PhyParas>();
        for (PhyParas item : paras)
        {
            if (item._Formula != null && !"".equals(item._Formula) && item._DataSourse != null && item._DataSourse.stream().count() > 0)
            {
                if ("TLIST".equalsIgnoreCase(item._Type))
                {
                    item._StandardValue = item._StudentValue;
                } else if ("MATRIXLIST".equalsIgnoreCase(item._Type)) {
                    //MATRIXLIST类型的数据源可以是自身，由于计算时是根据数据源的标准值_StandardValue进行计算，所以TLIST类型标准值要提前赋值，计算完成后再重新赋计算值
                    item._StandardValue = item._StudentValue;
                } else {
                    //间接测量值，需要计算，复制时先赋值为空
                    item._StandardValue = null;
                }
            }
            else
            {
                item._StandardValue = item._StudentValue;
            }
            ParaList.add(item.Clone());
        }
        paraModel.setPhyParasList(ParaList);
        return  paraModel;
    }

    /**
     * 已经完成原始参数的数据计算，再次将计算过程中用到的数值类型（特别是表格）按照传入时的格式进行重写，以便于让大厅得到与原始数据格式一致的标准结果
     * @param paras 计算完成后包含完整结果的数据
     * @param tempparas 将完整结果数据赋值到的缓存数据
     */
    public static List<PhyParas> ReCopyParas(List<PhyParas> paras,  List<PhyParas> tempparas)
    {
        for (int i = 0; i < paras.stream().count(); i++)
        {
            if (tempparas.get(i)._Name.equals(paras.get(i)._Name))
            {
                tempparas.get(i)._StandardValue = paras.get(i)._StandardValue;
            }
        }
        return tempparas;
    }
}
