class LNode {
    private String chStr;
    private int prob;
    private Object next;

    public LNode(String chStr, int prob) {
        this.chStr = chStr;
        this.prob = prob;
    }

    public void setNext(Object next) {
        this.next = next;
    }

    public Object getNext() {
        return next;
    }

    public int getProb() { return prob; }

    public String getChar() { return chStr; }
}
