import java.util.ArrayList;

public abstract class Parent extends Child {
    private ArrayList<Child> children;

    Parent() {
        children = new ArrayList<>();
    }

    Parent(ArrayList<Child> children) {
        this.children = children;
    }

    public void setChildren(ArrayList<Child> children) {
        this.children = children;
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public void add(Child child) {
        if (!getChildren().contains(child)) {
            children.add(child);
            child.setParent(this);
        }
    }




    public void alert() {
        children.forEach(e -> {
            e.onAlert();
            try {
                ((Parent) e).alert();
            } catch (Exception ignored) {}
        });
    }

}
