package com.LoDeNico.Verduleria.Service.Producto;

import com.LoDeNico.Verduleria.Dto.Request.MontoRequest;
import com.LoDeNico.Verduleria.Dto.Request.Producto.ProductoRequest;
import com.LoDeNico.Verduleria.Dto.Response.Producto.ProductoResponse;

import java.util.List;

public interface ProductoService {
    public ProductoResponse getProducto(Long id);
    public List<ProductoResponse> getProductoList(boolean pl);
    public int deleteProducto(Long id);
    public ProductoResponse createProducto(ProductoRequest productoRequest);
    public ProductoResponse updateProducto(ProductoRequest productoRequest, Long id);
    public int updateStock(Long id, int n);
    public List<ProductoResponse> busProducto(String nombre, int uni, MontoRequest montoRequest);
}
