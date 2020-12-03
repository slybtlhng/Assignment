/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import com.opensymphony.xwork2.ActionContext;
import hangntk.daos.PartDAO;
import hangntk.daos.TribulationDAO;
import hangntk.dtos.TribulationDTO;
import hangntk.dtos.UserDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class LoadCurrentTribuAction {

    private List<TribulationDTO> listTribu;

    public LoadCurrentTribuAction() {
    }

    public String execute() throws Exception {
        TribulationDAO daoTribu = new TribulationDAO();
        PartDAO daoPart = new PartDAO();
        Map session = ActionContext.getContext().getSession();
        UserDTO dto = (UserDTO) session.get("INFO");
        String castID = dto.getUserID();

        List<String> listTribulationID = daoPart.findTribulationIDbyCastID(castID);
        Date currentDate = new Date();

        listTribu = new ArrayList<>();
        for (String tribualtionID : listTribulationID) {
            TribulationDTO dtoTribu = daoTribu.findByPrimaryKey(tribualtionID);
            if (currentDate.compareTo(dtoTribu.getTribulationEndTime()) < 0) {
                listTribu.add(dtoTribu);
            }
        }

        return "success";
    }

    public List<TribulationDTO> getListTribu() {
        return listTribu;
    }

}
