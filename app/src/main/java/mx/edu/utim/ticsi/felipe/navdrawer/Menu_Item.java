package mx.edu.utim.ticsi.felipe.navdrawer;

/**
 * Created by felipe on 4/03/15.
 */
public class Menu_Item {
    private String titulo;
    private int icono;
    public Menu_Item(String title, int icon) {
        this.titulo = title;
        this.icono = icon;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public int getIcono() {
        return icono;
    }
    public void setIcono(int icono) {
        this.icono = icono;
    }
}
