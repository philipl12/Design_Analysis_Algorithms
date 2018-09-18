class LNode {
    String chStr;
    int prob;
    LNode next;

    public LNode() {
        chStr = "dummy";
        prob = 0;
        next = null;
    }

    public LNode(String chStr, int prob) {
        this.chStr = chStr;
        this.prob = prob;
        next = null;
    }

}
