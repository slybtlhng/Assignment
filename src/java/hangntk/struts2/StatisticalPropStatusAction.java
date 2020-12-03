/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import hangntk.daos.PropertiesDAO;
import hangntk.dtos.PropertiesDTO;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class StatisticalPropStatusAction {

    private String searchValue;
    private final String SUCCESS = "success";
    private List<PropertiesDTO> listProperties;

    public StatisticalPropStatusAction() {
    }

    public String execute() throws Exception {
        PropertiesDAO dao = new PropertiesDAO();
        int status=-1;
        if(searchValue.equals("In stock")){
            status=1;
        }else{
            status=0;
        }
        listProperties = dao.findByStatus(status);
        return SUCCESS;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public List<PropertiesDTO> getListProperties() {
        return listProperties;
    }

    public void setListProperties(List<PropertiesDTO> listProperties) {
        this.listProperties = listProperties;
    }

}
