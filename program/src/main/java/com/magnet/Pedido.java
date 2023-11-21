class Pedido {
    private final StringProperty mesa;
    private final StringProperty plato;

    public Pedido(String mesa, String plato) {
        this.mesa = new SimpleStringProperty(mesa);
        this.plato = new SimpleStringProperty(plato);
    }

    public String getMesa() {
        return mesa.get();
    }

    public StringProperty mesaProperty() {
        return mesa;
    }

    public String getPlato() {
        return plato.get();
    }

    public StringProperty platoProperty() {
        return plato;
    }
}