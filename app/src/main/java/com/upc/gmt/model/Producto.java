package com.upc.gmt.model;

import java.math.BigDecimal;
import java.util.Date;

public class Producto {
    private Integer idProducto;

    private String SKU;

    private String descripcion;

    private Integer idTipoProducto;

    private String genero;

    private BigDecimal precioUnitario;

    private BigDecimal precioVendedor;

    private String estilo;

    private String estado;

    private Integer stockVenta;

    private Integer stockObservado;

    private Integer stockCritico;

    private Date fechaUltimaCarga;

    private Date fechaProximaCarga;

    private String usuarioGenero;

    private Date fechaGeneracion;

    private String usuarioModifico;

    private Date fechaModificacion;

    private BigDecimal altoPaquete;

    private BigDecimal anchoPaquete;

    private BigDecimal largoPaquete;

    private String observacion;

    private Integer idTemporada;

    private Integer idUMD;

    private BigDecimal peso;

    //ADICIONAL
    private String tipoProducto;
    private String temporada;
    private String idColor;
    private String color;
    private Integer nroTalla; 
    private String componente;
    private String material;
    
    public Producto(Integer idProducto, String SKU, String descripcion, Integer idTipoProducto, String genero, BigDecimal precioUnitario, BigDecimal precioVendedor, String estilo, String estado, Integer stockVenta, Integer stockObservado, Integer stockCritico, Date fechaUltimaCarga, Date fechaProximaCarga, String usuarioGenero, Date fechaGeneracion, String usuarioModifico, Date fechaModificacion, BigDecimal altoPaquete, BigDecimal anchoPaquete, BigDecimal largoPaquete, String observacion, Integer idTemporada, Integer idUMD, BigDecimal peso) {
        this.idProducto = idProducto;
        this.SKU = SKU;
        this.descripcion = descripcion;
        this.idTipoProducto = idTipoProducto;
        this.genero = genero;
        this.precioUnitario = precioUnitario;
        this.precioVendedor = precioVendedor;
        this.estilo = estilo;
        this.estado = estado;
        this.stockVenta = stockVenta;
        this.stockObservado = stockObservado;
        this.stockCritico = stockCritico;
        this.fechaUltimaCarga = fechaUltimaCarga;
        this.fechaProximaCarga = fechaProximaCarga;
        this.usuarioGenero = usuarioGenero;
        this.fechaGeneracion = fechaGeneracion;
        this.usuarioModifico = usuarioModifico;
        this.fechaModificacion = fechaModificacion;
        this.altoPaquete = altoPaquete;
        this.anchoPaquete = anchoPaquete;
        this.largoPaquete = largoPaquete;
        this.observacion = observacion;
        this.idTemporada = idTemporada;
        this.idUMD = idUMD;
        this.peso = peso;
    }

    public Producto() {
        super();
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU == null ? null : SKU.trim();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion == null ? null : descripcion.trim();
    }

