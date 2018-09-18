#include <iostream>
#include <fstream>
using namespace std;

class LNode {
public:

    string chStr;
    int prob;
    LNode* next;

    LNode() {
        chStr = "dummy";
        prob = 0;
        next = NULL;
    }

    LNode(string chStrCopy, int probCopy) {
        chStr = chStrCopy;
        prob = probCopy;
    }

};

class LList {
public:

    LNode listHead2;

    LList() {
        listHead2 = LNode();
    }

    void insertOneNode(LNode listHeadCopy, LNode newNode) {
        //LNode spot = findSpot(listHeadCopy, newNode);
        LNode spot = listHeadCopy;
        spot.next = &newNode;
        newNode.next = NULL;
        while (spot.next != NULL) {
            cout << spot.chStr << ' ' << spot.prob << " -> " << newNode.chStr << endl;
            spot = spot.next;
        }

    }

    LNode findSpot(LNode listHeadCopy, LNode newNode) {
        LNode spot = listHeadCopy;
        while (spot.next != NULL && spot.prob < newNode.prob) {
            spot = *spot.next;

        }
        return spot;
    }

    /*
    void printList(ofstream &fileOut) {
        LNode spot = listHead2;
        LNode temp = *spot.next;
        do {
            fileOut << spot.chStr << ", " << spot.prob << ", " << temp.chStr << " --> ";
            spot = *spot.next;
            fileOut << '\n';
            if(spot.next == NULL) {
                fileOut << spot.chStr << ", " << spot.prob << ", NULL"  << " --> NULL\n";
            }
        } while(spot.next != NULL);
    }
    */
};

int main(int argc, char** argv) {
    ifstream fileIn;
    ofstream fileOut;
    string chStr;
    int prob;
    LList listHead1 = LList();

    fileIn.open(argv[1]);
    fileOut.open(argv[2]);

    while (fileIn >> chStr >> prob) {
        LNode newNode = LNode(chStr, prob);
        //newNode->chStr = chStr;
        //newNode->prob = prob;
        //cout << "line 77 " << newNode->chStr << newNode->prob << endl;
        listHead1.insertOneNode(listHead1.listHead2, newNode);
        //listHead1.printList(fileOut);
    }

    fileIn.close();
    fileOut.close();
    return 0;
}
