import java.io.*;

class LList {
    LNode listHead2;

    public LList() {
        listHead2 = new LNode();
    }

    public void insertOneNode(LNode listHead, LNode newNode) {
        LNode spot = findSpot(listHead, newNode);
        newNode.next = spot.next;
        spot.next = newNode;

    }

    public LNode findSpot(LNode listHead, LNode newNode) {
        LNode spot = listHead;
        while (spot.next != null && spot.next.prob < newNode.prob) {
            spot = spot.next;
        }
        return spot;
    }

    public void printList(BufferedWriter outWrite) {
        try {
            outWrite.write("listHead --> ");
            LNode spot = listHead2;
            do {
                outWrite.write(spot.chStr + ", " + spot.prob + ", " + spot.next.chStr + " --> ");
                spot = spot.next;
                if (spot.next == null) {
                    outWrite.write(spot.chStr + ", " + spot.prob + ", NULL"  + " --> NULL");
                }
            } while(spot.next != null);

            outWrite.write('\n');

        } catch (Exception e) {
            System.out.println("No file");
        }
    }
}
