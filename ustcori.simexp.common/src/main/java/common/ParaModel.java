package common;

import java.util.List;

/**
 * @author Administrator
 */
public class ParaModel {
    private List<PhyParas> phyParasList;
    private List<PhyParas> tempPhyParasList;

    public List<PhyParas> getPhyParasList() {
        return phyParasList;
    }

    public void setPhyParasList(List<PhyParas> phyParasList) {
        this.phyParasList = phyParasList;
    }

    public List<PhyParas> getTempPhyParasList() {
        return tempPhyParasList;
    }

    public void setTempPhyParasList(List<PhyParas> tempPhyParasList) {
        this.tempPhyParasList = tempPhyParasList;
    }
}
