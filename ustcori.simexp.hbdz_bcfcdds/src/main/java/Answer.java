import common.ParaModel;
import common.PhyParas;

import java.util.ArrayList;
import java.util.List;

/**
 * 实验数据标准答案计算类
 *
 * @author cp
 */
public class Answer {
    // region Field

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
     *
     * @param paras 由大厅传入的XML数据参数(不包含标准值)：List_PhyParas
     * @return 返回给实验大厅的参数完整数据结果
     */
    public List<PhyParas> InitMethod(List<PhyParas> paras) {
        ParaList = new ArrayList<PhyParas>();
        TempParas = new ArrayList<PhyParas>();
        ParaModel paraModel = CopyData.CopyParas(paras);
        ParaList = paraModel.getPhyParasList();
        //使用缓存数据进行数据计算
        CalParasMethod();
        //对完成计算的缓存数据进行转换,转换为大厅传入的标准格式，并存放到ParaList中
        TempParas = CopyData.ReCopyParas(ParaList, paraModel.getTempPhyParasList());
        //将参数返回到实验大厅
        return TempParas;
    }

    // endregion

    // region Read and Set Param

    public void SetParam(String name, PhyParas value) {
        for (int i = 0; i < ParaList.stream().count(); i++) {
            if (name.equals(ParaList.get(i)._Name)) {
                ParaList.set(i, value);
            }
        }
    }

    public PhyParas ReadParam(String name) {
        PhyParas para = new PhyParas();

        for (PhyParas item : ParaList) {
            if (name.equals(item._Name)) {
                para = item;
            }
        }

        if (!"".equals(para._Name)) {
            return para;
        } else {
            return null;
        }
    }
    // endregion

    // region Method

    /**
     * 根据不同参数，进行标准值求解过程
     */
    public void CalParasMethod() {
        try {
            for (int num = 0; num < ParaList.size(); num++) {

                switch (ParaList.get(num)._Formula) {


                    // region 实验数据记录表
                    case "GS_BCFCDDS_SJBG": {
                        ParaList.set(num, Function.GS_BCFCDDS_SJBG(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 计算电阻丝长度L'CD六次测量的平均值
                    case "GS_BCFCDDS_Lcdpj": {
                        ParaList.set(num, Function.GS_BCFCDDS_Lcdpj(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 记录数据值L
                    case "GS_BCFCDDS_L": {
                        ParaList.set(num, Function.GS_BCFCDDS_L(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 记录数据值L'
                    case "GS_BCFCDDS_L2": {
                        ParaList.set(num, Function.GS_BCFCDDS_L2(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 根据公式计算出L'CD的最大误差
                    case "GS_BCFCDDS_Lwc": {
                        ParaList.set(num, Function.GS_BCFCDDS_Lwc(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 求出待测电池的电动势
                    case "GS_BCFCDDS_Expj": {
                        ParaList.set(num, Function.GS_BCFCDDS_Expj(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region L'CD的A类不确定度

                    case "GS_BCFCDDS_UALcd": {
                        ParaList.set(num, Function.GS_BCFCDDS_UALcd(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region L'CD的B类不确定度
                    case "GS_BCFCDDS_UBLcd": {
                        ParaList.set(num, Function.GS_BCFCDDS_UBLcd(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region LcD的合成不确定度
                    case "GS_BCFCDDS_ULcd": {
                        ParaList.set(num, Function.GS_BCFCDDS_ULcd(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 考虑A的B类不确定度则
                    case "GS_BCFCDDS_UABLcd": {
                        ParaList.set(num, Function.GS_BCFCDDS_UABLcd(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 由相对不确定度传递公式可得，Ex的相对不确定度
                    case "GS_BCFCDDS_UrEx": {
                        ParaList.set(num, Function.GS_BCFCDDS_UrEx(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region Ex的不确定度
                    case "GS_BCFCDDS_UEx": {
                        ParaList.set(num, Function.GS_BCFCDDS_UEx(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 待测电池的电动势的结果表达式
                    case "GS_BCFCDDS_BDS": {
                        ParaList.set(num, Function.GS_BCFCDDS_BDS(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion
                    default:
                        break;
                }
            }
        } catch (Exception ex) {
        }
    }

    // endregion

    public List<PhyParas> DStoPP(List<String> paraDataSourse) {
        List<PhyParas> lp = new ArrayList<PhyParas>();

        for (int i = 0; i < paraDataSourse.stream().count(); i++) {
            PhyParas p = ReadParam(paraDataSourse.get(i));
            lp.add(p);
        }

        return lp;
    }


}