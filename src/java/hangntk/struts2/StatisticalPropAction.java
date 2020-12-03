/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import hangntk.daos.TribulationPropertiesDAO;
import hangntk.dtos.TribulationPropertiesDTO;
import hangntk.dtos.UserDTO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class StatisticalPropAction extends ActionSupport {

    private List<TribulationPropertiesDTO> list;
    private String startime;
    private String endtime;

    public StatisticalPropAction() {
    }

    public String execute() throws Exception {
        TribulationPropertiesDAO dao = new TribulationPropertiesDAO();
        Map session = ActionContext.getContext().getSession();
        UserDTO dto = (UserDTO) session.get("INFO");
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        Date star = formater.parse(startime);
        Date end = formater.parse(endtime);
        list = dao.loadListTribuPropdependDate(dto.getUserID(), star, end);

        return "success";
    }

    @Override
    public void validate() {
        super.validate(); //To change body of generated methods, choose Tools | Templates.
        if (endtime != null && startime != null) {
            if (endtime.compareTo(startime) < 0) {
                addFieldError("startime", "Date End must after Date Start");
            }
        }
    }

    public List<TribulationPropertiesDTO> getList() {
        return list;
    }

    public String getStartime() {
        return startime;
    }

    public void setStartime(String startime) {
        this.startime = startime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

}
