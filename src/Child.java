public abstract class Child {
    private Parent parent;
    private Object oneWayParent;
    public Parent getParent() {
        return this.parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
            if (!parent.getChildren().contains(this)) {
                parent.add(this);
            }
    }

    public void setParentOneWay(Object parent) {
        this.oneWayParent = parent;
    }

    public Object getParentOneWay() {
        return oneWayParent;
    }

    public Parent getRoot() {
        Parent current = this.parent;
        while (current.getParent() != null) {
            current = current.getParent();
        }
        return current;
    }

    public CardGameController getController() {
        Parent current = this.parent;
        while (current.getClass() != CardGameController.class) {
            if (current.getParent() == null) {
                return null;
            }
            current = current.getParent();
        }
        return (CardGameController) current;
    }

    public Object getNull() {
        return null;
    }

    public PlayerBoard getBoard() {
        Parent current = this.parent;
        while (current.getClass() != PlayerBoard.class) {
            if (current.getParent() == null) {
                return null;
            }
            current = current.getParent();
        }
        return (PlayerBoard) current;
    }

    public abstract void onAlert();



    public PCard getPCard() {
        Parent current = this.parent;
        while (current.getClass() != PCard.class) {
            if (current.getParent() == null) {
                return null;
            }
            current = current.getParent();
        }
        return (PCard) current;
    }
}
