class LList {
    private Object next;
    private int prob = 0;

    public LList() {
        next = null;
    }

    public void setNext(Object next) {
        this.next = next;
    }

    public Object getNext() { return next; }

    public int getCount() { return prob; }

    public void insertOneNode(LList listHead, LNode newNode) {
        listHead.setNext(newNode);
        newNode.setNext(null);
    }

/*
    public void insertOneNode(LList listHead, LNode newNode) {
        Object spot;
        spot = spot.findSpot(listHead, newNode);
        spot.setNext(newNode);
    }

    public Object findSpot(LList listHead, LNode newNode) {
        Object spot = listHead;
        if (spot.getNext() != null && spot.getNext().getCount() < newNode.getCount()) {

        }
    }
*/
}
