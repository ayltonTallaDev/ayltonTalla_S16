package vallegrande.edu.pe.sistema_videojuegos.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import vallegrande.edu.pe.sistema_videojuegos.dao.ProductoDAO;
import vallegrande.edu.pe.sistema_videojuegos.model.Producto;

import java.time.LocalDate;

public class ProductoController {

    @FXML private TextField txtTitulo;
    @FXML private TextField txtPlataforma;
    @FXML private TextField txtGenero;
    @FXML private TextField txtClasificacion;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtStock;
    @FXML private DatePicker dpFechaLanzamiento;

    @FXML private TableView<Producto> tablaProductos;
    @FXML private TableColumn<Producto, Integer> colId;
    @FXML private TableColumn<Producto, String> colTitulo;
    @FXML private TableColumn<Producto, String> colPlataforma;
    @FXML private TableColumn<Producto, String> colGenero;
    @FXML private TableColumn<Producto, String> colClasificacion;
    @FXML private TableColumn<Producto, Double> colPrecio;
    @FXML private TableColumn<Producto, Integer> colStock;
    @FXML private TableColumn<Producto, LocalDate> colFecha;

    private final ProductoDAO dao = new ProductoDAO();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(
                new PropertyValueFactory<>("idProducto"));
        colTitulo.setCellValueFactory(
                new PropertyValueFactory<>("titulo"));
        colPlataforma.setCellValueFactory(
                new PropertyValueFactory<>("plataforma"));
        colGenero.setCellValueFactory(
                new PropertyValueFactory<>("genero"));
        colClasificacion.setCellValueFactory(
                new PropertyValueFactory<>("clasificacion"));
        colPrecio.setCellValueFactory(
                new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(
                new PropertyValueFactory<>("stock"));
        colFecha.setCellValueFactory(
                new PropertyValueFactory<>("fechaLanzamiento"));

        tablaProductos.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, anterior, actual) -> {
                    if (actual != null) {
                        mostrarProducto(actual);
                    }
                });

        listar();
    }

    @FXML
    private void listar() {
        tablaProductos.setItems(
                FXCollections.observableArrayList(dao.listar()));
    }

    @FXML
    private void registrar() {
        try {
            Producto producto = leerFormulario();

            if (dao.registrar(producto)) {
                alerta("Producto registrado correctamente");
                listar();
                limpiar();
            } else {
                alerta("No se pudo registrar el producto");
            }
        } catch (IllegalArgumentException e) {
            alerta(e.getMessage());
        }
    }

    @FXML
    private void actualizar() {
        Producto seleccionado =
                tablaProductos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            alerta("Selecciona un producto para actualizar");
            return;
        }

        try {
            Producto producto = leerFormulario();
            producto.setIdProducto(seleccionado.getIdProducto());

            if (dao.actualizar(producto)) {
                alerta("Producto actualizado correctamente");
                listar();
                limpiar();
            } else {
                alerta("No se pudo actualizar el producto");
            }
        } catch (IllegalArgumentException e) {
            alerta(e.getMessage());
        }
    }

    @FXML
    private void eliminar() {
        Producto seleccionado =
                tablaProductos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            alerta("Selecciona un producto para eliminar");
            return;
        }

        Alert confirmacion = new Alert(
                Alert.AlertType.CONFIRMATION,
                "¿Deseas eliminar " + seleccionado.getTitulo() + "?",
                ButtonType.YES,
                ButtonType.NO
        );

        confirmacion.setHeaderText("Confirmar eliminación");

        if (confirmacion.showAndWait().orElse(ButtonType.NO)
                == ButtonType.YES) {

            if (dao.eliminar(seleccionado.getIdProducto())) {
                alerta("Producto eliminado correctamente");
                listar();
                limpiar();
            } else {
                alerta("No se pudo eliminar el producto");
            }
        }
    }

    private Producto leerFormulario() {
        String titulo = txtTitulo.getText().trim();
        String plataforma = txtPlataforma.getText().trim();
        String genero = txtGenero.getText().trim();
        String clasificacion = txtClasificacion.getText().trim();

        if (titulo.isEmpty() || plataforma.isEmpty()
                || genero.isEmpty() || clasificacion.isEmpty()
                || txtPrecio.getText().isBlank()
                || txtStock.getText().isBlank()
                || dpFechaLanzamiento.getValue() == null) {
            throw new IllegalArgumentException(
                    "Completa todos los campos");
        }

        try {
            double precio = Double.parseDouble(
                    txtPrecio.getText().trim());
            int stock = Integer.parseInt(
                    txtStock.getText().trim());

            if (!Double.isFinite(precio) || precio < 0 || stock < 0) {
                throw new IllegalArgumentException(
                        "El precio y stock deben ser válidos y no negativos");
            }

            return new Producto(
                    0, titulo, plataforma, genero,
                    clasificacion, precio, stock,
                    dpFechaLanzamiento.getValue()
            );

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Precio o stock tienen un formato incorrecto");
        }
    }

    private void mostrarProducto(Producto p) {
        txtTitulo.setText(p.getTitulo());
        txtPlataforma.setText(p.getPlataforma());
        txtGenero.setText(p.getGenero());
        txtClasificacion.setText(p.getClasificacion());
        txtPrecio.setText(String.valueOf(p.getPrecio()));
        txtStock.setText(String.valueOf(p.getStock()));
        dpFechaLanzamiento.setValue(p.getFechaLanzamiento());
    }

    @FXML
    private void limpiar() {
        tablaProductos.getSelectionModel().clearSelection();
        txtTitulo.clear();
        txtPlataforma.clear();
        txtGenero.clear();
        txtClasificacion.clear();
        txtPrecio.clear();
        txtStock.clear();
        dpFechaLanzamiento.setValue(null);
    }

    private void alerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sistema de Videojuegos");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