    public Integer getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(Integer idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero == null ? null : genero.trim();
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getPrecioVendedor() {
        return precioVendedor;
    }

    public void setPrecioVendedor(BigDecimal precioVendedor) {
        this.precioVendedor = precioVendedor;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo == null ? null : estilo.trim();
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado == null ? null : estado.trim();
    }

    public Integer getStockVenta() {
        return stockVenta;
    }

    public void setStockVenta(Integer stockVenta) {
        this.stockVenta = stockVenta;
    }

    public Integer getStockObservado() {
        return stockObservado;
    }

    public void setStockObservado(Integer stockObservado) {
        this.stockObservado = stockObservado;
    }

    public Integer getStockCritico() {
        return stockCritico;
    }

    public void setStockCritico(Integer stockCritico) {
        this.stockCritico = stockCritico;
    }

    public Date getFechaUltimaCarga() {
        return fechaUltimaCarga;
    }

    public void setFechaUltimaCarga(Date fechaUltimaCarga) {
        this.fechaUltimaCarga = fechaUltimaCarga;
    }

    public Date getFechaProximaCarga() {
        return fechaProximaCarga;
    }

    public void setFechaProximaCarga(Date fechaProximaCarga) {
        this.fechaProximaCarga = fechaProximaCarga;
    }

    public String getUsuarioGenero() {
        return usuarioGenero;
    }

    public void setUsuarioGenero(String usuarioGenero) {
        this.usuarioGenero = usuarioGenero == null ? null : usuarioGenero.trim();
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getUsuarioModifico() {
        return usuarioModifico;
    }

    public void setUsuarioModifico(String usuarioModifico) {
        this.usuarioModifico = usuarioModifico == null ? null : usuarioModifico.trim();
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public BigDecimal getAltoPaquete() {
        return altoPaquete;
    }

    public void setAltoPaquete(BigDecimal altoPaquete) {
        this.altoPaquete = altoPaquete;
    }

    public BigDecimal getAnchoPaquete() {
        return anchoPaquete;
    }

    public void setAnchoPaquete(BigDecimal anchoPaquete) {
        this.anchoPaquete = anchoPaquete;
    }

    public BigDecimal getLargoPaquete() {
        return largoPaquete;
    }

    public void setLargoPaquete(BigDecimal largoPaquete) {
        this.largoPaquete = largoPaquete;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion == null ? null : observacion.trim();
    }

    public Integer getIdTemporada() {
        return idTemporada;
    }

    public void setIdTemporada(Integer idTemporada) {
        this.idTemporada = idTemporada;
    }

    public Integer getIdUMD() {
        return idUMD;
    }

    public void setIdUMD(Integer idUMD) {
        this.idUMD = idUMD;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getNroTalla() {
		return nroTalla;
	}

	public void setNroTalla(Integer nroTalla) {
		this.nroTalla = nroTalla;
	}

	public String getIdColor() {
		return idColor;
	}

	public void setIdColor(String idColor) {
		this.idColor = idColor;
	}

	public String getComponente() {
		return componente;
	}

	public void setComponente(String componente) {
		this.componente = componente;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Producto [idProducto=");
		builder.append(idProducto);
		builder.append(", SKU=");
		builder.append(SKU);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", idTipoProducto=");
		builder.append(idTipoProducto);
		builder.append(", genero=");
		builder.append(genero);
		builder.append(", precioUnitario=");
		builder.append(precioUnitario);
		builder.append(", precioVendedor=");
		builder.append(precioVendedor);
		builder.append(", estilo=");
		builder.append(estilo);
		builder.append(", estado=");
		builder.append(estado);
		builder.append(", stockVenta=");
		builder.append(stockVenta);
		builder.append(", stockObservado=");
		builder.append(stockObservado);
		builder.append(", stockCritico=");
		builder.append(stockCritico);
		builder.append(", fechaUltimaCarga=");
		builder.append(fechaUltimaCarga);
		builder.append(", fechaProximaCarga=");
		builder.append(fechaProximaCarga);
		builder.append(", usuarioGenero=");
		builder.append(usuarioGenero);
		builder.append(", fechaGeneracion=");
		builder.append(fechaGeneracion);
		builder.append(", usuarioModifico=");
		builder.append(usuarioModifico);
		builder.append(", fechaModificacion=");
		builder.append(fechaModificacion);
		builder.append(", altoPaquete=");
		builder.append(altoPaquete);
		builder.append(", anchoPaquete=");
		builder.append(anchoPaquete);
		builder.append(", largoPaquete=");
		builder.append(largoPaquete);
		builder.append(", observacion=");
		builder.append(observacion);
		builder.append(", idTemporada=");
		builder.append(idTemporada);
		builder.append(", idUMD=");
		builder.append(idUMD);
		builder.append(", peso=");
		builder.append(peso);
		builder.append(", tipoProducto=");
		builder.append(tipoProducto);
		builder.append(", temporada=");
		builder.append(temporada);
		builder.append(", idColor=");
		builder.append(idColor);
		builder.append(", color=");
		builder.append(color);
		builder.append(", nroTalla=");
		builder.append(nroTalla);
		builder.append(", componente=");
		builder.append(componente);
		builder.append(", material=");
		builder.append(material);
		builder.append("]");
		return builder.toString();
	}
}