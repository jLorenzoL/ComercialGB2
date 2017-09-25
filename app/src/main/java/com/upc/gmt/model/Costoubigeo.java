package com.upc.gmt.model;

import java.math.BigDecimal;

public class Costoubigeo {
    private String codUbigeoCosto;

    private String descripion;

    private BigDecimal costoEnvio;

    private String estado;

    public Costoubigeo(String codUbigeoCosto, String descripion, BigDecimal costoEnvio, String estado) {
        this.codUbigeoCosto = codUbigeoCosto;
        this.descripion = descripion;
        this.costoEnvio = costoEnvio;
        this.estado = estado;
    }

    public Costoubigeo() {
        super();
    }

    public String getCodUbigeoCosto() {
        return codUbigeoCosto;
    }

    public void setCodUbigeoCosto(String codUbigeoCosto) {
        this.codUbigeoCosto = codUbigeoCosto == null ? null : codUbigeoCosto.trim();
    }

    public String getDescripion() {
        return descripion;
    }

    public void setDescripion(String descripion) {
        this.descripion = descripion == null ? null : descripion.trim();
    }

    public BigDecimal getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(BigDecimal costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado == null ? null : estado.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", codUbigeoCosto=").append(codUbigeoCosto);
        sb.append(", descripion=").append(descripion);
        sb.append(", costoEnvio=").append(costoEnvio);
        sb.append(", estado=").append(estado);
        sb.append("]");
        return sb.toString();
    }
}