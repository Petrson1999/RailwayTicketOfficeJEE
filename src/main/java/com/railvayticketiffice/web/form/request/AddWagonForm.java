package com.railvayticketiffice.web.form.request;

public class AddWagonForm {

    private int typeId;

    private String name;

    public AddWagonForm( String name ,int typeId) {
        this.typeId = typeId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
