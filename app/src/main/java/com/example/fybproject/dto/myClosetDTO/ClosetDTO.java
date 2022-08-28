package com.example.fybproject.dto.myClosetDTO;

public class ClosetDTO {
    private long id;
    private long uid;
    private int pnotes;
    private String pname;
    private String pkind;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public int getPnotes() {
        return pnotes;
    }

    public void setPnotes(int pnotes) {
        this.pnotes = pnotes;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPkind() {
        return pkind;
    }

    public void setPkind(String pkind) {
        this.pkind = pkind;
    }

    @Override
    public String toString() {
        return "PostResult {" +
                "uid = " + uid +
                ", pname = " + pname +
                ", pnotes = " + pnotes +
                ", pkind = " + pkind +
                '}';
    }
}
