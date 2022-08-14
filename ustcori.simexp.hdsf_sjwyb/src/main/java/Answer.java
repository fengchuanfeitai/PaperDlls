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
                    // region 1.电压表改装
                    case "A_Ig_FH": {
                        ParaList.set(num, Function.A_Ig_FH(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "A_r_FH": {
                        ParaList.set(num, Function.A_r_FH(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "A_R1_FH": {
                        ParaList.set(num, Function.A_R1_FH(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "A_V_FH": {
                        ParaList.set(num, Function.A_V_FH(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 2.电流表改装
                    case "B_Ig_FH": {
                        ParaList.set(num, Function.B_Ig_FH(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "B_r_FH": {
                        ParaList.set(num, Function.B_r_FH(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "B_R1_FH": {
                        ParaList.set(num, Function.B_R1_FH(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "B_I_FH": {
                        ParaList.set(num, Function.B_I_FH(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 3.欧姆表改装
                    case "C_Ig_FH": {
                        ParaList.set(num, Function.C_Ig_FH(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "C_r_FH": {
                        ParaList.set(num, Function.C_r_FH(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "C_R1_FH": {
                        ParaList.set(num, Function.C_R1_FH(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "C_R2_FH": {
                        ParaList.set(num, Function.C_R2_FH(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "C_R3_FH": {
                        ParaList.set(num, Function.C_R3_FH(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 4.万用表改装
                    case "D_Ig_FH": {
                        ParaList.set(num, Function.D_Ig_FH(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "D_r_FH": {
                        ParaList.set(num, Function.D_r_FH(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    // endregion

                    // region 5.根据改装后得电流表满偏量程为10mA,电压表满偏量程为5V和欧姆表100Ω校准为100Ω得要求，分别计算出所需电流当分流电阻值、电压档分压电阻值、欧姆档调零电阻值、欧姆档校准电阻值。
                    case "E_R1_FH": {
                        ParaList.set(num, Function.E_R1_FH(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "E_R2_FH": {
                        ParaList.set(num, Function.E_R2_FH(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "E_R3_FH": {
                        ParaList.set(num, Function.E_R3_FH(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "E_R4_FH": {
                        ParaList.set(num, Function.E_R4_FH(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "E_I_FH": {
                        ParaList.set(num, Function.E_I_FH(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "E_V_FH": {
                        ParaList.set(num, Function.E_V_FH(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "E_R_FH": {
                        ParaList.set(num, Function.E_R_FH(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
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
