package com.railvayticketiffice.web.form.response;

import com.railvayticketiffice.web.data.AjaxResponse;

import java.util.List;
import java.util.Map;

public class SeatsResponse extends AjaxResponse {

    private Map<Integer , List<Integer>> freeSeats;

    public Map<Integer, List<Integer>> getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(Map<Integer, List<Integer>> freeSeats) {
        this.freeSeats = freeSeats;
    }

}
