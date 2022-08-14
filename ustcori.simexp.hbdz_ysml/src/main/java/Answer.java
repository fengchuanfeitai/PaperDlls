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

    /**
     * 根据不同参数，进行标准值求解过程
     */
    public void CalParasMethod() {
        try {
            for (int num = 0; num < ParaList.size(); num++) {

                switch (ParaList.get(num)._Formula) {
                    // region 实验数据记录表
                    case "GS_YSLM_SJBG": {
                        ParaList.set(num, Function.GS_YSLM_SJBG(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 用逐差法处理数据，计算出每增减荷重3个砝码标尺读数△n
                    case "GS_YSLM_ZCF": {
                        ParaList.set(num, Function.GS_YSLM_ZCF(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 每增减荷重3个砝码标尺读数△n的平均值
                    case "GS_YSML_nPJ": {
                        ParaList.set(num, Function.GS_YSML_nPJ(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 出钢丝直径d的平均值
                    case "GS_YSML_dPJ": {
                        ParaList.set(num, Function.GS_YSML_dPJ(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 记录金属丝原长的测量值L
                    case "GS_YSML_L": {
                        ParaList.set(num, Function.GS_YSML_L(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 记录光杠杆平面镜到标尺的距离的测量值D
                    case "GS_YSML_D": {
                        ParaList.set(num, Function.GS_YSML_D(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 记录光杠杆臂长的测量值b
                    case "GS_YSML_b": {
                        ParaList.set(num, Function.GS_YSML_b(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 计算出金属丝原长L的不确定度u (L)
                    case "GS_YSML_Ul": {
                        ParaList.set(num, Function.GS_YSML_Ul(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 计算出光杠杆平面镜到标尺的距离D的不确定度u (D)
                    case "GS_YSML_UD": {
                        ParaList.set(num, Function.GS_YSML_UD(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 计算出光杠杆臂长b的不确定度u (b)

                    case "GS_YSML_Ub": {
                        ParaList.set(num, Function.GS_YSML_Ub(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 计算出金属丝直径d的A类不确定度
                    case "GS_YSML_UAd": {
                        ParaList.set(num, Function.GS_YSML_UAd(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 计算出金属丝直径d的B类不确定度
                    case "GS_YSML_UBd": {
                        ParaList.set(num, Function.GS_YSML_UBd(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 计算出金属丝直径d的不确定度
                    case "GS_YSML_UZJ": {
                        ParaList.set(num, Function.GS_YSML_UZJ(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 计算出△n平均值的A类不确定度
                    case "GS_YSML_nPJA": {
                        ParaList.set(num, Function.GS_YSML_nPJA(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 计算出△n平均值的B类不确定度
                    case "GS_YSML_nPJB": {
                        ParaList.set(num, Function.GS_YSML_nPJB(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 计算出△n平均值的不确定度
                    case "GS_YSML_nPJBQD": {
                        ParaList.set(num, Function.GS_YSML_nPJBQD(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 杨氏模量的结果表达式
                    case "GS_YSML_YSMLExpression": {
                        ParaList.set(num, Function.GS_YSML_YSMLExpression(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
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

    public List<PhyParas> DStoPP(List<String> paraDataSourse) {
        List<PhyParas> lp = new ArrayList<PhyParas>();

        for (int i = 0; i < paraDataSourse.stream().count(); i++) {
            PhyParas p = ReadParam(paraDataSourse.get(i));
            lp.add(p);
        }

        return lp;
    }

}
