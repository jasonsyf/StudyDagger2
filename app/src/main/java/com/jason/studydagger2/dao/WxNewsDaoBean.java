package com.jason.studydagger2.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Jason_Sunyf on 2017/9/11 0011.
 * Email： jason_sunyf@163.com
 */
@Entity
public class WxNewsDaoBean {
    public static final int TYPE_CART = 0x01;
    public static final int TYPE_COLLECT = 0x02;
    @Id(autoincrement = true)
    private Long id;
    private String ctime;
    private String title;
    private String description;
    private String picUrl;
    private String url;
    @Generated(hash = 230612438)
    public WxNewsDaoBean(Long id, String ctime, String title, String description,
            String picUrl, String url) {
        this.id = id;
        this.ctime = ctime;
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
        this.url = url;
    }
    @Generated(hash = 916558324)
    public WxNewsDaoBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCtime() {
        return this.ctime;
    }
    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPicUrl() {
        return this.picUrl;
    }
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
