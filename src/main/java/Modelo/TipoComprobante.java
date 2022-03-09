package Modelo;

import lombok.Data;

@Data
public class TipoComprobante {
  private int idTipoComprobante;
  private String tipoComprobante;
  private boolean estado;
}
