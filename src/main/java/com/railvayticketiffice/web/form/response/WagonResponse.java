package com.railvayticketiffice.web.form.response;

import com.railvayticketiffice.dto.WagonDto;
import com.railvayticketiffice.web.data.AjaxResponse;

import java.util.List;

public class WagonResponse extends AjaxResponse {

    private List<WagonDto> wagonDtos;

    public List<WagonDto> getWagonDtos() {
        return wagonDtos;
    }

    public void setWagonDtos(List<WagonDto> wagonDtos) {
        this.wagonDtos = wagonDtos;
    }
}
