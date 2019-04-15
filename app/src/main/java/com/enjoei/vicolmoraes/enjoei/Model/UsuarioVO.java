package com.enjoei.vicolmoraes.enjoei.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioVO {
    @JsonProperty("id")
    private int idUsuario;

    @JsonProperty("name")
    private String name;

    @JsonProperty("avatar")
    private FotoBO avatar;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FotoBO getAvatar() {
        return avatar;
    }

    public void setAvatar(FotoBO avatar) {
        this.avatar = avatar;
    }

}
