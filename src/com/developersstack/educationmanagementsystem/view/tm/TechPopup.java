package com.developersstack.educationmanagementsystem.view.tm;

public class TechPopup {

    private String id;
    private String technologiesName;


    public TechPopup() {}
    public TechPopup(String id, String technologiesName) {
        this.id = id;
        this.technologiesName = technologiesName;
    }


    public String getId() {return id;}
    public String getTechnologiesName() {return technologiesName;}


    public void setId(String id) {this.id = id;}
    public void setTechnologiesName(String technologiesName) {this.technologiesName = technologiesName;}
}
