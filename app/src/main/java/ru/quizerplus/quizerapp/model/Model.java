package ru.quizerplus.quizerapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Model {
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("info")
        @Expose
        private String info;
        @SerializedName("offers")
        @Expose
        private List<Offer> offers = null;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public List<Offer> getOffers() {
            return offers;
        }

        public void setOffers(List<Offer> offers) {
            this.offers = offers;
        }

    }