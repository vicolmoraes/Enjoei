package com.enjoei.vicolmoraes.enjoei.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
class FotoBO {
    @JsonProperty("public_id")
    private String public_id;

    @JsonProperty("crop")
    private String crop;

    @JsonProperty("gravity")
    private String gravity;

    public String getPublic_id() {
        return public_id;
    }

    public void setPublic_id(String public_id) {
        this.public_id = public_id;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public String gerarUrl() {
        return "http://res.cloudinary.com/demo/image/upload/c_" + this.crop + "g_" + this.gravity + "w_150,h_200/" + this.public_id + ".jpg";
    }

}
