package main.java.fr.enseeiht.lbs.view.gui;

/**
 * Une interface que doit implémenter toute vue de la fenêtre.
 */
public interface GuiComponent {

    /**
     * Reset la frame avant de la mettre en arrière plan
     */
    void reset();

    /**
     * Preparer la fenêtre à être affichée en premier plan
     */
    void init();
}
