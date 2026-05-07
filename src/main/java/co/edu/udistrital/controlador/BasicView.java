package co.edu.udistrital.controlador;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.util.LangUtils;

import java.io.Serializable;
import java.util.Locale;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("treeBasicView")
@ViewScoped
public class BasicView implements Serializable {

    private TreeNode root;

    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("Files", null);
        TreeNode node0 = new DefaultTreeNode("Documents", root);
        TreeNode node1 = new DefaultTreeNode("Events", root);
        TreeNode node2 = new DefaultTreeNode("Movies", root);

        TreeNode node00 = new DefaultTreeNode("Work", node0);
        TreeNode node01 = new DefaultTreeNode("Home", node0);

        node00.getChildren().add(new DefaultTreeNode("Expenses.doc"));
        node00.getChildren().add(new DefaultTreeNode("Resume.doc"));
        node01.getChildren().add(new DefaultTreeNode("Invoices.txt"));

        TreeNode node10 = new DefaultTreeNode("Meeting", node1);
        TreeNode node11 = new DefaultTreeNode("Product Launch", node1);
        TreeNode node12 = new DefaultTreeNode("Report Review", node1);

        TreeNode node20 = new DefaultTreeNode("Al Pacino", node2);
        TreeNode node21 = new DefaultTreeNode("Robert De Niro", node2);

        node20.getChildren().add(new DefaultTreeNode("Scarface"));
        node20.getChildren().add(new DefaultTreeNode("Serpico"));

        node21.getChildren().add(new DefaultTreeNode("Goodfellas"));
        node21.getChildren().add(new DefaultTreeNode("Untouchables"));

    }

    public TreeNode getRoot() {
        return root;
    }

    public boolean customFilter(TreeNode treeNode, Object filter, Locale locale) {
        if (treeNode.getData() == null || filter == null) {
            return true;
        }
        String filterText = filter.toString().trim().toLowerCase(locale);
        if (LangUtils.isBlank(filterText)) {
            return true;
        }
        return ((String) treeNode.getData()).toLowerCase(locale).contains(filterText);
    }

}