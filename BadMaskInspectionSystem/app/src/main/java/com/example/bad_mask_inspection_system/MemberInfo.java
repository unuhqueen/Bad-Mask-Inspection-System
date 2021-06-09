package com.example.bad_mask_inspection_system;

import android.widget.EditText;

//회원 정보 가져오는 기능

public class MemberInfo {
    private String name;
    private String ownNumber;
    private String email;
    private String uid;

    public MemberInfo(String name, String ownNumber, String email, String uid) {
        this.name = name;
        this.ownNumber = ownNumber;
        this.email = email;
        this.uid = uid;
    }

    public String getName() {
        return this.name;
    }

    public void setName() {
        this.name = name;
    }

    public String getOwnNumber() {
        return this.ownNumber;
    }

    public void setOwnNumber() {
        this.ownNumber = ownNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail() {
        this.email = email;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid() {
        this.uid = uid;
    }
}
