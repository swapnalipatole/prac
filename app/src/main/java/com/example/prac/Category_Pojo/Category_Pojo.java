package com.example.prac.Category_Pojo;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category_Pojo implements Serializable {

        @SerializedName("cats")
        @Expose
        private List<Cat> cats = null;
        private final static long serialVersionUID = -5425391689214310835L;

        public List<Cat> getCats() {
            return cats;
        }

        public void setCats(List<Cat> cats) {
            this.cats = cats;
        }

}
