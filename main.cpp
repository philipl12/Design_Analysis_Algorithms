#include <iostream>
#include <fstream>
using namespace std;

class LNode {
public:

    string chStr;
    int prob;
    LNode *next = NULL;

    LNode() {
        chStr = "dummy";
        prob = 0;
    }

    LNode(string chStrCopy, int probCopy) {
        chStr = chStrCopy;
        prob = probCopy;
    }
/*
    void insertOneNode(LNode listHead, LNode*newNode) {
        LNode spot = findSpot(listHead, newNode);
        spot.next = newNode;
        newNode.next = NULL;
    }

    LNode findSpot(LNode listHead, LNode newNode) {
        LNode spot = listHead;
        while (spot.next != null && spot.prob < newNode.prob) {
            spot = spot.next;
        }
        return spot;
    }
*/
};

class LList {
public:

    LNode listHead2;

    LList() {
        listHead2 = LNode();
    }

};

int main(int argc, char** argv) {
    ifstream fileIn;
    ofstream fileOut;
    string chStr;
    int prob;

    fileIn.open(argv[1]);
    fileOut.open(argv[2]);

    while (fileIn >> chStr >> prob) {
        cout << chStr << ' ' << prob << '\n';
    }

    fileIn.close();
    fileOut.close();
    return 0;
}
